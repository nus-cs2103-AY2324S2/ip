import java.lang.reflect.Array;
import java.util.ArrayList;
public class Command {
    ArrayList<Task> tasklist = new ArrayList<>();
    public Command(ArrayList<Task> tasklist) {
        this.tasklist = tasklist;
    }
    public void execute(Task task, String command) {
        if (command.startsWith("mark")) {
            try {
                String str = command.substring(5);
                int number = Integer.parseInt(str) - 1;
                String name = tasklist.get(number).getTask();
                tasklist.get(number).mark();
                System.out.println(task.mark(number, name, tasklist.get(number)));
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Enter task to mark done: e.g. mark 1");
            } catch (NumberFormatException e) {
                System.out.println("Enter task to mark done: e.g. mark 1");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("No such task to mark.");
            }
        } else if (command.startsWith("unmark")) {
                    try {
                        String str = command.substring(7);
                        int number = Integer.parseInt(str) - 1;
                        String name = tasklist.get(number).getTask();
                        tasklist.get(number).unmark();
                        System.out.println(task.unmark(number, name, tasklist.get(number)));
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Enter task to unmark: e.g. unmark 1");
                    } catch (NumberFormatException e) {
                        System.out.println("Enter task to unmark: e.g. unmark 1");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("No such task to unmark.");
                    }

        } else if (command.startsWith("delete")) {
                    try {
                        String str = command.substring(7);
                        int number = Integer.parseInt(str) - 1;
                        Task name = tasklist.get(number);
                        tasklist.remove(number);
                        System.out.println(task.delete(name));
                        System.out.println("Now you have " + tasklist.size() + " tasks in the list.");
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Enter task to delete: e.g. delete 1");
                    } catch (NumberFormatException e) {
                        System.out.println("Enter task to delete: e.g. delete 1");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("No such task to delete.");
                    }
        } else {
            try {
                if (command.startsWith("todo")) {
                    String str = command.substring(5);
                    Todo todo = new Todo(str);
                    tasklist.add(todo);
                    System.out.println(todo.addTodo());
                    System.out.println("Now you have " + tasklist.size() + " tasks in the list.");
                } else if (command.startsWith("deadline")) {
                    try {
                        String str = command.substring(9);
                        String[] arr = str.split("/");
                        String c = arr[0];
                        String ddl = arr[1].substring(3);
                        Deadline deadline = new Deadline(ddl, c);
                        tasklist.add(deadline);
                        System.out.println(deadline.addDeadline());
                        System.out.println("Now you have " + tasklist.size() + " tasks in the list.");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Let me know your deadlines babe: e.g. deadline <deadline> /by <ddl>");
                    }
                } else if (command.startsWith("event")) {
                    try {
                        String str = command.substring(6);
                        String[] arr = str.split("/");
                        String c = arr[0];
                        String start = arr[1].substring(5);
                        String end = arr[2].substring(3);
                        Event event = new Event(start, end, c);
                        tasklist.add(event);
                        System.out.println(event.addEvent() + "\n" +"Now you have " + tasklist.size() + " tasks in the list.");
                    } catch(ArrayIndexOutOfBoundsException e) {
                        System.out.println("Let me know when this event is bb: e.g. event <event> /from <when>/to <when>");
                    }
                } else {
                    System.out.println("Baby, what are you saying? Tell me what your TODOs, DEADLINEs and EVENTs are!");
                }
            } catch (StringIndexOutOfBoundsException e){
                System.out.println("ENTER INSTRUCTION");
            }
        }
    }
}
