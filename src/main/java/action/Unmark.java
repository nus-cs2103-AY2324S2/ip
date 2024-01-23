package action;
import util.PrintUtil;
import util.Store;

public class Unmark implements Action {
    private Store store;
    private int i;
    public Unmark(Store store, int i) {
        this.store = store;
        this.i = i;
    }
    @Override
    public void execute() {
        PrintUtil.print("You know, sometimes things don't go as planned, but that's okay! " +
                "\nThe important part is to keep moving forward. " +
                "\nUnmarking a task is just a step in the journey. Believe it! " +
                "\nWe'll get there, one task at a time! ᕙ(⇀‸↼‶)ᕗ");
        PrintUtil.print(this.store.unmark(i));
    }
}
