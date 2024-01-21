public class List implements Action {
    private Store store;
    List(Store store){
        this.store = store;
    }

    public void execute() {
        PrintUtil.print(this.store.toString());
    }
}
