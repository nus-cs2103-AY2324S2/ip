package osiris;

import java.util.Scanner;

import osiris.commands.Command;
import osiris.interpreters.UserInputInterpreter;
import osiris.task.TaskManager;
import osiris.ui.Ui;

public class Osiris {

    private final TaskManager taskManager = new TaskManager();
    private final Ui userInterface = new Ui();

    public void startChat() {
        Scanner scanner = new Scanner(System.in);
        this.taskManager.initialise();

        this.userInterface.outputIntroductions();

        boolean terminateChat = false;

        while (!terminateChat) {
            this.userInterface.messageOsirisPrompt();
            String userInput = scanner.nextLine();

            Command userCommand = UserInputInterpreter.getInstance().interpretUserInput(userInput);
            userCommand.execute(this.taskManager, this.userInterface);
            terminateChat = userCommand.isTerminateChat();
        }

        this.userInterface.outputGoodbyes();
    }

}
