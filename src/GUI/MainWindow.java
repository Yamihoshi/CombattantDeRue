package GUI;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class MainWindow extends JFrame{

	boolean debug_mode = true;
	public static final String IMAGE_PATH = "ressource/img/Street_Fighter_logo.png";
	
	public MainWindow()
	{
		this.setTitle("Street Fighter desu");
		this.setSize(800,600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		showLoadingScreen();
		
		Action actionListener = new AbstractAction() {
		      public void actionPerformed(ActionEvent actionEvent) {
		    	  getContentPane().removeAll();
		    	  getContentPane().add(new CharacterSelection(),BorderLayout.CENTER);
		    	  repaint();
		    	  revalidate();
		      }
		    };
		    JPanel content = (JPanel) this.getContentPane();

		    KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0,true);

		    InputMap inputMap = content.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		    inputMap.put(stroke, "OPEN");
		    content.getActionMap().put("OPEN", actionListener);
		
		this.setVisible(true);
	}
	
	private void showLoadingScreen()
	{
		ImageIcon imageIcon = new ImageIcon(new ImageIcon(IMAGE_PATH).getImage().getScaledInstance(800,600, Image.SCALE_DEFAULT));
		this.getContentPane().add(new JLabel(imageIcon),BorderLayout.CENTER);
		this.getContentPane().repaint();
		this.getContentPane().validate();
	}
}
