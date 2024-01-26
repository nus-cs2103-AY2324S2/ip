import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public enum Instruction {
        LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, BYE, ANYTHING_ELSE
    }
    
    private static Instruction getInstr(String input) {
        if (input.toLowerCase().startsWith("list")) {
            return Instruction.LIST;
        } else if (input.toLowerCase().startsWith("mark")) {
            return Instruction.MARK;
        } else if (input.toLowerCase().startsWith("unmark")) {
            return Instruction.UNMARK;
        } else if (input.toLowerCase().startsWith("todo")) {
            return Instruction.TODO;
        } else if (input.toLowerCase().startsWith("deadline")) {
            return Instruction.DEADLINE;
        } else if (input.toLowerCase().startsWith("event")) {
            return Instruction.EVENT;
        } else if (input.toLowerCase().startsWith("delete")) {
            return Instruction.DELETE;
        } else if (input.toLowerCase().startsWith("bye")){
            return Instruction.BYE;
        } else {
            return Instruction.ANYTHING_ELSE;
        }
    }
    
    private static void echo(Task task, ArrayList<Task> list) {
        String echo = "Got it. I've added this task:\n" + "  " + task.toString() + "\n"
            + "Now you have " + (list.size() + 1) + " tasks in the list"
            + "\n___________________________________" ;
        System.out.println(echo);
        list.add(task);
    }
    public static void main(String[] args) throws DukeException {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        String greeting = "___________________________________\n"
            + "Hello! I'm Jinni\n"
            + "What can I do for you?\n"
            + "___________________________________" ;
        
        System.out.println(greeting);
        
        while(true) {
            String inputFromUser = sc.nextLine();
            
            Instruction instruction = Duke.getInstr(inputFromUser);
            
            switch (instruction) {
                case LIST:
                    System.out.println("Here are the tasks in your list\n");
                    if(list.size() == 0) {
                        System.out.println("\n___________________________________");
                    } else {
                        int num = 1;
                        for(Task t: list) {
                            System.out.println(num + "." + t.toString() + "\n");
                            num++;
                        }
                        System.out.println("___________________________________");
                    }
                    break;
                case MARK:
                    if (Integer.parseInt(inputFromUser.substring(5)) > list.size()) {
                        throw new DukeException("You do not have that many tasks");
                    }
                    if (Integer.parseInt(inputFromUser.substring(5)) < 1) {
                        throw new DukeException("No negative task number");
                    }
                    int num = Integer.parseInt(inputFromUser.substring(5));
                    Task taskToBeMarked = list.get(num - 1);
                    taskToBeMarked.markDone();
                    System.out.println("Nice! I have marked this task as done\n");
                    System.out.println(taskToBeMarked.toString());
                    System.out.println("___________________________________");
                    break;
                case UNMARK:
                    if (Integer.parseInt(inputFromUser.substring(7)) > list.size()) {
                        throw new DukeException("You do not have that many tasks");
                    }
                    if (Integer.parseInt(inputFromUser.substring(7)) < 1) {
                        throw new DukeException("No negative task number");
                    }
                    int taskNum = Integer.parseInt(inputFromUser.substring(7));
                    Task taskToBeUnmarked = list.get(taskNum - 1);
                    taskToBeUnmarked.markUndone();
                    System.out.println("Ok, I've marked this task as not done yet\n");
                    System.out.println(taskToBeUnmarked.toString());
                    System.out.println("___________________________________");
                    break;
                case TODO:
                    if (!(inputFromUser.substring(4).matches(".*\\S.*"))) {
                        throw new DukeException("Description of the task can't be empty");
                    }
                    String todoDescription = inputFromUser.substring(5);
                    Task todoTask = new ToDos(todoDescription);
                    Duke.echo(todoTask, list);
                    break;
                case DEADLINE:
                    if (!(inputFromUser.substring(8).matches(".*\\S.*"))) {
                        throw new DukeException("Description of the task can't be empty");
                    }
                    int indexOfBy = inputFromUser.indexOf("/by");
                    String deadlineDescription = inputFromUser.substring(9, indexOfBy - 1);
                    String by = inputFromUser.substring(indexOfBy + 4);
                    Task deadlineTask = new Deadlines(deadlineDescription, by);
                    Duke.echo(deadlineTask, list);
                    break;
                case EVENT:
                    if (!(inputFromUser.substring(5).matches(".*\\S.*"))) {
                        throw new DukeException("Description of the task can't be empty");
                    }
                    int indexOfFrom = inputFromUser.indexOf("/from");
                    int indexOfTo = inputFromUser.indexOf("/to");
                    String eventDescription = inputFromUser.substring(6, indexOfFrom - 1);
                    String start = inputFromUser.substring(indexOfFrom + 6, indexOfTo - 1);
                    String end = inputFromUser.substring(indexOfTo + 4);
                    Task eventTask = new Events(eventDescription, start, end);
                    Duke.echo(eventTask, list);
                    break;
                case DELETE:
                    int indexOfTaskToDelete = Integer.parseInt(inputFromUser.substring(7));
                    if (list.size() < 1) {
                        throw new DukeException("No task at the moment");
                    }
                    if (indexOfTaskToDelete > list.size() || indexOfTaskToDelete < 1) {
                        throw new DukeException("Check you task number");
                    }
                    Task taskToDelete = list.get(indexOfTaskToDelete - 1);
                    String toPrint = "Noted. I've removed this task:\n" + "  " + taskToDelete.toString() + "\n"
                        + "Now you have " + (list.size() - 1) + " tasks in  the list"
                        + "\n___________________________________" ;
                    System.out.println(toPrint);
                    list.remove(taskToDelete);
                    break;
                case BYE:
                    String bye = "___________________________________\n"
                        + "Bye. Hope to see you again soon!\n"
                        + "___________________________________";
                    System.out.println(bye);
                    return;
                default:
                    throw new DukeException("Can't understand your instruction");
            }
        }
    }
}