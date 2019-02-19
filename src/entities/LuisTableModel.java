package entities;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class LuisTableModel extends AbstractTableModel {
	/*private String[] columnNames = {"First Name",
             "Last Name",
             "Sport",
             "# of Years",
             "Vegetarian"};
private Object[][] data = {
{"Kathy", "Smith",
"Snowboarding",8, new Boolean(false)},
{"John", "Doe",
"Rowing", new Integer(3), new Boolean(true)},
{"Sue", "Black",
"Knitting", new Integer(2), new Boolean(false)},
{"Jane", "White",
"Speed reading", new Integer(20), new Boolean(true)},
{"Joe", "Brown",
"Pool", new Integer(10), new Boolean(false)}
};
public LuisTableModel(ResultSet rs) {
	//Aqui rellenamos el metodo
	try {
	    ResultSetMetaData	rsmd=(ResultSetMetaData) rs.getMetaData();

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}*/
	private String[] columnNames;
	private Object[][] data;

	public LuisTableModel(String[] columnNames,Object[][] data) {
		this.columnNames=columnNames;
		
		this.data=data;
	}
	
	private Object[][] setData(Object[][] data) {
		
	
		
		return data;
	}
	
	public LuisTableModel() {

	}
	public int getColumnCount() {
		return columnNames.length;
	}



	public int getRowCount() {
		return data.length;
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

	/*
	 * JTable uses this method to determine the default renderer/
	 * editor for each cell.  If we didn't implement this method,
	 * then the last column would contain text ("true"/"false"),
	 * rather than a check box.
	 */
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	/*
	 * Don't need to implement this method unless your table's
	 * editable.
	 */
	public boolean isCellEditable(int row, int col) {
		//Note that the data/cell address is constant,
		//no matter where the cell appears onscreen.
		if(col==columnNames.length-1) {
			return true;
		}
		return false;

	}

	/*
	 * Don't need to implement this method unless your table's
	 * data can change.
	 */
	public void setValueAt(Object value, int row, int col) {
		data[row][col] = value;
		fireTableCellUpdated(row, col);
	}

}
