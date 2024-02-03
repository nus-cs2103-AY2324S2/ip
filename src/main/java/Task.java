

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone? "X" : " ");
    }

    public String toString() {
        return this.description;
    }

    public String printWithStatus() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    public static Task parser(String taskString) {
        String str = taskString.substring(1,2);

        switch (str) {
            case "T":
                Todo todoTask = new Todo(taskString.substring(7).trim());
                if (taskString.substring(4, 5).equals("X")) {
                    todoTask.mark();
                } else {
                    todoTask.unmark();
                }
                return todoTask;
            case "D":
                String by = taskString.split(":")[1].split("\\)")[0].trim();
                String description = taskString.split("\\(")[0].substring(7).trim();
                Deadline deadlineTask = new Deadline(description, by);
                if (taskString.substring(4, 5).equals("X")) {
                    deadlineTask.mark();
                } else {
                    deadlineTask.unmark();
                }
                return deadlineTask;
            case "E":
                String descr = taskString.split("\\(")[0].substring(7).trim();
                String from = taskString.split(":")[1].trim();
                String to = taskString.split(":")[2].split("\\)")[0].trim();

                Event eventTask = new Event(descr, from, to);
                if (taskString.substring(4, 5).equals("X")) {
                    eventTask.mark();
                } else {
                    eventTask.unmark();
                }
                return eventTask;
            default:
                System.out.println("parsing error");
                return null;

        }
    }
}
