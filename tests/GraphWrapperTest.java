package hw4;

import java.util.*;
import java.util.concurrent.*;

import org.junit.BeforeClass;
import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;



public final class GraphWrapperTest{

	private void eq(GraphWrapper thegraph_, String thenodes_, String thechildren_){
		TreeMap<String, List<String>> fakegraph = new TreeMap<String, List<String>>();
		List<String> thenodes = new ArrayList<String>(Arrays.asList(thenodes_.split(" ")));
		List<String> thechildren_init = new ArrayList<String>(Arrays.asList(thechildren_.split(",")));
		List<List<String>> thechildren = new ArrayList<List<String>>();
		System.out.println(" 					new test");
		if (!(thenodes.get(0) == "")){
			for (int i = 0; i < thechildren_init.size(); i++){
				thechildren.add(Arrays.asList(thechildren_init.get(i).split(" ")));
			}

			for (int i = 0; i < thenodes.size(); i++){
					if (i < thechildren.size())
						fakegraph.put(thenodes.get(i), thechildren.get(i));	
					else
						fakegraph.put(thenodes.get(i), new ArrayList<String>());
			}
		}
		
		TreeMap<String, List<String>> thegraph = thegraph_.getAsTreeMap();
		System.out.println("fake: " + fakegraph);
		System.out.println("real: " + thegraph);
		assertEquals(thegraph.toString(), fakegraph.toString());
	}

	@Test
	// Also tests reflexive edges and duplicates of them
	public void testTwoNodeGraphDuplicateEdges(){
		GraphWrapper a = new GraphWrapper();
		a.addNode("d");
		a.addNode("c");
		a.addEdge("d", "d", "");
		a.addEdge("d", "d", "");
		a.addEdge("d", "d", "");
		a.addEdge("d", "c", "");
		a.addEdge("d", "c", "");
		eq(a, "c d", ",c c d d d");
	}
	
	@Test
	public void testListNodes(){
		GraphWrapper a = new GraphWrapper();
		a.addNode("b");
		a.addNode("c");
		a.addNode("a");
		a.addEdge("c", "b", "2");
		a.addEdge("a", "b", "4");
		a.addEdge("a", "c", "4");
		a.addEdge("b", "c", "5");
		a.addEdge("a", "c", "7");
		List<String> b = new ArrayList<String>();
		b.add("a");
		b.add("b");
		b.add("c");
		Iterator it1 = b.iterator();
		Iterator it2 = a.listNodes();
		while (it1.hasNext()){
			assertEquals(it1.next(), it2.next());
		}
	}

	@Test
	public void testListChildrenNoLabels(){
		GraphWrapper a = new GraphWrapper();
		a.addNode("d");
		a.addNode("b");
		a.addNode("c");
		a.addNode("a");
		a.addEdge("a", "b", "");
		a.addEdge("a", "a", "");
		a.addEdge("b", "a", "");
		a.addEdge("b", "c", "");
		List<String> b = new ArrayList<String>();
		b.add("a()");
		b.add("c()");
		Iterator<String> it1 = b.iterator();
		Iterator<String> it2 = a.listChildren("b");
		while (it1.hasNext()){
			assertEquals(it1.next(), it2.next());
		}
	}



}