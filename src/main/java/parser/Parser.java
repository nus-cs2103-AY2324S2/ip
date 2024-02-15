package parser;

import java.io.IOException;

import processor.*;

/**
 * The Parser class is responsible for processing user commands and delegating them
 * to the appropriate methods in the Processor class.
 */
public class Parser {

    private final AddTaskProcessor addTaskProcessor;
    private final DeleteTaskProcessor deleteTaskProcessor;
    //private final ExitProcessor exitProcessor;
    private final FindTaskProcessor findTaskProcessor;
    private final ListTasksProcessor listTasksProcessor;
   //private final MarkUnMarkTaskProcessor markTaskProcessor;
    private final MarkTaskProcessor markTaskProcessor;
    private final UnmarkTaskProcessor unmarkTaskProcessor;

    public Parser(Factory factory) {
        this.addTaskProcessor = factory.createAddTaskProcessor();
        this.deleteTaskProcessor = factory.createDeleteTaskProcessor();
        // this.exitProcessor = factory.createExitProcessor();
        this.findTaskProcessor = factory.createFindTaskProcessor();
        this.listTasksProcessor = factory.createListTasksProcessor();
        this.markTaskProcessor = factory.createMarkTaskProcessor();
        this.unmarkTaskProcessor = factory.createUnmarkTaskProcessor();
    }

    /**
     * Processes the given user command by delegating it to the appropriate method in the Processor class.
     * @param userInput the user command to be processed
     * @throws IOException if an I/O error occurs while processing the command
     */
    public void parse(String userInput) throws IOException {
        switch (userInput.split(" ")[0]) {
        case "find":
            findTaskProcessor.processCommand(userInput);
            break;
        case "delete":
            deleteTaskProcessor.processCommand(userInput);
            break;
        case "mark":
            markTaskProcessor.processCommand(userInput);
            break;
        case "unmark":
            unmarkTaskProcessor.processCommand(userInput);
            break;
        case "list":
            listTasksProcessor.processCommand(userInput);
            break;
        default:
            addTaskProcessor.processCommand(userInput);
            break;
        }
    }
}