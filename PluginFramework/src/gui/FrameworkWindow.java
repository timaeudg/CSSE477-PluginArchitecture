package gui;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;

public class FrameworkWindow extends JFrame implements FrameworkGUI,
		ActionListener {

	private static final long serialVersionUID = -594503413910961630L;
	private JPanel contentPane;

	private JPanel ExecutionPanel;
	private JList<String> PluginList;
	private JTextArea StatusArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameworkWindow frame = new FrameworkWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
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
		ExecutionPanel.setBounds(200, 5, 579, 395);
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
		StatusPanel.add(StatusArea);

		JPanel PluginPanel = new JPanel();
		PluginPanel.setBounds(5, 5, 185, 395);
		contentPane.add(PluginPanel);
		PluginPanel.setLayout(null);

		JButton LoadButton = new JButton("Load Plugin");
		LoadButton.setBounds(0, 372, 185, 23);
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
	public void populatePluginList(String[] pluginList) {
		this.PluginList.setListData(pluginList);
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
		//this.framework.loadPlugin(currentlySelectedPlugin);
	}
}
