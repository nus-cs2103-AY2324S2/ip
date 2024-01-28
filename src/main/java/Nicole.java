import java.io.IOException;

public class Nicole {
    public static final String botName = "Nicole";
    private Ui ui;
    public Nicole() {
        try {
            System.out.println(new Ui().talkToUser());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new Nicole();
    }
}
