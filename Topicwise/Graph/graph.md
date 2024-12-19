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

- When the Source and Destination is given

- Any graph with negative weights cannot implement Djikstra's Algorithm.

### **V.Imp Point**

- Keep in mind the **Sorting Criteria for Priority Queue**:
  1. Use the **Main Constraint** as the priority:
     - Example: For "Cheapest Flights Within K Stops," prioritize **steps** over **cost** since the solution must satisfy the "at most K steps" condition.
  2. If the data is already sorted (e.g., steps), a normal queue may suffice, and a priority queue isn't needed.