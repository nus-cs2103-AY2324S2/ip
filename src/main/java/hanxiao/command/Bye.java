package hanxiao.command;

/**
 * duke.command.Command to exit the program
 */
public class Bye implements Command {
    /**
     * Override the interface method.
     * exit the program
     */
    @Override
    public String reply() {
        System.exit(0);
        assert true : "Should not reach this line.";
        return "Bye, Hope to see you again soon!\n";
    }
}
