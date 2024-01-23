public class DukeInvalidCommand extends DukeException{
    String command;
    public DukeInvalidCommand(String command) {
        this.command = command;
    }
    @Override
    public String toString() {
        return String.format("%s This is not a valid command: \""+command+"\"", super.toString());
    }
}
