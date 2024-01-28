import java.io.IOException;
import java.time.LocalDate;

public class Ui {
    public Ui() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public void greet() {
        String text = "\t____________________________________________________________\n"
                + "\tHello! I'm Teemo!\n"
                + "\tWhat can I do for you?\n"
                + "\t____________________________________________________________\n";

        System.out.println(text);
    }

    public void nextCommand(Options choice, TaskList taskList, String trail, Storage storage) throws DukeException, IOException {
        LocalDate now = LocalDate.now(); // Current date
        Task add;
        String taskName;

        switch (choice) {
            case bye:
                exit();
                break;

            case list:
                taskList.List();
                break;

            case delete:
                int no = Integer.parseInt(trail);
                taskList.delete(no);
                break;

            case mark:
                int markNo = Integer.parseInt(trail);
                taskList.mark(markNo);
                break;

            case unmark:
                int unmarkNo = Integer.parseInt(trail);
                taskList.unmark(unmarkNo);
                break;

            case todo:
                if (trail.isEmpty()) {throw new DukeException("Description of a ToDo cannot be empty!");}
                add = new ToDo(trail);
                taskList.add(add);
                break;

            case deadline:
                if (!trail.contains(" /by ")) {
                    throw new DukeException("Description of a Deadline must contain \" /by \"!");
                }
                taskName = trail.substring(0, trail.indexOf(" /by "));
                if (taskName.isEmpty()) {throw new DukeException("Description of a Deadline cannot be empty!");}
                String by = trail.substring(trail.indexOf(" /by ") + 5);
                // depending on whether by can be empty or not
                // if (by.isEmpty()) {throw new DukeException("Deadline cannot be empty!");}

                LocalDate d1 = LocalDate.parse(by);

                if (d1.isBefore(now)) {
                    throw new DukeException("Deadline must be after today!");
                }

                add = new Deadline(taskName, d1);
                taskList.add(add);
                break;

            case event:
                if (!trail.contains(" /from ") || !trail.contains(" /to ")) {
                    throw new DukeException("Description of a Event must contain \" /from \" and \" /to \"!");
                }
                taskName = trail.substring(0, trail.indexOf(" /from "));
                if (taskName.isEmpty()) {throw new DukeException("Description of a Event cannot be empty!");}
                int a = trail.indexOf(" /from ") + 7;
                int b = trail.indexOf(" /to ");
                if (a > b) {throw new DukeException("From cannot be empty!");}
                String from = trail.substring(a, b);
                // depending on whether from can be empty or not
                // if (from.isEmpty()) {throw new DukeException("From cannot be empty!");}
                String to = trail.substring(trail.indexOf(" /to ") + 5);
                // depending on whether to can be empty or not
                // if (to.isEmpty()) {throw new DukeException("To cannot be empty!");}

                LocalDate d2 = LocalDate.parse(from);
                LocalDate d3 = LocalDate.parse(to);

                if (d3.isBefore(d2)) {
                    throw new DukeException("To must be after From!");
                }

                if (d2.isBefore(now)) {
                    throw new DukeException("From must be after today!");
                }

                add = new Event(taskName, d2, d3);
                taskList.add(add);
                break;

            case save:
                storage.save(taskList.getTaskList());
                break;

            case error:
                throw new DukeException("Command not found! Please try again.");
        }
    }

    public void exit() {
        String text = "\t____________________________________________________________\n"
                + "\tBye. Hope to see you again soon!\n"
                + "\t____________________________________________________________\n";

        System.out.println(text);
    }




    public void showLoadingError() {
        String text = "\t____________________________________________________________\n"
                + "\tSave file not found, creating a new one.\n"
                + "\t____________________________________________________________\n";
        System.out.println(text);
    }
}
