package si.gos.eclipse.parts;

import java.util.ArrayList;
import java.util.List;

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
	
	private int addIndex = 0;
	private int editIndex = 1;
	private int removeIndex = 2;
	
	private boolean add = true;
	private boolean edit = true;
	private boolean remove = true;

	public CRUDPart() {
		super(new String[] {"Add", "Edit", "Remove"}, new int[] {1, 2});
	}
	
	public CRUDPart(boolean add, boolean edit, boolean remove) {
		super(new String[]{});
		
		this.add = add;
		this.edit = edit;
		this.remove = remove;
		
		int index = 0;
		List<String> buttons = new ArrayList<String>();
		List<Integer> sensitives = new ArrayList<Integer>();
		
		if (add) {
			addIndex = index;
			buttons.add("Add");
			index++;
		}
		
		if (edit) {
			editIndex = index;
			buttons.add("Edit");
			sensitives.add(index);
			index++;
		}
		
		if (remove) {
			removeIndex = index;
			buttons.add("Remove");
			sensitives.add(index);
			index++;
		}

		setButtonLabels(buttons.toArray(new String[]{}));
		setSensitiveButtons(sensitives);
	}
	
	public CRUDPart(String[] buttonLabels, int[] sensitiveButtons) {
		super(buttonLabels, sensitiveButtons);
	}

	protected void buttonSelected(Button button, int index) {
		IStructuredSelection selection = getSelection();
		
		if (add && index == addIndex) {
			handleAdd(selection);
		} else if (edit && index == editIndex) {
			handleEdit(selection);
		} else if (remove && index == removeIndex) {
			handleRemove(selection);
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
		if (add) {
			manager.add(addAction);
		}
		
		if (edit) {
			manager.add(editAction);
		}
		
		if (remove) {
			manager.add(removeAction);
		}
	}

	protected void handleAdd(IStructuredSelection selection) {
	}

	protected void handleEdit(IStructuredSelection selection) {
	}

	protected void handleRemove(IStructuredSelection selection) {
	}

}
