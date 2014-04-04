package si.gos.eclipse.widgets.utils;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;

public class ToolkitFactory extends AbstractWidgetFactory {
	
	protected FormToolkit toolkit;
	
	public ToolkitFactory(FormToolkit toolkit) {
		this.toolkit = toolkit;
	}
	
	public FormToolkit getToolkit() {
		return toolkit;
	}
	
	protected int getBorderStyle() {
		return toolkit.getBorderStyle();
	}

	// ---- Button
	
	public Button createButton(Composite parent, String text, int style) {
		return toolkit.createButton(parent, text, style);
	}
	
	// ---- Label
	
	public Label createLabel(Composite parent, String text, int style) {
		return toolkit.createLabel(parent, text, style);
	}
	
	
	// ---- Composite
	
	public Composite createComposite(Composite parent, int style) {
		return toolkit.createComposite(parent, style);
	}
	
	
	// ---- ExpandableComposite

	public ExpandableComposite createExpandableComposite(Composite parent, int style) {
		return toolkit.createExpandableComposite(parent, style);
	}
	
	public ExpandableComposite createExpandableComposite(Composite parent, int style, int expansionStyle) {
		ExpandableComposite ec = new ExpandableComposite(parent, style | toolkit.getOrientation(),
				expansionStyle);
		ec.setMenu(parent.getMenu());
		toolkit.adapt(ec, true, true);
		return ec;
	}
	
	// ---- Text
	
	public Text createText(Composite parent, String text, int style) {
		return toolkit.createText(parent, text, style);
	}
	
	// ---- ComboBox

	public Combo createCombo(Composite parent, int style) {
		Combo combo = new Combo(parent, style);
		toolkit.adapt(combo, false, false);
		return combo;
	}
	
	public Composite createCoolCombo(Composite parent, int style) {
		Composite combo;
		if (toolkit.getBorderStyle() == SWT.BORDER)
			combo = new Combo(parent, style | SWT.BORDER);
		else
			combo = new CCombo(parent, style | SWT.FLAT);
		toolkit.adapt(combo, true, true);
		
		return combo;
	}
	
	public Hyperlink createHyperlink(Composite parent, String text, int style) {
		return toolkit.createHyperlink(parent, text, style);
	}

	
	
}
