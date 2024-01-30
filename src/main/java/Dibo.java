import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Dibo {
    private final String name;
    private final Store store;
    public static void main(String[] args) {
        Dibo dibo = new Dibo(new String[]{"todo", "deadline", "event"}, "Dibo");

        try {
            dibo.start("./data/dibo.txt");
        } catch (DiboException d) {
            System.out.println(d.getMessage());
            return;
        }

        try {
            dibo.takeCommands("./data/dibo.txt");
        } catch (DiboException d) {
            System.out.println(d.getMessage());
            return;
        }

        dibo.end();
    }
    private Dibo(String[] tasks, String name) {
        this.name = name;
        this.store = new Store(tasks);
    }

    private void start(String path) throws DiboException {
        this.loadData(path);
        this.greet();
    }

    private void loadData(String path) throws DiboException {
        try {
            File f = new File(path);
            Scanner sc = new Scanner(f);
            String taskDetails;

            while (sc.hasNextLine()) {
                taskDetails = sc.nextLine();
                String[] details = taskDetails.split("\\|");

                String type = details[0].trim();
                Task t;
                switch (type) {
                case "todo":
                    String description1 = details[2];
                    t = new ToDo(description1);
                    break;
                case "deadline":
                    String description2 = details[2];
                    String by = details[3];
                    t = new Deadline(description2, by);
                    break;
                case "event":
                    String description3 = details[2];
                    String start = details[3];
                    String end = details[4];
                    t = new Event(description3, start, end);
                    break;
                default:
                    throw new DiboException("Sorry sir! Unfortunately the loaded text file "
                            + "contains an invalid task type :O");
                }

                if (Integer.parseInt(details[1].trim()) == 1) {
                    t.markAsDone();
                    System.out.println("hi");
                }

                this.store.addTask(t);
            }
        } catch (FileNotFoundException e) {
            File f = new File(path);
            if (f.mkdir()) {
                System.out.println("Hi sir! We have just created a new text file "
                        + "for you to store your task list :D");
            } else {
                throw new DiboException("Sorry sir! We tried a new text file "
                        + "for you to store your task list but was unable to do so.\n"
                        + "Please do us a favour and check the path name:D");
            }
        } catch (DateTimeParseException e) {
            throw new DiboException("Oh no sir! The file seems to be corrupted :O "
                    + " The dates are not in the correct format. It ought to be: yyyy-mm-dd  :(");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DiboException("Oh no sir! The file seems to be corrupted :O "
                    + " You might want to take a look at the formatting of the text file :(");
        }
    }

    private void greet() {
        System.out.println("Hello sir! I'm " + this.name + "\n"
                + "What can I do for you today?");
    }

    private void takeCommands(String path) throws DiboException {
        // Getting the command
        Scanner sc = new Scanner(System.in);
        boolean hasEnded = false;

        // Responding
        while (!hasEnded) {
            String command = sc.nextLine();
            String[] commandDetails = command.split(" ");
            switch (commandDetails[0]) {
            case "list":
                this.store.displayStore();
                break;
            case "unmark":
                try {
                    int taskId = Integer.parseInt(commandDetails[1]);
                    this.store.unmarkTask(taskId);
                } catch (NumberFormatException e) {
                    System.out.println("Oh no sir! You have to unmark the items based on their index."
                            + "If you are not sure of the index, enter 'list' to check it out :D");
                }
                break;
            case "mark":
                try {
                    int taskId = Integer.parseInt(commandDetails[1]);
                    this.store.markTask(taskId);
                } catch (NumberFormatException e) {
                    System.out.println("Oh no sir! You have to mark the items based on their index."
                            + "If you are not sure of the index, enter 'list' to check it out :D");
                }
                break;
            case "delete":
                try {
                    int taskId = Integer.parseInt(commandDetails[1]);
                    this.store.deleteTask(taskId);
                } catch (NumberFormatException e) {
                    System.out.println("Oh no sir! You have to delete the items based on their index."
                            + "If you are not sure of the index, enter 'list' to check it out :D");
                }
                break;
            case "bye":
                hasEnded = true;
                break;
            default:
                try {
                    System.out.println(this.store.addTask(command));
                } catch (DiboException d) {
                    System.out.println(d.getMessage());
                }
                break;
            }

            try {
                this.updateData(path);
            } catch (IOException e) {
                throw new DiboException("Oh no sir! We are unable to update the task lists.\n"
                        + "We are terminating the chatbot :(. Check the file again and restart.\n"
                        + "We will be waiting for you here :D.");
            }
        }
        sc.close();
    }

    private void updateData(String path) throws IOException {
        String updatedData = this.store.getFullList();
        FileWriter fw = new FileWriter(path);
        fw.write(updatedData);
        fw.close();
    }


    private void end() {
        //
        sayGoodbye();
    }

    private void sayGoodbye() {
        System.out.println("Bye sir! Always happy to assist you :D "
                + "Hope to see you again soon!");
    }


}
