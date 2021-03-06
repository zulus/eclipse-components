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
import si.gos.eclipse.parts.StructuredViewerConfig;
import si.gos.eclipse.parts.StructuredViewerPart;
import si.gos.eclipse.parts.TreePart;
import si.gos.eclipse.widgets.utils.ToolkitFactory;


public abstract class TreeSection extends StructuredViewerSection {

	class PartAdapter extends TreePart implements IStructuredViewerAdapter {
		public PartAdapter(StructuredViewerConfig config) {
			super(config);
		}

		public void selectionChanged(IStructuredSelection selection) {
			getManagedForm().fireSelectionChanged(TreeSection.this, selection);
			TreeSection.this.selectionChanged(selection);
		}

		public void handleDoubleClick(IStructuredSelection selection) {
			TreeSection.this.handleDoubleClick(selection);
		}
		
		public void handleAction(PartAction action, int index) {
			super.handleAction(action, index);
			TreeSection.this.handleAction(index);
		}
		
		protected void createButtons(Composite parent, FormToolkit toolkit) {
			super.createButtons(parent, new ToolkitFactory(toolkit));
		}

		public void fillContextMenu(IMenuManager manager) {
			super.fillContextMenu(manager);
			TreeSection.this.fillContextMenu(manager);
		}
		
		public void registerContextMenu(IMenuManager contextMenuManager) {
			super.registerContextMenu(contextMenuManager);
			TreeSection.this.registerContextMenu(contextMenuManager);
		}
		
		public boolean createCount() {
			return TreeSection.this.createCount();
		}
		
		public boolean createContextMenu() {
			return TreeSection.this.createContextMenu();
		}

	}

	public TreeSection(SharedFormPage formPage, Composite parent, int style, StructuredViewerConfig config) {
		this(formPage, parent, style, true, config);
	}

	public TreeSection(SharedFormPage formPage, Composite parent, int style, boolean titleBar, StructuredViewerConfig config) {
		super(formPage, parent, style, titleBar, config);
	}

	protected StructuredViewerPart createViewerPart(StructuredViewerConfig config) {
		return new PartAdapter(config);
	}

	protected TreePart getTreePart() {
		return (TreePart) viewerPart;
	}
	
}
