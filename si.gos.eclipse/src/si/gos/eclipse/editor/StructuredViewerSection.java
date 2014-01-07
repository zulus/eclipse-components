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
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import si.gos.eclipse.forms.FormLayoutFactory;
import si.gos.eclipse.parts.StructuredViewerPart;
import si.gos.eclipse.widgets.helper.ToolkitFactory;


public abstract class StructuredViewerSection extends SharedSection {

	protected StructuredViewerPart viewerPart;

	private boolean doSelection;
	
	protected interface IStructuredViewerAdapter {
		public void fillContextMenu(IMenuManager manager);
		
		public void registerPopupMenu(MenuManager popupMenuManager);

	}

	/**
	 * Constructor for StructuredViewerSection.
	 * @param formPage
	 */
	public StructuredViewerSection(SharedFormPage formPage, Composite parent, int style, String[] buttonLabels) {
		this(formPage, parent, style, true, buttonLabels, new int[]{});
	}
	
	/**
	 * Constructor for StructuredViewerSection.
	 * @param formPage
	 */
	public StructuredViewerSection(SharedFormPage formPage, Composite parent, int style, String[] buttonLabels, int[] sensitiveButtons) {
		this(formPage, parent, style, true, buttonLabels, sensitiveButtons);
	}
	
	/**
	 * Constructor for StructuredViewerSection.
	 * @param formPage
	 */
	public StructuredViewerSection(SharedFormPage formPage, Composite parent, int style, boolean titleBar, String[] buttonLabels, int[] sensitiveButtons) {
		super(formPage, parent, style, titleBar);
		viewerPart = createViewerPart(buttonLabels, sensitiveButtons);
		viewerPart.setMinimumSize(50, 50);
		FormToolkit toolkit = formPage.getManagedForm().getToolkit();
		createClient(getSection(), toolkit);
		doSelection = true;
	}

	protected void createViewerPartControl(Composite parent, int style, int span, FormToolkit toolkit) {
		viewerPart.createControl(parent, style, span, new ToolkitFactory(toolkit));
	}

	/**
	 * If the context menu for this section should be registered, do it here
	 * with the appropriate id etc.  By default do nothing.
	 * @param popupMenuManager the menu manager to be registered
	 */
	protected void registerPopupMenu(MenuManager popupMenuManager) {
		// do nothing by default
	}

	protected Composite createClientContainer(Composite parent, int span, FormToolkit toolkit) {
		Composite container = toolkit.createComposite(parent);
		container.setLayout(FormLayoutFactory.createSectionClientGridLayout(false, span));
		return container;
	}

	protected abstract StructuredViewerPart createViewerPart(String[] buttonLabels, int[] sensitiveButtons);

	protected void fillContextMenu(IMenuManager manager) {
	}

	protected void buttonSelected(int index) {
	}


	protected IStructuredSelection getSelection() {
		return (IStructuredSelection)viewerPart.getViewer().getSelection();
	}

	protected void doPaste(Object targetObject, Object[] sourceObjects) {
		// NO-OP
		// Children will override to provide fuctionality
	}

	protected boolean canPaste(Object targetObject, Object[] sourceObjects) {
		return false;
	}

	public void setFocus() {
		viewerPart.getControl().setFocus();
	}

	public StructuredViewerPart getStructuredViewerPart() {
		return this.viewerPart;
	}

	/**
	 * <p>Given the index of TreeViewer item and the size of the array of its immediate
	 * siblings, gets the index of the desired new selection as follows:
	 * <ul><li>if this is the only item, return -1 (meaning select the parent)</li>
	 * <li>if this is the last item, return the index of the predecessor</li>
	 * <li>otherwise, return the index of the successor</li></p>
	 * 
	 * @param thisIndex
	 * 			the item's index
	 * @param length
	 * 			the array length
	 * @return
	 * 			new selection index or -1 for parent
	 */
	protected int getNewSelectionIndex(int thisIndex, int length) {
		if (thisIndex == length - 1)
			return thisIndex - 1;
		return thisIndex + 1;
	}

	protected int getArrayIndex(Object[] array, Object object) {
		for (int i = 0; i < array.length; i++) {
			if (array[i].equals(object))
				return i;
		}
		return -1;
	}


	protected void doSelect(boolean select) {
		doSelection = select;
	}

	protected boolean canSelect() {
		return doSelection;
	}

}
