public class Bye implements Command{
    @Override
    public void reply() {
        System.out.println("    Bye. Hope to see you again soon!");
        System.exit(0);
    }
}
