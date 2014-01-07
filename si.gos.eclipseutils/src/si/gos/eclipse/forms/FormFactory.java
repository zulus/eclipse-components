package si.gos.eclipse.forms;

import org.eclipse.swt.widgets.Composite;

import si.gos.eclipse.widgets.helper.IWidgetFactory;

public class FormFactory {

	private IWidgetFactory factory;
	
	public FormFactory(IWidgetFactory factory) {
		this.factory = factory;
	}

	/**
	 * 
	 * @param parent
	 * @param labelText
	 * @return
	 */
	public FormEntry createFormEntry(Composite parent, String labelText) {
		return new FormEntry(parent, factory, labelText);
	}

	/**
	 * 
	 * @param parent
	 * @param labelText
	 * @param style
	 * @return
	 */
	public FormEntry createFormEntry(Composite parent, String labelText,
			int style) {
		return new FormEntry(parent, factory, labelText, style);
	}

	/**
	 * 
	 * @param parent
	 * @param labelText
	 * @param indent
	 * @param colspan
	 * @return
	 */
	public FormEntry createFormEntry(Composite parent, String labelText,
			int indent, int colspan) {
		return new FormEntry(parent, factory, labelText, indent, colspan);
	}

	/**
	 * 
	 * @param parent
	 * @param labelText
	 * @param browseText
	 * @return
	 */
	public FormEntry createFormEntry(Composite parent, String labelText,
			String browseText) {
		return new FormEntry(parent, factory, labelText, browseText);
	}

	/**
	 * 
	 * @param parent
	 * @param labelText
	 * @param browseText
	 * @param indent
	 * @return
	 */
	public FormEntry createFormEntry(Composite parent, String labelText,
			String browseText, int indent) {
		return new FormEntry(parent, factory, labelText, browseText, indent);
	}

	/**
	 * 
	 * @param parent
	 * @param labelText
	 * @param browseText
	 * @param indent
	 * @param colspan
	 * @return
	 */
	public FormEntry createFormEntry(Composite parent, String labelText,
			String browseText, int indent, int colspan) {
		return new FormEntry(parent, factory, labelText, browseText, indent, colspan);
	}

	/**
	 * 
	 * @param parent
	 * @param labelText
	 * @param linkLabel
	 * @return
	 */
	public FormEntry createFormEntry(Composite parent, String labelText,
			boolean linkLabel) {
		return new FormEntry(parent, factory, labelText, linkLabel);
	}

	/**
	 * 
	 * @param parent
	 * @param labelText
	 * @param linkLabel
	 * @param indent
	 * @return
	 */
	public FormEntry createFormEntry(Composite parent, String labelText,
			boolean linkLabel, int indent) {
		return new FormEntry(parent, factory, labelText, linkLabel, indent);
	}

	/**
	 * 
	 * @param parent
	 * @param labelText
	 * @param linkLabel
	 * @param indent
	 * @param colspan
	 * @return
	 */
	public FormEntry createFormEntry(Composite parent, String labelText,
			boolean linkLabel, int indent, int colspan) {
		return new FormEntry(parent, factory, labelText, linkLabel, indent, colspan);
	}

	/**
	 * 
	 * @param parent
	 * @param labelText
	 * @param browseText
	 * @param linkLabel
	 * @return
	 */
	public FormEntry createFormEntry(Composite parent, String labelText,
			String browseText, boolean linkLabel) {
		return new FormEntry(parent, factory, labelText, browseText, linkLabel);
	}

	/**
	 * 
	 * @param parent
	 * @param labelText
	 * @param browseText
	 * @param linkLabel
	 * @param indent
	 * @return
	 */
	public FormEntry createFormEntry(Composite parent, String labelText,
			String browseText, boolean linkLabel, int indent) {
		return new FormEntry(parent, factory, labelText, browseText, linkLabel, indent);
	}

	/**
	 * 
	 * @param parent
	 * @param labelText
	 * @param browseText
	 * @param linkLabel
	 * @param indent
	 * @param colspan
	 * @return
	 */
	public FormEntry createFormEntry(Composite parent, String labelText,
			String browseText, boolean linkLabel, int indent, int colspan) {
		return new FormEntry(parent, factory, labelText, browseText, linkLabel, indent, colspan);
	}

	/**
	 * 
	 * @param parent
	 * @param labelText
	 * @return
	 */
	public WeblinkFormEntry createWeblink(Composite parent, String labelText) {
		return new WeblinkFormEntry(parent, factory, labelText);
	}

	/**
	 * 
	 * @param parent
	 * @param labelText
	 * @param style
	 * @return
	 */
	public WeblinkFormEntry createWeblink(Composite parent, String labelText,
			int style) {
		return new WeblinkFormEntry(parent, factory, labelText, style);
	}

	/**
	 * 
	 * @param parent
	 * @param labelText
	 * @param indent
	 * @param colspan
	 * @return
	 */
	public WeblinkFormEntry createWeblink(Composite parent, String labelText,
			int indent, int colspan) {
		return new WeblinkFormEntry(parent, factory, labelText, indent, colspan);
	}

	/**
	 * 
	 * @param parent
	 * @param labelText
	 * @return
	 */
	public BooleanFormEntry createBoolean(Composite parent, String labelText) {
		return new BooleanFormEntry(parent, factory, labelText);
	}

	/**
	 * 
	 * @param parent
	 * @param labelText
	 * @param style
	 * @return
	 */
	public BooleanFormEntry createBoolean(Composite parent, String labelText,
			int style) {
		return new BooleanFormEntry(parent, factory, labelText, style);
	}

	/**
	 * 
	 * @param parent
	 * @param labelText
	 * @return
	 */
	public ComboFormEntry createCombo(Composite parent, String labelText) {
		return new ComboFormEntry(parent, factory, labelText);
	}

	/**
	 * 
	 * @param parent
	 * @param labelText
	 * @param style
	 * @return
	 */
	public ComboFormEntry createCombo(Composite parent, String labelText,
			int style) {
		return new ComboFormEntry(parent, factory, labelText, style);
	}
	

}
