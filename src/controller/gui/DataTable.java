/**
 * 
 */
package controller.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 * This class initialize a table that can show the shopping list
 * in a more structural way. Sorting can be done while clicking 
 * on the column above. 
 * 
 * @author ernie
 *
 */
public class DataTable extends JPanel{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private JTable table;
	
	/**
	 * Constructor for the table must take an argument of data.
	 * @param objects The constructor takes an two dimension array
	 * of {@line Object}. Initialize the content of the table.
	 */
	public DataTable(Object[][] objects, String[] columns){
		TableModel tableModel = new DefaultTableModel(objects, columns){
			private static final long serialVersionUID = 1L;

			public Class<?> getColumnClass(int column) {
		        Class<?> returnValue;
		        if ((column >= 0) && (column < getColumnCount())) {
		          returnValue = getValueAt(0, column).getClass();
		        } else {
		          returnValue = Object.class;
		        }
		        return returnValue;
		      }
		};
		table = new JTable(tableModel);
		// table.setSize(800, 200);
		RowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(tableModel);
		table.setRowSorter(rowSorter);
		JScrollPane jScrollPane = new JScrollPane(table);
		// jScrollPane.setSize(800, 200);
		add(jScrollPane);
	    // setSize(800, 200);
		// System.out.println(this.getWidth() + ", " + this.getHeight());
	}
	/*
	public void setModel(Object[][] objects, String[] columns){
		TableModel tableModel = new DefaultTableModel(objects, columns){
			private static final long serialVersionUID = 1L;
	
			public Class<?> getColumnClass(int column) {
		        Class<?> returnValue;
		        if ((column >= 0) && (column < getColumnCount())) {
		          returnValue = getValueAt(0, column).getClass();
		        } else {
		          returnValue = Object.class;
		        }
		        return returnValue;
		      }
		};
		table.setModel(tableModel);
		table.repaint();
		System.out.println(table.getRowCount());
	}*/
}
