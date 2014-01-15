package si.gos.eclipse.parts;

import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Composite;

import si.gos.eclipse.widgets.helper.IWidgetFactory;

public class TableCRUDPart extends CRUDPart {

	public TableCRUDPart() {
		super();
	}
	
	public TableCRUDPart(boolean add, boolean edit, boolean remove) {
		super(add, edit, remove);
	}
	
	/*
	 * @see StructuredViewerPart#createStructuredViewer(Composite, IWidgetFactory)
	 */
	protected StructuredViewer createStructuredViewer(Composite parent, int style, IWidgetFactory factory) {
		return factory.createTableViewer(parent, true);
	}

	public TableViewer getTableViewer() {
		return (TableViewer) getViewer();
	}
	
	protected void updateLabel() {
		if (count != null && !count.isDisposed()) {
			count.setText("Total: " + Integer.toString(getTableViewer().getTable().getItemCount()));
		}
	}

}
