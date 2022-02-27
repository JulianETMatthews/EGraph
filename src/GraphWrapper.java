package hw4;

import java.util.*;
import java.util.concurrent.*;



public class GraphWrapper{

	private EGraph g;
	// This is the constructor of GraphWrapper. It initializes the instance field with a new empty instance 
	// of your Graph ADT.
	public GraphWrapper(){
		g = new EGraph();
	}

	// Adds a node represented by the string nodeData to your graph. If an identical node already exists in 
	// the graph, the output of addNode is not defined, that is, it is left at your discretion.
	public void addNode(String nodeData){
		g.addNode(nodeData);
	}

	// Creates an edge from parentNode to childNode with label edgeLabel in your graph. If either of the 
	// nodes does not exist in the graph, the output of this command is not defined. If an identical edge (same parent, child, and label) already exists, the output of this command is not defined either, as it is left at your discretion whether to allow identical edges in your implementation.
	public void addEdge(String parentNode, String childNode, String edgeLabel){
		g.addEdge(parentNode, childNode, edgeLabel);
	}

	// This operation has no effect on your graph. It returns an iterator which represents the nodes in 
	// lexicographical (alphabetical) order.
	// As an example, for the graph shown in Figure 3 above, a call to listNodes() should return an 
	// iterator that goes through the following strings:
	// a
	// b
	// c
	public Iterator<String> listNodes(){
		return g.listNodes();
	}

	// This operation has no effect on your graph. It returns an iterator which represents the 
	// list of childNode(edgeLabel) in lexicographical (alphabetical) order by node name and secondarily by edge label. childNode(edgeLabel) means there is an edge with label edgeLabel from parentNode to childNode. If there are multiple edges from parentNode to some childNode, there should be a separate entry for each edge. If there is a reflexive edge, parentNode(edgeLabel) should be in the list.
	// As an example, for the graph shown in Figure 3 above, a call to listChildren("a") should 
	// return an iterator that goes through the following strings:
	// b(4)
	// c(4)
	// c(7)
	public Iterator<String> listChildren(String parentNode){
		return g.listChildren(parentNode);
	}

	public TreeMap<String, List<String>> getAsTreeMap(){
		return g.getAsTreeMap();
	}

	
}