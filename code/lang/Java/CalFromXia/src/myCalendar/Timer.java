package myCalendar;

import java.util.Date;

import javax.swing.JOptionPane;

public class Timer extends Thread{
	public Timer(){
		
	}
	public int[] getLatestAgenda(){
		int[] time=new int[3];
		Date now = new Date();
		int currentMonth=now.getMonth()+1;
		int currentDay=now.getDate();
		String[][] date=AgendaDao.getDate();
		int latestMonth=0;
		int latestDay=0;
		int minm=12;
		int mind=32;
		for(int i=0;i<date.length;i++)
			if((Integer.parseInt(date[i][1])-currentMonth)<minm){
				minm=(Integer.parseInt(date[i][1])-currentMonth);
			}
		for(int i=0;i<date.length;i++)
			if(Integer.parseInt(date[i][1])==(minm+currentMonth)){
				if((Integer.parseInt(date[i][2])-currentDay)<mind){
					mind=(Integer.parseInt(date[i][2])-currentDay);
				}
			}
		
		time[0]=minm+currentMonth;
		time[1]=mind+currentDay;
		return time;
	}

	public void run(){
		while(true){
			Date now=new Date();
			
			if((getLatestAgenda()[0]==now.getMonth()+1)&&(getLatestAgenda()[1]==now.getDate())){
				String show="您今天有日程安排";
				JOptionPane.showMessageDialog(null, show);
			}

            

            try {

                   sleep(100000);

            } catch (InterruptedException e) {

                   e.printStackTrace();

            }

		}
	}

}
