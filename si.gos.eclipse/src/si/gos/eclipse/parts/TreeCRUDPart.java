package si.gos.eclipse.parts;

import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;

import si.gos.eclipse.widgets.helper.IWidgetFactory;

public class TreeCRUDPart extends CRUDPart {

	/*
	 * @see StructuredViewerPart#createStructuredViewer(Composite, IWidgetFactory)
	 */
	protected StructuredViewer createStructuredViewer(Composite parent, int style, IWidgetFactory factory) {
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
