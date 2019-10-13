package Interface;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Tools.ReadData;
import Tools.WriteData;

/**
 * Add station GUI
 * @author Yue Zhang
 * @version 1.0
 */
public class AddStation extends JFrame{
	
	private JLabel noticeLabel;
	private JLabel StationLabel, ScooterLabel;
	private JTextField StationField, ScooterField;
	private JButton cancelButton, confirmButton;
	private JPanel panel1, panel2, panel3, panel4, panel5, panel6;
	
	private String input;
	
	public AddStation() {
		super("Add Station");
		Container container = getContentPane();
		container.setLayout(new GridLayout(7, 1));
		
		noticeLabel = new JLabel("Assign New User", JLabel.CENTER);
		
		StationLabel = new JLabel("Station Name: ");
		ScooterLabel = new JLabel("Scooter Number: ");
		
		StationField = new JTextField(20);
		ScooterField = new JTextField(20);
		
		cancelButton = new JButton("Cancel");
		confirmButton = new JButton("Confirm");
		
		panel1 = new JPanel(new FlowLayout());
		panel2 = new JPanel(new FlowLayout());
		panel3 = new JPanel(new FlowLayout());
		panel4 = new JPanel(new FlowLayout());
		panel5 = new JPanel(new FlowLayout());
		panel6 = new JPanel(new FlowLayout());
		
		panel1.add(StationLabel);		panel1.add(StationField);
		panel2.add(ScooterLabel);		panel2.add(ScooterField);
		panel6.add(cancelButton);	panel6.add(confirmButton);
		
		container.add(noticeLabel);
		container.add(panel1);
		container.add(panel2);
		container.add(panel3);
		container.add(panel4);
		container.add(panel5);
		container.add(panel6);
		
		setResizable(false);	//set frame can not be resize
		setSize(500, 370);	//set frame's size
		setVisible(true);	//display frame
		setLocationRelativeTo(null);	//display in the center of window

		
		actionCancel();
		actionConfirm();
	}
	
	public void actionCancel() {
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminMenu menu = new AdminMenu();
				menu.setVisible(true);
				menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
			}
		});
	}

	public void actionConfirm() {
		confirmButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String StationInput = StationField.getText();
				String ScooterInput = ScooterField.getText();

					List<String> dataList_station=ReadData.importCsv(new File("src/Station.csv"));
					if(dataList_station!=null && !dataList_station.isEmpty()) {
						for(int i=0; i<dataList_station.size();i++ ) {
							if(i!=0) {
								input = StationInput + "," + ScooterInput;
								dataList_station.add(input);
								WriteData.exportCsv(new File("src/Station.csv"), dataList_station);
								JOptionPane.showMessageDialog( null, "Success add a station (" + StationInput + ") with " + ScooterInput + " scooters!");
								StationField.setText(""); //Set field to null
								ScooterField.setText(""); //Set field to null
								break;
								
						}
					}
				}
				
			}
		});
	}
	
//	
//	public static void main(String[] args) {
//		AddStation station = new AddStation();
//		station.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	}
}

