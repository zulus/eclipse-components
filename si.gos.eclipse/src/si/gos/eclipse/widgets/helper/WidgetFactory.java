package si.gos.eclipse.widgets.helper;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.Hyperlink;

public class WidgetFactory extends AbstractWidgetFactory {

	public WidgetFactory() {
	}
	
	@Override
	protected int getBorderStyle() {
		return SWT.BORDER;
	}
	
	// ---- Button
	
	public Button createButton(Composite parent, String text, int style) {
		Button btn = createButton(parent, style);
		btn.setText(text);
		return btn;
	}
	
	// ---- Label
	
	public Label createLabel(Composite parent, String text, int style) {
		Label lbl = createLabel(parent, style);
		lbl.setText(text);
		return lbl;
	}
	
	
	// ---- Composite
		
	public Composite createComposite(Composite parent, int style) {
		return new Composite(parent, style);
	}
	
	
	// ---- ExpandableComposite
	
	public ExpandableComposite createExpandableComposite(Composite parent, int style) {
		return new ExpandableComposite(parent, style);
	}
	
	public ExpandableComposite createExpandableComposite(Composite parent, int style, int expansionStyle) {
		return new ExpandableComposite(parent, style, expansionStyle);
	}


	// ---- Text
	
	public Text createText(Composite parent, String text, int style) {
		Text txt = createText(parent, style);
		txt.setText(text);
		return txt;
	}
	
	
	// ---- ComboBox
	
	public Combo createCombo(Composite parent, int style) {
		return new Combo(parent, style);
	}

	public Composite createCoolCombo(Composite parent, int style) {
		return createCombo(parent, style);
	}

	
	// ---- Hyperlink
	
	public Hyperlink createHyperlink(Composite parent, String text, int style) {
		Hyperlink link = createHyperlink(parent, style);
		link.setText(text);
		return link;
	}
}
