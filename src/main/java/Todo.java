class Todo extends Task {
    public Todo(String description) throws HenryException {
        super(description);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}