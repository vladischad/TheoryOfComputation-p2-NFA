package fa.nfa;

import fa.State;
import java.util.*;

public class NFA implements NFAInterface {
    private Set<Character> sigma;   //Alphabet
    private Map<String, NFAState> states;
    private NFAState startState;
    private Set<NFAState> finalStates;

    public NFA () {
        sigma = new LinkedHashSet<>();
        states = new LinkedHashMap<>();
        startState = null;
        finalStates = new LinkedHashSet<>();
    }

    @Override
    public boolean addState(String name) {
        if (states.containsKey(name)) return false;
        states.put(name, new NFAState(name));
        return true;
    }

    @Override
    public boolean setFinal(String name) {
        NFAState state = states.get(name);
        if (state == null) return false;
        finalStates.add(state);
        return true;
    }

    @Override
    public boolean setStart(String name) {
        NFAState state = states.get(name);
        if (state == null) return false;
        startState = state;
        return true;
    }

    @Override
    public void addSigma(char symbol) {
        sigma.add(symbol);
    }

    @Override
    public Set<Character> getSigma() {
        return sigma;
    }

    @Override
    public State getState(String name) {
        return states.get(name);
    }

    @Override
    public boolean isFinal(String name) {
        NFAState state = states.get(name);
        return state != null && finalStates.contains(state);
    }

    @Override
    public boolean isStart(String name) {
        return startState != null && startState.getName().equals(name);
    }

}
