package myCalendar;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
public class Agenda extends JFrame{
	JButton check=new JButton("��ת�鿴");
	JButton del=new JButton("ɾ���ճ�");
	JButton close=new JButton("�ر�");
	String[] cols={"��","��","��","ʱ��","����"};
	JTable agendaTable=null;
	public void initAgenda(String[][] date){
		agendaTable.setModel(new DefaultTableModel(date, cols));
		
	}
	public String getContentBySelectedRow(int i){
		return ""+agendaTable.getValueAt(i, 4);
	}

	public Agenda(){
		JPanel jp=(JPanel)this.getContentPane();
		JPanel jp1=new JPanel();
		jp.setLayout(new BorderLayout());
		jp.add(jp1,BorderLayout.SOUTH);
		jp1.add(check);
		jp1.add(del);
		jp1.add(close);
		check.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
			
		});
		close.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
			
		});
		check.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				initAgenda(AgendaDao.getDate());
			}
			
		});
		del.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String s=null;
				int i=agendaTable.getSelectedRow();
				s=getContentBySelectedRow(i);
				AgendaDao.delbycontent(s);
				JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
				initAgenda(AgendaDao.getDate());
			}
			
		});
	
		String[][] date=AgendaDao.getDate();
		agendaTable=new JTable(date,cols);
		jp.add(new JScrollPane(agendaTable),BorderLayout.CENTER);
		this.setSize(500,300);
		this.setVisible(true);
	}

}
