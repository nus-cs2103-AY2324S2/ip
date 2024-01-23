public class Duke {
    public static void main(String[] args) {
        Duke d = new Duke();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        d.greet();
        d.bye();
    }

    public String greet() {
        return "Hello, I'm Baymax " + "\n" + "What can I do for you?";
    }

    public String bye() {
        return "Bye. Hope to see you again soon!";
    }

}
