public class ConsoleUserInterface implements UserInterface {

    @Override
    public void greetUser() {
        printSeparator();
        System.out.println("Hello! I'm MicroManager");
        System.out.println("What can I do for you?");
        printSeparator();
    }

    @Override
    public String getUserInput() {
        return null;
    }

    @Override
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        printSeparator();
    }

    public void printSeparator() {
        System.out.println("____________________________________________________________");
    }
}
