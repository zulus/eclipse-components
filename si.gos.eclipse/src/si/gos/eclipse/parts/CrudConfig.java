package si.gos.eclipse.parts;

public class CrudConfig extends StructuredViewerConfig {

	public String[] getActionLabels() {
		return new String[]{getAddLabel(), getEditLabel(), getRemoveLabel()};
	}

	public int[] getSensitiveActions() {
		return new int[]{1, 2};
	}

	public String getAddLabel() {
		return "Add ...";
	}
	
	public String getEditLabel() {
		return "Edit ...";
	}
	
	public String getRemoveLabel() {
		return "Remove";
	}
	
	public int getAddIndex() {
		return 0;
	}
	
	public int getEditIndex() {
		return 1;
	}
	
	public int getRemoveIndex() {
		return 2;
	}

	public boolean getAddVisible() {
		return true;
	}

	public boolean getEditVisible() {
		return true;
	}

	public boolean getRemoveVisible() {
		return true;
	}

}
