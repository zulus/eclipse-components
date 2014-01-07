package si.gos.eclipseutils.widgets.helper;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.Hyperlink;

public abstract class AbstractWidgetFactory implements IWidgetFactory {

	public int getDefaultTextStyle() {
		return SWT.BORDER | SWT.SINGLE;
	}
	
	public int getDefaultButtonStyle() {
		return SWT.PUSH;
	}
	
	public int getDefaultLabelStyle() {
		return SWT.NONE;
	}
	
	public int getDefaultCompositeStyle() {
		return SWT.NO_SCROLL;
	}
	
	public int getDefaultExpandableCompositeStyle() {
		return SWT.NONE;
	}
	
	public int getDefaultComboStyle() {
		return SWT.BORDER;
	}
	
	public int getDefaultHyperlinkStyle() {
		return SWT.NULL;
	}
	
	public int getDefaultTableViewerStyle() {
		return SWT.H_SCROLL | SWT.V_SCROLL;
	}
	
	public int getDefaultTreeViewerStyle() {
		return SWT.H_SCROLL | SWT.V_SCROLL;
	}
	
	// ---- Utils
	protected abstract int getBorderStyle();
	
	
	// ---- Button
	
	public Button createButton(Composite parent) {
		return createButton(parent, getDefaultButtonStyle());
	}
	
	public Button createButton(Composite parent, int style) {
		return createButton(parent, "", style);
	}
	
	public Button createButton(Composite parent, String text) {
		return createButton(parent, text, getDefaultButtonStyle());
	}
	
	// ---- Label
	
	public Label createLabel(Composite parent) {
		return createLabel(parent, getDefaultLabelStyle());
	}
	
	public Label createLabel(Composite parent, int style) {
		return createLabel(parent, "", style);
	}
	
	public Label createLabel(Composite parent, String text) {
		return createLabel(parent, text, getDefaultLabelStyle());
	}
	
	// ---- Composite
	
	public Composite createComposite(Composite parent) {
		return createComposite(parent, getDefaultCompositeStyle());
	}
	
	
	// ---- ExpandableComposite
	
	public ExpandableComposite createExpandableComposite(Composite parent) {
		return createExpandableComposite(parent, getDefaultExpandableCompositeStyle());
	}

	// ---- Text
	
	public Text createText(Composite parent) {
		return createText(parent, getDefaultTextStyle());
	}
	
	public Text createText(Composite parent, int style) {
		return createText(parent, "", style);
	}
	
	public Text createText(Composite parent, String text) {
		return createText(parent, text, getDefaultTextStyle());
	}
	
	// ---- Hyperlink
	
	public Hyperlink createHyperlink(Composite parent) {
		return createHyperlink(parent, getDefaultHyperlinkStyle());
	}
	
	public Hyperlink createHyperlink(Composite parent, int style) {
		return createHyperlink(parent, "", style);
	}
	
	public Hyperlink createHyperlink(Composite parent, String text) {
		return createHyperlink(parent, text, getDefaultHyperlinkStyle());
	}
	
	// ---- ComboBox
	
	public Combo createCombo(Composite parent) {
		return createCombo(parent, getDefaultComboStyle());
	}
	
	public Composite createCoolCombo(Composite parent) {
		return createCombo(parent, getDefaultComboStyle());
	}
	
	// ---- TableViewer
	
	public TableViewer createTableViewer(Composite parent) {
		return createTableViewer(parent, getDefaultTableViewerStyle());
	}
	
	public TableViewer createTableViewer(Composite parent, int style) {
		return new TableViewer(parent, style | getDefaultTableViewerStyle());
	}
	
	public TableViewer createTableViewer(Composite parent, int style, boolean border) {
		return createTableViewer(parent, style | (border ? getBorderStyle() : 0));
	}
	
	public TableViewer createTableViewer(Composite parent, boolean border) {
		return createTableViewer(parent, border ? getBorderStyle() : 0);
	}
	

	// ---- TreeViewer
	
	public TreeViewer createTreeViewer(Composite parent) {
		return createTreeViewer(parent, getDefaultTreeViewerStyle());
	}
	
	public TreeViewer createTreeViewer(Composite parent, int style) {
		return new TreeViewer(parent, style | getDefaultTreeViewerStyle());
	}
	
	public TreeViewer createTreeViewer(Composite parent, int style, boolean border) {
		return createTreeViewer(parent, style | (border ? getBorderStyle() : 0));
	}

	public TreeViewer createTreeViewer(Composite parent, boolean border) {
		return createTreeViewer(parent, border ? getBorderStyle() : 0);
	}	
}
