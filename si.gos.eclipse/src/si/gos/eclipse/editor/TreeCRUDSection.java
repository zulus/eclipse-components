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
 *     https://raw.github.com/eclipse/eclipse.pde.ui/master/ui/org.eclipse.pde.ui/src/org/eclipse/pde/internal/ui/editor/TableSection.java
 *******************************************************************************/
package si.gos.eclipse.editor;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import si.gos.eclipse.parts.StructuredViewerPart;
import si.gos.eclipse.parts.TreeCRUDPart;
import si.gos.eclipse.parts.TreePart;
import si.gos.eclipse.widgets.helper.ToolkitFactory;


public abstract class TreeCRUDSection extends StructuredViewerSection {
	protected boolean handleDefaultButton = true;

	class PartAdapter extends TreeCRUDPart {
		public PartAdapter() {
			super();
		}

		public void entryModified(Object entry, String value) {
			TreeCRUDSection.this.entryModified(entry, value);
		}

		public void selectionChanged(IStructuredSelection selection) {
			getManagedForm().fireSelectionChanged(TreeCRUDSection.this, selection);
			TreeCRUDSection.this.selectionChanged(selection);
		}

		public void handleDoubleClick(IStructuredSelection selection) {
			TreeCRUDSection.this.handleDoubleClick(selection);
		}

		public void buttonSelected(Button button, int index) {
			TreeCRUDSection.this.buttonSelected(index);
			if (handleDefaultButton)
				button.getShell().setDefaultButton(null);
		}

		protected void createButtons(Composite parent, FormToolkit toolkit) {
			super.createButtons(parent, new ToolkitFactory(toolkit));
			enableButtons();
		}
	}
	
	/**
	 * Constructor for TableSection.
	 * 
	 * @param formPage
	 */
	public TreeCRUDSection(SharedFormPage formPage, Composite parent, int style, String[] buttonLabels) {
		this(formPage, parent, style, true, buttonLabels);
	}

	/**
	 * Constructor for TableSection.
	 * 
	 * @param formPage
	 */
	public TreeCRUDSection(SharedFormPage formPage, Composite parent, int style, boolean titleBar, String[] buttonLabels) {
		super(formPage, parent, style, titleBar, buttonLabels, new int[]{});
	}

	protected StructuredViewerPart createViewerPart(String[] buttonLabels, int[] senstiveButtons) {
		return new PartAdapter();
	}

	protected TreePart getTreePart() {
		return (TreePart) viewerPart;
	}

	protected void entryModified(Object entry, String value) {
	}

	protected void selectionChanged(IStructuredSelection selection) {
	}

	protected void handleDoubleClick(IStructuredSelection selection) {
	}

	protected void enableButtons() {
	}

	protected boolean createCount() {
		return false;
	}
}
