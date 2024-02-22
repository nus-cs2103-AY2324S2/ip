package command;

import xiaobai.XiaoBai;

/**
 * Represents a command issued by the user.
 * A command consists of an array of inputs and a corresponding input type.
 */
public class Command {
    private String[] inputs;
    private XiaoBai.CommandType inputType;

    public Command(String[] inputs, XiaoBai.CommandType inputType) {
        this.inputs = inputs;
        this.inputType = inputType;
    }

    public XiaoBai.CommandType getInputType() {
        return this.inputType;
    }

    public String[] getInputs() {
        return this.inputs;
    }
}
