public class Duke {
    String name = "Aerial";

    public Duke() {
    }

    public void greeting() {
        horizontalLines();
        System.out.println("HELLO, Nice to meet you. I am " + this.name);
        System.out.println("What can I do for you?");
        horizontalLines();
    }

    public void bye() {
        System.out.println("See you next time!");
        horizontalLines();
    }

    public void horizontalLines() {
        System.out.println("\n____________________________________________________________");
    }

    public static void main(String[] args) {
        Duke Duke = new Duke();
        Duke.greeting();
        Duke.bye();
    }
    /*
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

     */
}
