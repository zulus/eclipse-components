package si.gos.eclipse.parts;

import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Composite;

import si.gos.eclipse.widgets.utils.IWidgetFactory;

public class TableCrudPart extends CrudPart {

	public TableCrudPart() {
		super();
	}
	
	public TableCrudPart(CrudConfig config) {
		super(config);
	}

	/*
	 * @see StructuredViewerPart#createStructuredViewer(Composite, IWidgetFactory)
	 */
	protected StructuredViewer createStructuredViewer(Composite parent, IWidgetFactory factory) {
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
