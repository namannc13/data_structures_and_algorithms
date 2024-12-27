# When to Use Graphs

## On Matrices

Graphs can be used to represent matrices for tasks that involve connectivity, traversal, or mapping relationships between cells or elements.

---

# When to Use BFS

1. **Simultaneous Neighbor Traversal:**
   - BFS is ideal when approaching neighboring nodes simultaneously (e.g., problems like rotten oranges where multiple nodes spread an effect).
2. **Level-Order Traversal:**
   - Use BFS for tasks where a level-wise exploration of nodes is required.
3. **Non-Simultaneous Operations:**
   - BFS can also be used when counting simultaneous operations is not necessary.

---

# When to Use DFS

1. **Non-Simultaneous Operations:**
   - DFS is suitable when we do not need to count simultaneous operations.
2. **Depth-Based Traversal:**
   - Use DFS when you need to explore paths or traverse as deeply as possible before backtracking.

# Tips

1. **In case of matrix, We often need to find the starting point ( could be many ) in many questions from where we will start the DFS or BFS**

# Bipartite Graph

- **Adjacent nodes will not have the same colors**

# Topological Sort - Only for DAG

- A sorting where if there is an edge from A to B, A will appear before B in sorting
- Only applicable to Directed Acyclic Graphs

# When to Use Topological Sort

1. **DAG Problems**: The graph is directed and acyclic.
2. **Dependency Problems**: Tasks, courses, or projects have dependencies.
3. **Linear Ordering**: You need to find a sequence where `u -> v` means `u` comes before `v`.
4. **Cycle Detection in Directed Graphs**: Use topological sort to detect cycles.
5. **"Order" or "Schedule" Keywords**: Valid sequence or schedule required.
6. **Hierarchical Structures**: Dependencies form a hierarchy.

**Key Clues**: Words like "dependencies," "precedence," "ordering," or "sequence."

# Kahn or Topological Sort

### Use **Kahn's Algorithm (BFS)** when:

- Detecting cycles in a directed graph.
- Finding one valid topological order.
- Counting valid topological orders.
- If the graph is cyclic, Kahn can be used to get the nodes which are not involved in the cycle.
- Breadth-first traversal is required.

### Use **DFS-based Topological Sort** when:

- Finding one valid topological order.
- Graph is already in adjacency list form.
- Reverse post-order is needed (e.g., SCC detection).
- Recursive traversal fits the problem.

### Quick Rule:

**Cycle detection or level-order processing → Kahn's Algorithm.**\
**Just need one valid order or SCC → DFS.**

# Djikstra's Algorithm

### **Important Points to remember:**

- **Keywords:** Shortest path, Path with Minimum/Maximum steps/weight/efforts etc.

- It is Used for finding and printing Shortest Paths.

- When the Source and Destination is given.

- Djikstra's work on Directed Cyclic Graph and Undirected Cyclic Graph.

- Any graph with negative weights cannot implement Djikstra's Algorithm.

### **V.Imp Point**

- Keep in mind the **Sorting Criteria for Priority Queue**:
  1. Use the **Main Constraint** as the priority:
     - Example: For "Cheapest Flights Within K Stops," prioritize **steps** over **cost** since the solution must satisfy the "at most K steps" condition.
  2. If the data is already sorted (e.g., steps), a normal queue may suffice, and a priority queue isn't needed.

### **Tips**

- If we are using a normal Queue and know that every entry is going to be in a sorted order in the Queue, we can end the loop when we get the answer

  ```java
  if(p.node == end) return;
  ```

### **Issues Faced**

- `In Some Questions dist[]` Misinterpretation:
  - `dist[]` is supposed to keep track of the minimum steps required to reach a specific node (value). However, the BFS implementation does not always guarantee this property because of the presence of a third variable.
  - A state with fewer steps (`dist[node]`) might actually require more `( third variable value )`, so simply comparing steps is insufficient.

### Solutions

- When the above issue arises:
  - Remove If condition ( check if dist\[node\] already has a lower value ) and traverse through all possibilities
  - Remove dist\[\] array if it is only storing something which increments by constant value like 'steps' and just return 'steps' when the target is found
  - Lose Hope and move on with the day ig
  - Don't use Graph when a third parameter is there
- When in the bfs algorithm , in the for loop when we perform operations or take neighbourhood nodes , if we encounter a variable value which can be used only a limited amount of times, Don't use Graph ( Example: **Minimum Moves to Reach Target Score** )

# Bellman Ford Algorithm

- Only works on **Directed Graphs ( Make Undirected Graphs Directed )**
- Works on **Negative Edge Weights** Unlike Djikstra's and Detects **Negative Cycles**.
- Needs a Source Node from where we will calculate the Shortest Distance.

# Floyd Warshall Algorithm

- Only works on **Directed Graphs ( Make Undirected Graphs Directed )**
- Works on **Negative Edge Weights** Unlike Djikstra's and Detects **Negative Cycles**.
- Calculates the Shortest Distance from Each node to Every node.

### **CASE** - When we need to hold the operation of removing a node from a list or set or wait for the current level to end so that it can be considered in other paths, we can:

- Wait for the Same level to end and then mark them visited or delete them ( depends on the problem )
- Do not remove it till it is the node that comes out of the queue
- Example - PrimsAlgorithm ( We wait and do not mark the nodes as visited till they are the one which appears when polling a queue )

# Prim's Algorithm

- Used to create **Spanning Trees**.

- Almost Djikstras Algorithm is used **except** ( Weight added to queue every iteration is not the combined weight but the individual weight & the node is marked visited only after it itself is polled from queue and not when we add it to the queue so that every node at the same level can take it in it's path )...