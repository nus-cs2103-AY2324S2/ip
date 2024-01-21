public class Goodbye implements Action {
    Goodbye(){}
    @Override
    public void execute() {
        PrintUtil.print("Bye bye! ヾ( ˃ᴗ˂ )◞ • *✰");
        System.exit(0);
    }
}
