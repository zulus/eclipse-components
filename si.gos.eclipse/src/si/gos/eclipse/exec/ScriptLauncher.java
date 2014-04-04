package si.gos.eclipse.exec;

import java.io.IOException;

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
public class ScriptLauncher extends Launcher {

	private Environment environment;
	
	public ScriptLauncher(Environment environment, IProject project) {
		super(project);
		this.environment = environment;
		this.environment.setUp(project);
	}
	
	public void launch(String command) throws ExecuteException, IOException, InterruptedException {
		launch(command, new String[]{});
	}
	
	public void launch(String command, String[] arguments) throws ExecuteException, IOException, InterruptedException {
		CommandLine cmd = environment.getCommand();
		cmd.addArgument(command);
		cmd.addArguments(arguments);
		
		super.launch(cmd);
	}
}
