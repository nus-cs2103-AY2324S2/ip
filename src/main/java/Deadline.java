public class Deadline extends Item{

    private boolean isDone = false;
    private String name = "";
    private String doneBy = "";
    public Deadline(String[] info) {
        int index = 1;
        String s = "";
        while (!info[index].equals("/by")) {
            this.name += info[index] + " ";
            index++;
        }
        for (int i = index + 1; i < info.length; i++) {
            this.doneBy += info[i] + " ";
        }
        this.name = this.name.trim();
        this.doneBy = this.doneBy.trim();
        this.isDone = false;
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
