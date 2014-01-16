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
import si.gos.eclipse.parts.StructuredViewerPart;
import si.gos.eclipse.parts.TableCRUDPart;
import si.gos.eclipse.parts.TreePart;
import si.gos.eclipse.widgets.helper.ToolkitFactory;


public abstract class TreeCRUDSection extends CRUDSection {
	
	class PartAdapter extends TableCRUDPart implements ICRUDPartAdapter {
		public PartAdapter() {
			super();
		}

		public void selectionChanged(IStructuredSelection selection) {
			super.selectionChanged(selection);
			getManagedForm().fireSelectionChanged(TreeCRUDSection.this, selection);
			TreeCRUDSection.this.selectionChanged(selection);
		}

		public void handleDoubleClick(IStructuredSelection selection) {
			super.handleDoubleClick(selection);
			TreeCRUDSection.this.handleDoubleClick(selection);
		}

		public void handleAction(PartAction action, int index) {
			super.handleAction(action, index);
			TreeCRUDSection.this.handleAction(index);
		}
		
		protected void createButtons(Composite parent, FormToolkit toolkit) {
			super.createButtons(parent, new ToolkitFactory(toolkit));
		}
		
		public void fillContextMenu(IMenuManager manager) {
			super.fillContextMenu(manager);
			TreeCRUDSection.this.fillContextMenu(manager);
		}
		
		public void registerContextMenu(IMenuManager contextMenuManager) {
			super.registerContextMenu(contextMenuManager);
			TreeCRUDSection.this.registerContextMenu(contextMenuManager);
		}
		
		public boolean createCount() {
			return TreeCRUDSection.this.createCount();
		}
		
		public boolean createContextMenu() {
			return TreeCRUDSection.this.createContextMenu();
		}
		
		public void handleAdd(IStructuredSelection selection) {
			super.handleAdd(selection);
			TreeCRUDSection.this.handleAdd(selection);
		}

		public void handleEdit(IStructuredSelection selection) {
			super.handleEdit(selection);
			TreeCRUDSection.this.handleEdit(selection);
		}

		public void handleRemove(IStructuredSelection selection) {
			super.handleRemove(selection);
			TreeCRUDSection.this.handleRemove(selection);
		}
	}
	
	public TreeCRUDSection(SharedFormPage formPage, Composite parent, int style) {
		super(formPage, parent, style, true);
	}

	protected StructuredViewerPart createViewerPart(String[] actionLabels, int[] senstiveActions) {
		return new PartAdapter();
	}

	protected TreePart getTreePart() {
		return (TreePart) viewerPart;
	}
}
