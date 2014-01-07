package si.gos.eclipseutils.parts;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Button;


public abstract class CRUDPart extends StructuredViewerPart {

	public CRUDPart() {
		super(new String[]{"Add", "Edit", "Remove"}, new int[]{1, 2});
	}
	
	protected void buttonSelected(Button button, int index) {
		IStructuredSelection selection = getSelection();
		switch (index) {
		case 0:
			handleAddButton(selection);
			break;
			
		case 1:
			handleEditButton(selection);
			break;
			
		case 2:
			handleRemoveButton(selection);
			break;
		}
	}
	
	private IStructuredSelection getSelection() {
		return (IStructuredSelection)getViewer().getSelection();
	}
	
	protected void handleAddButton(IStructuredSelection selection) {
		
	}
	
	protected void handleEditButton(IStructuredSelection selection) {
		
	}
	
	protected void handleRemoveButton(IStructuredSelection selection) {
		
	}

}
