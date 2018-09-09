package myCalendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarDao {
	public static boolean isLeapYear(int year) {
		return ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0);
	}

	public static int getDaysOfMonth(int year, int month) {
		int days = 31;
		switch (month) {
		case 4:
		case 6:
		case 9:
		case 11:
			days = 30;
			break;
		case 2:
			if (isLeapYear(year))
				days = 29;
			else
				days = 28;
			break;

		}

		return days;
	}

	public static int[] getDays(int year, int month) {
		int days[] = new int[42];
		for (int i = 0; i < days.length; i++) {
			days[i] = 0;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, 1);
		int n = calendar.get(calendar.DAY_OF_WEEK) - 1;// n是指定日期（xx年xx月1号）对应days数组中的下标.day
														// of week永远是7.
		int daysOfMonth = getDaysOfMonth(year, month);
		int j = 1;
		try {
			for (int i = n - 1; i < n + daysOfMonth - 1; i++) {
				days[i] = j;
				j++;
			}
		} catch (Exception ex) {
			for (int i = 6; i < n + daysOfMonth - 1; i++) {
				days[i] = j;
				j++;
			}

		}
		return days;
	}

	public static String[][] transform(int[] x) {
		String[][] y = new String[6][7];
		for (int i = 0; i < 42; i++) {
			y[i / 7][i % 7] = "" + x[i];
			if(x[i]==0)
				y[i / 7][i % 7]=" ";
		}
		return y;
	}

}
