import java.util.Scanner;

public class Duke {

    protected static TaskList lst = new TaskList();
    protected static Storage storage = new Storage();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        intro();
        lst = storage.loadTasks();
        while (sc.hasNextLine()) {
            try {
                String s = sc.nextLine();
                Parser.Command command = Parser.parseCommand(s);
                String taskDetail = Parser.parseTaskDetail(s);
                switch (command) {
                    case LIST:
                        lst.displayList();
                        break;
                    case BYE:
                        exit();
                        Storage.saveTasks();
                        return;
                    case MARK:
                        lst.markComplete(Integer.parseInt(taskDetail.trim()));
                        break;
                    case UNMARK:
                        lst.unmarkComplete(Integer.parseInt(taskDetail.trim()));
                        break;
                    case TODO:
                        lst.addToList(new Todo(taskDetail));
                        break;
                    case DEADLINE:
                        lst.addToList(new Deadline(taskDetail));
                        break;
                    case EVENT:
                        lst.addToList(new Event(taskDetail));
                        break;
                    case DELETE:
                        lst.deleteTask(Integer.parseInt(taskDetail.trim()));
                        break;
                    case UNKNOWN:
                        throw new AllyException();
                }
            } catch (Exception e) {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
    private static void intro() {
        String logo = "        _  _        \n  __ _ | || | _   _ \n / _` || || || | | |\n| (_| || || || |_| |\n \\__,_||_||_| \\__, |\n              |___/ \n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Ally \n" + "What can I do for you?");
    }
    private static void echo(Scanner sc) {
        String task = sc.nextLine();
        while (!task.equals("bye")) {
            System.out.println(task);
            task = sc.nextLine();
        }
    }
    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }


}
