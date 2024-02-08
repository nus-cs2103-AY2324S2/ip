package solaire;

import java.util.Scanner;

import solaire.storage.Storage;
import solaire.tasklist.TaskList;
import solaire.ui.Ui;

/**
 * Represents the chatbot, Solaire.
 * The chatbot can keep track of various types of tasks and mark/unmark tasks as complete.
 */
public class Solaire {
    private TaskList taskList;
    private Ui ui;

    enum UserCommands {
        GREET, BYE, MARK, UNMARK, LIST, TODO, EVENT, DEADLINE, DELETE, FIND
    }

    /**
     * Creates a new instance of the chatbot.
     */
    public Solaire() {
        this.ui = new Ui(new Scanner(System.in));
        this.taskList = new TaskList(Storage.loadFromLocal());
    }

    //    public static void main(String[] args) {
    //        Solaire solaire = new Solaire();
    //        solaire.startConversation();
    //    }

    /**
     * Begins the chatbot's working loop.
     * Runs infinitely until user inputs "bye".
     */
    public void startConversation() {
        ui.greet(); // UI

        while (true) {
            String input = ui.acceptInput();

            if (input.equals("bye")) {
                break;
            }
            processInput(input);
            Storage.write(this.taskList.getTaskList());
        }

        ui.waveBye(); // UI
    }

    /**
     * Processes the user input and returns the appropriate response.
     *
     * @param input user input
     * @return String response to user input
     */
    public String processInput(String input) {
        String[] inputCommand = input.split(" ", 2);
        String solaireOutput = "";
        try {
            UserCommands command = UserCommands.valueOf(inputCommand[0].toUpperCase());
            switch (command) {
            case GREET:
                solaireOutput = ui.greet();
                break;
            case BYE:
                solaireOutput = ui.waveBye();
                break;
            case MARK:
                solaireOutput = taskList.markDone(Integer.parseInt(inputCommand[1]));
                break;
            case UNMARK:
                solaireOutput = taskList.unmarkDone(Integer.parseInt(inputCommand[1]));
                break;
            case LIST:
                solaireOutput = taskList.showList();
                break;
            case TODO:
            case DEADLINE:
            case EVENT:
                solaireOutput = taskList.processTaskCommand(input);
                break;
            case DELETE:
                solaireOutput = taskList.processRemoveFromList(input);
                break;
            case FIND:
                solaireOutput = taskList.findTask(inputCommand[1]);
                break;
            default:
                solaireOutput = "Unsupported command pattern\n";
                break;
            }
        } catch (IllegalArgumentException e) {
            solaireOutput = "I am not yet familiar with these commands";
        } finally {
            return solaireOutput;
        }
    }
}
