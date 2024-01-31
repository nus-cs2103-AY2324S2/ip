package osiris;

import java.util.Scanner;

import osiris.commands.Command;
import osiris.interpreters.UserInputInterpreter;
import osiris.task.TaskManager;
import osiris.ui.Ui;

/**
 * The Osiris class represents the main chatbot application.
 * It manages user interactions, interprets commands, and handles task management.
 */
public class Osiris {

    private final TaskManager taskManager = new TaskManager();
    private final Ui userInterface = new Ui();

    /**
     * Initiates the chat session with the user.
     * Manages user input, interprets commands, and handles task management.
     */
    public void startChat(){
        Scanner scanner = new Scanner(System.in);
        this.taskManager.initialise();

        this.userInterface.outputIntroductions();

        boolean terminateChat = false;

        while (!terminateChat){

            this.userInterface.messageOsirisPrompt();
            String userInput = scanner.nextLine();

            Command userCommand = UserInputInterpreter.getInstance().interpretUserInput(userInput);
            userCommand.execute(this.taskManager, this.userInterface);
            terminateChat = userCommand.isTerminateChat();
        }

        this.userInterface.outputGoodbyes();
    }

}
