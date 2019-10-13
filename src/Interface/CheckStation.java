package Interface;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * Admin choose the station to check details GUI
 * @author Yue Zhang
 * @version 1.0
 */
public class CheckStation extends JFrame{
	private JLabel selectLabel;
	private JRadioButton aButton, bButton, cButton;
	private ButtonGroup stationGroup;
	private JButton backButton, checkButton;
	private JLabel emptyLabel1, emptyLabel2;
	private JPanel confirmPanel, backPanel, buttonPanel, centerPanel;
	private String Station;
	
	public CheckStation() {
		super("Select the Station");
		
		Container container = getContentPane();
		container.setLayout(new GridLayout(3,1));
		
		selectLabel = new JLabel("Please select the docking station:", JLabel.CENTER);
		
		aButton = new JRadioButton("Station A (Library)", false);
		bButton = new JRadioButton("Station B (Informatics Teaching Laboratories)", false);
		cButton = new JRadioButton("Station C (Village Shop)", false);
		
		buttonPanel = new JPanel(new GridLayout(3,1));
		buttonPanel.add(aButton);
		buttonPanel.add(bButton);
		buttonPanel.add(cButton);
		
		emptyLabel1 = new JLabel("                       ");
		emptyLabel2 = new JLabel("     ");
		centerPanel = new JPanel(new BorderLayout());
		centerPanel.add(buttonPanel, BorderLayout.CENTER);
		centerPanel.add(emptyLabel1, BorderLayout.WEST);
		centerPanel.add(emptyLabel2, BorderLayout.EAST);
		
		stationGroup = new ButtonGroup();
		stationGroup.add(aButton);
		stationGroup.add(bButton);
		stationGroup.add(cButton);
		
		checkButton = new JButton("Confirm");
		confirmPanel = new JPanel();
		confirmPanel.add(checkButton);
		
		
		backButton = new JButton("Back");
		backPanel = new JPanel();
		backPanel.add(backButton);
		
		JPanel confirm_back = new JPanel(new GridLayout(1,2));
		confirm_back.add(confirmPanel);
		confirm_back.add(backPanel);
		

		container.add(selectLabel);
		container.add(centerPanel);
		container.add(confirm_back);
		
		setResizable(false);	//set frame can not be resize
		setSize(500, 300);	//set frame's size
		setVisible(true);	//display frame
		setLocationRelativeTo(null);	//display in the center of window
		
		actionCheck();
		actionback();
		actionstationA();
		actionstationB();
		actionstationC();
	}
	
	public void actionCheck() {
		checkButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CheckDetail station = new CheckDetail(Station);
				station.setVisible(true);
				station.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
			}
		});
	}
	
	public void actionback(){
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				AdminMenu menu = new AdminMenu();
				menu.setVisible(true);
				menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
			}
		});
	}
	
	public void actionstationA(){
		aButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Station = "A";
			}
		});
	}
	public void actionstationB(){
		bButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Station = "B";
			}
		});
	}
	public void actionstationC(){
		cButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Station = "C";
			}
		});
	}

//	public static void main(String[] args) {
//		CheckStation station = new CheckStation();
//		station.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	}
}
