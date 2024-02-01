package parser;
import java.io.IOException;

import processor.Processor;

/**
 * The Parser class is responsible for processing user commands and delegating them to the appropriate methods in the Processor class.
 */
public class Parser {
    private final Processor processor;
    
    /**
     * Constructs a Parser object with the specified Processor.
     * 
     * @param processor the Processor object to be used for processing user commands
     */
    public Parser(Processor processor) {
        this.processor = processor;
    }

    /**
     * Processes the given user command by delegating it to the appropriate method in the Processor class.
     * @param userInput the user command to be processed
     * @throws IOException if an I/O error occurs while processing the command
     */
    public void processCommand(String userInput) throws IOException {
        if (userInput.startsWith("delete")) {
            processor.userInputDeleteTask(userInput);
        } else if (userInput.startsWith("mark") || userInput.startsWith("unmark")) {
            processor.userInputProcessMarkUnmark(userInput);
        } else if (userInput.equals("list")) {
            processor.userInputListTasks();
        } else {
            processor.userInputAddTask(userInput);
        }
    }
}