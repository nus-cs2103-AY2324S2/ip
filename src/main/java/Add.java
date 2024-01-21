public class Add implements Action {
    private String input;
    private Store store;
    Add(String input, Store store) {
        this.input = input;
        this.store = store;
    }

    @Override
    public void execute() {
        this.store.add(this.input);
        PrintUtil.print("added: " + this.input);
    }
}
