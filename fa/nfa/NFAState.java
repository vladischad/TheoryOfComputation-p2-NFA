package fa.nfa;

import fa.State;
import java.util.*;

/**
 * NFAState represents a state in a NFA and manages its transitions.
 * Each state maintains a mapping of input symbols to next states.
 * 
 */
public class NFAState extends State {
    private Map<Character, Set<NFAState>> transitions;

    /* 
     * Call super constructor to 
     * assign the state a name.
     * Creates a hashmap to store
     * transitions to other states.
     */
    public NFAState(String name) {
        super(name);
        this.transitions = new HashMap<>();
    }

    /**
     * Adds the transition to the NFA's delta data structure
     * @param symbol is the symbol to transition to toState
     * @param toState is the label of the state where the transition ends
     */
    public void addTransition(char symbol, NFAState toState) {
        transitions.putIfAbsent(symbol, new HashSet<>());
        transitions.get(symbol).add(toState);
    }

    /**
     * Gets the next states based on the transition symbol
     * @param symbol is the symbol to transition to toState
     * @return the next states from the symbol (empty set if non existent)
     */
    public Set<NFAState> getNextStates(char symbol) {
        return transitions.getOrDefault(symbol, Collections.emptySet());
    }
}