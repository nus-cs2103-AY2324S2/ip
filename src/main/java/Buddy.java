public class Buddy {
    private String lineBreak = "____________________________________________________________\n";

    private void greet() {
        String logo =
            "               ____            _     _       \n" +
            "              |  _ \\          | |   | |      \n" +
            "              | |_) |_   _  __| | __| |_   _ \n" +
            "              |  _ <| | | |/ _` |/ _` | | | |\n" +
            "              | |_) | |_| | (_| | (_| | |_| |\n" +
            "              |____/ \\__,_|\\__,_|\\__,_|\\__, |\n" +
            "                                        __/ |\n" +
            "                                       |___/ \n";
        System.out.println(lineBreak + logo + lineBreak + " Hello friend!\n" + " How can I help you?");
    }

    private void exit() {
        System.out.println(lineBreak + " Bye. Hope to see you again soon!\n" + lineBreak);
    }

    public static void main(String[] args) {
        Buddy buddy = new Buddy();
        buddy.greet();
        buddy.exit();
    }
}
