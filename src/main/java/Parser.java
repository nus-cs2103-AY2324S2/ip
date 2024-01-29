import java.util.Scanner;
public class Parser {
    private Scanner scanner;
    private TaskList tasklist;
    private Ui ui;
    private Storage storage;
    public Parser(Scanner s, TaskList t, Storage st){
        scanner = s;
        tasklist = t;
        ui = new Ui();
        storage = st;
    }

    public void read() {
        while (scanner.hasNext()) {

            String userInput = scanner.nextLine();
            String userInputLowercase = userInput.toLowerCase();

            if (checkFeeding(userInputLowercase)) {
                ui.happy();
                continue;
            } else if (checkIfBaseCommand(userInputLowercase)) {
                handleBaseCommand(userInput.split(" "));
                storage.writeToFile();
            } else if (checkIfLeave(userInputLowercase)) {
                ui.goodbye();
                break;
            } else if (checkIfList(userInputLowercase)) {
                handleList(tasklist);
            }
        }
    }

    public void handleList(TaskList t) {
        if (t.length() == 0) {
            System.out.println("You're a lazy duck get back on the grind");
        } else {
            t.iterateout();
        }
    }
    public boolean checkIfList(String f) {
        return (f.equals("list"));
    }
    public boolean checkFeeding(String f) {
        return (f.equals("feed bread to bearducky"));
    }

    public boolean checkIfLeave(String f) {
        return (f.equals("bye"));
    }
    public boolean checkIfBaseCommand(String f) {
        return (f.startsWith("mark ") || f.startsWith("unmark ") || f.startsWith("delete "));
    }

    public void handleBaseCommand(String[] commandsplit) {
        String firstword = commandsplit[0].toLowerCase();
        int num = Integer.parseInt(commandsplit[1]);
        try {
            if (firstword.equals("mark")) {
                tasklist.mark(num - 1); //REMEMBER YOU NEED TO UPDATE STORAGE or just put after every function is it
            } else if (firstword.equals("unmark")) {
                tasklist.unmark(num - 1);
            } else if (firstword.equals("delete")) {
                tasklist.delete(num - 1);
            }
        } catch (NumberFormatException e) {
            System.out.println("[angry quacking] I can only mark numbers!");
        } catch (IndexOutOfBoundsException a) {
            System.out.println("[exasperated quacking] You're not that busy - numbers from 1 to " + tasklist.length() +
                    " only, please.");
        }

    }

}
