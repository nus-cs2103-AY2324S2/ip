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

                // Check if it is mark / unmark command.
                Boolean isMark = input.split(" ", 2)[0].toLowerCase().equals("mark");
                Boolean isUnmark = input.split(" ", 2)[0].toLowerCase().equals("unmark");

                if (isMark) {
                    String indexString = input.split(" ", 2)[1];
                    if (isNumeric(indexString)) {
                        int index = Integer.parseInt(indexString);
                        tracker.markTaskIndex(index);
                    } else {
                        System.out.println("Input a valid number to mark! + \n" +
                                "Usage: mark [int]");
                    }
                }
                else if (isUnmark) {
                    String indexString = input.split(" ", 2)[1];
                    if (isNumeric(indexString)) {
                        int index = Integer.parseInt(indexString);
                        tracker.unmarkTaskIndex(index);
                    } else {
                        System.out.println("Input a valid number to unmark! + \n" +
                                "Usage: unmark [int]");
                    }
                }
                else {
                    Task newTask = new Task(input);
                    tracker.addTask(newTask);
                }

            }
            input = reader.nextLine();
        }

        goodbye();
    }

    private static Boolean isNumeric(String string) {
        return string.matches("\\d+");
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
