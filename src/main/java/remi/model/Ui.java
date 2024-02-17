package remi.model;

import remi.io.Inputter;
import remi.io.Message;
import remi.io.Outputter;
import remi.model.commands.CommandList;
import remi.parsing.Parser;
import remi.storage.Storage;
import remi.utils.RemiError;

/**
 * Handles the input output loop, receiving commands and sending output to the console. Supposed to be the "main" class
 * of the application.
 */
public class Ui {

    private boolean isFinished;
    private StoredTaskList taskList;
    private CommandList commandList;
    private Parser parser;

    /**
     * Basic constructor for the Ui class. Initializes the TaskList, CommandList, and Parser objects.
     */
    public Ui() {
        isFinished = false;
        taskList = Storage.get();
        commandList = new CommandList(taskList, this);
        parser = new Parser(commandList);
    }


    /**
     * Initializes the io loop of the chatbot. This includes loading the storage and running the message event loop.
     */
    public void doIoLoop() {
        initialize();

        while (!isFinished) {
            doOnce();
            if (isFinished) {
                break;
            }
        }
    }

    /**
     * Does the first step in initializing the IO loop.
     */
    public void initialize() {
        Outputter.outputMessage(new Message("Hello! I'm Remi\n" + "What can I do for you?"));
    }

    /**
     * Does one iteration of the io loop. Gets input from stdin.
     */
    public void doOnce() {
        Message input = Inputter.inputMessage();
        Message output = doOnce(input);
        Outputter.outputMessage(output);
    }

    /**
     * Does one iteration of the io loop. Receives input as a parameter.
     *
     * @param input the input in the Message format.
     * @return the message to be outputted.
     */
    public Message doOnce(Message input) {
        try {
            return parser.parseAndRun(input);
        } catch (RemiError err) {
            return new Message(err.getMessage());
        }
    }

    /**
     * Utility function to exit the io loop.
     * If called at least once, termination will occur after all pending commands are finished.
     */
    public void exitIoLoop() {
        isFinished = true;
    }

    public boolean getIsFinished() {
        return isFinished;
    }
}
