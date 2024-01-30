import Commands.Command;
import Interpreters.UserInputInterpreter;
import Task.Task;
import Task.TaskManager;
import UI.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Osiris {

    private final TaskManager taskManager = new TaskManager();
    private final Ui userInterface = new Ui();

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
