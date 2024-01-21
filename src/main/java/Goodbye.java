public class Goodbye implements Action {
    Goodbye(){}
    @Override
    public void execute() {
        PrintUtil.print("Bye bye!");
        System.exit(0);
    }
}
