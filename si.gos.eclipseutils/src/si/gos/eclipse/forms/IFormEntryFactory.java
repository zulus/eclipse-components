package si.gos.eclipse.forms;

import org.eclipse.swt.widgets.Composite;

public interface IFormEntryFactory {

	public FormEntry createFormEntry(Composite parent, String labelText);
	
	public FormEntry createFormEntry(Composite parent, String labelText, int style);
	
	public FormEntry createFormEntry(Composite parent, String labelText, int indent, int colspan);
	
	public FormEntry createFormEntry(Composite parent, String labelText, String browseText);

	public FormEntry createFormEntry(Composite parent, String labelText, String browseText, int indent);
	
	public FormEntry createFormEntry(Composite parent, String labelText, String browseText, int indent, int colspan);
	
	public FormEntry createFormEntry(Composite parent, String labelText, boolean linkLabel);

	public FormEntry createFormEntry(Composite parent, String labelText, boolean linkLabel, int indent);
	
	public FormEntry createFormEntry(Composite parent, String labelText, boolean linkLabel, int indent, int colspan);
	
	public FormEntry createFormEntry(Composite parent, String labelText, String browseText, boolean linkLabel);

	public FormEntry createFormEntry(Composite parent, String labelText, String browseText, boolean linkLabel, int indent);
	
	public FormEntry createFormEntry(Composite parent, String labelText, String browseText, boolean linkLabel, int indent, int colspan);

	
	
	public WeblinkFormEntry createWeblink(Composite parent, String labelText);
	
	public WeblinkFormEntry createWeblink(Composite parent, String labelText, int style);
	
	public WeblinkFormEntry createWeblink(Composite parent, String labelText, int indent, int colspan);
	
	
	
	public BooleanFormEntry createBoolean(Composite parent, String labelText);
	
	public BooleanFormEntry createBoolean(Composite parent, String labelText, int style);
	
	
	
	public ComboFormEntry createCombo(Composite parent, String labelText);
	
	public ComboFormEntry createCombo(Composite parent, String labelText, int style);
}
