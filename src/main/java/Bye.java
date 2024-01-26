/**
 * Command to exit the program
 */
public class Bye implements Command{
    /**
     * Override the interface method.
     * exit the program
     */
    @Override
    public String reply() {
        System.exit(0);
        return "";
    }
}
