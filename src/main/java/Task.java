public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done: \n" + this.toString() + "\n");
    }

    public void unmarkDone() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet: \n" + this.toString() + "\n");
    }

    @Override
    public String toString() {
        return (isDone ? "X" : " ") + " | " + description;
    }

    public static Task fromString(String input) {
        Task task;
        if (input.startsWith("T")) {
            task = ToDo.fromString(input);
        } else if (input.startsWith("D")) {
            task = Deadline.fromString(input);
        } else {
            task = Event.fromString(input);
        }
        return task;
    }
}
