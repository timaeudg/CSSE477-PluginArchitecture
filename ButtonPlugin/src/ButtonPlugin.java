import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import framework.Plugin;


public class ButtonPlugin extends Plugin {
	
	public ButtonPlugin () {
		super();
	}

	public void startup() {
		JTextArea jta = new JTextArea("Press the button!", 5, 20);
		jta.setVisible(true);
		ButtonPluginListener bpl = new ButtonPluginListener(jta);
		JButton button = new JButton("yay?");
		button.setVisible(true);
		
		button.addActionListener(bpl);
		
		this.executionPanel.setLayout(new FlowLayout());
		this.executionPanel.add(button);		
		this.executionPanel.add(jta);

		this.statusPanel.append("The button plugin loaded!!!");
	}

	public void pause() {
		// TODO Auto-generated method stub
		
	}
	
	class ButtonPluginListener implements ActionListener {
		private JTextArea jta;
		
		public ButtonPluginListener(JTextArea jta) {
			this.jta = jta;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			jta.setText("Yay!");
			
		}
		
	}

}
