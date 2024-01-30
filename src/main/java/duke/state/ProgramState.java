package duke.state;

public class ProgramState {
    public static enum State {
        NORMAL, EXIT, ERROR
    }

    private State state;

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
