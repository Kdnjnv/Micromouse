import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MouseClass {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MouseClass window = new MouseClass();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MouseClass() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(500, 200, 300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblRow = new JLabel("row");
		lblRow.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblRow.setBounds(26, 39, 73, 35);
		frame.getContentPane().add(lblRow);
		
		JLabel lblColumn = new JLabel("column");
		lblColumn.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblColumn.setBounds(26, 100, 91, 19);
		frame.getContentPane().add(lblColumn);
		
		textField = new JTextField();
		textField.setBounds(129, 36, 116, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(129, 97, 116, 22);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnClickMe = new JButton("GO");
		btnClickMe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
		    		int a=Integer.parseInt(textField.getText());
		    		int b=Integer.parseInt(textField_1.getText());
		    		Grid.main(a,b);
		    		}catch(Exception e){
		    			JOptionPane.showMessageDialog(null, "Enter  valid rows and columns");
		    		}
			}
		});
		btnClickMe.setBounds(73, 156, 97, 25);
		frame.getContentPane().add(btnClickMe);
	}
}
