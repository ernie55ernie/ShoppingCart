/**
 * 
 */
package controller.gui;

import javax.swing.JFrame;

import com.mxgraph.swing.mxGraphComponent;
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
	
	
	/**
	 * 
	 */
	public RuleGUI(){
		super("Rule implication");
		
		mxGraph graph = new mxGraph();
		Object parent = graph.getDefaultParent();
		
		graph.getModel().beginUpdate();
		try{
			Object ancedentVertex = graph.insertVertex(parent, null,
					"Ancedent", 20, 20, 80, 30);
			Object consequentVertex = graph.insertVertex(parent, null,
					"Consequent", 20, 100, 80, 30);
			graph.insertEdge(parent, null, "Imply", ancedentVertex, consequentVertex);
		}finally{
			graph.getModel().endUpdate();
		}
		
		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		getContentPane().add(graphComponent);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 320);
		this.setVisible(true);
	}
}
