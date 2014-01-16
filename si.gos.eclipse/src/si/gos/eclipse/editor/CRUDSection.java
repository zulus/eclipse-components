package si.gos.eclipse.editor;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;


public abstract class CRUDSection extends StructuredViewerSection {

	protected interface ICRUDPartAdapter extends IStructuredViewerAdapter {
		public void handleAdd(IStructuredSelection selection);

		public void handleEdit(IStructuredSelection selection);

		public void handleRemove(IStructuredSelection selection);
	}
	
	public CRUDSection(SharedFormPage formPage, Composite parent, int style, boolean titleBar) {
		super(formPage, parent, style, titleBar, new String[]{}, new int[]{});
	}

	protected void handleAdd(IStructuredSelection selection) {
	}

	protected void handleEdit(IStructuredSelection selection) {
	}

	protected void handleRemove(IStructuredSelection selection) {
	}
}
