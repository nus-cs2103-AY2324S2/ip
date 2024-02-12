package parser;

import java.io.IOException;

import processor.*;

/**
 * The Parser class is responsible for processing user commands and delegating them
 * to the appropriate methods in the Processor class.
 */
public class Parser {
    
    private final Factory factory;
    private final AddTaskProcessor addTaskProcessor;
    private final DeleteTaskProcessor deleteTaskProcessor;
    //private final ExitProcessor exitProcessor;
    private final FindTaskProcessor findTaskProcessor;
    private final ListTasksProcessor listTasksProcessor;
    private final MarkUnMarkTaskProcessor markTaskProcessor;

    public Parser(Factory factory) {
        this.factory = factory;
        this.addTaskProcessor = factory.createAddTaskProcessor();
        this.deleteTaskProcessor = factory.createDeleteTaskProcessor();
        // Uncomment the following line when you have implemented createExitProcessor
        // this.exitProcessor = factory.createExitProcessor();
        this.findTaskProcessor = factory.createFindTaskProcessor();
        this.listTasksProcessor = factory.createListTasksProcessor();
        this.markTaskProcessor = factory.createMarkUnmarkTaskProcessor();
    }

    /**
     * Processes the given user command by delegating it to the appropriate method in the Processor class.
     * @param userInput the user command to be processed
     * @throws IOException if an I/O error occurs while processing the command
     */
    public void processCommand(String userInput) throws IOException {

        if (userInput.startsWith("find")) {
            findTaskProcessor.processCommand(userInput);
        } else if (userInput.startsWith("delete")) {
            deleteTaskProcessor.processCommand(userInput);
        } else if (userInput.startsWith("mark") || userInput.startsWith("unmark")) {
            markTaskProcessor.processCommand(userInput);
        } else if (userInput.equals("list")) {
            listTasksProcessor.processCommand(userInput);
        } else {
            addTaskProcessor.processCommand(userInput);
        }
    }
}