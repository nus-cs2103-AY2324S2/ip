package command;

import duke.Duke;

public class Command {
    public String[] inputs;
    public Duke.CommandType inputType;

    public Command(String[] inputs, Duke.CommandType inputType) {
        this.inputs = inputs;
        this.inputType = inputType;
    }
}
