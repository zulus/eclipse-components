package si.gos.eclipse.editor;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import si.gos.eclipse.parts.StructuredViewerPart;
import si.gos.eclipse.parts.TableCRUDPart;
import si.gos.eclipse.parts.TablePart;
import si.gos.eclipse.widgets.helper.ToolkitFactory;


public abstract class TableCRUDSection extends StructuredViewerSection {

	protected boolean handleDefaultButton = true;

	class PartAdapter extends TableCRUDPart implements IStructuredViewerAdapter {
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

		public void buttonSelected(Button button, int index) {
			super.buttonSelected(button, index);
			TableCRUDSection.this.buttonSelected(index);
			if (handleDefaultButton)
				button.getShell().setDefaultButton(null);
		}
		
		public void fillContextMenu(IMenuManager manager) {
			super.fillContextMenu(manager);
			TableCRUDSection.this.fillContextMenu(manager);
		}
		
		public void registerPopupMenu(MenuManager popupMenuManager) {
			super.registerPopupMenu(popupMenuManager);
			TableCRUDSection.this.registerPopupMenu(popupMenuManager);
		}
		
		public boolean createCount() {
			return TableCRUDSection.this.createCount();
		}

		protected void createButtons(Composite parent, FormToolkit toolkit) {
			super.createButtons(parent, new ToolkitFactory(toolkit));
			enableButtons();
		}
	}
	
	public TableCRUDSection(SharedFormPage formPage, Composite parent, int style) {
		super(formPage, parent, style, new String[]{});
	}

	protected StructuredViewerPart createViewerPart(String[] buttonLabels, int[] senstiveButtons) {
		return new PartAdapter();
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
