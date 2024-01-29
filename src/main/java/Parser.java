import java.util.Scanner;
public class Parser {
    private Scanner scanner;
    private TaskList tasklist;
    private Ui ui;
    public Parser(Scanner s, TaskList t){
        scanner = s;
        tasklist = t;
        ui = new Ui();
    }

    public void read() {
        while (scanner.hasNext()) {
            String userInput = scanner.nextLine();
            String userInputLowercase = userInput.toLowerCase();
            if (userInputLowercase.equals("feed bread to bearducky")) {
                ui.happy();
                continue;
            } else if (checkIfBaseCommand(userInputLowercase)) {
                handleBaseCommand(userInput.split(" "));
            }
        }
    }

    public boolean checkIfBaseCommand(String f) {
        return (f.startsWith("mark ") || f.startsWith("unmark ") || f.startsWith("delete "));
    }

    public void handleBaseCommand(String[] commandsplit) {
        String firstword = commandsplit[0].toLowerCase();
        int num = Integer.parseInt(commandsplit[1]);
        if (firstword.equals("mark")) {
            tasklist.mark(num); //REMEMBER YOU NEED TO UPDATE STORAGE or just put after every function is it
        } else if (firstword.equals("unmark")) {
            tasklist.unmark(num);
        } else if (firstword.equals("delete")) {

        }

    }

}
