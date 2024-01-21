public class Add implements Action {
    private Task task;
    private Store store;
    Add(Task task, Store store) {
        this.task = task;
        this.store = store;
    }

    @Override
    public void execute() {
        this.store.add(this.task);
        PrintUtil.print("added: " + this.task);
    }
}
