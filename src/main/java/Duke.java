public class Duke {

    public static void startupMessage() {
        String spacer = "____________________________________________________________\n";
        String name = "CBBW";
        System.out.println(spacer + "Hello! I'm " + name 
                           + "\nWhat can I do for you?\n" + spacer);
    }

    public static void goodbyeMessage() {
        String spacer = "____________________________________________________________\n";
        String name = "CBBW";
        System.out.println("Bye. Hope to see you again soon!\n" + spacer);
    }
    public static void main(String[] args) {
        startupMessage();
        goodbyeMessage();
    }
}
