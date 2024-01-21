public class Unmark implements Action {
    private Store store;
    private int i;
    Unmark(Store store, int i) {
        this.store = store;
        this.i = i;
    }
    @Override
    public void execute() {
        PrintUtil.print("OK, I've marked this task as not done yet:");
        PrintUtil.print(this.store.unmark(i));
    }
}
