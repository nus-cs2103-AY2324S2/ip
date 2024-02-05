public class Parser {
    private String[] validCommands;
    Parser(String[] validCommands) {
        this.validCommands = validCommands;
    }

    public boolean isInputValid(String input) {
        String[] splited = input.split(" ");
        String command = splited[0];
        for (String validCommand: validCommands) {
            if (command.equals(validCommand)) {
                return true;
            }
        }
        return false;
    }

    public boolean is

    public String getCommand(String input) {
        String[] splited = input.split(" ");
        String command = splited[0];
        return command;
    }
}
