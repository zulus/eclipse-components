package si.gos.eclipse.actions;

import org.eclipse.jface.action.Action;

public class PartAction extends Action {

	private boolean visible = true;
	
	public PartAction(String text) {
		super(text);
	}

	/**
	 * @return the visible
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * @param visible the visible to set
	 */
	public void setVisible(boolean visible) {
		boolean oldVisible = this.visible;
		this.visible = visible;
		firePropertyChange("visible", oldVisible, this.visible);
	}
	
}