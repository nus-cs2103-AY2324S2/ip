import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
                storage.writeToFile(tasklist);
            } else if (checkIfLeave(userInputLowercase)) {
                ui.goodbye();
                break;
            } else if (checkIfList(userInputLowercase)) {
                handleList(tasklist);
            } else if (checkIfTodo(userInputLowercase)) {
                handleTodo(userInput, tasklist);
                storage.writeToFile(tasklist);
            } else if (checkIfEvent(userInputLowercase)) {
                handleEvent(userInput, tasklist);
                storage.writeToFile(tasklist);
            } else if (checkIfDeadline(userInputLowercase)) {
                handleDeadline(userInput, tasklist);
                storage.writeToFile(tasklist);
            } else {
                ui.instructionMessage();
            }
        }
    }

    public void handleEvent(String s, TaskList t) {
        String eventname = "";
        String[] temp = s.split(" ");
        if (temp.length == 1 || temp[1].startsWith("/from")) {
            System.out.println("Event cannot be blank");
            return;
        }
        for (int a = 1; a < temp.length; a++) {
            if (temp[a].startsWith("/from")) {
                break;
            }
            eventname = eventname.concat(temp[a]);
            eventname = eventname.concat(" ");
        }
        try {
            String[] findperiod = s.split(" /from ");
            String start = findperiod[1].split(" /to ")[0];
            String end = findperiod[1].split(" /to ")[1];
            if (!canBeHandled(start) || !canBeHandled(end)) {
                System.out.println("Please enter a event with the format event eventname /from dd/mm/yyyy /to dd/mm/yyyy!");
                return;
            }
            Task ne = new Event(eventname, DateConvert(start), DateConvert(end));
            t.add(ne);
            System.out.println("Task added! You now have " + t.length() +" tasks to attend to.");
            return;

        } catch (ArrayIndexOutOfBoundsException b) {
            System.out.println("Please enter a event with the format event eventname /from dd/mm/yyyy /to dd/mm/yyyy!");
        }
    }

    public boolean canBeHandled(String s) {
        return (DateConvert(s) != null);
    }

    public LocalDate DateConvert(String s) {
        String[] patterns = {"MM/dd/yyyy", "M/dd/yyyy", "MM/d/yyyy",
                "M/d/yyyy", "MM-dd-yyyy", "M-dd-yyyy", "MM-d-yyyy", "M-d-yyyy"};
        for (String pattern : patterns) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                return LocalDate.parse(s, formatter);
            } catch (DateTimeParseException e) {
                continue;
            }
        }
        return null;
    }

    public void handleDeadline(String s, TaskList t) {
        String deadlinename = "";
        String[] temp = s.split(" ");
        if (temp.length == 1 || temp[1].startsWith("/by")) {
            System.out.println("Deadline cannot be blank");
            return;
        }
        //create the deadline name
        for (int a = 1; a < temp.length; a++) {
            if (temp[a].startsWith("/by")) {
                break;
            }
            deadlinename = deadlinename.concat(temp[a]);
            deadlinename = deadlinename.concat(" ");
        }

        try {
            String[] finddeadline = s.split(" /by ");
            String deadline = finddeadline[1];
            if (!canBeHandled(deadline)) {
                System.out.println("Please enter a deadline with the format deadline deadlinename /by dd/mm/yyyy!");
                return;
            }
            Task nd = new Deadline(deadlinename, DateConvert(deadline));
            t.add(nd);
            System.out.println("Task added! You now have " + t.length() +" tasks to attend to.");
            return;
        } catch (ArrayIndexOutOfBoundsException b) {
            System.out.println("Please enter a deadline with the format deadline deadlinename /by dd/mm/yyyy!");
        }
    }
    public void handleTodo(String s, TaskList t) {
        String todoname = "";
        String[] temp = s.split(" ");
        if (temp.length == 1) {
            System.out.println("Todo cannot be blank");
            return;
        }
        for (int a = 1; a < temp.length; a++) {
            todoname = todoname.concat(temp[a]);
            todoname = todoname.concat(" ");
        }
        Task nt = new ToDo(todoname);
        t.add(nt);
        System.out.println("Task added! You now have " + t.length() +" tasks to attend to.");
    }
    public boolean checkIfTodo(String s) {
        return s.startsWith("todo ");
    }

    public boolean checkIfEvent(String s) {
        return s.startsWith("event ");
    }

    public boolean checkIfDeadline(String s) {
        return s.startsWith("deadline ");
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
                tasklist.mark(num - 1);
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
