package framework;

import javax.swing.JPanel;

public abstract class Plugin implements IPlugin {
	private JPanel executionPanel;
	private JPanel statusPanel;
	public JPanel getExecutionPanel() {
		return executionPanel;
	}
	public void setExecutionPanel(JPanel executionPanel) {
		this.executionPanel = executionPanel;
	}
	public JPanel getStatusPanel() {
		return statusPanel;
	}
	public void setStatusPanel(JPanel statusPanel) {
		this.statusPanel = statusPanel;
	}
}
