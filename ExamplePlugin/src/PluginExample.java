import javax.swing.JPanel;

import framework.IPlugin;
import framework.Plugin;

/**
 * This is not a plugin to turn in. It is merely useful for the purposes of
 * Scott's JAR loading
 */
public class PluginExample extends Plugin {

	@Override
	public void saveExecutionPanelContents() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JPanel getExecutionPanelContents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void startup() {
		// TODO Auto-generated method stub
		this.statusPanel.append("Yay! this SUPER basic plugin was loaded");
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

}
