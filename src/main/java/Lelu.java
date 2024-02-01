import Exceptions.*;
import Tasks.Task;
import Tasks.ToDo;
import Tasks.Event;
import Tasks.Deadline;


import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Lelu {

    public static ArrayList<Task> tasks;
    public static int index;

    private UI ui;

    public Lelu(String filePath) {
        this.ui = new UI();
        ui.greet();
        Lelu.tasks = new ArrayList<>();
        Lelu.index = 0;

        Storage store = new Storage(filePath);
        while (true) {
            try {
                store.load(Lelu.tasks);
                this.listen();
                store.save(Lelu.tasks);
                break;
            } catch (DateTimeParseException e) {
                ui.dateFormatInstructions();
            } catch (LeluException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void list() {
        for (int i = 0; i < Lelu.tasks.size(); i++) {
            System.out.printf("    %d.%s\n", i + 1, tasks.get(i).toString());
        }
        System.out.println();
    }
    public void mark(int i) {
        Task t = Lelu.tasks.get(i);
        t.markTask();
        this.ui.markMessage(t);
    }
    public void unmark(int i) {
        Task t = Lelu.tasks.get(i);
        t.unmarkTask();
        this.ui.unmarkMessage(t);
    }

    public void delete(int i) {
        Task t = Lelu.tasks.get(i);
        Lelu.tasks.remove(i);
        this.ui.deleteMessage(t);
    }
    public void listen() throws InvalidInputException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String taskName = sc.nextLine();

            if (taskName.equals("bye")) {
                ui.exit();
                break;
            } else if (taskName.equals("list")) {
                Lelu.list();
                continue;
            }
            Task t = null;
            switch (taskName.split(" ")[0]) {
                case "mark":
                    this.mark(Integer.parseInt(taskName.split(" ")[1]) - 1);
                    break;
                case "unmark":
                    this.unmark(Integer.parseInt(taskName.split(" ")[1]) - 1);
                    break;
                case "delete":
                    this.delete(Integer.parseInt(taskName.split(" ")[1]) - 1);
                    break;
                case "todo":
                    t = ToDo.ToDoOf(taskName);
                    break;
                case "deadline":
                    t = Deadline.DeadlineOf(taskName);
                    break;
                case "event":
                    t = Event.EventOf(taskName);
                    break;
                default:
                    throw new InvalidInputException(ui.showInstructions());
            }
            if (t != null) {
                Lelu.tasks.add(t);
                ui.addMessage(t);
            }
        }
    }

    public static void main(String[] args) {
        new Lelu("./data/lelu.txt");
     }

}

