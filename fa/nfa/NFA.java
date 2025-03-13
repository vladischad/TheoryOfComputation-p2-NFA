package fa.nfa;

import java.util.*;

/**
 * This class implements a Non-deterministic Finite Automaton (NFA),
 * supporting operations such as state transitions, epsilon closures,
 * and input acceptance checking.
 * 
 * @author  Vladyslav (Vlad) Maliutin
 *          Reggie Wade
 */
public class NFA implements NFAInterface {

    @Override
    public boolean isDFA() {
        return false;
    }

    private Set<Character> sigma;   // Alphabet
    private Map<String, NFAState> states; // Mapping of state names to state objects
    private NFAState startState; // Start state of the NFA
    private Set<NFAState> finalStates; // Set of final (accepting) states

    /**
     * Constructs an empty NFA with an empty alphabet and no states.
     */
    public NFA() {
        sigma = new LinkedHashSet<>();
        sigma.add('e'); // Add epsilon transition symbol by default
        states = new LinkedHashMap<>();
        startState = null;
        finalStates = new LinkedHashSet<>();
    }

    /**
     * Adds a new state to the NFA.
     * @param name The name of the state.
     * @return True if the state was successfully added, false if it already exists.
     */
    @Override
    public boolean addState(String name) {
        if (states.containsKey(name)) return false;
        states.put(name, new NFAState(name));
        return true;
    }

    /**
     * Marks a state as a final (accepting) state.
     * @param name The name of the state.
     * @return True if successful, false if the state does not exist.
     */
    @Override
    public boolean setFinal(String name) {
        NFAState state = states.get(name);
        if (state == null) return false;
        finalStates.add(state);
        return true;
    }

    /**
     * Sets the start state of the NFA.
     * @param name The name of the state.
     * @return True if successful, false if the state does not exist.
     */
    @Override
    public boolean setStart(String name) {
        NFAState state = states.get(name);
        if (state == null) return false;
        startState = state;
        return true;
    }

    /**
     * Adds a symbol to the alphabet of the NFA.
     * @param symbol The symbol to add.
     */
    @Override
    public void addSigma(char symbol) {
        sigma.add(symbol);
    }

    /**
     * Returns the alphabet of the NFA.
     * @return A set containing all symbols in the NFA's alphabet.
     */
    @Override
    public Set<Character> getSigma() {
        return sigma;
    }

    /**
     * Retrieves a state by its name.
     * @param name The name of the state.
     * @return The state object, or null if it does not exist.
     */
    @Override
    public NFAState getState(String name) {
        return states.get(name);
    }

    /**
     * Checks if a state is a final state.
     * @param name The name of the state.
     * @return True if the state is final, false otherwise.
     */
    @Override
    public boolean isFinal(String name) {
        NFAState state = states.get(name);
        return state != null && finalStates.contains(state);
    }

    /**
     * Checks if a state is the start state.
     * @param name The name of the state.
     * @return True if the state is the start state, false otherwise.
     */
    @Override
    public boolean isStart(String name) {
        return startState != null && startState.getName().equals(name);
    }

    /**
     * Adds a transition between states.
     * @param fromState The name of the starting state.
     * @param toStates The set of destination states.
     * @param onSymb The transition symbol.
     * @return True if successful, false otherwise.
     */
    @Override
    public boolean addTransition(String fromState, Set<String> toStates, char onSymb) {
        if (!states.containsKey(fromState) || !sigma.contains(onSymb)) return false;
        NFAState from = states.get(fromState);
        for (String toState : toStates) {
            if (!states.containsKey(toState)) return false;
            from.addTransition(onSymb, states.get(toState));
        }
        return true;
    }

    /**
     * Retrieves the states that can be reached from a given state on a specific symbol.
     * @param from The source state.
     * @param onSymb The transition symbol.
     * @return A set of reachable states.
     */
    @Override
    public Set<NFAState> getToState(NFAState from, char onSymb) {
        return from.toStates(onSymb);
    }

    /**
     * Computes the epsilon closure of a state.
     * @param s The state.
     * @return A set of states reachable via epsilon transitions.
     */
    @Override
    public Set<NFAState> eClosure(NFAState s) {
        Set<NFAState> closure = new HashSet<>();
        Stack<NFAState> stack = new Stack<>();
        stack.push(s);
        while (!stack.isEmpty()) {
            NFAState state = stack.pop();
            if (closure.add(state)) {
                stack.addAll(state.toStates('e'));
            }
        }
        return closure;
    }

    /**
     * Determines if the NFA accepts a given input string.
     * @param s The input string.
     * @return True if the input is accepted, false otherwise.
     */
    @Override
    public boolean accepts(String s) {
        Set<NFAState> currentStates = eClosure(startState);
        for (char c : s.toCharArray()) {
            Set<NFAState> nextStates = new HashSet<>();
            for (NFAState state : currentStates) {
                for (NFAState nextState : state.toStates(c)) {
                    nextStates.addAll(eClosure(nextState));
                }
            }
            currentStates = nextStates;
        }
        for (NFAState state : currentStates) {
            if (finalStates.contains(state)) return true;
        }
        return false;
    }

    /**
     * Determines the maximum number of NFA copies created when processing an input string.
     * @param s The input string.
     * @return The maximum number of NFA copies.
     */
    @Override
    public int maxCopies(String s) {
        Set<NFAState> currentStates = eClosure(startState);
        int maxCopies = currentStates.size();
        for (char c : s.toCharArray()) {
            Set<NFAState> nextStates = new HashSet<>();
            for (NFAState state : currentStates) {
                for (NFAState nextState : state.toStates(c)) {
                    nextStates.addAll(eClosure(nextState));
                }
            }
            currentStates = nextStates;
            maxCopies = Math.max(maxCopies, currentStates.size());
        }
        return maxCopies;
    }
}
