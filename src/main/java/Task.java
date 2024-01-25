public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
        this.printMarking(this, this.getTag());
    }

    public void unmark() {
        this.isDone = false;
        this.printMarking(this, this.getTag());
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public String getTag() {
        return "[T]";
    }

    public void printTaskDesc(int num, boolean isLast){
        if (!isLast) {
            if (num == 1) {
                System.out.print("      ________________________________________________________\n");
                System.out.printf("      Here are the tasks in your list: \n      %d.%s[%s] %s\n",
                        num, this.getTag(), this.getStatusIcon(), this.getDescription());
            } else {
                System.out.printf("      %d.%s[%s] %s \n", num, this.getTag(), this.getStatusIcon(), this.getDescription());
            }
        } else {
            if (num == 1) {
                System.out.print("      ________________________________________________________\n");
                System.out.printf("      Here are the tasks in your list: \n      %d.%s[%s] %s\n",
                        num, this.getTag(), this.getStatusIcon(), this.getDescription());
                System.out.print("      ________________________________________________________\n");
            } else {
                System.out.printf("      %d.%s[%s] %s \n", num, this.getTag(), this.getStatusIcon(), this.getDescription());
                System.out.print("      ________________________________________________________\n");
            }
        }
    }

    public void printFullDesc() {
        System.out.printf("         %s[%s] %s \n", this.getTag(), this.getStatusIcon(), this.getDescription());
    }

    public void printMarking(Task task, String tag) {
        if (this.isDone) {
            System.out.print("      ________________________________________________________\n"
                    + "      Great job! I've marked this task as done: \n");
            System.out.printf("      %s[%s] %s \n", tag, task.getStatusIcon(), task.getDescription());
            System.out.print("      ________________________________________________________\n");
        } else {
            System.out.print("      ________________________________________________________\n"
                    + "      Ok, I've marked this task as not done yet: \n");
            System.out.printf("      %s[%s] %s \n", tag, task.getStatusIcon(), task.getDescription());
            System.out.print("      ________________________________________________________\n");
        }
    }
}
