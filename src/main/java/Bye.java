/**
 * Command to exit the program
 */
public class Bye implements Command{
    /**
     * Override the interface method.
     * exit the program
     */
    @Override
    public void reply() {
        System.out.println("    Bye. Hope to see you again soon!");
        System.exit(0);
    }
}
