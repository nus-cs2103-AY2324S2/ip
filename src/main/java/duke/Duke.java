package duke;

import java.util.Scanner;

import duke.dukeException.InvalidCommandException;
import duke.dukeException.ListOutofBoundsException;
import duke.dukeException.MissingArgumentsException;

import duke.dukeException.WrongTimeFormatException;

import duke.ui.UI;

import duke.parser.Parser;
import duke.parser.Token;

/**
 * The Duke program is a simple task manager that allows users to manage their tasks through a command-line interface.
 */
public class Duke {

    /**
     * Runs the Duke program. Allowing users to manage their tasks.
     *
     * @param args The command-line arguments (unused in this program).
     */
    public static void main(String[] args) {
        UI ui = new UI();
        UI.greeting();
        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser();
        boolean isTrue = true;
        while(isTrue) {
            String input = scanner.nextLine().trim();
            parser.feed(input);
            Token output;
            try {
                output = parser.parse();
            } catch (InvalidCommandException e) {
                UI.error(e.getMessage());
                continue;
            } catch (MissingArgumentsException e) {
                UI.error(e.getMessage());
                continue;
            } catch (WrongTimeFormatException e) {
                UI.error(e.getMessage());
                continue;
            }
            switch (output.getCmd()) {
            case BYE:
                UI.goodbye();
                isTrue = false;
                break;
            case LIST:
                ui.listItems();
                break;
            case UNMARK:
                try {
                    ui.unMarkTask(output.getSelectedItem() - 1);
                } catch (ListOutofBoundsException e){
                    UI.error(e.getMessage());
                }
                break;
            case MARK:
                try {
                    ui.markTaskUI(output.getSelectedItem() - 1);
                } catch (ListOutofBoundsException e) {
                    UI.error(e.getMessage());
                }
                break;
            case FIND:
                ui.findTaskUI(output.getSearchKey());
                break;
            case TODO:
                // Fallthrough
            case DEADLINE:
                // Fallthrough
            case EVENT:
                ui.addItem(output.getTask());
                break;
            case DELETE:
                try {
                    ui.removeTask(output.getSelectedItem() - 1);
                } catch (ListOutofBoundsException e) {
                    UI.error(e.getMessage());
                }
                break;
            default:
                break;
            }
        }
    }
}
