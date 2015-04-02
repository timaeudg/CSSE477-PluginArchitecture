package main;

import javax.swing.JPanel;

import framework.Plugin;

public class PluginRunner {
	private JPanel executionPanel;
	private Plugin runningPlugin;
	
	public PluginRunner(JPanel excutionPanel) {
		this.executionPanel = excutionPanel;
	}

	void pausePlugin(Plugin toPause){
		
	}
	
	void runPlugin(Plugin toRun){
		
	}
	
	Plugin swapRunningPlugin(Plugin newRunningPlugin){
		//TODO: fill this in
		return null;
	}
}
