package chatbot;

import java.util.Scanner;
import java.io.FileNotFoundException;


public class Alfred {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    public Alfred(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        this.tasks = new TaskList();
        this.parser = new Parser();
        try {
            tasks.processData(storage.getFile());
        } catch (FileNotFoundException e) {
            ui.echo("Sorry Master Bruce. I cannot find the file.");
        }
    }

    /**
     * Runs the Alfred chatbot.
     */
    public void run() {
        ui.greet();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            switch (input.trim()) {
            case "bye":
                ui.bye();
                ui.echo(storage.updateData(tasks.getTaskList()));
                return;
            case "list":
                ui.printList(tasks.getTaskList());
                break;
            default:
                if (input.startsWith("unmark")) {
                    ui.echo(tasks.unmarkList(parser.parseDescription(input)));
                } else if (input.startsWith("mark")) {
                    ui.echo(tasks.markList(parser.parseDescription(input)));
                } else if (input.startsWith("delete")) {
                    ui.echo(tasks.deleteList(parser.parseDescription(input)));
                } else if (input.startsWith("find")) {
                    String keyword = parser.parseDescription(input).trim();
                    ui.printList(tasks.findByKeyword(keyword));
                } else if (input.startsWith("date")) {
                    String date = parser.parseDescription(input).trim();
                    ui.printList(tasks.findByDate(parser.parseDateTime(date)));
                } else {
                    ui.echo(tasks.addList(input));
                }
                break;
            }
        }
    }
    public static void main(String[] args) {
        Alfred alfred = new Alfred("data/alfred.txt");
        alfred.run();
    }
}