package model;

import io.Inputter;
import io.Message;
import io.Outputter;
import model.commands.CommandList;
import parsing.Parser;

public class Chatbot {

    private boolean exitLoop;
    private TaskList taskList;
    private CommandList commandList;
    private Parser parser;

    public Chatbot() {
        this.exitLoop = false;
        this.taskList = new TaskList();
        this.commandList = new CommandList(this.taskList);
        this.parser = new Parser(this.commandList);
    }


    public void ioLoop() {
        Outputter.outputMessage(new Message("Hello! I'm Remi\n" + "What can I do for you?\n"));

        while (!exitLoop) {
            Message input = Inputter.inputMessage();
            Message output = parser.parseAndRun(input);
            Outputter.outputMessage(output);
            if (exitLoop)
                break;
        }
    }

    public void exitIoLoop() {
        exitLoop = true;
    }
}
