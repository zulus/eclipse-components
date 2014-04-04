package si.gos.eclipse.widgets.utils;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;

public class WidgetHelper {

	/**
	 * Trims a composite and its vertical and horizontal spacing 
	 * 
	 * @param composite
	 * @param top
	 * @param right
	 * @param bottom
	 * @param left
	 * @param vertical
	 * @param horizontal
	 */
	public static void trim(Composite composite, int top, int right, int bottom, int left, int vertical, int horizontal) {
		Layout raw = composite.getLayout();
		if (raw instanceof GridLayout) {
			GridLayout layout = (GridLayout)raw;
			layout.marginTop = top;
			layout.marginRight = right;
			layout.marginBottom = bottom;
			layout.marginLeft = left;
			layout.verticalSpacing = vertical;
			layout.horizontalSpacing = horizontal;
		}
		
		if (raw instanceof RowLayout) {
			RowLayout layout = (RowLayout)raw;
			layout.marginTop = top;
			layout.marginRight = right;
			layout.marginBottom = bottom;
			layout.marginLeft = left;
			
			layout.spacing = Math.round((vertical + horizontal) / 2);
		}
	}
	
	/**
	 * Trims a composite
	 * 
	 * @param composite
	 * @param top
	 * @param right
	 * @param bottom
	 * @param left
	 */
	public static void trim(Composite composite, int top, int right, int bottom, int left) {
		Layout raw = composite.getLayout();
		if (raw instanceof GridLayout) {
			GridLayout layout = (GridLayout)raw;
			layout.marginTop = top;
			layout.marginRight = right;
			layout.marginBottom = bottom;
			layout.marginLeft = left;
		}
		
		if (raw instanceof RowLayout) {
			RowLayout layout = (RowLayout)raw;
			layout.marginTop = top;
			layout.marginRight = right;
			layout.marginBottom = bottom;
			layout.marginLeft = left;
		}
	}
	
	/**
	 * Trims a composite
	 * 
	 * @param composite
	 * @param top
	 * @param rightleft
	 * @param bottom
	 */
	public static void trim(Composite composite, int top, int rightleft, int bottom) {
		Layout raw = composite.getLayout();
		if (raw instanceof GridLayout) {
			GridLayout layout = (GridLayout)raw;
			layout.marginTop = top;
			layout.marginRight = rightleft;
			layout.marginBottom = bottom;
			layout.marginLeft = rightleft;
		}
		
		if (raw instanceof RowLayout) {
			RowLayout layout = (RowLayout)raw;
			layout.marginTop = top;
			layout.marginRight = rightleft;
			layout.marginBottom = bottom;
			layout.marginLeft = rightleft;
		}
	}
	
	/**
	 * Trims a composite
	 * 
	 * @param composite
	 * @param topbottom
	 * @param rightleft
	 */
	public static void trim(Composite composite, int topbottom, int rightleft) {
		Layout raw = composite.getLayout();
		if (raw instanceof GridLayout) {
			GridLayout layout = (GridLayout)raw;
			layout.marginTop = topbottom;
			layout.marginRight = rightleft;
			layout.marginBottom = topbottom;
			layout.marginLeft = rightleft;
		}

		if (raw instanceof RowLayout) {
			RowLayout layout = (RowLayout)raw;
			layout.marginTop = topbottom;
			layout.marginRight = rightleft;
			layout.marginBottom = topbottom;
			layout.marginLeft = rightleft;
		}
	}
	
	/**
	 * Trims a composite
	 * 
	 * @param composite
	 * @param all
	 */
	public static void trim(Composite composite, int all) {
		Layout raw = composite.getLayout();
		if (raw instanceof GridLayout) {
			GridLayout layout = (GridLayout)raw;
			layout.marginTop = all;
			layout.marginRight = all;
			layout.marginBottom = all;
			layout.marginLeft = all;
		}
		
		if (raw instanceof RowLayout) {
			RowLayout layout = (RowLayout)raw;
			layout.marginTop = all;
			layout.marginRight = all;
			layout.marginBottom = all;
			layout.marginLeft = all;
		}
	}
	
	/**
	 * Sets spacing for a composite
	 * 
	 * @param composite
	 * @param vertical
	 * @param horizontal
	 */
	public static void setSpacing(Composite composite, int vertical, int horizontal) {
		Layout raw = composite.getLayout();
		if (raw instanceof GridLayout) {
			GridLayout layout = (GridLayout)raw;
			layout.verticalSpacing = vertical;
			layout.horizontalSpacing = horizontal;
		}
		
		if (raw instanceof RowLayout) {
			RowLayout layout = (RowLayout)raw;
			layout.spacing = Math.round((vertical + horizontal) / 2);
		}
	}
	
	/**
	 * Sets spacing for a composite
	 * 
	 * @param composite
	 * @param vertical
	 * @param horizontal
	 */
	public static void setSpacing(Composite composite, int spacing) {
		Layout raw = composite.getLayout();
		if (raw instanceof GridLayout) {
			GridLayout layout = (GridLayout)raw;
			layout.verticalSpacing = spacing;
			layout.horizontalSpacing = spacing;
		}
		
		if (raw instanceof RowLayout) {
			RowLayout layout = (RowLayout)raw;
			layout.spacing = spacing;
		}
	}
	
	/**
	 * Sets margin for a composite
	 * 
	 * @param composite
	 * @param vertical
	 * @param horizontal
	 */
	public static void setMargin(Composite composite, int vertical, int horizontal) {
		Layout raw = composite.getLayout();
		if (raw instanceof GridLayout) {
			GridLayout layout = (GridLayout)raw;
			layout.marginHeight = vertical;
			layout.marginWidth = horizontal;
		}
	}
	
	/**
	 * Make the composite grow to maximum horizontal and vertical space
	 * 
	 * @param composite
	 */
	public static void fillBoth(Composite composite) {
		Composite parent = composite.getParent();
		Layout raw = parent.getLayout();
		
		if (raw instanceof GridLayout) {
			GridData gd = (GridData)composite.getLayoutData();
			if (gd == null) {
				gd = new GridData();
			}
			
			gd.grabExcessHorizontalSpace = true;
			gd.grabExcessVerticalSpace = true;
			gd.horizontalAlignment = GridData.FILL;
			gd.verticalAlignment = GridData.FILL;
			
			composite.setLayoutData(gd);
		}
	}
}
