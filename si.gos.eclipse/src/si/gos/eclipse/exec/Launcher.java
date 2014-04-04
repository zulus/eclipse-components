package si.gos.eclipse.exec;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.ExecuteException;
import org.eclipse.core.resources.IProject;

import si.gos.eclipse.internals.exec.Executor;

/**
 * 
 * Uses a {@link Executor} to launch a script with a specific {@link Environment}.
 * 
 * You can use an {@link ExecutionResponseListener} to retrieve the output
 * of the executed script.
 *
 */
public class Launcher {

	protected IProject project;
	private Executor executor;
	private Set<ExecutionResponseListener> listeners = new HashSet<ExecutionResponseListener>();
	private Integer timeout = null;
	
	public Launcher() {
		
	}
	
	public Launcher(IProject project) {
		this.project = project;
	}

	public void addResponseListener(ExecutionResponseListener listener) {
		listeners.add(listener);
	}

	public void removeResponseListener(ExecutionResponseListener listener) {
		listeners.remove(listener);
	}
	
	public void launch(CommandLine cmd) throws ExecuteException, IOException, InterruptedException {
		executor = new Executor();
		
		if (timeout != null) {
			executor.setTimeout(timeout);
		}

		if (project != null) {
			executor.setWorkingDirectory(project.getLocation().toFile());
		}
		
		for (ExecutionResponseListener listener : listeners) {
			executor.addResponseListener(listener);
		}
		
		executor.execute(cmd);
	}
	
	public void abort() {
		executor.abort();
	}
	
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
}
