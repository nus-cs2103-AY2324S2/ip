public final class PrintHandler {
    public static final PrintHandler instance = new PrintHandler();
    private static final String DIVIDER = "------------------------------------";
    private PrintHandler() {}

    public void printWithDivider(String msg) {
        System.out.println(msg);
        System.out.println(DIVIDER);
    }

    public void print(String msg) {
        System.out.println(msg);
    }

}
