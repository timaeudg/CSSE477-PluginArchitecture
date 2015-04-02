package gui;

import javax.swing.JPanel;

public interface FrameworkGUI {

	/**
	 * Print the string to the Status Panel
	 * 
	 * @param s
	 *            string to print
	 */
	public void print(String s);

	/**
	 * Print the string to the Status Panel with a newline character
	 * 
	 * @param s
	 *            string to print
	 */
	public void println(String s);

	/**
	 * Clear the contents of the Status Panel
	 */
	public void clearStatus();

	/**
	 * Set the PluginListPanel to the contents of the given array of Strings
	 * 
	 * @param pluginList
	 *            String array of plugin names
	 */
	public void populatePluginList(String[] pluginList);

	/**
	 * Gets the Execution Panel
	 * 
	 * @return execution panel
	 */
	public JPanel getExecutionPanel();

	// public JPanel resetExecutionPanel();

}
