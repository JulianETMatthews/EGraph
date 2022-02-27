package hw4;

import java.util.*;
import java.util.concurrent.*;

import org.junit.BeforeClass;
import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;



public final class EGraphTest {
	// Creates map graph from string parameters, compares to input EGraph
	// Examples
	// thenodes: "a b g" -> [a,b,g]
	// thechildren: "a g,a b,g g g a" -> [[a,g],[a,b],[g,g,g,a]]
	// thelabels  :  "1 3, 5,3 6 7" ->  [[1,3],[,5],[3,6,7]]
	private void eq(EGraph thegraph_, String thenodes_, String thechildren_){
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

	private void eqLabels(EGraph thelabels_, String thenodes_, String thechildren_){
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
		
		TreeMap<String, List<String>> thegraph = thelabels_.getLabelsAsTreeMap();
		System.out.println("fake: " + fakegraph);
		System.out.println("real: " + thegraph);
		assertEquals(thegraph.toString(), fakegraph.toString());
	}


	@Test
	public void testNoNodeGraph(){
		EGraph a = new EGraph();
		eq(a, "", "");
	}

	@Test
	public void testOneNodeGraph(){
		EGraph a = new EGraph();
		a.addNode("a");
		eq(a, "a", "");
	}

	@Test
	public void testTwoNodeGraph(){
		EGraph a = new EGraph();
		a.addNode("a");
		a.addNode("b");
		a.addEdge("a", "b", "");
		eq(a, "a b", "b,");
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testAddEdgeToNonexistantNode(){
		EGraph a = new EGraph();
		a.addNode("a");
		a.addNode("b");
		a.addEdge("h", "b", "");;
	}

	@Test
	public void testTwoNodeGraphLabel(){
		EGraph a = new EGraph();
		a.addNode("d");
		a.addNode("c");
		a.addEdge("d", "d", "3");
		eq(a, "c d", ",d");
		eqLabels(a, "c d", ",3");
	}

	@Test
	public void testTwoNodeGraphNoEdge(){
		EGraph a = new EGraph();
		a.addNode("d");
		a.addNode("c");
		eq(a, "c d", ",");
		eqLabels(a, "c d", ",");
	}

	@Test
	// Also tests reflexive edges and duplicates of them
	public void testTwoNodeGraphDuplicateEdges(){
		EGraph a = new EGraph();
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
	public void testComplexGraph1(){
		EGraph a = new EGraph();
		a.addNode("d");
		a.addNode("b");
		a.addNode("c");
		a.addNode("a");
		a.addEdge("a", "b", "");
		a.addEdge("a", "a", "");
		a.addEdge("b", "a", "");
		a.addEdge("b", "c", "");
		eq(a, "a b c d", "a b,a c,,");
	}

	@Test
	public void testComplexGraph2(){
		EGraph a = new EGraph();
		a.addNode("b");
		a.addNode("c");
		a.addNode("a");
		a.addEdge("c", "b", "2");
		a.addEdge("a", "b", "4");
		a.addEdge("a", "c", "4");
		a.addEdge("b", "c", "5");
		a.addEdge("a", "c", "7");
		eq(a, "a b c", "b c c,c,b");
		eqLabels(a, "a b c", "4 4 7,5,2");
	}

	@Test
	public void testListNodes(){
		EGraph a = new EGraph();
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
		EGraph a = new EGraph();
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

	@Test
	public void testListChildrenLabels(){
		EGraph a = new EGraph();
		a.addNode("d");
		a.addNode("b");
		a.addNode("c");
		a.addNode("a");
		a.addEdge("a", "b", "7");
		a.addEdge("a", "a", "3");

		a.addEdge("b", "a", "65");
		a.addEdge("b", "d", "8");
		a.addEdge("b", "b", "92");
		a.addEdge("b", "b", "92");
		a.addEdge("b", "b", "91");
		a.addEdge("b", "c", "1");
		List<String> b = new ArrayList<String>();
		b.add("a(65)");
		b.add("b(91)");
		b.add("b(92)");
		b.add("b(92)");
		b.add("c(1)");
		b.add("d(8)");
		Iterator<String> it1 = b.iterator();
		Iterator<String> it2 = a.listChildren("b");
		while (it1.hasNext()){
			assertEquals(it1.next(), it2.next());
		}
	}

}






















