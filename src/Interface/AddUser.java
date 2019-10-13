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
 * Add user GUI
 * @author Yue Zhang
 * @version 1.0
 */
public class AddUser extends JFrame{
	
	private JLabel noticeLabel;
	private JLabel QMLabel, nameLabel, emailLabel, telLabel, addressLabel;
	private JTextField QMField, nameField, emailField, telField, addressField;
	private JButton cancelButton, confirmButton;
	private JPanel panel1, panel2, panel3, panel4, panel5, panel6;
	
	private String ID, QMID, Station, s, as[], input;
	
	public AddUser() {
		super("Add User");
		Container container = getContentPane();
		container.setLayout(new GridLayout(7, 1));
		
		noticeLabel = new JLabel("Assign New User", JLabel.CENTER);
		
		QMLabel = new JLabel("QM ID: ");
		nameLabel = new JLabel("Name: ");
		emailLabel = new JLabel("Email: ");
		telLabel = new JLabel("Tel: ");
		addressLabel = new JLabel("Address: ");
		
		QMField = new JTextField(20);
		nameField = new JTextField(20);
		emailField = new JTextField(20);
		telField = new JTextField(20);
		addressField = new JTextField(20);
		
		cancelButton = new JButton("Cancel");
		confirmButton = new JButton("Confirm");
		
		panel1 = new JPanel(new FlowLayout());
		panel2 = new JPanel(new FlowLayout());
		panel3 = new JPanel(new FlowLayout());
		panel4 = new JPanel(new FlowLayout());
		panel5 = new JPanel(new FlowLayout());
		panel6 = new JPanel(new FlowLayout());
		
		panel1.add(QMLabel);		panel1.add(QMField);
		panel2.add(nameLabel);		panel2.add(nameField);
		panel3.add(emailLabel);		panel3.add(emailField);
		panel4.add(telLabel);		panel4.add(telField);
		panel5.add(addressLabel);	panel5.add(addressField);
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
				String QMInput = QMField.getText();
				String nameInput = nameField.getText();
				String emailInput  = emailField.getText();
				String telInput = telField.getText();
				String addressInput = addressField.getText();
				if(QMInput.equals("") || nameInput.equals("") || emailInput.equals("") || telInput.equals("") || addressInput.equals("")) {
					JOptionPane.showMessageDialog(null, "You have to fill all of these sections! Please enter again!");
				}
				else {
				if(emailInput.matches("^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$")) {
				
					if(QMInput.matches("^\\d{9}$")) {
						List<String> dataList_student=ReadData.importCsv(new File("src/Student.csv"));
						if(dataList_student!=null && !dataList_student.isEmpty()) {
							for(int i=0; i<dataList_student.size();i++ ) {
								if(i!=0) {
									s = dataList_student.get(i);
									as = s.split(",");
									if(as[0].equals(QMInput)) {
										JOptionPane.showMessageDialog(null, "This QMID has been registered! Please enter another one and try again!");
										QMField.setText("");
										break;
									}
									else {
										input = QMInput + "," + nameInput + "," + emailInput + "," + telInput + "," + addressInput + "," + "Free" + "," + "0" + "," + "0";
										dataList_student.add(input);
										WriteData.exportCsv(new File("src/Student.csv"), dataList_student);
										JOptionPane.showMessageDialog( null, "Success add a user!");
										QMField.setText(""); //Set field to null
										nameField.setText(""); //Set field to null
										emailField.setText(""); //Set field to null
										telField.setText(""); //Set field to null
										addressField.setText(""); //Set field to null
										break;
										}
									}
								}
							}
						}
						else {
							JOptionPane.showMessageDialog(null, "QMID must be 9 numbers! Please enter again!");
							QMField.setText("");
						}
					}
				else {
					JOptionPane.showMessageDialog(null, "Wrong Email address! Please enter again!");
					emailField.setText("");
				}
				}
			}
		});
	}
	
	
//	public static void main(String[] args) {
//		AddUser user = new AddUser();
//		user.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	}
}
