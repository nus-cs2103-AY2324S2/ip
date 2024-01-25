public abstract class Task {
    private String task;
    private boolean isDone = false;

    public Task(String task) {
        this.task = task;
    }

    public String changeMark(String command) {
        if (command.equals("MARK")) {
            isDone = true;
            return "Nice! I've marked this task as done:\n";
        } else { // UNMARK command
            isDone = false;
            return "OK, I've marked this task as not done yet:\n";
        }
    }

    @Override
    public String toString() {
        String mark = isDone ? "X" : " ";
        return String.format("[%s] %s", mark, task);
    }
}
