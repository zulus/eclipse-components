package si.gos.eclipse.exec;

import org.eclipse.core.resources.IProject;

/**
 * Interface for retrieving the proper {@link Environment} to launch a script
 * for a project.
 */
public interface EnvironmentFactory {

	public Environment getEnvironment(IProject project);
	
}
