package duke;

import java.util.Scanner;

public class Duke {
    private final Storage storage;
    private final ItemList itemList;
    private final UI ui;
    private Parser parser;

    public Duke(String filepath) {
        this.storage = new Storage(filepath);
        this.itemList = storage.readFromFile();
        this.ui = new UI();
        this.parser = new Parser(itemList);
    }



    public static void main(String[] args) {
        Duke elias = new Duke("./data/duke.txt");
        System.out.println(elias.ui.logo);
        System.out.println(elias.ui.format(elias.ui.greet));
        elias.parser = new Parser(elias.itemList);
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        while (!command.equals("bye")) {
            try {
                String out = elias.parser.parse(command);
                if (!(out == null)) {
                    System.out.println(elias.ui.format(out));
                }
            } catch (CustomExceptions.unrecognizedCommandException e) {
                System.out.println(elias.ui.format("Sorry I do not recognize this command: " + command));
            } catch (CustomExceptions e) {
                System.out.println(elias.ui.format(e.getMessage()));
            }
            command = sc.nextLine();
        }

        elias.storage.writeToFile(elias.itemList);
        System.out.println(elias.ui.format(elias.ui.bye));
    }
}
