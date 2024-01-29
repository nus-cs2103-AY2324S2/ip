package kervyn;

import kervyn.Tasks.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
public class Ui {

    private final String CHATBOTNAME = "Kervyn";
    public Ui() {}

    public void startChatBot(TaskList taskList, Storage storage) {
        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser(storage);
        System.out.println("\tHello! I'm " + this.CHATBOTNAME);
        System.out.println("\tWhat can I do for you?");
        String userInput;
        Task task;

        do {
            userInput = scanner.nextLine();
            parser.deduceCommand(userInput, taskList);
        } while (!Objects.equals(userInput, "bye"));
    }

}
