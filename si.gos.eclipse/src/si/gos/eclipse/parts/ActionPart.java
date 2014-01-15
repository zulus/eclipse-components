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
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import si.gos.eclipse.widgets.helper.IWidgetFactory;

/**
 * The ActionPart has content on the left and buttons on the right side. The buttons
 * indicate the actions to manipulate the content on the left.
 * 
 */
public abstract class ActionPart extends SharedPart {
	private String[] fButtonLabels;
	private Button[] fButtons;
	private boolean[] fButtonEnabledStates;
	protected Composite fButtonContainer;
	private Shell shell;

	private class SelectionHandler implements SelectionListener {
		public void widgetSelected(SelectionEvent e) {
			buttonSelected(e);
		}

		public void widgetDefaultSelected(SelectionEvent e) {
			buttonSelected(e);
		}

		private void buttonSelected(SelectionEvent e) {
			Integer index = (Integer) e.widget.getData();
			ActionPart.this.buttonSelected((Button) e.widget, index.intValue());
		}
	}

	public ActionPart(String[] buttonLabels) {
		setButtonLabels(buttonLabels);
	}
	
	protected void setButtonLabels(String[] buttonLabels) {
		fButtonLabels = buttonLabels;
	}

	public void setButtonEnabled(int index, boolean enabled) {
		if (fButtons != null && index >= 0 && fButtons.length > index) {
			fButtons[index].setEnabled(enabled);
		}
	}

	/**
	 * Set the specified button's visibility.
	 * Fix for defect 190717.
	 * @param index The index of the button to be changed
	 * @param visible true if the button is to be shown, false if hidden
	 */
	public void setButtonVisible(int index, boolean visible) {
		if (fButtons != null && index >= 0 && fButtons.length > index) {
			fButtons[index].setVisible(visible);
		}
	}

	protected abstract void createMainControl(Composite parent, int style, int span, IWidgetFactory factory);

	protected abstract void buttonSelected(Button button, int index);

	/*
	 * @see SharedPart#createControl(Composite, FormWidgetFactory)
	 */
	public void createControl(Composite parent, int style, int span, IWidgetFactory factory) {
		shell = parent.getShell();
		createMainLabel(parent, span, factory);
		createMainControl(parent, style, span - 1, factory);
		createButtons(parent, factory);
	}
	
	protected Shell getShell() {
		return shell;
	}

	protected void createButtons(Composite parent, IWidgetFactory factory) {
		if (fButtonLabels != null && fButtonLabels.length > 0) {
			fButtonContainer = factory.createComposite(parent);
			GridData gd = new GridData(GridData.FILL_VERTICAL);
			fButtonContainer.setLayoutData(gd);
			fButtonContainer.setLayout(createButtonsLayout());
			fButtons = new Button[fButtonLabels.length];
			SelectionHandler listener = new SelectionHandler();
			for (int i = 0; i < fButtonLabels.length; i++) {
				String label = fButtonLabels[i];
				if (label != null) {
					Button button = createButton(fButtonContainer, label, i, factory);
					button.addSelectionListener(listener);
					fButtons[i] = button;
				} else {
					createEmptySpace(fButtonContainer, 1, factory);
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
		Button button = factory.createButton(parent, label, SWT.PUSH);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING);
		button.setLayoutData(gd);
		button.setData(new Integer(index));

		// Set the default button size
		button.setFont(JFaceResources.getDialogFont());
		PixelConverter converter = new PixelConverter(button);
		int widthHint = converter.convertHorizontalDLUsToPixels(IDialogConstants.BUTTON_WIDTH);
		gd.widthHint = Math.max(widthHint, button.computeSize(SWT.DEFAULT, SWT.DEFAULT, true).x);

		return button;
	}

	protected void updateEnabledState() {
		for (int i = 0; i < fButtons.length; i++) {
			// activate
			if (isEnabled()) {
				fButtons[i].setEnabled(fButtonEnabledStates[i]);
			} 
			
			// deatcivate
			else {
				fButtonEnabledStates[i] = fButtons[i].getEnabled(); 
			}
		}
	}

	protected void createMainLabel(Composite parent, int span, IWidgetFactory factory) {
	}

	public Button getButton(int index) {
		//
		if ((fButtons == null) || (index < 0) || (index >= fButtons.length)) {
			return null;
		}
		//
		return fButtons[index];
	}
}