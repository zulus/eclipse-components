package si.gos.eclipse.parts;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import si.gos.eclipse.widgets.helper.IWidgetFactory;

public abstract class CRUDPart extends StructuredViewerPart {

	private Action addAction;
	private Action editAction;
	private Action removeAction;

	public CRUDPart() {
		super(new String[] {"Add", "Edit", "Remove"}, new int[] {1, 2});
	}

	protected void buttonSelected(Button button, int index) {
		IStructuredSelection selection = getSelection();
		switch (index) {
		case 0:
			handleAdd(selection);
			break;

		case 1:
			handleEdit(selection);
			break;

		case 2:
			handleRemove(selection);
			break;
		}
	}

	@Override
	protected void createButtons(Composite parent, IWidgetFactory factory) {
		super.createButtons(parent, factory);

		// create menu actions
		addAction = new Action("Add") {
			public void run() {
				handleAdd(getSelection());
			}
		};

		editAction = new Action("Edit") {
			public void run() {
				handleAdd(getSelection());
			}
		};
		editAction.setEnabled(false);

		removeAction = new Action("Remove") {
			public void run() {
				handleAdd(getSelection());
			}
		};
		removeAction.setEnabled(false);
	}

	private IStructuredSelection getSelection() {
		return (IStructuredSelection) getViewer().getSelection();
	}

	@Override
	protected void selectionChanged(IStructuredSelection selection) {
		editAction.setEnabled(!selection.isEmpty());
		removeAction.setEnabled(!selection.isEmpty());
	}

	@Override
	protected void fillContextMenu(IMenuManager manager) {
		manager.add(addAction);
		manager.add(editAction);
		manager.add(removeAction);
	}

	protected void handleAdd(IStructuredSelection selection) {
	}

	protected void handleEdit(IStructuredSelection selection) {
	}

	protected void handleRemove(IStructuredSelection selection) {
	}

}
