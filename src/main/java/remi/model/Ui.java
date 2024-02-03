package remi.model;

import remi.io.Inputter;
import remi.io.Message;
import remi.io.Outputter;
import remi.model.commands.CommandList;
import remi.parsing.Parser;
import remi.storage.Storage;
import remi.utils.RemiError;

public class Ui {

    private boolean exitLoop;
    private StoredTaskList taskList;
    private CommandList commandList;
    private Parser parser;

    public Ui() {
        this.exitLoop = false;
        this.taskList = Storage.get();
        this.commandList = new CommandList(this.taskList, this);
        this.parser = new Parser(this.commandList);
    }

    /**
     * Initializes the io loop of the chatbot. This includes loading the storage and running the message event loop.
     */
    public void ioLoop() {
        Outputter.outputMessage(new Message("Hello! I'm Remi\n" + "What can I do for you?"));
        Storage.get();

        while (!exitLoop) {
            try {
                Message input = Inputter.inputMessage();
                Message output = parser.parseAndRun(input);
                Outputter.outputMessage(output);
                if (exitLoop)
                    break;
            } catch (RemiError err) {
                Outputter.outputMessage(new Message(err.getMessage()));
            }
        }
    }

    /**
     * Utility function to exit the io loop.
     * If called at least once, termination will occur after all pending commands are finished.
     */
    public void exitIoLoop() {
        exitLoop = true;
    }
}
