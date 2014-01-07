package si.gos.eclipseutils.parts;

import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;

import si.gos.eclipseutils.widgets.helper.IWidgetFactory;

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

}
