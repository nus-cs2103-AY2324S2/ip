package axolotl.command;

public class CommandResult {

    public final String output;

    public CommandResult(String output) {
        this.output = output;
    }

    @Override
    public String toString() {
        return output;
    }
}
