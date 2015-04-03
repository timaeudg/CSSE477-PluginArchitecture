import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import framework.IPlugin;
import framework.Plugin;

/**
 * This is not a plugin to turn in. It is merely useful for the purposes of
 * Scott's JAR loading
 */
public class PluginExample extends Plugin {

	@Override
	public void startup() {
		JLabel yayLabel = new JLabel("Yay! this SUPER basic plugin was loaded");
		this.executionPanel.setLayout(new FlowLayout());
		this.executionPanel.add(yayLabel);
		this.statusPanel.append("Yay! this SUPER basic plugin was loaded\n");
	}

	@Override
	public void pause() {
		
	}

}
