package main;

import gui.FrameworkWindow;

import java.awt.EventQueue;
import java.nio.file.Path;

public class PluginPlatform {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					// Sets the plugin loader for this GUI.
					PluginLoader p = new PluginLoader();
					PluginRunner runner = new PluginRunner();
					FrameworkWindow frame = new FrameworkWindow();
					p.setGUI(frame);
					frame.setPluginLoader(p);
					frame.setPluginRunner(runner);
					frame.setVisible(true);
					p.reload();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
