/*******************************************************************************
 *  Copyright (c) 2000, 2011 IBM Corporation and others.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 * 
 *  Contributors:
 *     IBM Corporation - initial API and implementation
 *     Les Jones <lesojones@gmail.com> - bug 190717
 *     
 *  Source:
 *     https://github.com/eclipse/eclipse.pde.ui/blob/master/ui/org.eclipse.pde.ui/src/org/eclipse/pde/internal/ui/parts/SharedPartWithButtons.java
 *******************************************************************************/
package si.gos.eclipse.parts;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.PixelConverter;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import si.gos.eclipse.actions.PartAction;
import si.gos.eclipse.widgets.utils.IWidgetFactory;
import si.gos.eclipse.widgets.utils.WidgetHelper;

/**
 * The ActionPart has content on the left and buttons on the right side. The buttons
 * indicate the actions to manipulate the content on the left.
 * 
 */
public abstract class ActionPart extends SharedPart {
	
	public final static String ACTION_KEY = "action";
	
	protected String labelText = null;
	protected Label label;
	
	private PartAction[] actions;
	private boolean[] enabledStates;
	
	private Button[] buttons;
	protected Composite buttonContainer;
	private Shell shell;
	
	private class SelectionHandler implements SelectionListener {
		public void widgetSelected(SelectionEvent e) {
			buttonSelected(e);
		}

		public void widgetDefaultSelected(SelectionEvent e) {
			buttonSelected(e);
		}

		private void buttonSelected(SelectionEvent e) {
			Object action = e.widget.getData(ACTION_KEY);
			if (action instanceof PartAction) {
				((PartAction)action).run();
			}
		}
	}

	public ActionPart(ActionConfig config) {
		createActions(config.getActionLabels());
		labelText = config.getLabel();
	}
	
	public ActionPart(String[] actionLabels) {
		createActions(actionLabels);
	}

	private void createActions(String[] actionLabels) {
		actions = new PartAction[actionLabels.length];
		
		for (int i = 0; i < actionLabels.length; i++) {
			final int index = i;
			String label = actionLabels[i];
			actions[i] = createAction(label, index);
		}
	}

	protected PartAction createAction(String label, final int index) {
		return new PartAction(label) {
			public void run() {
				handleAction(this, index);
			}
		};
	}
	
	public PartAction getAction(int index) {
		if ((actions == null) || (index < 0) || (index >= actions.length)) {
			return null;
		}

		return actions[index];
	}
	
	public PartAction[] getActions() {
		return actions;
	}
	
	public void setActionEnabled(int index, boolean enabled) {
		if (actions != null && index >= 0 && actions.length > index) {
			actions[index].setEnabled(enabled);
		}
	}
	
	public void setActionVisible(int index, boolean visible) {
		if (actions != null && index >= 0 && actions.length > index) {
			actions[index].setVisible(visible);
		}
	}
	
	protected Shell getShell() {
		return shell;
	}

	protected abstract void handleAction(PartAction action, int index);

	public Composite createControl(Composite parent, IWidgetFactory factory) {
		shell = parent.getShell();
		Composite container = createContainer(parent, factory);
		createMainLabel(container, factory);
		createMainControl(container, factory);
		createButtons(container, factory);
		
		return container;
	}
	
	public Composite createContainer(Composite parent, IWidgetFactory factory) {
		Composite container = factory.createComposite(parent, SWT.NO_SCROLL);
		container.setLayout(new GridLayout(2, false));
		WidgetHelper.fillBoth(container);
		WidgetHelper.setMargin(container, 0, 0);
		WidgetHelper.setSpacing(container, 0, 0);

		return container;
	}
	
	protected abstract void createMainControl(Composite parent, IWidgetFactory factory);

	protected void createButtons(Composite parent, IWidgetFactory factory) {
		if (actions != null && actions.length > 0) {
			buttonContainer = factory.createComposite(parent);
			GridData gd = new GridData(GridData.FILL_VERTICAL);
			buttonContainer.setLayoutData(gd);
			buttonContainer.setLayout(createButtonsLayout());
			buttons = new Button[actions.length];
			SelectionHandler listener = new SelectionHandler();
			for (int i = 0; i < actions.length; i++) {
				String label = actions[i].getText();
				if (label != null) {
					Button button = createButton(buttonContainer, label, i, factory);
					button.addSelectionListener(listener);
					buttons[i] = button;
				} else {
					createEmptySpace(buttonContainer, 1, factory);
				}
			}
		}
	}

	protected GridLayout createButtonsLayout() {
		GridLayout layout = new GridLayout();
		layout.marginWidth = layout.marginHeight = 0;
		layout.marginTop = layout.marginBottom = 0;
		layout.marginLeft = layout.marginRight = 0;
		layout.verticalSpacing = layout.horizontalSpacing = 0;
		return layout;
	}

	protected Button createButton(Composite parent, String label, int index, IWidgetFactory factory) {
		
		boolean visible = getAction(index).isVisible();
		final Button button = factory.createButton(parent, label, SWT.PUSH);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING);
		gd.exclude = !visible;
		button.setLayoutData(gd);
		button.setData(new Integer(index));
		button.setVisible(visible);

		// add property change listener
		PartAction action = getAction(index);
		action.addPropertyChangeListener(new IPropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent event) {
				String prop = event.getProperty();
				
				if (prop.equals("enabled")) {
					button.setEnabled((Boolean)event.getNewValue());
				}
				
				else if (prop.equals("visible")) {
					GridData gd = (GridData)button.getLayoutData();
					gd.exclude = !(Boolean)event.getNewValue();
					button.setLayoutData(gd);
					button.setVisible((Boolean)event.getNewValue());
					button.getParent().layout(true, true);
				}

				else if (prop.equals("text")) {
					button.setText((String)event.getNewValue());
				}
			}
		});
		button.setData(ACTION_KEY, action);
		
		// Set the default button size
		button.setFont(JFaceResources.getDialogFont());
		PixelConverter converter = new PixelConverter(button);
		int widthHint = converter.convertHorizontalDLUsToPixels(IDialogConstants.BUTTON_WIDTH);
		gd.widthHint = Math.max(widthHint, button.computeSize(SWT.DEFAULT, SWT.DEFAULT, true).x);

		return button;
	}

	protected void updateEnabledState() {
		if (enabledStates == null) {
			enabledStates = new boolean[actions.length];
		}

		for (int i = 0; i < actions.length; i++) {
			// activate
			if (isEnabled()) {
				actions[i].setEnabled(enabledStates[i]);
			} 
			
			// deatcivate
			else {
				enabledStates[i] = actions[i] != null ? actions[i].isEnabled() : true;
			}
		}
	}
	
	@Override
	protected void updateGrayedState() {
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setGrayed(isGrayed());
		}
		
		super.updateGrayedState();
	}

	protected void createMainLabel(Composite parent, IWidgetFactory factory) {
		if (labelText != null) {
			label = factory.createLabel(parent, labelText);
			GridData gd = new GridData();
			gd.horizontalSpan = 2;
			label.setLayoutData(gd);
		}
	}

	public Button getButton(int index) {
		if ((buttons == null) || (index < 0) || (index >= buttons.length)) {
			return null;
		}

		return buttons[index];
	}

	
}