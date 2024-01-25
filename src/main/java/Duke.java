import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
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
            
            if ("bye".equalsIgnoreCase(inputFromUser)) {
                String bye = "___________________________________\n"
                    + "Bye. Hope to see you again soon!\n"
                    + "___________________________________";
                System.out.println(bye);
                break;
            }
            else if ("list".equalsIgnoreCase(inputFromUser)){
                System.out.println("Here are the tasks in your list\n");
                if(list.size() == 0) {
                    System.out.println("\n___________________________________");
                } else {
                    int num = 1;
                    for(Task t: list) {
                        num++;
                        System.out.println(t.toString() + "\n");
                    }
                    System.out.println("___________________________________");
                }
            } else if (inputFromUser.toLowerCase().startsWith("mark")) {
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
            } else if (inputFromUser.toLowerCase().startsWith("unmark")) {
                if (Integer.parseInt(inputFromUser.substring(7)) > list.size()) {
                    throw new DukeException("You do not have that many tasks");
                }
                if (Integer.parseInt(inputFromUser.substring(7)) < 1) {
                    throw new DukeException("No negative task number");
                }
                int num = Integer.parseInt(inputFromUser.substring(7));
                Task taskToBeUnmarked = list.get(num - 1);
                taskToBeUnmarked.markUndone();
                System.out.println("Ok, I've marked this task as not done yet\n");
                System.out.println(taskToBeUnmarked.toString());
                System.out.println("___________________________________");
            } else if (!inputFromUser.toLowerCase().startsWith("mark")
            && !inputFromUser.toLowerCase().startsWith("unmark")
            && !inputFromUser.toLowerCase().startsWith("todo")
            && !inputFromUser.toLowerCase().startsWith("deadline")
            && !inputFromUser.toLowerCase().startsWith("event")
            && !inputFromUser.toLowerCase().startsWith("list")
            && !inputFromUser.toLowerCase().startsWith("bye")) {
                throw new DukeException("Can't understand your command");
            }
            else {
                Task task = null;
                if (inputFromUser.toLowerCase().startsWith("todo")) {
                    if (!(inputFromUser.substring(4).matches(".*\\S.*"))) {
                        throw new DukeException("Description of the task can't be empty");
                    }
                    String description = inputFromUser.substring(5);
                    task = new ToDos(description);
                }
                if (inputFromUser.toLowerCase().startsWith("deadline")) {
                    if (!(inputFromUser.substring(8).matches(".*\\S.*"))) {
                        throw new DukeException("Description of the task can't be empty");
                    }
                    int indexOfBy = inputFromUser.indexOf("/by");
                    String description = inputFromUser.substring(9, indexOfBy - 1);
                    String by = inputFromUser.substring(indexOfBy + 4);
                    task = new Deadlines(description, by);
                }
                if (inputFromUser.toLowerCase().startsWith("event")) {
                    if (!(inputFromUser.substring(5).matches(".*\\S.*"))) {
                        throw new DukeException("Description of the task can't be empty");
                    }
                    int indexOfFrom = inputFromUser.indexOf("/from");
                    int indexOfTo = inputFromUser.indexOf("/to");
                    String description = inputFromUser.substring(6, indexOfFrom - 1);
                    String start = inputFromUser.substring(indexOfFrom + 6, indexOfTo - 1);
                    String end = inputFromUser.substring(indexOfTo + 4);
                    task = new Events(description, start, end);
                }
                if (task != null) {
                    String echo = "Got it. I've added this task:\n" + "  " + task.toString() + "\n"
                        + "Now you have " + (list.size() + 1) + " tasks in the list"
                        + "\n___________________________________" ;
                    System.out.println(echo);
                    list.add(task);
                } else {
                 throw new DukeException("Task is null");
                }
            }
        }
        sc.close();
    }
}