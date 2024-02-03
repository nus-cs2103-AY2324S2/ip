import java.io.Serializable;

public class Deadline implements Item, Serializable {

    private boolean isDone = false;
    private String name = "";
    private String doneBy = "";
    public Deadline(String[] info) throws CustomExceptions {
        int index = 1;
        String s = "";
        while ((index < info.length) && !info[index].equals("/by")) {
            this.name += info[index] + " ";
            index++;
        }
        for (int i = index + 1; i < info.length; i++) {
            this.doneBy += info[i] + " ";
        }
        this.name = this.name.trim();
        this.doneBy = this.doneBy.trim();
        this.isDone = false;
        if (this.name.equals("")) {
            throw new CustomExceptions.namelessTaskException("Missing Event Name");
        }
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
        return "[D][" + printChecked(this.isDone)+ "] " + this.name + " " + "(by: " + this.doneBy +")";
    }
}
