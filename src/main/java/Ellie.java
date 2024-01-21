import java.util.Scanner;

public class Ellie {

    final static String horizontalLine = "____________________________________________________________";
    final static String logoElephant =
                                        "     __\n" +
                                        " .--()°'.'\n" +
                                        "'|, . ,'\n" +
                                        " !_-(_\\";

    private Tracker tracker;

    public Ellie() {
        tracker = new Tracker();
    }

    public void start() {

        hello();

        Scanner reader = new Scanner(System.in);
        String input = reader.nextLine();

        while ( !input.toLowerCase().equals("bye") ) {
            if (input.toLowerCase().equals("list")) {
                tracker.listTasks();
            } else {
                Task newTask = new Task(input);
                tracker.addTask(newTask);
            }
            input = reader.nextLine();
        }
        goodbye();
    }

    private void hello() {
        System.out.println("Hello! I'm Ellie, your CS2103T chat bot!");
        System.out.println(logoElephant + "\n" + horizontalLine);
        System.out.println("What can I do for you?");
    }

    private void goodbye() {
        System.out.println("\n Bye! Hope to see you again soon!");
        System.out.println(horizontalLine);
    }


}
