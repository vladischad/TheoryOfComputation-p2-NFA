package fa.nfa;

import fa.State;
import java.util.*;

public class NFA implements NFAInterface {
    private Set<Character> sigma;   //Alphabet
    private Map<Character, NFAState> states;
    private NFAState startState;
    private Set<NFAState> finalStates;

    public NFA () {
        sigma = new LinkedHashSet<>();
        states = new LinkedHashMap<>();
        startState = null;
        finalStates = new LinkedHashSet<>();
    }

    
}
