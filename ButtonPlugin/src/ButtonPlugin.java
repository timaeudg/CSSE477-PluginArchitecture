import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import framework.IPlugin;


public class ButtonPlugin extends JPanel implements IPlugin {

	public JButton button;
	public JTextArea jta;
	
	public ButtonPlugin () {
		
	}
	
	@Override
	public void saveExecutionPanelContents() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getExecutionPanelContents() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startup() {
		ButtonPluginListener bpl = new ButtonPluginListener();
		button = new JButton("yay?");
		this.add(button);
		button.addActionListener(bpl);
		
		jta = new JTextArea("Press the button!");
		this.add(jta);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}
	
	class ButtonPluginListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			jta.setText("Yay!");
			
		}
		
	}

}
