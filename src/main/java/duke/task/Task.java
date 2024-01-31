package duke.task;

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
    public String mark(int number) {
        return "    " + this.getCat() + this.marked() + " "
                + this.getTask();
    }

    public String delete() {
        return "     " + this.getCat() + this.marked() + " "
                + this.getTask();
    }

    public void unmark() {
        isDone = false;
    }

    public String unmark(int number) {
        return "    " + this.getCat() + marked() + " "
                        + this.getTask();
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

        public String add() {
            if (this instanceof Todo) {
                return ((Todo) this).add();
            } else if (this instanceof Deadline) {
                return ((Deadline) this).add();
            } else if (this instanceof Event) {
                return ((Event) this).add();
            }
            return "";
        }


}
