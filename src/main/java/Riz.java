import riz.data.*;
import riz.io.*;

import java.util.Scanner;

public class Riz {
    private TaskList taskList;
    private Storage storage;

    public Riz(String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(this.storage.loadFromFile());
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String dotted = "-----------------------------------";
        //greetings
        Ui.printGreetings();
        boolean running = true;

        while (running) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                running = false;
                Ui.printGoodbye();
            } else {
                Parser.parse(scanner, this.taskList, this.storage, input);
            }
        }
    }
    public static void main(String[] args) {
        Riz riz = new Riz("riz/data/riz.txt");
        riz.run();
    }
}