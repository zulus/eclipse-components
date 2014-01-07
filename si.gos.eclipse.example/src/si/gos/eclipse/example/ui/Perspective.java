package si.gos.eclipse.example.ui;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import si.gos.eclipse.example.editor.EditorView;

public class Perspective implements IPerspectiveFactory {

	public static final String ID = "si.gos.eclipse.example.perspective";
	
	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(true);
		layout.setFixed(false);
		
		layout.addView(View.ID, IPageLayout.LEFT, 0.3f, layout.getEditorArea());
		IFolderLayout folder = layout.createFolder("editorArea", IPageLayout.RIGHT, 0.7f, layout.getEditorArea());
		
		folder.addPlaceholder(EditorView.ID);
		folder.addView(EditorView.ID);
	}

}
