package action;
import util.PrintUtil;

public class Goodbye implements Action {
    public Goodbye(){}
    @Override
    public void execute() {
        PrintUtil.print("Bye bye! ヾ( ˃ᴗ˂ )◞ • *✰");
        System.exit(0);
    }
}
