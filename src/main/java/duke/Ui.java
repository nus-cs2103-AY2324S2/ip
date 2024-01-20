package duke;

public class Ui {
    public static String logo() {
        return
          "░░  ░░░░░░   ░░░░░░  ░░    ░░    ░░\n"
        + "▒▒ ▒▒       ▒▒       ▒▒     ▒▒  ▒▒ \n"
        + "▒▒ ▒▒   ▒▒▒ ▒▒   ▒▒▒ ▒▒      ▒▒▒▒  \n"
        + "▓▓ ▓▓    ▓▓ ▓▓    ▓▓ ▓▓       ▓▓   \n"
        + "██  ██████   ██████  ███████  ██   \n";
    }

    public static String greet() {
        return "____________________________________________________________\n" +
               " Hello! I'm Iggly, your personal assistant chatbot.\n" +
               " What can I do for you?\n" +
               "____________________________________________________________\n";
    }

    public static String exit() {
        return "____________________________________________________________\n" +
               " Bye. Hope to see you again soon!\n" +
               "____________________________________________________________\n";
    }
}
