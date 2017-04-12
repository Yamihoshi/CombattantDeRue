package GUI;

import java.awt.*;
import javax.swing.*;

public class CharacterSelection extends JPanel{

	private JLabel chara_1;
	private JLabel chara_2;
	
	
	
	public CharacterSelection()
	{
		this.setLayout(new BorderLayout());
		this.setBackground(Color.BLACK);
		this.add(selectPlayer1(),BorderLayout.WEST);
		this.add(selectPlayer2(),BorderLayout.EAST);
	}
	
	public JPanel selectPlayer1()
	{
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setLayout(new BorderLayout());
		panel.add(new JLabel("J1"),BorderLayout.SOUTH);
		ImageIcon imageIcon = new ImageIcon(new ImageIcon("ressource/Portraits/Chun_Li.jpg").getImage().getScaledInstance(150,300, Image.SCALE_DEFAULT));
		panel.add(new JLabel(imageIcon),BorderLayout.CENTER);
		return panel;
	}
	
	public JPanel selectPlayer2()
	{
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setLayout(new BorderLayout());
		panel.add(new JLabel("J2"),BorderLayout.SOUTH);
		ImageIcon imageIcon = new ImageIcon(new ImageIcon("ressource/Portraits/Cammy.jpg").getImage().getScaledInstance(150,300, Image.SCALE_DEFAULT));
		panel.add(new JLabel(imageIcon),BorderLayout.CENTER);
		return panel;
	}
	
	public void switchCharacter()
	{

	}
}
