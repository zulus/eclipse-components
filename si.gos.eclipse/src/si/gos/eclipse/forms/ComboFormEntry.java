package si.gos.eclipse.forms;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.ui.forms.IFormColors;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

import si.gos.eclipse.parts.ComboPart;
import si.gos.eclipse.widgets.helper.IWidgetFactory;
import si.gos.eclipse.widgets.helper.ToolkitFactory;

public class ComboFormEntry {

	private Label label;
	private ComboPart combo;
	private boolean ignoreNotify = false;
	
	private ArrayList<IComboFormEntryListener> listeners = new ArrayList<IComboFormEntryListener>();
	
	/**
	 * 
	 * @param parent
	 * @param factory
	 * @param labelText
	 */
	public ComboFormEntry(Composite parent, IWidgetFactory factory, String labelText) {
		this(parent, factory, labelText, SWT.FLAT);
	}
	
	/**
	 * 
	 * @param parent
	 * @param factory
	 * @param labelText
	 * @param style
	 */
	public ComboFormEntry(Composite parent, IWidgetFactory factory, String labelText, int style) {
		createControl(parent, factory, labelText, style);
	}
	

	public Label getLabel() {
		return label;
	}
	
	public ComboPart getComboPart() {
		return combo;
	}

	public String getValue() {
		return combo.getSelection();
	}
	
	public void setValue(String value) {
		combo.setText(value);
	}
	
	public void setValue(String value, boolean ignoreNotify) {
		this.ignoreNotify = ignoreNotify;
		setValue(value);
		this.ignoreNotify = false;
	}
	
	public void setEditable(boolean editable) {
		combo.setEnabled(editable);
	}
	
	/**
	 * Attaches the listener for the entry.
	 * 
	 * @param listener
	 */
	public void addComboFormEntryListener(IComboFormEntryListener listener) {
		listeners.add(listener);
	}
	

	/**
	 * Detaches the listener for the entry.
	 * 
	 * @param listener
	 */
	public void removeComboFormEntryListener(IComboFormEntryListener listener) {
		listeners.remove(listener);
	}

	private void createControl(Composite parent, IWidgetFactory factory, String labelText, int style) {
		if (labelText != null) {
			label = factory.createLabel(parent, labelText);
			if (factory instanceof ToolkitFactory) {
				label.setForeground(((ToolkitFactory)factory).getToolkit().getColors().getColor(IFormColors.TITLE));
			}
		}
		
		combo = new ComboPart();
		combo.createControl(parent, factory, style);
		
		addListener();
		fillIntoGrid(parent);
	}
	
	private void addListener() {
		combo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				if (ignoreNotify) 
					return;
				
				for (IComboFormEntryListener listener : listeners) {
					listener.textValueChanged(ComboFormEntry.this);
				}
			}
		});
		
		combo.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				if (ignoreNotify) 
					return;
				
				for (IComboFormEntryListener listener : listeners) {
					listener.selectionChanged(ComboFormEntry.this);
				}
			}
			
			public void widgetDefaultSelected(SelectionEvent e) {}
		});
	}
	
	private void fillIntoGrid(Composite parent) {
		Layout layout = parent.getLayout();
		if (layout instanceof GridLayout) {
			int span = ((GridLayout) layout).numColumns;
			
			GridData gd;
			if (label != null) {
				gd = new GridData(GridData.VERTICAL_ALIGN_CENTER);
				label.setLayoutData(gd);
				span--;
			}
			gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
			gd.horizontalSpan = span;
			if (label != null) {
				gd.horizontalIndent = FormLayoutFactory.CONTROL_HORIZONTAL_INDENT;
			}
			gd.grabExcessHorizontalSpace = (span == 1);
			gd.widthHint = 10;
			combo.getControl().setLayoutData(gd);
		} else if (layout instanceof TableWrapLayout) {
			int span = ((TableWrapLayout) layout).numColumns;

			TableWrapData td;
			if (label != null) {
				td = new TableWrapData();
				td.valign = TableWrapData.MIDDLE;
				label.setLayoutData(td);
				span--;
			}
			td = new TableWrapData(TableWrapData.FILL);
			td.colspan = span;
			if (label != null) {
				td.indent = FormLayoutFactory.CONTROL_HORIZONTAL_INDENT;
			}
			td.grabHorizontal = (span == 1);
			td.valign = TableWrapData.MIDDLE;
			combo.getControl().setLayoutData(td);
		}
	}
}
