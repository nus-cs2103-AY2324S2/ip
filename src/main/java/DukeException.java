public class DukeException extends Exception{
    public DukeException(String tasktype) {
        super("      ______________________________________________________________________________________\n"
                + "      Oops! Please state the description of your "
                + tasktype
                + "\n      ______________________________________________________________________________________\n");
    }

    public DukeException() {
        super("      ______________________________________________________________________________________\n"
                + "      Oops! I'm afraid I don't understand what that means.\n"
                + "      Try adding a todo task like this: todo buy strawberry shortcake \n"
                + "      ______________________________________________________________________________________\n");
    }

}
