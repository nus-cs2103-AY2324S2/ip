class Todo extends Task {
    public Todo(String input) {
        super(input);
    }
    public Todo(String description, Boolean hasDone) {
        this.setHasDone(hasDone);
        super.setDescription(description);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
