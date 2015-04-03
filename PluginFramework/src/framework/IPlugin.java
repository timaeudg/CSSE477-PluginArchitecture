package framework;

import javax.swing.JPanel;

public interface IPlugin {	
	void saveExecutionPanelContents();
	JPanel getExecutionPanelContents();
	void startup();
	void pause();

}
