package hw4;

import java.util.*;
import java.util.concurrent.*;


/** <b>EGraph<b> represents a mutable multi, labeled graph.
<p>EGraphs are stored as a collection of strings, each of which represents a node, 
and corresponding strings of lists each of which represent that node's children. <p>
For example, the nodes might be ["a","b","g"] with children [["a","g"],["a","b"],["g","g","g","a"]].*/ 
/* 
Abstraction Function:
EGraph g represents the graph constructed by the nodes [n0, n1, ... , nx] where 0 through x are arbitrary
but different string node names, and the children of each corresponding node 
[[n, n, ...], [n, n], ... [n, n]] where each n is any node in the graph from 0 to x.
Additionally, all the children of each node have corresponding labels 
[[l, l, ...], [l, l], ... [l, l]] where each l is an arbitrary different or same string label. 

The nodes are stored identically in the two TreeMap<String, List<String>>'s egraph and labels. 
In both, the node names are stored as the key strings. In egraph, the children names are stored in the 
corresponding List<String>, and in labels the node parent to that child's edge labels
are stored in the corresponding List<String>.

For instance:
// egraph:
// key		value
// a 		b, c, c
// b 		b
// c 		
// d 		a, c
// 
// labels:
// key		value
// a 	    1, 4, 2	
// b 		8
// c
// d 		2, 3

Representation Invariant for every EGraph g:
egraph.keySet == labels.keySet
for each string i in egraph.keySet
	there is no other i in egraph.keySet
	there is only one matching i in labels.keySet
for each string j in egraph.values
	there is exactly one matching j in egraph.keySet


*/
public class EGraph {

	private TreeMap<String, List<String>> egraph;
	private TreeMap<String, List<String>> labels;
	/** @effects Constructs a new empty EGraph */
	public EGraph(){
		egraph = new TreeMap<String, List<String>>();
		labels = new TreeMap<String, List<String>>();
		checkRep();
	}

	public static final boolean DOCHECKREP = false;

	/**
	 * @param nodeData The string name of the node to be added
	 * @effects Adds a node to the graph with empty array for children
	 * @modifies egraph, labels
	 * @throws UnsupportedOperationException if string name includes comma
	 */
	public void addNode(String nodeData){
		if (nodeData.contains(",") || nodeData.contains(" ")){
			throw new UnsupportedOperationException("cannot use a comma as node name");
		}
		if (egraph.keySet().contains(nodeData) || labels.keySet().contains(nodeData))
			return;
		egraph.put(nodeData, new ArrayList<String>());
		labels.put(nodeData, new ArrayList<String>());
		checkRep();
	}

	/**
	 * @param parentNode The name of the parent node from which the edge will be created
	 * @param childNode The name of the child node to which the edge will be created
	 * @param edgeLabel The label of the edge, leave blank string for no label
	 * @effects Creates a new edge between parent and child node
	 * @modifies egraph, labels
	 * @throws IndexOutOfBoundsException if either of the input nodes doesn't exist in the graph
	 */
	public void addEdge(String parentNode, String childNode, String edgeLabel){
		if (!(egraph.keySet().contains(childNode)) || !(egraph.keySet().contains(parentNode)))
			throw new IndexOutOfBoundsException("No such node exists");
		egraph.get(parentNode).add(childNode);
		labels.get(parentNode).add(edgeLabel);
		checkRep();
	}

	public void removeEdge(String parentNode, String childNode){
		if (!(egraph.keySet().contains(childNode)) || !(egraph.keySet().contains(parentNode)))
			throw new IndexOutOfBoundsException("No such node exists");
		egraph.get(parentNode).add(childNode);
		labels.get(parentNode).add(edgeLabel);
		checkRep();
	}

	/**
	 * @return iterator to all the nodes string names in alphabetical order
	 */
	public Iterator<String> listNodes(){
		TreeMap<String, List<String>> cloned = new TreeMap<String, List<String>>(egraph);
		return cloned.keySet().iterator();
	}

	/**
	 * @param parentNode The name of the parent node from which the edge exists
	 * @param childNode The name of the child node to which the edge exists
	 * @return the label of that edge
	 */
	public String getLabel(String parentNode, String chidlNode){
		int i = egraph.get(parentNode).indexOf(childNode);
		return labels.get(parentNode).get(i);
	}

	/**
	 * @param parentNode The name of the parent node whos children will be iterated over
	 * @return an iterator to the children of the node, formated as childnodename(labelname) and 
	 * sorted by alphabetical
	 */
	public Iterator<String> listChildren(String parentNode){
		if (!(egraph.keySet().contains(parentNode)))
			return new ArrayList<String>().iterator();
		List<String> c = new ArrayList<String>(egraph.get(parentNode));
		List<String> clabels = new ArrayList<String>(labels.get(parentNode));
		for (int i = 0; i < c.size(); i++){
			c.set(i, c.get(i) + "(" + clabels.get(i) + ")");
		}
		Collections.sort(c);
		return c.iterator();
	}

	/**
	 * @return the graph as a treemap copy
	 */
	public TreeMap<String, List<String>> getAsTreeMap(){
		for (String s : egraph.keySet()){
			Collections.sort(egraph.get(s));
		}
		return (TreeMap<String, List<String>>)egraph.clone();
	}

	/**
	 * @return the graph edge labels as a treemap copy
	 */
	public TreeMap<String, List<String>> getLabelsAsTreeMap(){
		return (TreeMap<String, List<String>>)labels.clone();
	}
/*
note - no duplicate keys is already gauranteed by using TreeMap
*/
	private void checkRep() throws RuntimeException {
		if (!DOCHECKREP)
			return;
		if (!(egraph.keySet().equals(labels.keySet()))){
			System.out.println("egraph keySet: "+egraph.keySet()+".");
			System.out.println("labels keySet: "+labels.keySet()+".");
			throw new RuntimeException("egraph.keySet() != labels.keySet()");
		}
		for (String i : egraph.keySet()){
			List<String> l1 = new ArrayList<String>(egraph.get(i));
			for (String j : l1)
				if (!egraph.containsKey(j)) throw new RuntimeException("child is not a node in the graph");
		}
	}
}























