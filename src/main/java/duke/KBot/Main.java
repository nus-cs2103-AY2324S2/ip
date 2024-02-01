package duke.kbot;

import duke.ui_design.Ui;

public class Main {
    public static void main(String[] args) {
        Ui ui = new Ui();
        System.out.println(ui.getStartMessage()); // opening statement
        TaskManager.loadLocal(); // checking if there are local files to load
        KBot.simulate(); // simulate kaipybara chatbot
        System.out.println(ui.getEndMessage()); // closing statement
        System.out.println(ui.getFlower());
    }
}
