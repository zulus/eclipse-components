package si.gos.eclipseutils.widgets.helper;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.Hyperlink;

public interface IWidgetFactory {
	public Button createButton(Composite parent);
	
	public Button createButton(Composite parent, int style);
	
	public Button createButton(Composite parent, String text);
	
	public Button createButton(Composite parent, String text, int style);
	
	// ---- Label
	
	public Label createLabel(Composite parent);
	
	public Label createLabel(Composite parent, int style);
	
	public Label createLabel(Composite parent, String text);
	
	public Label createLabel(Composite parent, String text, int style);
	
	
	// ---- Composite
	
	public Composite createComposite(Composite parent);
	
	public Composite createComposite(Composite parent, int style);
	
	
	// ---- ExpandableComposite
	
	public ExpandableComposite createExpandableComposite(Composite parent);
	
	public ExpandableComposite createExpandableComposite(Composite parent, int style);
	
	public ExpandableComposite createExpandableComposite(Composite parent, int style, int expansionStyle);


	// ---- Text
	
	public Text createText(Composite parent);
	
	public Text createText(Composite parent, int style);
	
	public Text createText(Composite parent, String text);
	
	public Text createText(Composite parent, String text, int style);
	
	
	// ---- ComboBox
	
	public Combo createCombo(Composite parent);
	
	public Combo createCombo(Composite parent, int style);
	
	public Composite createCoolCombo(Composite parent);
	
	public Composite createCoolCombo(Composite parent, int style);
	
	
	// ---- Hyperlink
	
	public Hyperlink createHyperlink(Composite parent);
	
	public Hyperlink createHyperlink(Composite parent, int style);
	
	public Hyperlink createHyperlink(Composite parent, String text);
	
	public Hyperlink createHyperlink(Composite parent, String text, int style);
	
	
	// ---- TableViewer
	
	public TableViewer createTableViewer(Composite parent);
	
	public TableViewer createTableViewer(Composite parent, int style);
	
	public TableViewer createTableViewer(Composite parent, int style, boolean border);
	
	public TableViewer createTableViewer(Composite parent, boolean border);
	
	
	// ---- TreeViewer
	
	public TreeViewer createTreeViewer(Composite parent);
	
	public TreeViewer createTreeViewer(Composite parent, int style);
	
	public TreeViewer createTreeViewer(Composite parent, int style, boolean border);
	
	public TreeViewer createTreeViewer(Composite parent, boolean border);
}
