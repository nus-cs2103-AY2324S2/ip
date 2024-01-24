public class Item {
    private boolean isDone = false;
    private String name;

    public Item(String name) {
        this.name = name;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    String doneMessage() {
        return "Nice! I've marked this task as done:\n     " +
                this.toString();
    }

    String undoneMessage() {
        return "OK, I've marked this task as not done yet:\n     " +
                this.toString();
    }


    private String printChecked(boolean b) {
        return b ? "X" : " ";
    }

    @Override
    public String toString() {
        return "[" + printChecked(this.isDone)+ "] " + this.name;
    }

    public String getName() {
        return this.name;
    }

    public boolean getDone() {
        return this.isDone;
    }
}
