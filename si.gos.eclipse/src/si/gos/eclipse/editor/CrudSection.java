package si.gos.eclipse.editor;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;

import si.gos.eclipse.parts.CrudConfig;


public abstract class CrudSection extends StructuredViewerSection {

	protected interface ICRUDPartAdapter extends IStructuredViewerAdapter {
		public void handleAdd(IStructuredSelection selection);

		public void handleEdit(IStructuredSelection selection);

		public void handleRemove(IStructuredSelection selection);
	}
	
	public CrudSection(SharedFormPage formPage, Composite parent, int style) {
		this(formPage, parent, style, true);
	}
	
	public CrudSection(SharedFormPage formPage, Composite parent, int style, boolean titleBar) {
		this(formPage, parent, style, titleBar, new CrudConfig());
	}
	
	public CrudSection(SharedFormPage formPage, Composite parent, int style, boolean titleBar, CrudConfig config) {
		super(formPage, parent, style, titleBar, config);
	}

	protected void handleAdd(IStructuredSelection selection) {
	}

	protected void handleEdit(IStructuredSelection selection) {
	}

	protected void handleRemove(IStructuredSelection selection) {
	}
}
