/**
 * 
 */
package controller.console;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 * @author ernie
 *
 */
public class DataTable {

	public DataTable(Object[][] objects){
		JFrame frame = new JFrame("ShoppingCart enumeration");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		String[] columns = new String[]{"Customer", "Product"};
		
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
		JTable table = new JTable(tableModel);
		RowSorter<TableModel> rowSorter = new TableRowSorter<TableModel>(tableModel);
		table.setRowSorter(rowSorter);
		JScrollPane jScrollPane = new JScrollPane(table);
		frame.add(jScrollPane, BorderLayout.CENTER);
	    frame.setSize(500, 300);
	    frame.setVisible(true);
	}
}
