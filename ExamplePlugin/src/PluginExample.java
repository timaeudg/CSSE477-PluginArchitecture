import framework.IPlugin;

/**
 * This is not a plugin to turn in. It is merely useful for the purposes of
 * Scott's JAR loading
 */
public class PluginExample implements IPlugin {

	public PluginExample() {

	}

	@Override
	public void saveExecutionPanelContents() {
		// TODO Auto-generated method stub

	}

	@Override
	public void getExecutionPanelContents() {
		// TODO Auto-generated method stub

	}

	@Override
	public void startup() {
		System.out.println("Yay!");

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

}
