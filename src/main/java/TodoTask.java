public class TodoTask extends Task {
    public TodoTask(String name) throws DukeException{
        super(name);
        if (name.equals("")) {
            throw new DukeException("oi the task needs a name la \uD83D\uDE21\uD83D\uDE21");
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
