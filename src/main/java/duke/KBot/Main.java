package duke.kbot;
import duke.ui_design.Messages;

public class Main {
    public static void main(String[] args) {
        System.out.println(Messages.getStartMessage()); // opening statement
        TaskManager.loadLocal(); // checking if there are local files to load
        KBot.simulate(); // simulate kaipybara chatbot
        System.out.println(Messages.getEndMessage()); // closing statement
        System.out.println(Messages.getFlower());
    }
}
