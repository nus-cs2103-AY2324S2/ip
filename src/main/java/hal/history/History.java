package hal.history;

import java.util.ArrayList;

import hal.exceptions.ProcessingException;

/**
 * The `History` class manages the history of states in the HAL9000 application.
 * It allows for rolling back and rolling forward to previous states.
 */
public class History {
    private int currStateIdx;
    private final ArrayList<State> states;

    /**
     * Constructs a new History object with a starting state.
     *
     * @param startState The initial state of the history.
     */
    public History(State startState) {
        states = new ArrayList<>();
        states.add(startState);
        currStateIdx = 0;
    }

    /**
     * Removes states after the current state, effectively truncating the history.
     */
    private void removeStatesAfterCurrentState() {
        assert (currStateIdx >= 0 && currStateIdx < states.size() - 1);
        states.subList(currStateIdx + 1, states.size()).clear();
    }

    /**
     * Rolls back to the previous state in the history.
     *
     * @throws ProcessingException If there are no more previous states to roll back to.
     */
    public void rollBackState() throws ProcessingException {
        if (currStateIdx == 0) {
            throw new ProcessingException("You can't roll back the state anymore!");
        }
        currStateIdx -= 1;
    }

    /**
     * Rolls forward to the next state in the history.
     *
     * @throws ProcessingException If there are no more future states to roll forward to.
     */
    public void rollForwardState() throws ProcessingException {
        if (currStateIdx == states.size() - 1) {
            throw new ProcessingException("You can't roll forward the state anymore!");
        }
        currStateIdx += 1;
    }

    /**
     * Adds a new state to the history, removing subsequent states.
     *
     * @param state The state to add to the history.
     */
    public void addState(State state) {
        removeStatesAfterCurrentState();
        states.add(state);
        currStateIdx += 1;
    }

    /**
     * Gets the current state from the history.
     *
     * @return The current state.
     */
    public State getCurrState() {
        return states.get(currStateIdx);
    }
}
