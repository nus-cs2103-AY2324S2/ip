package ChatbotRan;

class Task {
    private String contents;
    private boolean completed;

    public Task(String contents) {
        this.contents = contents;
        this.completed = false;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "[" + (this.completed ? "X" : " ") + "] " + this.contents;
    }
}
