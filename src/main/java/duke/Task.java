package duke;

public abstract class Task {
    protected String name;
    protected boolean isDone;

    @Override
    public String toString() {
        String str = "";
        if (isDone) {
            str = String.format("[X] %s", name);
        } else {
            str = String.format("[ ] %s", name);
        }
        return str;
    }

    public void mark() {
        isDone = true;
        System.out.println("Good job! You have completed this task:");
        System.out.println(toString());
    }

    public void unmark() {
        isDone = false;
        System.out.println("Alright. This task has been unmarked");
        System.out.println(toString());
    }

    /**
     * Gets the name of the task
     *
     * @return the name of the task
     */
    public String getName() {
        return this.name;
    }

    public String convertToText() {
        return "";
    }
}
