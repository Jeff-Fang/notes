package myCalendar;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AgendaDao {
	public static void delbycontent(String s){
		try {
			String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
			Class.forName(driver);
			String url = "jdbc:odbc:mydb";
			Connection con = DriverManager.getConnection(url);
			Statement stm = con.createStatement();
			String sql = "DELETE from agenda where whattodo='"+s+"'";
			stm.executeUpdate(sql);
			
			con.close();
		} catch (Exception e) {
			System.out.print(e);
		}
	}

	public static int howmany(){
		int count=0;
		try {
			String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
			Class.forName(driver);
			String url = "jdbc:odbc:mydb";
			Connection con = DriverManager.getConnection(url);
			Statement stm = con.createStatement();
			String sql = "select count(*) from agenda";
			ResultSet rs = stm.executeQuery(sql);
			rs.next();
			count = rs.getInt(1);
			
			con.close();
		} catch (Exception e) {
			System.out.print(e);
		}
		return count;
	}
	

	public static String[][] getDate(){
		int count=howmany();
		String[][] date=new String[count][5];
		try {
			String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
			Class.forName(driver);
			String url = "jdbc:odbc:mydb";
			Connection con = DriverManager.getConnection(url);
			Statement stm = con.createStatement();
			String sql = "select * from agenda";
			ResultSet rs = stm.executeQuery(sql);
			int i = 0;
			while (rs.next()) {
				for (int j = 0; j < 5; j++)
					date[i][j] = rs.getString(j + 1);
				i++;

			}
			con.close();
		} catch (Exception e) {
			System.out.print(e);
		}
		return date;
	}
	public static void toDateBase(int year,int month,int day,int time,String a){
		try {
			String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
			Class.forName(driver);
			String url = "jdbc:odbc:mydb";
			Connection con = DriverManager.getConnection(url);
			Statement stm = con.createStatement();
			String sql = "insert into agenda values('"+year+"','"+month+"','"+day+"','"+time+"','"+a+"')";
			stm.executeUpdate(sql);
			con.close();
		} catch (Exception e) {
			System.out.print(e);
		}
	}
	
}
