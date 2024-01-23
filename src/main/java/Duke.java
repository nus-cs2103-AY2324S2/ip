import java.util.Scanner;
import java.util.ArrayList;


public class Duke {

    private final String name;
    private ArrayList<Task> taskList = new ArrayList<>();
    private final String line = "____________________________________________________________\n";
    public Duke(String name) {
        this.name = name;
    }
//
    public String greet() {
        return this.line + "Hello! I'm " + this.name + "\n" + "What can I do for you?\n" + this.line;
    }
    public String exit() {
        return this.line + "Bye. Hope to see you again soon!\n" +  this.line ;
    }

    public String printList(){
        String list = this.line + "Here are the tasks in your list:\n";
        for (int i = 0; i<this.taskList.size(); i++) {
            Task task = this.taskList.get(i);
            String message = Integer.toString(i+1) + "." +task.getType()+"[" + task.getStatusIcon() +"] " + task.getDescription() + "\n";
            list += message;

        }
        return list + this.line;
    }

    public String addTask(String command) throws DukeException {
        String msg = this.line + "Got it. I've added this task:\n";
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
        msg = msg+ newTask.getType() + "["+newTask.getStatusIcon() +"]" + " " +
                newTask.getDescription() + newTask.getExtraInfo() +"\nNow you have " +
                Integer.toString(taskList.size()) +" tasks in the list.\n";
        return msg+this.line;


    }
    public void processCmd(String command) throws DukeException {
        if (command.equalsIgnoreCase("list")){
            System.out.println(this.printList());
        } else if (command.startsWith("mark")){
            try{
                Integer id = Integer.parseInt(command.substring(5));
                System.out.println(this.line + this.taskList.get(id-1).markAsDone() + "\n" + this.line);
            } catch (NumberFormatException e) {
                throw new DukeException("Erm... Please enter a valid task number.");
            } catch (IndexOutOfBoundsException e){
                throw new DukeException("Erm... Please enter a task number.");
            }

        } else if (command.startsWith("unmark")){
            try {
                Integer id = Integer.parseInt(command.substring(7));
                System.out.println(this.line + this.taskList.get(id - 1).markAsDone() + "\n" + this.line);
            } catch (NumberFormatException e) {
                throw new DukeException("Erm... Please enter a valid task number.");
            } catch (IndexOutOfBoundsException e){
                throw new DukeException("Erm... Please enter a task number.");
            }
        }else {
            System.out.println(this.addTask(command));

        }
    }



    public void startChat() {
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
                System.out.println(this.line+e.getMessage() +"\n"+this.line);
            }
        }
    }

    public static void main(String[] args) {
        Duke Lery = new Duke("Lery");
        Lery.startChat();

    }


}
