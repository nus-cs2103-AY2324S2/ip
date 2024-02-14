package duke;
public class UiOld {
    private ChatSession session;

    UiOld(ChatSession session) {
        this.session = session;
    }

    public void initChat() {
        String message = "____________________________________________________________\n" +
        "Hello! I'm GoldBot!\n" +
        "What can I do for you?\n" +
        "____________________________________________________________\n";
        System.out.println(message);
    }

    public void printMessage(String message) {
        this.printHorizontalLine();
        System.out.println(message);
        this.printHorizontalLine();
    }

    public void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }


}
