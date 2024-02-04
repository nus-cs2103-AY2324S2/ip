public class Ui {

    public final String divider = "---------------------------------------------------------------";
    Ui() {
    }

    public void welcome() {
        String chat_name = "Dwight Schrute";
        System.out.printf("%s\nHello! I'm %s\nWhat Can I do for you?\n%s\n"
                , divider, chat_name, divider);
    }

    public void divider() {
        System.out.println(divider);
    }

    public void showCommands() {
        System.out.println("1. list - Lists out tasks");
        System.out.println("2. mark [index] - Marks task at given index as done");
        System.out.println("3. unmark [index] - Unmarks task at given index as done");
        System.out.println("4. todo [task] - Adds todo task");
        System.out.println("5. event [task] /from [start date] /to [end date] - Adds event task");
        System.out.println("6. deadline [task] /by [d/MM/yyyy HH:mm] - Adds deadline task");
        System.out.println("7. delete [index] - Deletes task at index");
        System.out.println("8. bye - Exits program");
    }

    public void showLoadingError() {
        System.out.println("Failed to load file");
    }

    public void invalidCommand() {
        System.out.println("Sorry, I didn't understand you. Here are a list of commands!");
        showCommands();
    }
}
