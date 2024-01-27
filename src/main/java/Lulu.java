import command.Command;
import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import ui.UI;

import exceptions.LuluException;
import exceptions.InvalidCommandException;
import exceptions.InvalidDateException;
import exceptions.InvalidSlashParameterException;

public class Lulu {
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    public Lulu() {
        storage = new Storage("src/main/java/data/lulu.txt");
        tasks = new TaskList(storage.retrieveLines());
        parser = new Parser();
    }

    public void start() {
        UI.print("Hello! I'm Lulu \n\tWhat can I do for you?");
    }

    public void exit() {
        UI.print("Bye. Hope to see you again soon!");
    }

    public void respond() {
        while (true) {
            String input = UI.nextLine();

            try {
                if (input.toLowerCase().equals("bye")) {
                    break;
                } else if (input.toLowerCase().equals("list")) {
                    UI.printTasks(this.tasks);
                } else {
                    Command command = parser.parse(input);
                    command.execute(this.tasks, this.storage);
                }
            } catch (InvalidCommandException e) {
                UI.print("Sorry, I dont think I quite understood what you meant...");
            } catch (InvalidDateException e) {
                UI.print("Please ensure that you are inputting valid start and end dates.");
            } catch (InvalidSlashParameterException e) {
                UI.print("Please ensure that you are inputting valid date parameters.");
            } catch (LuluException e) {
                UI.print(e.getMessage());
            }
        }
    }
    public static void main(String[] args) {
        Lulu chatbot = new Lulu();
        chatbot.start();
        try {
            chatbot.respond();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        chatbot.exit();
    }
}