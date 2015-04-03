package framework;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public abstract class Plugin implements IPlugin {
	protected JPanel executionPanel;
	protected JTextArea statusPanel;
	
	public Plugin() {
		
	}
	
	public JPanel getExecutionPanel() {
		return executionPanel;
	}
	public void setExecutionPanel(JPanel executionPanel) {
		this.executionPanel = executionPanel;
	}
	public JTextArea getStatusPanel() {
		return statusPanel;
	}
	public void setStatusPanel(JTextArea statusPanel) {
		this.statusPanel = statusPanel;
	}
}
