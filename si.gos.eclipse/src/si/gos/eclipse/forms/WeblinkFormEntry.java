package si.gos.eclipse.forms;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.Hyperlink;

import si.gos.eclipse.widgets.helper.IWidgetFactory;

public class WeblinkFormEntry extends FormEntry {

	/**
	 * 
	 * @param parent
	 * @param factory
	 * @param labelText
	 */
	public WeblinkFormEntry(Composite parent, IWidgetFactory factory, String labelText) {
		super(parent, factory, labelText, null, true);
		configure();
	}
	
	/**
	 * 
	 * @param parent
	 * @param factory
	 * @param labelText
	 * @param style
	 */
	public WeblinkFormEntry(Composite parent, IWidgetFactory factory, String labelText, int style) {
		super(parent, factory, labelText, style, true);
		configure();
	}
	
	/**
	 * 
	 * @param parent
	 * @param factory
	 * @param labelText
	 * @param indent
	 * @param colspan
	 */
	public WeblinkFormEntry(Composite parent, IWidgetFactory factory, String labelText, int indent, int colspan) {
		super(parent, factory, labelText, true, indent, colspan);
		configure();
	}
	
	private void configure() {
		addFormEntryListener(new FormEntryAdapter() {
			public void textValueChanged(FormEntry entry) {
				try {
					Hyperlink link = (Hyperlink)entry.getLabel();
					URL url = new URL(entry.getValue());
					link.setHref(url);
				} catch (MalformedURLException e) {
				}
			}

			public void linkActivated(HyperlinkEvent e) {
				if (e.getHref() != null && e.getHref().toString() != null) {
					Program.launch(e.getHref().toString());
				}
			}

		});		
	}
}
