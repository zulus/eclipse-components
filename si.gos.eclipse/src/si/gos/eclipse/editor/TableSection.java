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
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import si.gos.eclipse.actions.PartAction;
import si.gos.eclipse.parts.CrudConfig;
import si.gos.eclipse.parts.StructuredViewerConfig;
import si.gos.eclipse.parts.StructuredViewerPart;
import si.gos.eclipse.parts.TablePart;
import si.gos.eclipse.widgets.utils.ToolkitFactory;

public abstract class TableSection extends StructuredViewerSection {

	class PartAdapter extends TablePart implements IStructuredViewerAdapter {
		public PartAdapter(StructuredViewerConfig config) {
			super(config);
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

		public void handleAction(PartAction action, int index) {
			super.handleAction(action, index);
			TableSection.this.handleAction(index);
		}
		
		protected void createButtons(Composite parent, FormToolkit toolkit) {
			super.createButtons(parent, new ToolkitFactory(toolkit));
		}
		
		public void fillContextMenu(IMenuManager manager) {
			super.fillContextMenu(manager);
			TableSection.this.fillContextMenu(manager);
		}
		
		public void registerContextMenu(IMenuManager contextMenuManager) {
			super.registerContextMenu(contextMenuManager);
			TableSection.this.registerContextMenu(contextMenuManager);
		}
		
		public boolean createCount() {
			return TableSection.this.createCount();
		}
		
		public boolean createContextMenu() {
			return TableSection.this.createContextMenu();
		}

	}
	
	public TableSection(SharedFormPage formPage, Composite parent, int style, CrudConfig config) {
		this(formPage, parent, style, true, config);
	}

	public TableSection(SharedFormPage formPage, Composite parent, int style, boolean titleBar, CrudConfig config) {
		super(formPage, parent, style, titleBar, config);
	}

	protected StructuredViewerPart createViewerPart(StructuredViewerConfig config) {
		return new PartAdapter(config);
	}

	protected TablePart getTablePart() {
		return (TablePart) viewerPart;
	}
	
}
