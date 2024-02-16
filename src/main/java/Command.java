public class Command {
    String[] inputs;
    Duke.CommandType inputType;

    Command(String[] inputs, Duke.CommandType inputType) {
        this.inputs = inputs;
        this.inputType = inputType;
    }
}
