package duke;

import java.util.Scanner;

/**
 * Represents an instance of the chatbot Elias, which is
 * this project's rendition of project Duke. Elias is able
 * to take in commands to add, modify or delete items in a
 * ItemList. On exit, Items in the ItemList are serialized
 * and stored in the duke.txt file. The duke.txt file is created
 * when no such file exists, and is created in the ./data directory
 * unless no such directory exists, where it is added to the root.
 */
public class Duke {
    private final Storage storage;
    private final ItemList itemList;
    private final UI ui;
    private Parser parser;

    /**
     * Creates a new instance of Duke as Elias.
     *
     * @param filepath is a string that contains the path to the
     *                 directory to store and retrieve the serialized
     *                 ItemList in the duke.txt file
     */
    public Duke(String filepath) {
        this.storage = new Storage(filepath);
        this.itemList = storage.readFromFile();
        this.ui = new UI();
        this.parser = new Parser(itemList);
    }



    public static void main(String[] args) {
        Duke elias = new Duke("./data/duke.txt");
        System.out.println(elias.ui.getLogo());
        System.out.println(elias.ui.format(elias.ui.getGreet()));
        elias.parser = new Parser(elias.itemList);
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        while (!command.equals("bye")) {
            try {
                String out = elias.parser.parse(command);
                if (!(out == null)) {
                    System.out.println(elias.ui.format(out));
                }
            } catch (CustomExceptions.UnrecognizedCommandException e) {
                System.out.println(elias.ui.format("Sorry I do not recognize this command: " + command));
            } catch (CustomExceptions e) {
                System.out.println(elias.ui.format(e.getMessage()));
            }
            command = sc.nextLine();
        }

        elias.storage.writeToFile(elias.itemList);
        System.out.println(elias.ui.format(elias.ui.getBye()));
    }
}
