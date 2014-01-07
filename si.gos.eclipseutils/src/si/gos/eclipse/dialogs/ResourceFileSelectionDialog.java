package si.gos.eclipse.dialogs;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;

import si.gos.eclipse.EclipseUtils;

/**
 * Dialog to select files within the workspace.
 * 
 * @see http://stackoverflow.com/a/14775132/483492
 * @author qizer
 * @author gossi
 * 
 */
public class ResourceFileSelectionDialog extends ElementTreeSelectionDialog {
	private String[] extensions = new String[]{};

	private static final IStatus OK = new Status(IStatus.OK, EclipseUtils.PLUGIN_ID, 0, "", null);
	private static final IStatus ERROR = new Status(IStatus.ERROR, EclipseUtils.PLUGIN_ID, 0, "", null);

	/**
	 * File Validator
	 */
	private ISelectionStatusValidator validator = new ISelectionStatusValidator() {
		public IStatus validate(Object[] selection) {
			boolean valid = true;
			for (Object item : selection) {
				valid = valid && item instanceof IFile
					&& checkExtension(((IFile) item).getFileExtension());
			}

			return valid ? OK : ERROR;
		}
	};
	
	public ResourceFileSelectionDialog(Shell parent,
			ILabelProvider labelProvider, ITreeContentProvider contentProvider) {
		super(parent, labelProvider, contentProvider);
		setValidator(validator);
	}
	
//	@Override
//	public IFile[] getResult() {
//		return (IFile[])super.getResult();
//	}
//	
//	@Override
//	public IFile getFirstResult() {
//		IFile[] results = getResult();
//		if (results.length > 0) {
//			return results[0];
//		}
//		return null;
//	}

	/**
	 * Check file extension
	 */
	private boolean checkExtension(String name) {
		if (name.equals("*") || extensions.length == 0) {
			return true;
		}

		for (String extension : extensions) {
			if (extension.equals(name)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * @return the extensions
	 */
	public String[] getExtensions() {
		return extensions;
	}

	/**
	 * Sets extensions for valid selectable files
	 * 
	 * @param extensions the extensions to set
	 */
	public void setExtensions(String[] extensions) {
		this.extensions = extensions;
	}
}
