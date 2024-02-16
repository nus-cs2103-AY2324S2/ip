package command;

import duke.Duke;

/**
 * Represents a command issued by the user.
 * A command consists of an array of inputs and a corresponding input type.
 */
public class Command {
    private String[] inputs;
    private Duke.CommandType inputType;

    public Command(String[] inputs, Duke.CommandType inputType) {
        this.inputs = inputs;
        this.inputType = inputType;
    }
    
    public Duke.CommandType getInputType() {
        return this.inputType;
    }

    public String[] getInputs() {
        return this.inputs;
    }
}

