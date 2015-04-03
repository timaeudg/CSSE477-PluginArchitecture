package main;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import framework.Plugin;

public class PluginRunner {

	private Plugin runningPlugin;
	
	public PluginRunner() {
	}

	void pausePlugin(Plugin toPause){
		
	}
	
	public void runPlugin(Plugin toRun){
		
	}
	
	public Plugin swapRunningPlugin(Plugin newRunningPlugin, JPanel execPanel, JTextArea jta){
		if(this.runningPlugin != null) {
			this.runningPlugin.pause();
		}
		this.runningPlugin = newRunningPlugin;
		this.runningPlugin.setExecutionPanel(execPanel);
		this.runningPlugin.setStatusPanel(jta);
		this.runningPlugin.startup();
		execPanel.revalidate();
		execPanel.repaint();
		return null;
	}
}
