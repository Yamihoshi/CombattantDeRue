package GUI;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainWindow extends JFrame{

	boolean debug_mode = true;
	public static final String IMAGE_PATH = "ressource/img/street-fighter-logo.jpg";
	
	public MainWindow()
	{
		this.setTitle("Street Fighter desu");
		this.setSize(800,600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		showLoadingScreen();
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
