package task1.tree;


import java.util.ArrayList;
import java.util.HashMap;

class State {
    int len = 0;
    int link = 0;
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

    int get(Character c) {
        if (!next.containsKey(c)) {
            return 0;
        }
        return next.get(c);
    }

}

public class SuffixTree {
    private int sz;
    private int last;
    ArrayList<State> states;

    public SuffixTree() {
        states = new ArrayList<>();
    }

    private void saInit() {
        sz = 0;
        last = 0;
        states.add(new State(0, -1));
        sz++;
    }

    private void saExtend(Character c) {
        int cur = sz++;
        State state = new State(states.get(last).len + 1, -1);
        states.add(cur, state);
        int p;

        for (p = last; p != -1 && (countLinks(states.get(p), c) != 0); p = states.get(p).link) {
            states.get(p).next.put(c, cur); ///////////////////
        }
        if (p == -1) {
            states.get(cur).link = 0;
        } else {
            int q = states.get(p).get(c);
            if (states.get(p).len + 1 == states.get(q).len) {
                states.get(cur).link = q;
            } else {
                int clone = sz++;
                State stateClone = new State(states.get(p).len + 1, states.get(q).link, states.get(q).next);
                states.add(clone, stateClone);
                for (; p != -1 && states.get(p).get(c) == q; p = states.get(p).link) {
                    states.get(p).next.put(c, clone); //////////////////
                }
                states.get(q).link = states.get(cur).link = clone;
            }
        }
        last = cur;
    }

    private int countLinks(State state, Character c) {
        return state.get(c);
    }

    public String lsc (String s, String t) {
        saInit();

        for (int i = 0; i < s.length(); i++) {
            saExtend(s.charAt(i));
        }

        int v = 0, l = 0, best = 0, bestpos = 0;

        for (int i = 0; i < t.length(); i++) {
            while ((v != 0) && (countLinks(states.get(v), t.charAt(i)) != 0) ) {
                v = states.get(v).link;
                l = states.get(v).len;
            }
            int num;
            if ((num = countLinks(states.get(v), t.charAt(i))) != 0) {
                v = num;
                l++;
            }
            if (l > best) {
                best = l;
                bestpos = i;
            }
        }
        return t.substring(bestpos - best + 1, best);
    }

}
