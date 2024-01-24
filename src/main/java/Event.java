public class Event extends Item {
    private boolean isDone = false;
    private String name = "";
    private String start = "";
    private String end = "";
    public Event(String[] info) {
        int index = 1;
        String s = "";
        while (!info[index].equals("/from")) {
            if (info[index].equals("/to")) {
                // throw some exception (to should be after from)
            }
            this.name += info[index] + " ";
            index++;
        }
        for (int i = index; i < info.length; i++) {
            s += info[i] + " ";
        }
        this.start = s.split("/from|/to")[1];
        this.end = s.split("/from|/to")[2];

        this.name = this.name.trim();
        this.start = this.start.trim();
        this.end = this.end.trim();
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
        return "[E]["
                + printChecked(this.isDone) + "] " + this.name + " " + "(from: " + this.start + " to: " + this.end+ ")";
    }
}
