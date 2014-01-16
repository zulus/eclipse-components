package si.gos.eclipse.editor;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import si.gos.eclipse.actions.PartAction;
import si.gos.eclipse.parts.StructuredViewerPart;
import si.gos.eclipse.parts.TableCRUDPart;
import si.gos.eclipse.parts.TablePart;
import si.gos.eclipse.widgets.helper.ToolkitFactory;


public abstract class TableCRUDSection extends CRUDSection {

	class PartAdapter extends TableCRUDPart implements ICRUDPartAdapter {
		public PartAdapter() {
			super();
		}

		public void selectionChanged(IStructuredSelection selection) {
			super.selectionChanged(selection);
			getManagedForm().fireSelectionChanged(TableCRUDSection.this, selection);
			TableCRUDSection.this.selectionChanged(selection);
		}

		public void handleDoubleClick(IStructuredSelection selection) {
			super.handleDoubleClick(selection);
			TableCRUDSection.this.handleDoubleClick(selection);
		}

		public void handleAction(PartAction action, int index) {
			super.handleAction(action, index);
			TableCRUDSection.this.handleAction(index);
		}
		
		protected void createButtons(Composite parent, FormToolkit toolkit) {
			super.createButtons(parent, new ToolkitFactory(toolkit));
		}
		
		public void fillContextMenu(IMenuManager manager) {
			super.fillContextMenu(manager);
			TableCRUDSection.this.fillContextMenu(manager);
		}
		
		public void registerContextMenu(IMenuManager contextMenuManager) {
			super.registerContextMenu(contextMenuManager);
			TableCRUDSection.this.registerContextMenu(contextMenuManager);
		}
		
		public boolean createCount() {
			return TableCRUDSection.this.createCount();
		}
		
		public boolean createContextMenu() {
			return TableCRUDSection.this.createContextMenu();
		}
		
		public void handleAdd(IStructuredSelection selection) {
			super.handleAdd(selection);
			TableCRUDSection.this.handleAdd(selection);
		}

		public void handleEdit(IStructuredSelection selection) {
			super.handleEdit(selection);
			TableCRUDSection.this.handleEdit(selection);
		}

		public void handleRemove(IStructuredSelection selection) {
			super.handleRemove(selection);
			TableCRUDSection.this.handleRemove(selection);
		}
	}
	
	public TableCRUDSection(SharedFormPage formPage, Composite parent, int style) {
		super(formPage, parent, style, true);
	}

	protected StructuredViewerPart createViewerPart(String[] actionLabels, int[] senstiveActions) {
		return new PartAdapter();
	}

	protected TablePart getTablePart() {
		return (TablePart) viewerPart;
	}
}
