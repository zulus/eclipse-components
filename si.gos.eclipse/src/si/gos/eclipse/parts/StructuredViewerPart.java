/*******************************************************************************
 *  Copyright (c) 2000, 2008 IBM Corporation and others.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 * 
 *  Contributors:
 *     IBM Corporation - initial API and implementation
 *     
 *  Source:
 *     https://github.com/eclipse/eclipse.pde.ui/blob/master/ui/org.eclipse.pde.ui/src/org/eclipse/pde/internal/ui/parts/StructuredViewerPart.java
 *******************************************************************************/
package si.gos.eclipse.parts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.forms.IFormColors;

import si.gos.eclipse.actions.PartAction;
import si.gos.eclipse.widgets.utils.IWidgetFactory;
import si.gos.eclipse.widgets.utils.ToolkitFactory;

public abstract class StructuredViewerPart extends ActionPart {

	public final static boolean DEFAULT_CREATE_COUNT = false;
	public final static boolean DEFAULT_CREATE_CONTEXT_MENU = true;
	
	private StructuredViewer viewer;

	private Point minSize;
	
	private List<Integer> sensitiveActions = new ArrayList<Integer>();
	
	protected Label count;
	
	private MenuManager contextMenuManager;
	
	private List<ISelectionChangedListener> selectionChangedListener = new ArrayList<ISelectionChangedListener>();
	
	public StructuredViewerPart(String[] actionLabels) {
		super(actionLabels);
	}
	
	/**
	 * Creates are StructuredViewerPart with sensitive buttons. Sensitive actions will 
	 * be enabled once a selection is made in the viewer. 
	 * 
	 * @param actionLabels action labels
	 * @param sensitiveActions indices of sensitive actions
	 */
	public StructuredViewerPart(String[] actionLabels, int[] sensitiveActions) {
		super(actionLabels);
		initSensitiveActions(sensitiveActions);
	}
	
	public StructuredViewerPart(StructuredViewerConfig config) {
		super(config);
		initSensitiveActions(config.getSensitiveActions());
	}
	
	private void initSensitiveActions(int[] sensitiveActions) {
		for (int i = 0; i < sensitiveActions.length; i++) {
			this.sensitiveActions.add(sensitiveActions[i]);
		}
	}

	public StructuredViewer getViewer() {
		return viewer;
	}

	public Control getControl() {
		return viewer.getControl();
	}
	
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		if (!selectionChangedListener.contains(listener)) {
			selectionChangedListener.add(listener);
		}
	}
	
	public void removeSelectionChangedListener(ISelectionChangedListener listener) {
		selectionChangedListener.remove(listener);
	}

	protected void createMainControl(Composite parent, IWidgetFactory factory) {
		viewer = createStructuredViewer(parent, factory);
		
		// add listeners
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent e) {
				StructuredViewerPart.this.selectionChanged((IStructuredSelection) e.getSelection());
				
				for (int index : sensitiveActions) {
					getButton(index).setEnabled(!e.getSelection().isEmpty() && isEnabled());
				}
				
				for (ISelectionChangedListener listener : selectionChangedListener) {
					listener.selectionChanged(e);
				}
			}
		});
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent e) {
				StructuredViewerPart.this.handleDoubleClick((IStructuredSelection) e.getSelection());
			}
		});
		
		// layout
		Control control = viewer.getControl();
		control.setLayoutData(new GridData(GridData.FILL_BOTH));
		applyMinimumSize();
		
		// context menu
		if (createContextMenu()) {
			contextMenuManager = new MenuManager();
			IMenuListener listener = new IMenuListener() {
				public void menuAboutToShow(IMenuManager mng) {
					fillContextMenu(mng);
				}
			};
			contextMenuManager.addMenuListener(listener);
			contextMenuManager.setRemoveAllWhenShown(true);
			Menu menu = contextMenuManager.createContextMenu(control);
			control.setMenu(menu);
			registerContextMenu(contextMenuManager);
		}
	}

	protected abstract StructuredViewer createStructuredViewer(Composite parent, IWidgetFactory factory);

	@Override
	protected void createButtons(Composite parent, IWidgetFactory factory) {
		super.createButtons(parent, factory);
		
		if (createCount()) {
			Composite comp = factory.createComposite(buttonContainer);
			comp.setLayout(createButtonsLayout());
			comp.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_END | GridData.FILL_BOTH));
			count = factory.createLabel(comp);
			count.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			if (factory instanceof ToolkitFactory) {
				count.setForeground(((ToolkitFactory)factory).getToolkit().getColors().getColor(IFormColors.TITLE));
			}
			
			getControl().addPaintListener(new PaintListener() {
				public void paintControl(PaintEvent e) {
					updateLabel();
				}
			});
		}
	}
	
	@Override
	protected Button createButton(Composite parent, String label, int index, IWidgetFactory factory) {
		Button button = super.createButton(parent, label, index, factory);
		if (sensitiveActions.contains(index)) {
			button.setEnabled(false);
		}
		return button;
	}
	
	protected void selectionChanged(IStructuredSelection selection) {
		// enabled/disable sensitive actions
		for (int index : sensitiveActions) {
			getAction(index).setEnabled(!selection.isEmpty());
		}
	}
	
	protected abstract void updateLabel();
	
	protected boolean createCount() {
		return DEFAULT_CREATE_COUNT;
	}
	
	protected boolean createContextMenu() {
		return DEFAULT_CREATE_CONTEXT_MENU;
	}

	public void setMinimumSize(int width, int height) {
		minSize = new Point(width, height);
		
		if (viewer != null) {
			applyMinimumSize();
		}
	}

	private void applyMinimumSize() {
		if (minSize != null) {
			GridData gd = (GridData) viewer.getControl().getLayoutData();
			gd.widthHint = minSize.x;
			gd.heightHint = minSize.y;
		}
	}

	/**
	 * If the context menu for this part should be registered, do it here
	 * with the appropriate id etc. By default do nothing.
	 * 
	 * @param contextMenuManager the menu manager to be registered
	 */
	protected void registerContextMenu(IMenuManager contextMenuManager) {
		// do nothing by default
	}
	
	protected void fillContextMenu(IMenuManager manager) {
		for (PartAction action : getActions()) {
			if (action.isVisible()) {
				manager.add(action);
			}
		}
	}
	
	protected IMenuManager getContextMenu() {
		return contextMenuManager;
	}

	protected void updateEnabledState() {
		getControl().setEnabled(isEnabled());
		super.updateEnabledState();
	}

	protected void handleAction(PartAction action, int index) {
	}

	protected void handleDoubleClick(IStructuredSelection selection) {
	}
}
