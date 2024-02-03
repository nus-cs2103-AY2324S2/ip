import java.io.Serializable;

public class ToDo implements Item, Serializable {
    private boolean isDone = false;
    private String name = "";
    public ToDo(String[] name) {
        for (int i = 1; i < name.length; i++) {
            this.name += name[i] + " ";
        }
        this.name = this.name.trim();
    }

    @Override
    public String doneMessage() {
        return "Nice! I've marked this task as done:\n     " +
                this.toString();
    }

    @Override
    public String undoneMessage() {
        return "OK, I've marked this task as not done yet:\n     " +
                this.toString();
    }

    @Override
    public String printChecked(boolean b) {
        return b ? "X" : " ";
    }

    @Override
    public String addMessage(int num) {
        return "Got it. I've added this task:\n" +
                "       " + this.toString() +
                "\n     Now you have " + num +  " tasks in the list.";
    }

    @Override
    public String removeMessage(int num) {
        return "Noted. I've removed this task:\n" +
                "       " + this.toString() +
                "\n     Now you have " + num +  " tasks in the list.";
    }

    @Override
    public void markDone() {
        this.isDone = true;
    }

    @Override
    public void markUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[T][" + printChecked(this.isDone)+ "] " + this.name;
    }
}
