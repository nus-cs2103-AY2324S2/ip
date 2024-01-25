public class DukeExceptionInvalidCommand extends DukeException {
    public DukeExceptionInvalidCommand(String m) {
        super(m);
    }
    @Override
    public String toString() {
        return "Hey! I don't know that command yet, I only recognize: list, bye, mark, todo, event, deadlines." + super.toString();
    }
}