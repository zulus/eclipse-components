package si.gos.eclipse.parts;

import org.apache.commons.lang.ArrayUtils;
import org.eclipse.jface.viewers.IStructuredSelection;

import si.gos.eclipse.actions.PartAction;

public abstract class CRUDPart extends StructuredViewerPart {

	private int addIndex = 0;
	private int editIndex = 1;
	private int removeIndex = 2;

	public CRUDPart() {
		super(new String[] {"Add", "Edit", "Remove"}, new int[] {1, 2});
	}
	
	public CRUDPart(String[] actionLabels, int[] sensitiveActions, int addIndex, int editIndex, int removeIndex) {
		super(actionLabels, ArrayUtils.addAll(sensitiveActions, new int[]{addIndex, editIndex, removeIndex}));
		
		this.addIndex = addIndex;
		this.editIndex = editIndex;
		this.removeIndex = removeIndex;
	}
	
	public CRUDPart(boolean add, boolean edit, boolean remove) {
		this();
		
		setActionVisible(addIndex, add);
		setActionVisible(editIndex, edit);
		setActionVisible(removeIndex, remove);
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
