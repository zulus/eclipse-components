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
import si.gos.eclipse.parts.StructuredViewerPart;
import si.gos.eclipse.parts.TableCrudPart;
import si.gos.eclipse.parts.TreePart;
import si.gos.eclipse.widgets.utils.ToolkitFactory;


public abstract class TreeCrudSection extends CrudSection {
	
	class PartAdapter extends TableCrudPart implements ICRUDPartAdapter {
		public PartAdapter(CrudConfig config) {
			super(config);
		}

		public void selectionChanged(IStructuredSelection selection) {
			super.selectionChanged(selection);
			getManagedForm().fireSelectionChanged(TreeCrudSection.this, selection);
			TreeCrudSection.this.selectionChanged(selection);
		}

		public void handleDoubleClick(IStructuredSelection selection) {
			super.handleDoubleClick(selection);
			TreeCrudSection.this.handleDoubleClick(selection);
		}

		public void handleAction(PartAction action, int index) {
			super.handleAction(action, index);
			TreeCrudSection.this.handleAction(index);
		}
		
		protected void createButtons(Composite parent, FormToolkit toolkit) {
			super.createButtons(parent, new ToolkitFactory(toolkit));
		}
		
		public void fillContextMenu(IMenuManager manager) {
			super.fillContextMenu(manager);
			TreeCrudSection.this.fillContextMenu(manager);
		}
		
		public void registerContextMenu(IMenuManager contextMenuManager) {
			super.registerContextMenu(contextMenuManager);
			TreeCrudSection.this.registerContextMenu(contextMenuManager);
		}
		
		public boolean createCount() {
			return TreeCrudSection.this.createCount();
		}
		
		public boolean createContextMenu() {
			return TreeCrudSection.this.createContextMenu();
		}
		
		public void handleAdd(IStructuredSelection selection) {
			super.handleAdd(selection);
			TreeCrudSection.this.handleAdd(selection);
		}

		public void handleEdit(IStructuredSelection selection) {
			super.handleEdit(selection);
			TreeCrudSection.this.handleEdit(selection);
		}

		public void handleRemove(IStructuredSelection selection) {
			super.handleRemove(selection);
			TreeCrudSection.this.handleRemove(selection);
		}
	}
	
	public TreeCrudSection(SharedFormPage formPage, Composite parent, int style) {
		this(formPage, parent, style, true);
	}
	
	public TreeCrudSection(SharedFormPage formPage, Composite parent, int style, boolean titleBar) {
		super(formPage, parent, style, titleBar);
	}
	
	public TreeCrudSection(SharedFormPage formPage, Composite parent, int style, CrudConfig config) {
		this(formPage, parent, style, true, config);
	}
	
	public TreeCrudSection(SharedFormPage formPage, Composite parent, int style, boolean titleBar, CrudConfig config) {
		super(formPage, parent, style, titleBar, config);
	}

	protected StructuredViewerPart createViewerPart(CrudConfig config) {
		return new PartAdapter(config);
	}

	protected TreePart getTreePart() {
		return (TreePart) viewerPart;
	}
}
