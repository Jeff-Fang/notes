package myCalendar;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.*;
import java.awt.event.*;
import java.util.Date;

public class MainFrm extends JFrame {
	DefaultTableCellRenderer backGroundColor = new DefaultTableCellRenderer();
	DefaultTableCellRenderer defaultcolor=new DefaultTableCellRenderer();
	JButton today = new JButton("����");
	JButton agenda = new JButton("�ճ�");
	JComboBox monthOfyear = new JComboBox();
	JLabel time = new JLabel("��ѡ�����");
	JButton[] days = new JButton[42];
	JPanel center = new JPanel();
	JTable table = new JTable();
	String[] cols = { "����һ", "���ڶ�", "������", "������", "������", "������", "������" };
	JButton add = new JButton("��ӱ���");
	JTextField agendafield = new JTextField(50);
	TableColumn column = null;
	JLabel reminder =new JLabel("�ڴ˽���ʾ�����������ճ�");

	public void inittable(String[][] x,int year,int month) {
		table.setModel(new DefaultTableModel(x, cols));
		int n=AgendaDao.howmany();
		int[] days;
		days=CalendarDao.getDays(year, month);
		String[][] date=new String[n][5];
		date=AgendaDao.getDate();
		for(int i=0;i<n;i++){
			if(year==Integer.parseInt(date[i][0])&&month==Integer.parseInt(date[i][1])){
				int day=Integer.parseInt(date[i][2]);int r=0; int c=0;
				for(int a=0;a<days.length;a++)
						if(day==days[a]){
							r=a/7;
							c=a%7;
						}
				MyCellRender cr=new MyCellRender(r,c);
				// �����б�����------------------------//

				for (int j = 0; j < cols.length; j++)

				{

					table.getColumn(cols[j]).setCellRenderer(cr);

				}
			}
		}

	}

	public MainFrm() {
		Timer t=new Timer();
		t.start();
		if(t.getLatestAgenda()[0]<=12)
		   reminder.setText("�ճ�����������������Ϊ"+t.getLatestAgenda()[0]+"��"+t.getLatestAgenda()[1]+"��");
		backGroundColor.setBackground(Color.yellow);
		time.setText("�����뱸������");
		JPanel jp = (JPanel) this.getContentPane();
		jp.setLayout(new BorderLayout());
		JPanel up = new JPanel();

		JPanel upGrid = new JPanel();
		upGrid.setLayout(new GridLayout(1, 7));
		center.setLayout(new BorderLayout());
		up.setLayout(new BorderLayout());
		up.add(upGrid, BorderLayout.SOUTH);
		jp.add(up, BorderLayout.NORTH);
		jp.add(center, BorderLayout.CENTER);
		jp.add(reminder,BorderLayout.SOUTH);
		up.add(today, BorderLayout.WEST);
		up.add(agenda, BorderLayout.EAST);
		up.add(monthOfyear, BorderLayout.CENTER);
		center.add(add, BorderLayout.SOUTH);
		table.setCellSelectionEnabled(true);
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int yy = 0;
				int mm = 0;
				int dd = 0;
				int tt = 0;
				String a = "";
				yy = monthOfyear.getSelectedIndex() / 12 + 1900;
				mm = monthOfyear.getSelectedIndex() % 12 + 1;
				dd = Integer.parseInt((String) table.getValueAt(
						table.getSelectedRow(), table.getSelectedColumn()));
				a = agendafield.getText();
				try{AgendaDao.toDateBase(yy, mm, dd, tt, a);}catch(Exception ex){
					JOptionPane.showMessageDialog(null, "����Ҫ���뱸������");
				}
				Timer t=new Timer();
				reminder.setText("�ճ�����������������Ϊ"+t.getLatestAgenda()[0]+"��"+t.getLatestAgenda()[1]+"��");
				int r=table.getSelectedRow();int c=table.getSelectedColumn();
				MyCellRender cr=new MyCellRender(r,c);
				// �����б�����------------------------//

				for (int i = 0; i < cols.length; i++)

				{

					table.getColumn(cols[i]).setCellRenderer(cr);

				}
				  
				JOptionPane.showMessageDialog(null, "��ӱ���¼�ɹ�");

				

			}

		});
		JScrollPane jsp = new JScrollPane(table);
		jsp.setPreferredSize(new Dimension(300, 120));
		center.add(jsp, BorderLayout.NORTH);
		center.add(agendafield, BorderLayout.WEST);
		time.setHorizontalAlignment(SwingConstants.CENTER);
		up.add(time, BorderLayout.NORTH);
		agenda.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Agenda ag = new Agenda();

			}

		});
		today.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Date dt = new Date();
				time.setText("" + dt);
				int year = 1900 + dt.getYear();
				int month = dt.getMonth() + 1;
				inittable(CalendarDao.transform(CalendarDao
						.getDays(year, month)),year,month);
				int x = (year - 1900) * 12 + month - 1;
				monthOfyear.setSelectedIndex(x);
			}

		});
		for (int i = 1900; i < 2200; i++)
			for (int j = 1; j <= 12; j++) {
				monthOfyear.addItem(i + "��" + " " + j + "��");
			}
		monthOfyear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int index = monthOfyear.getSelectedIndex();
				int month = index % 12 + 1;
				int year = index / 12 + 1900;
				int[] daysint;
				daysint = CalendarDao.getDays(year, month);
				String[][] y;
				y = CalendarDao.transform(daysint);
				inittable(y,year,month);
				month = 0;
				year = 0;
			}

		});
		this.setTitle("���������ճ����ѹ���");
		this.setSize(500, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
