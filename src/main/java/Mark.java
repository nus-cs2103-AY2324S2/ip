public class Mark implements Action {
    private Store store;
    private int i;

    Mark(Store store, int i) {
        this.store = store;
        this.i = i;
    }

    @Override
    public void execute() {
        PrintUtil.print("Nice! I've marked this task as done:");
        PrintUtil.print(this.store.mark(i));
    }
}
