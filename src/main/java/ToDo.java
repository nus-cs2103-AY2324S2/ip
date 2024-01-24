public class ToDo extends Item{
    private boolean isDone = false;
    private String name = "";
    public ToDo(String[] name) {
        for (int i = 1; i < name.length; i++) {
            this.name += name[i] + " ";
        }
        this.name = this.name.trim();
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
