package command;

import xiaobai.XiaoBai;

/**
 * Represents a command issued by the user.
 * A command consists of an array of inputs and a corresponding input type.
 */
public class Command {
    private String[] inputs;
    private XiaoBai.CommandType inputType;

    /**
     * Constructs a new Command object with the given inputs and input type.
     * 
     * @param inputs    The array of inputs for the command.
     * @param inputType The type of input for the command.
     */
    public Command(String[] inputs, XiaoBai.CommandType inputType) {
        this.inputs = inputs;
        this.inputType = inputType;
    }

    /**
     * Retrieves the input type of this command.
     * 
     * @return The input type of this command.
     */
    public XiaoBai.CommandType getInputType() {
        return this.inputType;
    }

    /**
     * Retrieves the array of inputs for this command.
     * 
     * @return The array of inputs for this command.
     */
    public String[] getInputs() {
        return this.inputs;
    }
}
