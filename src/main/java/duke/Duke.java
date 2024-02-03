package duke;

import duke.command.Command;
import duke.util.Parser;
import duke.util.TaskList;
import duke.util.Ui;

import java.util.Scanner;

/**
 * Represents a chatbot whose name is "Alfred".
 * It is a task management app for recording different kinds of tasks.
 * It provides a czommand Line interface for users to interact with.
 */
public class Duke {
    private Parser parser;
    private Ui ui;
    private TaskList taskList;

    /**
     * Constructs a new instance of the Duke class.
     * This constructor initializes the Parser, Ui, and TaskList for the Duke application.
     */
    public Duke(){
        this.parser = new Parser();
        this.ui = new Ui();
        this.taskList = new TaskList();
    }
    /**
     * Runs the Duke application.
     * This method prompts the user with a greeting, accepts and processes user commands,
     * and continues execution until the user enters the "bye" command.
     * Handles invalid commands by informing the user.
     */
    public void run(){
        this.ui.greet();
        Scanner scanner = new Scanner(System.in);
        try {
            Command command;
            do{
                command = this.parser.parse(scanner.nextLine());
                command.run(this.taskList);
            }while(!command.getType().equals(Parser.Cmd.bye));
        }catch(IllegalArgumentException e){
            Ui.informInvalidCommand();
        }
    }

    public static void main(String[] args) {
        Duke Alfred = new Duke();
        Alfred.run();
    }
}
