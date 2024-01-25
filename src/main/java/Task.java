public class Task {

    private String taskContent;
    private Boolean done;
    private String type;

    public Task(String type, String input) {
        this.type = type;
        this.taskContent = input;
        this.done = false;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }


    @Override
    public String toString() {
        if (done) {
            return String.format("[%s][X] ", this.type) + this.taskContent;
        }
        return "[ ] " + this.taskContent;
    }
}
