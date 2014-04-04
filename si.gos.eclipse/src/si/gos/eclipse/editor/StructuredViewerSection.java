/*******************************************************************************
 *  Copyright (c) 2000, 2011 IBM Corporation and others.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 * 
 *  Contributors:
 *     IBM Corporation - initial API and implementation
 *     
 *  Source:
 *     https://raw.github.com/eclipse/eclipse.pde.ui/master/ui/org.eclipse.pde.ui/src/org/eclipse/pde/internal/ui/editor/StructuredViewerSection.java
 *******************************************************************************/
package si.gos.eclipse.editor;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import si.gos.eclipse.actions.PartAction;
import si.gos.eclipse.forms.FormLayoutFactory;
import si.gos.eclipse.parts.StructuredViewerConfig;
import si.gos.eclipse.parts.StructuredViewerPart;
import si.gos.eclipse.widgets.utils.ToolkitFactory;


public abstract class StructuredViewerSection extends SharedSection {

	protected StructuredViewerPart viewerPart;
	
	protected interface IStructuredViewerAdapter {
		
		public void selectionChanged(IStructuredSelection selection);

		public void handleDoubleClick(IStructuredSelection selection);

		public void handleAction(PartAction action, int index);
		
		public void fillContextMenu(IMenuManager manager);
		
		public void registerContextMenu(IMenuManager contextMenuManager);

		public boolean createCount();
		
		public boolean createContextMenu();
	}

	public StructuredViewerSection(SharedFormPage formPage, Composite parent, int style, StructuredViewerConfig config) {
		this(formPage, parent, style, true, config);
	}
	
	public StructuredViewerSection(SharedFormPage formPage, Composite parent, int style, boolean titleBar, StructuredViewerConfig config) {
		super(formPage, parent, style, titleBar);
		
		viewerPart = createViewerPart(config);
		viewerPart.setMinimumSize(50, 50);
		
		FormToolkit toolkit = formPage.getManagedForm().getToolkit();
		createClient(getSection(), toolkit);
	}

	protected abstract StructuredViewerPart createViewerPart(StructuredViewerConfig config);
	
	public StructuredViewerPart getStructuredViewerPart() {
		return viewerPart;
	}
	
	protected void createViewerPartControl(Composite parent, FormToolkit toolkit) {
		viewerPart.createControl(parent, new ToolkitFactory(toolkit));
	}
	
	protected Composite createClientContainer(Composite parent, int span, FormToolkit toolkit) {
		Composite container = toolkit.createComposite(parent);
		container.setLayout(FormLayoutFactory.createSectionClientGridLayout(false, span));
		return container;
	}

	public PartAction[] getActions() {
		return viewerPart.getActions();
	}
	
	public PartAction getAction(int index) {
		return viewerPart.getAction(index);
	}
	
	/**
	 * If the context menu for this section should be registered, do it here
	 * with the appropriate id etc. By default do nothing.
	 * 
	 * @param contextMenuManager the menu manager to be registered
	 */
	protected void registerContextMenu(IMenuManager contextMenuManager) {
		// do nothing by default
	}

	protected void fillContextMenu(IMenuManager manager) {
	}

	protected void handleAction(int index) {
	}
	
	protected void selectionChanged(IStructuredSelection selection) {
	}

	protected void handleDoubleClick(IStructuredSelection selection) {
	}

	protected IStructuredSelection getSelection() {
		return (IStructuredSelection)viewerPart.getViewer().getSelection();
	}

	protected void doPaste(Object targetObject, Object[] sourceObjects) {
		// NO-OP
		// Children will override to provide functionality
	}

	protected boolean canPaste(Object targetObject, Object[] sourceObjects) {
		return false;
	}

	public void setFocus() {
		viewerPart.getControl().setFocus();
	}
	
	@Override
	protected void updateEnabledState() {
		viewerPart.setEnabled(enabled);
	}

	protected boolean createCount() {
		return StructuredViewerPart.DEFAULT_CREATE_COUNT;
	}
	
	protected boolean createContextMenu() {
		return StructuredViewerPart.DEFAULT_CREATE_CONTEXT_MENU;
	}

}
