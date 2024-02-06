public class UI {
    public static void displayBar() {
        System.out.println("____________________________________________________________");
    }
    public static void displayGreetCommand() {
        System.out.println("Liv, under your instructions!");
        System.out.println("What is your command?\n");
    }
    public static void displayByeCommand() {
        System.out.println("Farewell, see you next time!");
    }
    public static void displayEventCommand(Event event) {
        System.out.println("Task added:");
        System.out.println(event.getDisplayedString());
        System.out.println("You have " + TaskList.getListSize() + " mission(s) on the list");
    }
}
