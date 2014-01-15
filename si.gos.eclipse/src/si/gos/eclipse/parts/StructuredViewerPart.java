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

import si.gos.eclipse.widgets.helper.IWidgetFactory;
import si.gos.eclipse.widgets.helper.ToolkitFactory;

public abstract class StructuredViewerPart extends ActionPart {

	private StructuredViewer fViewer;

	private Point fMinSize;
	
	private List<Integer> fSensitiveButtons = new ArrayList<Integer>();
	
	protected Label count;
	
	private MenuManager popupMenuManager;

	public StructuredViewerPart(String[] buttonLabels) {
		super(buttonLabels);
	}
	
	/**
	 * Creates are StructuredViewerPart with sensitive buttons. Sensitive buttons will 
	 * be enabled once a selection is made in the viewer. 
	 *  
	 * @param buttonLabels
	 * @param sensitiveButtons indices of sensitive buttons
	 */
	public StructuredViewerPart(String[] buttonLabels, int[] sensitiveButtons) {
		super(buttonLabels);

		for (int i = 0; i < sensitiveButtons.length; i++) {
			fSensitiveButtons.add(sensitiveButtons[i]);
		}
	}
	
	protected void setSensitiveButtons(List<Integer> sensitiveButtons) {
		fSensitiveButtons = sensitiveButtons;
	}

	public StructuredViewer getViewer() {
		return fViewer;
	}

	public Control getControl() {
		return fViewer.getControl();
	}

	protected void createMainControl(Composite parent, int style, int span, IWidgetFactory factory) {
		fViewer = createStructuredViewer(parent, style, factory);
		
		// add listeners
		fViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent e) {
				StructuredViewerPart.this.selectionChanged((IStructuredSelection) e.getSelection());
				
				for (int index : fSensitiveButtons) {
					getButton(index).setEnabled(!e.getSelection().isEmpty() && isEnabled());
				}
			}
		});
		fViewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent e) {
				StructuredViewerPart.this.handleDoubleClick((IStructuredSelection) e.getSelection());
			}
		});
		
		// layout
		Control control = fViewer.getControl();
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = span;
		control.setLayoutData(gd);
		applyMinimumSize();
		
		// context menu
		popupMenuManager = new MenuManager();
		IMenuListener listener = new IMenuListener() {
			public void menuAboutToShow(IMenuManager mng) {
				fillContextMenu(mng);
			}
		};
		popupMenuManager.addMenuListener(listener);
		popupMenuManager.setRemoveAllWhenShown(true);
		Menu menu = popupMenuManager.createContextMenu(control);
		control.setMenu(menu);
		registerPopupMenu(popupMenuManager);
	}
	
	@Override
	protected void createButtons(Composite parent, IWidgetFactory factory) {
		super.createButtons(parent, factory);
		
		if (createCount()) {
			Composite comp = factory.createComposite(fButtonContainer);
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
	protected Button createButton(Composite parent, String label, int index,
			IWidgetFactory factory) {
		Button button = super.createButton(parent, label, index, factory);
		if (fSensitiveButtons.contains(index)) {
			button.setEnabled(false);
		}
		return button;
	}
	
	
	protected abstract void updateLabel();
	
	
	protected boolean createCount() {
		return false;
	}

	public void setMinimumSize(int width, int height) {
		fMinSize = new Point(width, height);
		if (fViewer != null)
			applyMinimumSize();
	}

	private void applyMinimumSize() {
		if (fMinSize != null) {
			GridData gd = (GridData) fViewer.getControl().getLayoutData();
			gd.widthHint = fMinSize.x;
			gd.heightHint = fMinSize.y;
		}
	}
	
	/**
	 * If the context menu for this section should be registered, do it here
	 * with the appropriate id etc.  By default do nothing.
	 * @param popupMenuManager the menu manager to be registered
	 */
	protected void registerPopupMenu(MenuManager popupMenuManager) {
		// do nothing by default
	}
	
	protected void fillContextMenu(IMenuManager manager) {
	}
	
	protected MenuManager getMenuManager() {
		return popupMenuManager;
	}

	protected void updateEnabledState() {
		getControl().setEnabled(isEnabled());
		super.updateEnabledState();
	}
	
	/*
	 * @see SharedPartWithButtons#buttonSelected(int)
	 */
	protected void buttonSelected(Button button, int index) {
	}

	protected void selectionChanged(IStructuredSelection selection) {
	}

	protected void handleDoubleClick(IStructuredSelection selection) {
	}

	protected abstract StructuredViewer createStructuredViewer(Composite parent, int style, IWidgetFactory factory);
}
