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
import javax.swing.JTextField;

import Tools.ReadData;

/**
 * Admin remove the user GUI
 * @author Yue Zhang
 * @version 1.0
 */
public class RemoveUser extends JFrame {

	private JLabel noticeLabel;
	private JLabel QMLabel,nameLabel;
	private JTextField QMField, nameField;
	private JButton cancelButton, removeButton;
	private JPanel panel1, panel2, panel3;
	
	private String email, tel, address;
	private String input,s, as[];
	
	public RemoveUser() {
		super("Remove User");
		
		Container container = getContentPane();
		container.setLayout(new GridLayout(4,1));
		
		noticeLabel = new JLabel("Please enter the consumer you want to remove", JLabel.CENTER);
		
		QMLabel = new JLabel("QM ID: ");
		nameLabel = new JLabel("Name: ");
		
		QMField = new JTextField(20);
		nameField = new JTextField(20);
		
		cancelButton = new JButton("Cancel");
		removeButton = new JButton("Remove");
		
		panel1 = new JPanel(new FlowLayout());
		panel2 = new JPanel(new FlowLayout());
		panel3 = new JPanel(new FlowLayout());
		
		panel1.add(QMLabel);		panel1.add(QMField);
		panel2.add(nameLabel);		panel2.add(nameField);
		panel3.add(cancelButton);	panel3.add(removeButton);
		
		container.add(noticeLabel);
		container.add(panel1);
		container.add(panel2);
		container.add(panel3);
		
		setResizable(false);	//set frame can not be resize
		setSize(500, 370);	//set frame's size
		setVisible(true);	//display frame
		setLocationRelativeTo(null);	//display in the center of window
		
		actionCancel();
		actionRemove();
		
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
		removeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String QMID = QMField.getText();
				boolean flag = false;
				String name = nameField.getText();
				List<String> dataList_student = ReadData.importCsv(new File("src/Student.csv"));
				if(dataList_student!=null && !dataList_student.isEmpty()) {
					for(int i=0; i<dataList_student.size(); i++ ) {
						if(i!=0) {
							s=dataList_student.get(i);
							as = s.split(",");
							if(QMID.equals(as[0]) && name.equals(as[1])) {
								flag = true;
								email = as[2];
								tel = as[3];
								address = as[4];
							}
					}
				}
				if(flag) {
					RemoveDetail user = new RemoveDetail(QMID, name, email, tel, address);
					user.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(null,"Invalid ID! Please enter again!");
					QMField.setText("");
					QMField.requestFocus();
				}
			}
				
				}
			});
	}
	
	public static void main(String[] args) {
	RemoveUser user = new RemoveUser();
	user.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
