package parser;
import java.io.IOException;

import processor.Processor;

public class Parser {
    private final Processor processor;
    

    public Parser(Processor processor) {
        this.processor = processor;
    }

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