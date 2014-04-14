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
 *     https://github.com/eclipse/eclipse.pde.ui/blob/master/ui/org.eclipse.pde.ui/src/org/eclipse/pde/internal/ui/parts/TablePart.java
 *******************************************************************************/
package si.gos.eclipse.parts;

import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;

import si.gos.eclipse.widgets.utils.IWidgetFactory;

public class TreePart extends StructuredViewerPart {

	/**
	 * Constructor for TablePart.
	 * @param buttonLabels
	 */
	public TreePart(String[] buttonLabels) {
		super(buttonLabels);
	}
	
	/**
	 * Creates are TreePart with sensitive buttons. Sensitive buttons will 
	 * be enabled once a selection is made in the viewer. 
	 *  
	 * @param buttonLabels
	 * @param sensitiveButtons indices of sensitive buttons
	 */
	public TreePart(String[] buttonLabels, int[] sensitiveButtons) {
		super(buttonLabels, sensitiveButtons);
	}
	
	public TreePart(StructuredViewerConfig config) {
		super(config);
	}

	/*
	 * @see StructuredViewerPart#createStructuredViewer(Composite, IWidgetFactory)
	 */
	protected StructuredViewer createStructuredViewer(Composite parent, IWidgetFactory factory) {
		return factory.createTreeViewer(parent, true);
	}

	public TreeViewer getTreeViewer() {
		return (TreeViewer) getViewer();
	}

	protected void updateLabel() {
		if (count != null && !count.isDisposed()) {
			count.setText("Total: " + Integer.toString(getTreeViewer().getTree().getItemCount()));
		}
	}
}
