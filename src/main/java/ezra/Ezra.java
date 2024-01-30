package ezra;

import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;

public class Ezra {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;


    public Ezra(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            this.tasks = new TaskList();
        }
    }

    public void run() {
        ui.greet();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            Parser.read(input, storage, tasks);
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
