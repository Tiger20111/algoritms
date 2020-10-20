package task1.tree;


import java.util.ArrayList;
import java.util.HashMap;

class State {
    int len;
    int link;
    HashMap<Character, Integer> next;

    State(int len, int link) {
        this.len = len;
        this.link = link;
        next = new HashMap<>();
    }

    State(int len, int link, HashMap<Character, Integer> next) {
        this.len = len;
        this.link = link;
        this.next = new HashMap<>(next);
    }

    int stateChar(Character c) {
        if (!next.containsKey(c)) {
            return 0;
        }
        return next.get(c);
    }

}

public class SuffixTree {
    private int numStates;
    private int lastState;
    private ArrayList<State> states;

    public SuffixTree() {
        states = new ArrayList<>();
    }

    private void saInit() {
        numStates = 0;
        lastState = 0;
        states.add(new State(0, -1));
        numStates++;
    }

    private void saExtend(Character c) {
        int curState = numStates++;
        State state = new State(states.get(lastState).len + 1, 0);
        states.add(curState, state);
        int indexState;

        for (indexState = lastState; indexState != -1 && (countLinks(states.get(indexState), c) == 0); indexState = states.get(indexState).link) {
            states.get(indexState).next.put(c, curState);
        }
        if (indexState == -1) {
            states.get(curState).link = 0;
        } else {
            int stateCurrentChar = states.get(indexState).stateChar(c);
            if (states.get(indexState).len + 1 == states.get(stateCurrentChar).len) {
                states.get(curState).link = stateCurrentChar;
            } else {
                int newState = numStates++;
                State stateClone = new State(states.get(indexState).len + 1, states.get(stateCurrentChar).link, states.get(stateCurrentChar).next);
                states.add(newState, stateClone);
                for (; indexState != -1 && states.get(indexState).stateChar(c) == stateCurrentChar; indexState = states.get(indexState).link) {
                    states.get(indexState).next.put(c, newState);
                }
                states.get(stateCurrentChar).link = newState;
                states.get(curState).link = newState;
            }
        }
        lastState = curState;
    }

    private int countLinks(State state, Character c) {
        return state.stateChar(c);
    }

    public String lsc (String A, String B) {
        saInit();

        for (int i = 0; i < A.length(); i++) {
            saExtend(A.charAt(i));
        }

        int indexState = 0;
        int currentLen = 0;
        int max = 0;
        int maxPos = 0;

        for (int i = 0; i < B.length(); i++) {
            while ((indexState != 0) && (countLinks(states.get(indexState), B.charAt(i)) == 0)) {
                indexState = states.get(indexState).link;
                currentLen = states.get(indexState).len;
            }
            int num;
            if ((num = countLinks(states.get(indexState), B.charAt(i))) != 0) {
                indexState = num;
                currentLen++;
            }
            if (currentLen > max) {
                max = currentLen;
                maxPos = i;
            }
        }
        return B.substring(maxPos - max + 1, max);
    }

}
