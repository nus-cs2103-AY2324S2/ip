package view;

public class GreetView extends Ui {
    @Override
    public void display() {
        System.out.println(
        "░░  ░░░░░░   ░░░░░░  ░░    ░░    ░░\n" +
        "▒▒ ▒▒       ▒▒       ▒▒     ▒▒  ▒▒\n" +
        "▒▒ ▒▒   ▒▒▒ ▒▒   ▒▒▒ ▒▒      ▒▒▒▒\n" +
        "▓▓ ▓▓    ▓▓ ▓▓    ▓▓ ▓▓       ▓▓\n" +
        "██  ██████   ██████  ███████  ██\n" +
        "____________________________________________________________\n" +
        " Hello! I'm Iggly, your personal assistant chatbot.\n" +
        " What can I do for you?\n" +
        "____________________________________________________________\n"
        );
    }
}
