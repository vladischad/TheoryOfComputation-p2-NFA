# Project 2: Nondeterministic Finite Automata (NFA)

## Authors
* Vladyslav (Vlad) Maliutin  
* Reggie Wade  

## Description
This project implements a **Nondeterministic Finite Automaton (NFA)** in Java, supporting operations such as state transitions, epsilon closures, and input acceptance checking.

## Implemented Classes
1. **NFA.java**  
   - Implements `NFAInterface`.
   - Provides methods for managing states, transitions, and processing input strings.
   - Implements **Breadth-First Search (BFS)** in `accepts()`.
   - Implements **Depth-First Search (DFS)** in `eClosure()`.

2. **NFAState.java**  
   - Extends `State.java`.
   - Stores transitions in a `Map<Character, Set<NFAState>>`.

## Features Implemented
- Adding states and transitions
- Setting start and final states
- Computing epsilon closures (DFS-based)
- Checking if the NFA is a DFA
- Simulating input string processing (BFS-based)
- Finding the maximum number of NFA copies during processing

## Reflection

Working on this project was a great learning experience. Implementing an NFA helped us understand how nondeterministic automata work and how they differ from DFAs. The most challenging part was handling epsilon transitions correctly and ensuring that the eClosure() function was efficient. Debugging these issues took some time, but it really helped us get a better grasp of graph traversal algorithms like Depth-First Search (DFS) and Breadth-First Search (BFS).  Another issue we ran into was an error when it came to epsilon transitions because we weren't including epsilon in our alphabet by default.  Testing if the NFA was infact a DFA was easy as well, just checking if any epsilon transitions and and multiple transitions off the same symbol wasn't too difficult.

Overall, this project gave us valuable experience with finite automata, state transitions, and Java collections. It reinforced key concepts from class while also improving our problem-solving and debugging skills. Though it was challenging at times, it was satisfying to see everything come together in the end.

## Compilation and Execution Instructions
### **Compiling the Code**
Navigate to the top directory and compile using:
```bash
javac -cp .:/usr/share/java/junit.jar ./test/nfa/NFATest.java
java -cp .:/usr/share/java/junit.jar:/usr/share/java/hamcrest/core.jar
org.junit.runner.JUnitCore test.nfa.NFATest