package si.gos.eclipseutils.forms;

import org.eclipse.ui.forms.widgets.FormToolkit;

import si.gos.eclipseutils.widgets.helper.ToolkitFactory;

public class ToolkitFormFactory extends FormFactory {

	public ToolkitFormFactory(FormToolkit toolkit) {
		super(new ToolkitFactory(toolkit));
	}
}
