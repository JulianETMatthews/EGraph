# EGraph
EGraph represents a mutable multi, labeled graph.

EGraphs are stored as a collection of strings, each of which represents a node, and corresponding strings of lists each of which represent that node's children. 

For example, the nodes might be ["a","b","g"] with children [["a","g"],["a","b"],["g","g","g","a"]].


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