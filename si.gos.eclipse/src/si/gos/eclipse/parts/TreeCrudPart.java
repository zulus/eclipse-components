package si.gos.eclipse.parts;

import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;

import si.gos.eclipse.widgets.utils.IWidgetFactory;

public class TreeCrudPart extends CrudPart {

	public TreeCrudPart() {
		super();
	}
	
	public TreeCrudPart(CrudConfig config) {
		super(config);
	}
	
	/*
	 * @see StructuredViewerPart#createStructuredViewer(Composite, IWidgetFactory)
	 */
	protected StructuredViewer createStructuredViewer(Composite parent, IWidgetFactory factory) {
		return factory.createTreeViewer(parent, true);
	}

	public TreeViewer getTreeViewer() {
		return (TreeViewer) getViewer();
	}

	protected void updateLabel() {
		if (count != null && !count.isDisposed()) {
			count.setText("Total: " + Integer.toString(getTreeViewer().getTree().getItemCount()));
		}
	}
}
