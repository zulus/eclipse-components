package si.gos.eclipse.exec;

import java.util.HashMap;

import javax.inject.Inject;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.e4.core.di.annotations.Creatable;

/**
 * 
 * @Inject the {@link ScriptLauncherManager} into your service to retrieve a launcher
 * for executing scripts.
 */
@Creatable
public class ScriptLauncherManager {

	private static final String EXTENSION_POINT_ID = "si.gos.eclipse.exec";
	private final HashMap<String, EnvironmentFactory> factories = new HashMap<String, EnvironmentFactory>();
	
	@Inject
	public ScriptLauncherManager(IExtensionRegistry registry) {
		evaluate(registry);
	}
	
	private void evaluate(IExtensionRegistry registry) {
		try {
			IConfigurationElement[] config = registry.getConfigurationElementsFor(EXTENSION_POINT_ID);
			for (IConfigurationElement e : config) {
				final EnvironmentFactory factory = (EnvironmentFactory) e.createExecutableExtension("class");
				if (factory != null) {
					factories.put(e.getAttribute("id"), factory);
				}
			}
		} catch (Exception e) {
//			Logger.logException(e);
		}
	}
	
	private Environment getEnvironment(String factoryId, IProject project) throws ExecutableNotFoundException {
		if (!factories.containsKey(factoryId)) {
			return null;
		}
		
		return factories.get(factoryId).getEnvironment(project);
	}
	
	public ScriptLauncher getLauncher(String factoryId, IProject project) throws ExecutableNotFoundException {
		Environment env = getEnvironment(factoryId, project);
		if (env == null) {
			throw new ExecutableNotFoundException("Can't find any executable");
		}
		
		return new ScriptLauncher(env, project);
	}

//	public void resetEnvironment() {
//		/*
//		if (env != null) {
//			synchronized (env) {
//				env = null;
//			}	
//		}
//		*/
//	}
}
