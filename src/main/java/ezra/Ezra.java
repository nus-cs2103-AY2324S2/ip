package ezra;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Ezra {

    private Ui ui;
    private Storage storage;
    private Parser parser;
    private TaskList tasks;


    public Ezra(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            System.out.println("\tYou have no saved tasks");
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greet();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            parser.read(input, storage, tasks);
            if (input.equals("bye")) {
                break;
            }
        }
        scanner.close();
    }
    public static void main(String[] args) {
        new Ezra("data" + File.separator + "ezra.txt").run();
    }
}
