package si.gos.eclipse.exec;

import org.apache.commons.exec.CommandLine;
import org.eclipse.core.resources.IProject;

/**
 * Interface for the Environment in which a script should be executed.
 * 
 */
public interface Environment {

	public boolean isAvailable();
	
	public void setUp(IProject project);
	
	public CommandLine getCommand();
}
