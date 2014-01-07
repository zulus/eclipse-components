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
package si.gos.eclipseutils.parts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import si.gos.eclipseutils.widgets.helper.IWidgetFactory;

public abstract class StructuredViewerPart extends ActionPart {

	private StructuredViewer fViewer;

	private Point fMinSize;
	
	private List<Integer> fSensitiveButtons = new ArrayList<Integer>();

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

	public StructuredViewer getViewer() {
		return fViewer;
	}

	public Control getControl() {
		return fViewer.getControl();
	}

	protected void createMainControl(Composite parent, int style, int span, IWidgetFactory factory) {
		fViewer = createStructuredViewer(parent, style, factory);
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
		Control control = fViewer.getControl();
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = span;
		control.setLayoutData(gd);
		applyMinimumSize();
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
