package si.gos.eclipseutils.editor;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;

public class SharedFormPage extends FormPage {

	protected boolean enabled = true;
	
	public SharedFormPage(FormEditor editor, String id, String title) {
		super(editor, id, title);
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void contributeToToolbar(IToolBarManager manager, IManagedForm headerForm) {
		
	}
}
