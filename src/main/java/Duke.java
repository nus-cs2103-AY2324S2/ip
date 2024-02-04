import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) throws NoCmdException, FileNotFoundException{
        String greet = "Hello! I'm DOUMMI\n" +
                "What can I do for you?";


        System.out.println(greet);

        ArrayList<Task> task = new ArrayList<Task>();

        File store = new File("./data/list.txt");
        if (store.exists()) {
            Scanner reader = new Scanner(store);
            while (reader.hasNextLine()) {
                if (reader.hasNext("T")) {
                    String data = reader.nextLine();
                    String[] divided = data.split("\\|", 2);
                    boolean isDone;
                    String type = divided[0];
                    data = divided[1];
                    divided = data.split("\\|", 2);
                    String status = divided[0];
                    if (status == "1") {
                        isDone = true;
                    } else {
                        isDone = false;
                    }
                    String D = divided[1];
                    Task new_task = new ToDos(D);
                    if (isDone) {
                        new_task.markAsDone();
                    }
                    task.add(new_task);
                } else if (reader.hasNext("D")) {
                    String data = reader.nextLine();
                    String[] divided = data.split("\\|", 2);
                    boolean isDone;
                    String type = divided[0];
                    data = divided[1];
                    divided = data.split("\\|", 2);
                    System.out.println(data);
                    String status = divided[0];
                    if (status == "1") {
                        isDone = true;
                    } else {
                        isDone = false;
                    }
                    data = divided[1];
                    System.out.println(data);
                    divided = data.split("\\|", 2);
                    String D = divided[0];
                    String by = divided[1].trim();
                    System.out.println(by);
                    Task new_task = new Deadline(D, by);
                    if (isDone) {
                        new_task.markAsDone();
                    }
                    task.add(new_task);
                } else {
                    String data = reader.nextLine();
                    String[] divided = data.split("\\|", 2);
                    boolean isDone;
                    String type = divided[0];
                    data = divided[1];
                    divided = data.split("\\|", 2);
                    String status = divided[0];
                    if (status == "1") {
                        isDone = true;
                    } else {
                        isDone = false;
                    }
                    data = divided[1];
                    divided = data.split("\\|", 2);
                    String D = divided[0];
                    data = divided[1];
                    divided = data.split("\\|", 2);
                    String from = divided[0].trim();
                    String to = divided[1].trim();
                    System.out.println(from);
                    System.out.println(to);
                    Task new_task = new Events(D, from, to);
                    if (isDone) {
                        new_task.markAsDone();
                    }
                    task.add(new_task);
                }
            }
            store.delete();
        }


        Scanner cmd = new Scanner(System.in);

        while (!cmd.hasNext("bye")) {
            if (cmd.hasNext("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < task.size(); i++) {
                    int n = i + 1;
                    System.out.println(n + ". " + task.get(i).toString());
                }
                String userCmd = cmd.nextLine();
            } else if (cmd.hasNext("mark")) {
                String userCmd = cmd.nextLine();
                String [] uCmd = userCmd.split(" ");
                int index = Integer.parseInt(uCmd[1]) - 1;
                task.get(index).markAsDone();
                System.out.println("Nice! I've marked this task as done:\n" +
                        "\t" + task.get(index).toString());
            } else if (cmd.hasNext("unmark")) {
                String userCmd = cmd.nextLine();
                String [] uCmd = userCmd.split(" ");
                int index = Integer.parseInt(uCmd[1]) - 1;
                task.get(index).unmark();
                System.out.println("OK, I've marked this task as not done yet:\n" +
                        "\t" + task.get(index).toString());
            } else if (cmd.hasNext("todo")) {
                String userCmd = cmd.nextLine();
                try {
                    processToDos(userCmd, task);
                } catch (ToDosException e){
                    System.out.println("Sir, " + e.getMessage());
                }
            } else if (cmd.hasNext("event")) {
                String userCmd = cmd.nextLine();
                try {
                    processEvents(userCmd, task);
                } catch (EventException | DateException e){
                    System.out.println("Sir, " + e.getMessage());
                }
            } else if (cmd.hasNext("deadline")) {
                String userCmd = cmd.nextLine();
                try {
                    processDeadline(userCmd, task);
                } catch (DeadlineException | DateException e){
                    System.out.println("Sir, " + e.getMessage());
                }
            } else if (cmd.hasNext("delete")) {
                String userCmd = cmd.nextLine();
                String [] uCmd = userCmd.split(" ");
                int index = Integer.parseInt(uCmd[1]) - 1;
                Task removed = task.remove(index);
                String length = "" + task.size();
                System.out.println("Noted. I've removed this task:");
                System.out.println(removed.toString());
                System.out.println("Now you have " + length + " tasks in the list.");

            } else {
                throw new NoCmdException("Please tell me what you want me to do.");
            }
        }

        try {
            FileWriter data = new FileWriter("./data/list.txt");
            String to_record = "";
            for (int i = 0; i < task.size(); i++) {
                Task temp = task.get(i);
                if (temp instanceof ToDos) {
                    System.out.println("EH");
                    String isDone = "0";
                    if (temp.isDone) {
                        isDone = "1";
                    }
                    to_record += "T" +" | " + isDone
                            + " | " + temp.description + "\n";

                } else if (temp instanceof Events) {
                    System.out.println("OH");
                    Events t = (Events) temp;
                    String isDone = "0";
                    if (temp.isDone) {
                        isDone = "1";
                    }
                    to_record += "E" + " | " + isDone
                            + " | " + temp.description + "|"
                            + t.from.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"))
                            + "|" + t.to.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"))
                            + "\n";

                } else if (temp instanceof Deadline) {
                    System.out.println("YOU");
                    Deadline t = (Deadline) temp;
                    String isDone = "0";
                    if (temp.isDone) {
                        isDone = "1";
                    }
                    to_record += "D" + " | " + isDone
                            + " | " + temp.description + "|"
                            + t.by.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"))
                            + "\n";
                }
            }
            data.write(to_record);
            data.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        String bye = "Hope to see you again soon!";

        System.out.println(bye);
    }

    public static void processToDos(String cmd, ArrayList<Task> task) throws ToDosException{
        String [] divided = cmd.split(" ", 2);
        if (divided.length < 2) {
            throw new ToDosException("What todos do you need to record?");
        }
        String D = divided[1];
        Task new_task = new ToDos(D);
        task.add(new_task);
        String length = "" + task.size();
        System.out.println("Got it. I've added this task:\n" +
                "\t" + new_task.toString());
        System.out.println("Now you have " + length + " tasks in the list.");
    }

    public static void processDeadline(String cmd, ArrayList<Task> task) throws DeadlineException, DateException{
        String [] divided = cmd.split(" ",2);
        if (divided.length < 2) {
            throw new DeadlineException("What deadline do you need to record?");
        }
        String D = divided[1];
        divided = D.split("/by", 2);
        if (divided.length < 2) {
            throw new DeadlineException("When do you have to get it done");
        }
        D = divided[0];
        String by = divided[1].trim();
        boolean validateDate = by.matches("[0-9]{4}/[0-9]{2}/[0-9]{2}");
        if (!validateDate) {
            throw new DateException("Invalid format of the date");
        }
        Task new_task =  new Deadline(D, by);
        task.add(new_task);
        String length = "" + task.size();
        System.out.println("Got it. I've added this task:\n" +
                "\t" + new_task.toString());
        System.out.println("Now you have " + length + " tasks in the list.");
    }

    public static void processEvents(String cmd, ArrayList<Task> task) throws EventException, DateException{
        String [] divided = cmd.split(" ", 2);
        if (divided.length < 2) {
            throw new EventException("What event do you need to record?");
        }
        String D = divided[1];
        divided = D.split("/from", 2);
        if (divided.length < 2) {
            throw new EventException("There is no event timeline!");
        }
        D = divided[0];
        String fromTo = divided[1];
        divided = fromTo.split("/to", 2);
        if (divided.length < 2) {
            throw new EventException("There is no event timeline!");
        }
        String from = divided[0].trim();
        String to = divided[1].trim();
        boolean validateFromDate = from.matches("[0-9]{4}/[0-9]{2}/[0-9]{2} [0-9]{4}");
        if (!validateFromDate) {
            throw new DateException("Invalid format of the date");
        }
        boolean validateToDate = to.matches("[0-9]{4}/[0-9]{2}/[0-9]{2} [0-9]{4}");
        if (!validateToDate) {
            throw new DateException("Invalid format of the date");
        }
        Task new_task = new Events(D, from, to);
        task.add(new_task);
        String length = "" + task.size();
        System.out.println("Got it. I've added this task:\n" +
                "\t" + new_task.toString());
        System.out.println("Now you have " + length + " tasks in the list.");
    }
}
