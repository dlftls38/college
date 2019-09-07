import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.border.*;

import java.io.*;
import java.util.*;

public class s1  extends JFrame implements ActionListener{
	
	Container contentPane = getContentPane();
	JMenuBar menuBar = new JMenuBar();
	JMenu fileMenu = new JMenu("File");
	JMenuItem open = new JMenuItem("open");
	JMenuItem save = new JMenuItem("save");
	JTextArea textArea_1 = new JTextArea();
	JScrollPane scrollPane = new JScrollPane(textArea_1);
	JTextField textField_1 = new JTextField();
	JTextField textField_2 = new JTextField();
	JTextField textField_3 = new JTextField();
	JPanel panel_1 = new JPanel();
	JPanel panel_2 = new JPanel();
	JPanel panel_3 = new JPanel();
	JButton Regist = new JButton("Regist");
	JButton Delete = new JButton("Delete");
	JButton List = new JButton("List");
	JButton Exit = new JButton("Exit");
	JFileChooser chooser = new JFileChooser();
	s3 st = new s3();
	
	s1() {
		
		setTitle("ID/PW manage Program");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane.setLayout(new BorderLayout(5, 5));
		
		menuBar.add(fileMenu);
		
		open.addActionListener(this);
		save.addActionListener(this);
		
        fileMenu.add(open);
        fileMenu.add(save);
        
		
		contentPane.add(menuBar, BorderLayout.NORTH);
		
		textArea_1.setLineWrap(true);
		textArea_1.setColumns(26);
		textArea_1.setRows(9);
		

		
		
		panel_1.add(scrollPane);
		panel_1.setBorder(new TitledBorder(new LineBorder(Color.black,2),"Statement"));
		contentPane.add(panel_1, BorderLayout.CENTER);
		
		panel_2.setBorder(new TitledBorder(new LineBorder(Color.black,2),"Input"));
		panel_2.setLayout(new GridLayout(3,2,15,40));
		panel_2.add(new JLabel("ID :",JLabel.CENTER));
		panel_2.add(textField_1);
		panel_2.add(new JLabel("PW :",JLabel.CENTER));
		panel_2.add(textField_2);
		panel_2.add(new JLabel("COMMENT :",JLabel.CENTER));
		panel_2.add(textField_3);
		contentPane.add(panel_2, BorderLayout.EAST);
		
		
		Regist.addActionListener(this);
		Delete.addActionListener(this);
		List.addActionListener(this);
		Exit.addActionListener(this);
		
		panel_3.setLayout(new GridLayout(1,4,15,40));
		panel_3.add(Regist);
		panel_3.add(Delete);
		panel_3.add(List);
		panel_3.add(Exit);
		contentPane.add(panel_3, BorderLayout.SOUTH);
		
		
		
		
		setSize(500, 300);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==open)
        {
            int result = chooser.showOpenDialog(this);
            if( result == JFileChooser.APPROVE_OPTION)
            {
                //열기 버튼을 누르면
                File file = chooser.getSelectedFile();
                st.open(file);
    			textArea_1.append("파일의 정보를 받아왔습니다.\n\n");
    			textArea_1.setCaretPosition(textArea_1.getDocument().getLength());
            }
            
        }
        else if(e.getSource()==save)
        {
            int result = chooser.showSaveDialog(this);
            if( result == JFileChooser.APPROVE_OPTION)
            {
                //취소 버튼을 누르면
                File file = chooser.getSelectedFile();
                st.save(file);
    			textArea_1.append("파일에 정보를 저장했습니다.\n\n");
    			textArea_1.setCaretPosition(textArea_1.getDocument().getLength());
            }
        }
        else if(e.getSource()==Regist){
        	
	    	st.write(textField_1.getText(),1);
	    	st.write(textField_2.getText(),2);
	    	st.write(textField_3.getText(),3);
	    	textField_1.setText("");
	    	textField_2.setText("");
	    	textField_3.setText("");
			textArea_1.append("새로운 정보가 등록되었습니다.\n\n");
			textArea_1.setCaretPosition(textArea_1.getDocument().getLength());
        }
        else if(e.getSource()==Delete){
        	st.Delete(textField_3.getText());
        	textField_3.setText("");
			textArea_1.append("정보가 삭제되었습니다.\n\n");
			textArea_1.setCaretPosition(textArea_1.getDocument().getLength());
        }
        else if(e.getSource()==List){
    		if(st.list.size()==0){
    			textArea_1.append("등록된 정보가 없습니다.\n\n");
    			textArea_1.setCaretPosition(textArea_1.getDocument().getLength());
    		}
    		else{
    			textArea_1.append("번호  ID            passward  comment\n");
    			for(int i=0; i<st.list.size(); i++){
    				textArea_1.append((i+1) + "번   " + st.list.get(i).id + "     " + st.list.get(i).psw + "        " + st.list.get(i).com + "\n");
    				textArea_1.setCaretPosition(textArea_1.getDocument().getLength());  // 맨아래로 스크롤한다.
    			}
				textArea_1.append("\n");
				textArea_1.setCaretPosition(textArea_1.getDocument().getLength());  // 맨아래로 스크롤한다.
    		}
        }
        else if(e.getSource()==Exit){
        	System.exit(0);
        }
    }
	
	public static void main(String[] args) {
		new s1();
	}
}