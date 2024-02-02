package duke.state;

/**
 * Represents the state of the program. There are three possible states: normal,
 * exit, and error.
 */
public class ProgramState {
    /**
     * Represents the possible states of the program.
     */
    public static enum State {
        NORMAL, EXIT, ERROR
    }

    private State state;

    /**
     * Constructs a new program state. The initial state is normal.
     */
    public ProgramState() {
        this.state = State.NORMAL;
    }

    public void setState(State newState) {
        state = newState;
    }

    public void setNormal() {
        state = State.NORMAL;
    }

    public boolean isNormal() {
        return state == State.NORMAL;
    }

    public void setExit() {
        state = State.EXIT;
    }

    public boolean isExit() {
        return state == State.EXIT;
    }
}
