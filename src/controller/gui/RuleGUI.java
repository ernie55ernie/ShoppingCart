/**
 * 
 */
package controller.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;

/**
 * @author user
 *
 */
public class RuleGUI extends JFrame{

	/*
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel dataTable;
	
	/**
	 * @param objectArray
	 */
	public RuleGUI(Object[][] objectArray){
		super("Rule implication");
		
		mxGraph graph = new mxGraph(){
			@Override
			public boolean isCellSelectable(Object o){
				if(model.isEdge(o)) return false;
				return true;
			}
		};
		Object parent = graph.getDefaultParent();
		
		graph.getModel().beginUpdate();
		try{
			Object ancedentVertex = graph.insertVertex(parent, "Antecedent",
					"Ancedent", 20, 20, 150, 30);
			Object consequentVertex = graph.insertVertex(parent, "Consequent",
					"Consequent", 300, 20, 150, 30);
			graph.insertEdge(parent, "Imply", null, ancedentVertex, consequentVertex);
			graph.setCellsMovable(false);	// Disable the movement of vertices
			graph.setCellsResizable(false);	// Disable the resize of vertices
			graph.setAllowDanglingEdges(false);	// Disable the change of dangling edges
			graph.setEdgeLabelsMovable(false);	// Disable the movement of the label of edges
			graph.getStylesheet().getDefaultEdgeStyle().put(mxConstants.STYLE_NOLABEL, "1");
				// Disable the label of a edge
		}finally{
			graph.getModel().endUpdate();
		}
		
		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		graphComponent.setConnectable(false);	// Disable pull edges from vertices
		getContentPane().add(graphComponent, BorderLayout.NORTH);
		
		dataTable = new DataTable(objectArray);
		this.getContentPane().add(dataTable, BorderLayout.SOUTH);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 600);
		this.setVisible(true);
	}
}
