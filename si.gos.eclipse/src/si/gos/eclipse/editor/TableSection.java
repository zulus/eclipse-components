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

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import si.gos.eclipse.parts.StructuredViewerPart;
import si.gos.eclipse.parts.TablePart;
import si.gos.eclipse.widgets.helper.ToolkitFactory;

public abstract class TableSection extends StructuredViewerSection {
	protected boolean handleDefaultButton = true;

	class PartAdapter extends TablePart implements IStructuredViewerAdapter {
		public PartAdapter(String[] buttonLabels) {
			super(buttonLabels);
		}
		
		public PartAdapter(String[] buttonLabels, int[] sensitiveButtons) {
			super(buttonLabels, sensitiveButtons);
		}

		public void selectionChanged(IStructuredSelection selection) {
			super.selectionChanged(selection);
			getManagedForm().fireSelectionChanged(TableSection.this, selection);
			TableSection.this.selectionChanged(selection);
		}

		public void handleDoubleClick(IStructuredSelection selection) {
			super.handleDoubleClick(selection);
			TableSection.this.handleDoubleClick(selection);
		}

		public void buttonSelected(Button button, int index) {
			super.buttonSelected(button, index);
			TableSection.this.buttonSelected(index);
			if (handleDefaultButton)
				button.getShell().setDefaultButton(null);
		}
		
		public void fillContextMenu(IMenuManager manager) {
			super.fillContextMenu(manager);
			TableSection.this.fillContextMenu(manager);
		}
		
		public void registerPopupMenu(MenuManager popupMenuManager) {
			super.registerPopupMenu(popupMenuManager);
			TableSection.this.registerPopupMenu(popupMenuManager);
		}
		
		public boolean createCount() {
			return TableSection.this.createCount();
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
	public TableSection(SharedFormPage formPage, Composite parent, int style, String[] buttonLabels) {
		this(formPage, parent, style, true, buttonLabels);
	}

	/**
	 * Constructor for TableSection.
	 * 
	 * @param formPage
	 */
	public TableSection(SharedFormPage formPage, Composite parent, int style, boolean titleBar, String[] buttonLabels) {
		super(formPage, parent, style, titleBar, buttonLabels, new int[]{});
	}

	protected StructuredViewerPart createViewerPart(String[] buttonLabels, int[] senstiveButtons) {
		return new PartAdapter(buttonLabels, senstiveButtons);
	}

	protected TablePart getTablePart() {
		return (TablePart) viewerPart;
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
