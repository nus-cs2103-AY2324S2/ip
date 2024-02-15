package duke.history;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import duke.exceptions.ProcessingException;

/**
 * placeholder
 */
public class History implements Serializable {
    private static final long serialVersionUID = 6L;
    private int currStateIdx;
    private final ArrayList<State> states;
    /**
     * placeholder
     */
    public History(State startState) {
        states = new ArrayList<>();
        states.add(startState);
        currStateIdx = 0;
    }
    private void removeStatesAfterCurrentState() {
        assert (currStateIdx >= 0 && currStateIdx < states.size() - 1);
        states.subList(currStateIdx + 1, states.size()).clear();
    }

    /**
     * @throws ProcessingException placeholder
     */
    public void rollBackState() throws ProcessingException {
        if (currStateIdx == 0) {
            throw new ProcessingException("You can't roll back the state anymore!");
        }
        currStateIdx -= 1;
    }

    /**
     * @throws ProcessingException placeholder
     */
    public void rollForwardState() throws ProcessingException {
        if (currStateIdx == states.size() - 1) {
            throw new ProcessingException("You can't roll forward the state anymore!");
        }
        currStateIdx += 1;
    }

    /**
     * @param state placeholder
     * @throws ProcessingException placeholder
     */
    public void addState(State state) throws ProcessingException {
        removeStatesAfterCurrentState();
        states.add(state);
        currStateIdx += 1;
    }
    public State getCurrState() {
        return states.get(currStateIdx);
    }
    private void writeObject(ObjectOutputStream out) throws IOException, ClassNotFoundException {
        out.defaultWriteObject();
    }
    @Override
    public String toString() {
        return states.toString();
    }
}
