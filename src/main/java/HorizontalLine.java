public class HorizontalLine {
    private static HorizontalLine instance = null;

    // default linelength is set to 10
    private static int lineLength = 50;

    private HorizontalLine() {
        // necessary setup
    }

    public static HorizontalLine getInstance() {
        if (instance == null) {
            instance = new HorizontalLine();
        }

        return instance;
    }

    public void setLineLength(int length) {
        lineLength = length;
    }

    public void printLine() {
        for (int i = 0; i < lineLength; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
}
