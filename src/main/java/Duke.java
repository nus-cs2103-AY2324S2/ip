import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
/**
 * Duke is a chatbot program that is used to save tasks
 *
 * @author  Lin Shuang Shuang
 * @version 1.0
 * @since   2023-01-25
 */

public class Duke {

    private final String NAME;
    private ArrayList<Task> taskList = new ArrayList<>();
    private final String LINE = "____________________________________________________________\n";
    private final String FILENAME = "./data/duke.txt";
    public Duke(String NAME) {
        this.NAME = NAME;

    }


    public void loadTasks() throws DukeException {
        try {
            File tasksFile = new File(FILENAME);
            if (!tasksFile.exists()) {
                tasksFile.createNewFile();
            } else {
                Scanner s = new Scanner(tasksFile);
                while (s.hasNext()) {
                    String line = s.nextLine();
                    String[] splitLine = line.split(" \\| ");
                    String event = splitLine[2];
                    String type = splitLine[0];
                    Task newTask;
                    if (splitLine.length < 3 || splitLine.length >4) {
                        throw new DukeException("Erm... Textfile not in correct format!" + splitLine.length);
                    }
                    if (type.equalsIgnoreCase("T")) {
                        newTask = new Todo(event);
                    } else if (type.equalsIgnoreCase("D")) {
                        String extraInfo = splitLine[3];
                        newTask = new Deadline(event, extraInfo);
                    } else if (type.equalsIgnoreCase("E")) {
                        String extraInfo = splitLine[3];
                        newTask = new Event(event, extraInfo);
                    } else {
                        throw new DukeException("Erm... Invalid type!" + type);
                    }
                    taskList.add(newTask);

                }
            }
        } catch (IOException e) {
            throw new DukeException("Erm... Error loading tasks from file");
        }
    }

    public void saveTasks(Task newTask) throws DukeException {
        String msg = " | 0 | ";
        msg = "\n" + newTask.getType() + msg + newTask.getDescription();
        if (newTask instanceof Event || newTask instanceof Deadline) {
            msg += "| " + newTask.getExtraInfoShortened();
        }
        try {
            FileWriter fw = new FileWriter(FILENAME, true);
            fw.write(msg);
            fw.close();

        } catch (IOException e) {
            throw new DukeException("Erm... Error saving tasks from file");
        }
    }
    public String greet() {
        return this.LINE + "Hello! I'm " + this.NAME + "\n" + "What can I do for you?\n" + this.LINE;
    }
    public String exit() {
        return this.LINE + "Bye. Hope to see you again soon!\n" +  this.LINE ;
    }

    public String printList(){
        String list = this.LINE + "Here are the tasks in your list:\n";
        for (int i = 0; i<this.taskList.size(); i++) {
            Task task = this.taskList.get(i);
            String message = Integer.toString(i+1) + "." +"[" +task.getType()+"]"+"[" + task.getStatusIcon() +"] " + task.getDescription() + task.getExtraInfo() + "\n";
            list += message;

        }
        return list + this.LINE;
    }

    public String addTask(String command) throws DukeException {
        String msg = this.LINE + "Got it. I've added this task:\n";
        Task newTask = new Task(command);
        if (command.startsWith("todo")) {
            try {
                newTask = new Todo(command.substring(5));
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                throw new DukeException("Erm... Please provide event name.");
            }
        } else if (command.startsWith("deadline")){
            try {
                newTask = new Deadline(command.substring(9));
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                throw new DukeException("Erm... Please provide event details.");
            }
        } else if (command.startsWith("event")) {
            try {
                newTask = new Event(command.substring(6));
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                throw new DukeException("Erm... Please provide event details.");
            }
        } else {
            throw new DukeException("Erm... Please provide a valid command.");
        }
        taskList.add(newTask);
        saveTasks(newTask);
        msg = msg+"["+ newTask.getType()  +"]"+ "["+newTask.getStatusIcon() +"]" + " " +
                newTask.getDescription() + newTask.getExtraInfo() +"\nNow you have " +
                Integer.toString(taskList.size()) +" tasks in the list.\n";
        return msg+this.LINE;


    }

    public String deleteTask(Task task) {
        this.taskList.remove(task);
        String msg = "Noted. I've removed this task:\n" + task.getType() + "[" + task.getStatusIcon() + "]" +
                    " " + task.getDescription() + task.getExtraInfo() + "\nNow you have " +
                    Integer.toString(taskList.size()) + " tasks in the list.\n";
        return msg;
    }
    public void processCmd(String command) throws DukeException {
        if (command.equalsIgnoreCase("list")) {
            System.out.println(this.printList());
        } else if (command.startsWith("mark")) {
            try {
                Integer id = Integer.parseInt(command.substring(5));
                System.out.println(this.LINE + this.taskList.get(id - 1).markAsDone() + "\n" + this.LINE);
            } catch (NumberFormatException e) {
                throw new DukeException("Erm... Please enter a valid task number.");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Erm... Please enter a task number.");
            }

        } else if (command.startsWith("unmark")) {
            try {
                Integer id = Integer.parseInt(command.substring(7));
                System.out.println(this.LINE + this.taskList.get(id - 1).markAsDone() + "\n" + this.LINE);
            } catch (NumberFormatException e) {
                throw new DukeException("Erm... Please enter a valid task number.");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Erm... Please enter a task number.");
            }
        } else if (command.startsWith("delete")) {
            try {
                Integer id = Integer.parseInt(command.substring(7));
                System.out.println(this.LINE + this.deleteTask(this.taskList.get(id - 1)) + "\n" + this.LINE);
            } catch (NumberFormatException e) {
                throw new DukeException("Erm... Please enter a valid task number.");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Erm... Please enter a task number.");
            }
        } else {
            System.out.println(this.addTask(command));

        }
    }



    public void startChat() throws DukeException {
        loadTasks();
        System.out.println(this.greet());
        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNext()) {
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase("bye")) {
                System.out.println(this.exit());
                break;
            }
            try {
                this.processCmd(command);
            } catch (DukeException e) {
                System.out.println(this.LINE+e.getMessage() +"\n"+this.LINE);
            }
        }
    }

    /**
     * This is the main method which starts the chatbot.
     * @param args Unused.
     * @return Nothing.
     * @exception IOException On input error.
     * @see IOException
     */
    public static void main(String[] args) throws DukeException {
        Duke Lery = new Duke("Lery");
        Lery.startChat();

    }


}
