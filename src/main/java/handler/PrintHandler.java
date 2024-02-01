package handler;

public final class PrintHandler {
    public static final PrintHandler instance = new PrintHandler();
    private static final String DIVIDER = "------------------------------------";
    private PrintHandler() {}

    public void print(String msg) {
        System.out.println(msg);
    }
    public void printWithDivider(String msg) {
        System.out.println(msg);
        System.out.println(DIVIDER);
    }
    public void printNumberedDivider(String[] msgs) {
        for (int i = 0; i < msgs.length; i++) {
            int index = i + 1;
            System.out.println(Integer.toString(index) + ". " + msgs[i]);
        }
        System.out.println(DIVIDER);
    }

    public void printException(Exception e) {
        System.out.println("Exception: " + e.getMessage());
        System.out.println(DIVIDER);
    }


}
