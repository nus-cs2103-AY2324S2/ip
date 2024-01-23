public class Duke {

    private final String horizontalLine = "____________________________________________________________";
    private final String name;

    public Duke(String name) {
        this.name = name;
    }

    public void greetUser() {
        System.out.println(this.horizontalLine
                + "\nHello! I'm "
                + this.name
                + "\n"
                + "What can I do for you?\n"
                + this.horizontalLine);
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!\n" + this.horizontalLine);
    }

    public static void main(String[] args) {

        Duke zenifyBot = new Duke("Zenify");
        zenifyBot.greetUser();
        zenifyBot.exit();

    }
}
