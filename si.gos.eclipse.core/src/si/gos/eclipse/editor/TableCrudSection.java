package si.gos.eclipse.editor;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import si.gos.eclipse.actions.PartAction;
import si.gos.eclipse.parts.CrudConfig;
import si.gos.eclipse.parts.StructuredViewerPart;
import si.gos.eclipse.parts.TableCrudPart;
import si.gos.eclipse.parts.TablePart;
import si.gos.eclipse.widgets.utils.ToolkitFactory;


public abstract class TableCrudSection extends CrudSection {

	class PartAdapter extends TableCrudPart implements ICRUDPartAdapter {
		public PartAdapter(CrudConfig config) {
			super(config);
		}

		public void selectionChanged(IStructuredSelection selection) {
			super.selectionChanged(selection);
			getManagedForm().fireSelectionChanged(TableCrudSection.this, selection);
			TableCrudSection.this.selectionChanged(selection);
		}

		public void handleDoubleClick(IStructuredSelection selection) {
			super.handleDoubleClick(selection);
			TableCrudSection.this.handleDoubleClick(selection);
		}

		public void handleAction(PartAction action, int index) {
			super.handleAction(action, index);
			TableCrudSection.this.handleAction(index);
		}
		
		protected void createButtons(Composite parent, FormToolkit toolkit) {
			super.createButtons(parent, new ToolkitFactory(toolkit));
		}
		
		public void fillContextMenu(IMenuManager manager) {
			super.fillContextMenu(manager);
			TableCrudSection.this.fillContextMenu(manager);
		}
		
		public void registerContextMenu(IMenuManager contextMenuManager) {
			super.registerContextMenu(contextMenuManager);
			TableCrudSection.this.registerContextMenu(contextMenuManager);
		}
		
		public boolean createCount() {
			return TableCrudSection.this.createCount();
		}
		
		public boolean createContextMenu() {
			return TableCrudSection.this.createContextMenu();
		}
		
		public void handleAdd(IStructuredSelection selection) {
			super.handleAdd(selection);
			TableCrudSection.this.handleAdd(selection);
		}

		public void handleEdit(IStructuredSelection selection) {
			super.handleEdit(selection);
			TableCrudSection.this.handleEdit(selection);
		}

		public void handleRemove(IStructuredSelection selection) {
			super.handleRemove(selection);
			TableCrudSection.this.handleRemove(selection);
		}
	}
	
	public TableCrudSection(SharedFormPage formPage, Composite parent, int style) {
		this(formPage, parent, style, true);
	}
	
	public TableCrudSection(SharedFormPage formPage, Composite parent, int style, boolean titleBar) {
		super(formPage, parent, style, titleBar);
	}
	
	public TableCrudSection(SharedFormPage formPage, Composite parent, int style, CrudConfig config) {
		this(formPage, parent, style, true, config);
	}
	
	public TableCrudSection(SharedFormPage formPage, Composite parent, int style, boolean titleBar, CrudConfig config) {
		super(formPage, parent, style, titleBar, config);
	}

	protected StructuredViewerPart createViewerPart(CrudConfig config) {
		return new PartAdapter(config);
	}

	protected TablePart getTablePart() {
		return (TablePart) viewerPart;
	}
}
