package si.gos.eclipse.editor;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.SectionPart;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

public abstract class SharedSection extends SectionPart {

	private SharedFormPage page;
	protected boolean enabled = true;
	
	public SharedSection(SharedFormPage page, Composite parent, int style) {
		this(page, parent, style, true);
	}
	
	public SharedSection(SharedFormPage page, Composite parent, int style, boolean titleBar) {
		super(parent, page.getManagedForm().getToolkit(), titleBar ? (ExpandableComposite.TITLE_BAR | style) : style);
		this.page = page;
		initialize(page.getManagedForm());
	}
	
	public SharedFormPage getPage() {
		return page;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	protected abstract void createClient(Section section, FormToolkit toolkit);
}
