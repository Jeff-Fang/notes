package myCalendar;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MyCellRender extends DefaultTableCellRenderer {
	private int r;
	private int c;

	public MyCellRender(int r, int c) {
		this.r = r;
		this.c = c;
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column)

	{

		Component cell = super.getTableCellRendererComponent

		(table, value, isSelected, hasFocus, row, column);

		if (row == r && column == c && cell.isBackgroundSet())// 设置变色的单元格
			// if (row == r && column == c && cell.isBackgroundSet())

			cell.setBackground(Color.GREEN);
		else

			cell.setBackground(Color.WHITE);

		return cell;

	}

}
