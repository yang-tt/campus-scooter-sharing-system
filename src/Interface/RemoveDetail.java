package Interface;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

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
 * Admin remove the user details GUI
 * @author Yue Zhang
 * @version 1.0
 */
public class RemoveDetail extends JFrame {
	
	private JLabel noticeLabel1, noticeLabel2;
	private JLabel QMLabel, nameLabel, emailLabel, telLabel, addressLabel;
	private JButton cancelButton, confirmButton;
	private JPanel panel1;
	
	private String QM, name, email, tel, address;
	private String as[], s;
	
	public RemoveDetail(String QM, String name, String email, String tel, String address) {
		super("Detail Information of Removed User");
		
		this.QM = QM;
		this.name = name;
		this.email = email;
		this.tel = tel;
		this.address = address;
		
		List<String> dataList_student=ReadData.importCsv(new File("src/Student.csv"));
		
		Container container = getContentPane();
		container.setLayout(new GridLayout(8, 1));
		
		noticeLabel1 = new JLabel("Please enter the consumer you want to remove");
		noticeLabel2 = new JLabel("Are you sure you want to remove this consumer?");
		
		
		
		QMLabel = new JLabel("QM ID: " + QM);
		nameLabel = new JLabel("Name: " + name);
		emailLabel = new JLabel("Email: " + email);
		telLabel = new JLabel("Tel: " +tel);
		addressLabel = new JLabel("Address: " + address);
		
		cancelButton = new JButton("Cancel");
		confirmButton = new JButton("Confirm");
		
		panel1 = new JPanel(new FlowLayout());
		panel1.add(cancelButton);
		panel1.add(confirmButton);
		
		container.add(noticeLabel1);
		container.add(QMLabel);
		container.add(nameLabel);
		container.add(emailLabel);
		container.add(telLabel);
		container.add(addressLabel);
		container.add(noticeLabel2);
		container.add(panel1);
		
		setResizable(false);	//set frame can not be resize
		setSize(500, 370);	//set frame's size
		setVisible(true);	//display frame
		setLocationRelativeTo(null);	//display in the center of window
		
		actionRemove();
		actionCancel();
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
	
	public void actionRemove() {
		confirmButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				List<String> dataList_student = ReadData.importCsv(new File("src/Student.csv"));
				if(dataList_student!=null && !dataList_student.isEmpty()) {
					for(int i=0; i<dataList_student.size();i++ ) {
						if(i!=0) {
							s=dataList_student.get(i);
							as = s.split(",");
							if(QM.equals(as[0])) {
								dataList_student.remove(i);
								WriteData.exportCsv(new File("src/Student.csv"), dataList_student);
								JOptionPane.showMessageDialog( null, "Success remove a user!");
								break;
							}
						}
					}
				}
				}
			});
	}
}
