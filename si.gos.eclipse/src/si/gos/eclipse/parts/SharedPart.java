/*******************************************************************************
 *  Copyright (c) 2000, 2008 IBM Corporation and others.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 * 
 *  Contributors:
 *     IBM Corporation - initial API and implementation
 *     
 *  Source:
 *     https://github.com/eclipse/eclipse.pde.ui/blob/master/ui/org.eclipse.pde.ui/src/org/eclipse/pde/internal/ui/parts/SharedPart.java
 *******************************************************************************/
package si.gos.eclipse.parts;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import si.gos.eclipse.widgets.helper.IWidgetFactory;

public abstract class SharedPart {
	
	public final static boolean DEFAULT_GRAY_ON_DISABLED = true;
	
	private boolean enabled = true;
	private boolean grayed = false;
	
	private boolean grayOnDisabled = DEFAULT_GRAY_ON_DISABLED;

	public void setEnabled(boolean enabled) {
		if (enabled != this.enabled) {
			this.enabled = enabled;
			updateEnabledState();
			
			if (grayOnDisabled) {
				setGrayed(enabled);
			}
		}
	}

	public boolean isEnabled() {
		return enabled;
	}

	protected void updateEnabledState() {
	}
	
	/**
	 * @return the grayed
	 */
	public boolean isGrayed() {
		return grayed;
	}

	/**
	 * @param grayed the grayed to set
	 */
	public void setGrayed(boolean grayed) {
		this.grayed = grayed;
		updateGrayedState();
	}
	
	protected void updateGrayedState() {
	}
	
	/**
	 * @return the grayOnDisabled
	 */
	public boolean getGrayOnDisabled() {
		return grayOnDisabled;
	}

	/**
	 * @param grayOnDisabled the grayOnDisabled to set
	 */
	public void setGrayOnDisabled(boolean grayOnDisabled) {
		this.grayOnDisabled = grayOnDisabled;
	}

	public abstract void createControl(Composite parent, int style, int span, IWidgetFactory factory);
	
	protected abstract Shell getShell();

	protected Composite createComposite(Composite parent, IWidgetFactory factory) {
		return factory.createComposite(parent);
	}

	protected Label createEmptySpace(Composite parent, int span, IWidgetFactory factory) {
		Label label = factory.createLabel(parent);
		GridData gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		gd.horizontalSpan = span;
		gd.widthHint = 0;
		gd.heightHint = 0;
		label.setLayoutData(gd);
		return label;
	}
	
}

