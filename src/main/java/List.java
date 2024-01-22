public class List implements Action {
    private Store store;
    List(Store store){
        this.store = store;
    }

    public void execute() {
        PrintUtil.print("Here are the tasks in your list:\n" + this.store.toString());
    }
}
