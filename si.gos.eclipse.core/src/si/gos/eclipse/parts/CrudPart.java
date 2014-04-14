package si.gos.eclipse.parts;

import org.eclipse.jface.viewers.IStructuredSelection;

import si.gos.eclipse.actions.PartAction;

public abstract class CrudPart extends StructuredViewerPart {

	private int addIndex = 0;
	private int editIndex = 1;
	private int removeIndex = 2;

	public CrudPart() {
		this(new CrudConfig());
	}
	
	public CrudPart(CrudConfig config) {
		super(config);
		
		// set action indices
		addIndex = config.getAddIndex();
		editIndex = config.getEditIndex();
		removeIndex = config.getRemoveIndex();
		
		// set visible actions
		setActionVisible(addIndex, config.getAddVisible());
		setActionVisible(editIndex, config.getEditVisible());
		setActionVisible(removeIndex, config.getRemoveVisible());
	}

	protected void handleAction(PartAction action, int index) {
		IStructuredSelection selection = getSelection();
		
		if (index == addIndex) {
			handleAdd(selection);
		} else if (index == editIndex) {
			handleEdit(selection);
		} else if (index == removeIndex) {
			handleRemove(selection);
		}
	}

	private IStructuredSelection getSelection() {
		return (IStructuredSelection) getViewer().getSelection();
	}

	protected void handleAdd(IStructuredSelection selection) {
	}

	protected void handleEdit(IStructuredSelection selection) {
	}

	protected void handleRemove(IStructuredSelection selection) {
	}

}
