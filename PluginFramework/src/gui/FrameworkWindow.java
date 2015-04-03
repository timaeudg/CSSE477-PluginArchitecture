package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;

import main.PluginLoader;
import main.PluginRunner;

public class FrameworkWindow extends JFrame implements FrameworkGUI,
		ActionListener {
	public PluginLoader pluginLoader;
	public PluginRunner pluginRunner;

	public PluginLoader getPluginLoader() {
		return pluginLoader;
	}

	public void setPluginLoader(PluginLoader pluginLoader) {
		this.pluginLoader = pluginLoader;
		this.DirectoryLabel.setText("Current Dir: " + pluginLoader.getDir());
	}

	public PluginRunner getPluginRunner() {
		return pluginRunner;
	}

	public void setPluginRunner(PluginRunner pluginRunner) {
		this.pluginRunner = pluginRunner;
		
	}

	private static final long serialVersionUID = -594503413910961630L;
	private JPanel contentPane;

	private JPanel ExecutionPanel;
	private JList<String> PluginList;
	private JTextArea StatusArea;
	private JTextArea DirectoryLabel;


	/**
	 * Create the frame (notice that it instantiates with a plugin loader).
	 */
	public FrameworkWindow() {
		setTitle("Doge Plugin Framework");
		setIconImage(Toolkit
				.getDefaultToolkit()
				.getImage(
						"C:\\workspaces\\CSSE477\\CSSE477-PluginArchitecture\\PluginFramework\\pixel_doge.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		ExecutionPanel = new JPanel();
		ExecutionPanel.setBounds(200, 25, 579, 375);
		contentPane.add(ExecutionPanel);
		ExecutionPanel.setLayout(null);

		JPanel StatusPanel = new JPanel();
		StatusPanel.setBounds(5, 411, 774, 139);
		StatusPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		contentPane.add(StatusPanel);
		StatusPanel.setLayout(null);
		

		StatusArea = new JTextArea();
		StatusArea.setLineWrap(true);
		StatusArea.setEditable(false);
		StatusArea.setBounds(0, 0, 774, 139);

		JScrollPane scroll = new JScrollPane(StatusArea);
		scroll.setLayout(new ScrollPaneLayout());
		scroll.setBounds(0, 0, 774, 139);
		
		StatusPanel.add(scroll);
		

		// Scott's stuff starts here
		
		// Scott's panel (feel free to move)
		JPanel DirectoryPanel = new JPanel();
		DirectoryPanel.setBounds(5, 5, 800, 20);
		contentPane.add(DirectoryPanel);
		DirectoryPanel.setLayout(null);

		// Displays the current directory
		DirectoryLabel = new JTextArea();
		DirectoryLabel.setBounds(0, 0, 615, 20);
		DirectoryPanel.add(DirectoryLabel);

		// Button to press and switch to a new directory
		SwitchDirListener swl = new SwitchDirListener();
		JButton DirButton = new JButton("Set New Directory");
		DirButton.setBounds(620, 0, 150, 20);
		DirectoryPanel.add(DirButton);
		DirButton.addActionListener(swl);
		
		//End of Scott's Stuff
		
		JPanel PluginPanel = new JPanel();
		PluginPanel.setBounds(5, 30, 185, 370);
		contentPane.add(PluginPanel);
		PluginPanel.setLayout(null);

		JButton LoadButton = new JButton("Load Plugin");
		LoadButton.setBounds(0, 347, 185, 23);
		PluginPanel.add(LoadButton);
		LoadButton.addActionListener(this);

		PluginList = new JList<String>();
		PluginList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		PluginList.setVisibleRowCount(20);
		PluginList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		PluginList.setBounds(0, 0, 185, 361);
		PluginPanel.add(PluginList);
	}

	@Override
	public void print(String s) {
		this.StatusArea.setText(this.StatusArea.getText() + s);
	}

	@Override
	public void println(String s) {
		this.StatusArea.setText(this.StatusArea.getText() + s + "\n");
	}

	@Override
	public void clearStatus() {
		this.StatusArea.setText("");
	}

	@Override
	public void populatePluginList(List<String> pluginList) {
		String[] dumb = pluginList.toArray(new String[pluginList.size()]);
		this.PluginList.setListData(dumb);;
	}

	@Override
	public JPanel getExecutionPanel() {
		return this.ExecutionPanel;
	}

	// @Override
	public JPanel resetExecutionPanel() {
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.ExecutionPanel.removeAll();
		int index = this.PluginList.getSelectedIndex();
		if(index >= 0) {
			this.pluginRunner.swapRunningPlugin(this.pluginLoader.getLoadPlugins().get(index), this.ExecutionPanel, this.StatusArea);
		} else {
			this.StatusArea.append("No plugin selected to load, you dingus!");
		}
	}

	public void switchDir(String s) {
		pluginLoader.setDir(s);
		DirectoryLabel.setText("Current Dir: " + s);
	}

	/**
	 * If switch dir button is clicked, open a file chooser and choose a new dir
	 * 
	 * @author harrissa
	 * 
	 */
	class SwitchDirListener extends JPanel implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new java.io.File(pluginLoader.getDir()));
			chooser.setDialogTitle("Choose a directory to load jars from.");
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			chooser.setAcceptAllFileFilterUsed(false);
			if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				switchDir(chooser.getSelectedFile().toString());
			}

		}

	}
}
