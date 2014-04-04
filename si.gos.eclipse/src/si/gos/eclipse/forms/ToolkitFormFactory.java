package si.gos.eclipse.forms;

import org.eclipse.ui.forms.widgets.FormToolkit;

import si.gos.eclipse.widgets.utils.ToolkitFactory;

public class ToolkitFormFactory extends FormFactory {

	public ToolkitFormFactory(FormToolkit toolkit) {
		super(new ToolkitFactory(toolkit));
	}
}
