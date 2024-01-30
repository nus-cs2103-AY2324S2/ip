import java.io.File;
public class Task {
    private String task;
    private boolean isDone;

    public Task(String task) {
        this.task = task;
    }

    public void mark() {
        isDone = true;
    }
    public String mark(int number, String name, Task task) {
        isDone = true;
        return "    Nice! I've marked this task as done: \n"
                + "        " + task.getCat() + marked() + " "
                + name;
    }

    public String delete(Task task) {
        return "    Noted. I've removed this task: \n"
                + "        " + task.getCat() + task.marked() + " "
                + task.getTask();
    }

    public void unmark() {
        isDone = false;
    }

    public String unmark(int number, String name, Task task) {
        unmark();
        return "    Ok, I've marked this task as not done yet: \n"
        + "        " + task.getCat() + marked() + " "
                        + name;
    }

    public String marked() {
        if (isDone) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    public String getTask() {
        return task;
    }

        public String getCat() {
            if (this instanceof Todo) {
                return "[T]";
            } else if (this instanceof Deadline) {
                return "[D]";
            } else {
                return "[E]";
            }
        }

        public String getDetails() {
           if (this instanceof Deadline) {
                return ((Deadline) this).getDeadline();
            } else if (this instanceof Event) {
               return ((Event) this).getEvent();
           }
           return "";
        }

        public void writeToFile(File filePath) {
            if (this instanceof Todo) {
                 ((Todo) this).writeToFile(filePath);
            } else if (this instanceof Deadline) {
                ((Deadline) this).writeToFile(filePath);
            } else if (this instanceof Event) {
                ((Event) this).writeToFile(filePath);
            }
        }


}
