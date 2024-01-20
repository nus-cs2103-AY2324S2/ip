class Task {

    private boolean done = false;
    private String name = "";
    public Task(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    public String getName() {
        return this.name;
    }

    public String isDone() {
        if (this.done) {
            return "X";
        } else {
            return " ";
        }
    }

    public void dotask() {
        this.done = true;
    }

    public void undotask() {
        this.done = false;
    }
}