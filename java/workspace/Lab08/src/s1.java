import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
public class s1 extends JFrame {
	
	s1() {
		
		setTitle("ID/PW manage Program");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout(5, 5));
		
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		
		menuBar.add(fileMenu);
		contentPane.add(menuBar, BorderLayout.NORTH);
		
		JTextArea textArea_1 = new JTextArea("Console Area");
		textArea_1.setLineWrap(true);
		textArea_1.setColumns(26);
		textArea_1.setRows(9);
		
		JTextField textField_1 = new JTextField();
		JTextField textField_2 = new JTextField();
		JTextField textField_3 = new JTextField();

		
		JPanel panel_1 = new JPanel();
		panel_1.add(textArea_1);
		panel_1.setBorder(new TitledBorder(new LineBorder(Color.black,2),"Statement"));
		contentPane.add(panel_1, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new LineBorder(Color.black,2),"Input"));
		panel_2.setLayout(new GridLayout(3,2,15,40));
		panel_2.add(new JLabel("ID :",JLabel.CENTER));
		panel_2.add(textField_1);
		panel_2.add(new JLabel("PW :",JLabel.CENTER));
		panel_2.add(textField_2);
		panel_2.add(new JLabel("COMMENT :",JLabel.CENTER));
		panel_2.add(textField_3);
		contentPane.add(panel_2, BorderLayout.EAST);
		
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(new GridLayout(1,4,15,40));
		panel_3.add(new JButton("Regist"));
		panel_3.add(new JButton("Delete"));
		panel_3.add(new JButton("List"));
		panel_3.add(new JButton("Exit"));
		contentPane.add(panel_3, BorderLayout.SOUTH);
		
		
		
		
		setSize(500, 300);
		setVisible(true);
	}
	public static void main(String[] args) {
		new s1();
	}
}