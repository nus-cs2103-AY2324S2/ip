public class Event extends Item {
    private boolean isDone = false;
    private String name = "";
    private String start = "";
    private String end = "";
    public Event(String[] info) throws CustomExceptions {
        int index = 1;
        String s = "";
        while ((index < info.length) && !info[index].equals("/from")) {
            if (info[index].equals("/to")) {
                throw new CustomExceptions.toBeforeFromException("Wrong input, /to should be after /from");
            }
            this.name += info[index] + " ";
            index++;
        }
        if (index < info.length) {
            for (int i = index; i < info.length; i++) {
                s += info[i] + " ";
            }
            try {
                this.start = s.split("/from|/to")[1];
                this.end = s.split("/from|/to")[2];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new CustomExceptions.eventExceptionForFromTo("Unable to parse /from /to strings");
            }
        }

        this.name = this.name.trim();
        this.start = this.start.trim();
        this.end = this.end.trim();

        if (this.name.equals("")) {
            throw new CustomExceptions.namelessTaskException("Missing Event Name");
        }
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
