package mainfiles;

/**
 * Class for receiving user input and storing it as separate arguments.
 */
public class UserInput {
    private final String name;
    private final String arg1;
    private final String arg2;
    private final String arg3;

    /**
     * Constructor class for UserInput.
     * It will separate the user's input into a name and 3 arguments.
     * The name will be the first word, separated by whitespace
     * the other 3 parameters will be separated by the terms "/from", "/by", and "/to".
     * If any of the 3 parameters are left blank, then it will be treated as null.
     * @param input String input of the user
     */
    public UserInput(String input) {
        String[] readInput = input.split(" ", 2);
        this.name = readInput[0].trim();
        if (readInput.length == 1) {
            this.arg1 = null;
            this.arg2 = null;
            this.arg3 = null;
            return;
        }
        String[] arguments = readInput[1].split(" /from | /by | /to ", 3);

        switch (arguments.length) {
        case 1:
            this.arg1 = arguments[0].trim();
            this.arg2 = null;
            this.arg3 = null;
            break;
        case 2:
            this.arg1 = arguments[0].trim();
            this.arg2 = arguments[1].trim();
            this.arg3 = null;
            break;
        case 3:
            this.arg1 = arguments[0].trim();
            this.arg2 = arguments[1].trim();
            this.arg3 = arguments[2].trim();
            break;
        default:
            this.arg1 = null;
            this.arg2 = null;
            this.arg3 = null;
        }
    }

    public boolean arg1Empty() {
        return arg1 == null;
    }

    public boolean arg2Empty() {
        return arg2 == null;
    }

    public boolean arg3Empty() {
        return arg3 == null;
    }

    public String getInputName() {
        return this.name;
    }

    public String getArg1() {
        return this.arg1;
    }

    public String getArg2() {
        return this.arg2;
    }

    public String getArg3() {
        return this.arg3;
    }
}
