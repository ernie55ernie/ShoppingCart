/**
 * 
 */
package controller.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import model.rule.Rule;
import model.rule.RuleBase;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import com.mxgraph.view.mxGraph;

/**
 * @author ernie
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
	private RuleBase ruleBase;
	
	/**
	 * 
	 */
	private List<Rule> currentList;
	
	/**
	 * 
	 */
	private DataTable ruleTable;
	
	/**
	 * 
	 */
	private JButton btnImply;
	
	/**
	 * 
	 */
	private JButton btnNotAccept;
	
	/**
	 * 
	 */
	private mxGraph graph;
	
	/**
	 * 
	 */
	private mxIGraphModel model;
	
	/**
	 * 
	 */
	private mxCell antecedentVertex;
	
	/**
	 * 
	 */
	private mxCell consequentVertex;
	
	/**
	 * 
	 */
	private ActionListener buttonListener;
	
	/**
	 * 
	 */
	private String currentImplication;

	/**
	 * @param objectArray
	 */
	public RuleGUI(Object[][] objectArray, RuleBase ruleBase){
		super("Rule implication");
		this.setLayout(new BorderLayout());
		
		this.ruleBase = ruleBase;
		
		initializeGraph();
		
		/*ruleTable = new ruleTable(objectArray, new String[]{"Customer", "Product"});
		JScrollPane jScrollPane = new JScrollPane(ruleTable);
		this.getContentPane().add(jScrollPane, BorderLayout.PAGE_END);*/
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 600);
		this.setVisible(true);
	}
	
	/**
	 * 
	 */
	public void initializeGraph(){

		graph = new mxGraph();
		Object parent = graph.getDefaultParent();
		
		model = graph.getModel();
		model.beginUpdate();
		try{
			antecedentVertex = (mxCell)graph.insertVertex(parent, "Antecedent",
					"Antecedent", 20, 20, 300, 50);
			
			consequentVertex = (mxCell)graph.insertVertex(parent, "Consequent",
					"Consequent", 450, 20, 300, 50);
			graph.insertEdge(parent, "Imply", null, antecedentVertex, consequentVertex);
			graph.setCellsMovable(false);	// Disable the movement of vertices
			graph.setCellsResizable(false);	// Disable the resize of vertices
			graph.setAllowDanglingEdges(false);	// Disable the change of dangling edges
			graph.setEdgeLabelsMovable(false);	// Disable the movement of the label of edges
			graph.getStylesheet().getDefaultEdgeStyle().put(mxConstants.STYLE_NOLABEL, "1");
				// Disable the label of a edge
			
			graph.getSelectionModel().addListener(mxEvent.CHANGE, new RuleEventListener());
		}finally{
			model.endUpdate();
		}
		
		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		graphComponent.setConnectable(false);	// Disable pull edges from vertices
		getContentPane().add(graphComponent, BorderLayout.PAGE_START);
		
		buttonListener = new ButtonListener();
		btnImply = new JButton("Imply");
		btnImply.addActionListener(buttonListener);
		getContentPane().add(btnImply, BorderLayout.LINE_START);
		
		ruleTable = new DataTable(ruleBase.getObjectArray(), new String[]{"Antecedent", "Consequent"});
		JScrollPane jScrollPane = new JScrollPane(ruleTable);
		getContentPane().add(jScrollPane, BorderLayout.CENTER);
		
		btnNotAccept = new JButton("Not accept");
		btnNotAccept.addActionListener(buttonListener);
		getContentPane().add(btnNotAccept, BorderLayout.LINE_END);
	}
/*
	private void loadRuleTable(){
		Object[][] objects = new Object[currentList.size()][2];
		for(int i = 0; i < currentList.size(); i++){
			Rule rule = currentList.get(i);
			objects[i][0] = rule.antecedentString();
			objects[i][1] = rule.consequentString();
		}
		System.out.println(currentList.size());
		ruleTable.setModel(objects, new String[]{"Antecedent", "Consequent"});
	}*/
	
	/**
	 * @author ernie
	 *
	 */
	private class RuleEventListener implements mxIEventListener{
		@Override
		public void invoke(Object arg0, mxEventObject arg1) {
			// System.out.println("Selection invoke");
			// System.out.println(arg1.getProperties());
			try{
				model.beginUpdate();
				Map<String, Object> map = arg1.getProperties();
				for(Entry<String, Object> entry: map.entrySet()){
					@SuppressWarnings("unchecked")
					ArrayList<mxCell> list = (ArrayList<mxCell>)entry.getValue();
					// System.out.println(list.size());
					for(mxCell cell: list){
						// mxCellState cellState = graph.getView().getState(cell);
						switch(cell.getId()){
						case "Antecedent":
							/*// System.out.println("antecedent changed");
							// System.out.println(cell.getValue());
							currentList = ruleBase.getByAntecedent((String)cell.getValue());
							if(currentList.size() == 0){
								graph.getView().getState(consequentVertex).setLabel("No suggestion");
							}else{
								graph.getView().getState(consequentVertex).setLabel(currentList.get(0).consequentString());
							}*/
							currentImplication = "Antecedent";
							break;
						case "Consequent":
							/*currentList = ruleBase.getByConsequent((String)cell.getValue());
							if(currentList.size() == 0){
								graph.getView().getState(antecedentVertex).setLabel("No suggestion");
							}else{
								graph.getView().getState(antecedentVertex).setLabel(currentList.get(0).antecedentString());
							}*/
							currentImplication = "Consequent";
							break;
						}
					}
				}
			}finally{
				model.endUpdate();
			}
			graph.repaint();
		}
	}

	private class ButtonListener implements ActionListener{

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			try{
				model.beginUpdate();
				switch(e.getActionCommand()){
				case "Not accept":
					if(currentList == null) return;
					else if(currentList.size() != 0)currentList.remove(0);
				case "Imply":
					switch(currentImplication){
					case "Antecedent":
						currentList = ruleBase.getByAntecedent((String)antecedentVertex.getValue());
						if(currentList.size() == 0){
							graph.getView().getState(consequentVertex).setLabel("No suggestion");
						}else{
							graph.getView().getState(consequentVertex).setLabel(currentList.get(0).consequentString());
						}
						break;
					case "Consequent":
						currentList = ruleBase.getByConsequent((String)consequentVertex.getValue());
						if(currentList.size() == 0){
							graph.getView().getState(antecedentVertex).setLabel("No suggestion");
						}else{
							graph.getView().getState(antecedentVertex).setLabel(currentList.get(0).antecedentString());
						}
						break;
					}
					// loadRuleTable();
					break;
				}
			}finally{
				model.endUpdate();
			}
			graph.repaint();
		}
		
	}
}
