import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Duke {
    static List<Task> todo_list = new ArrayList<>();
    static String filePath = "./data/duke.txt";

    static String line = "_______________________________________________________\n";

    private static void initializeTaskList() throws FileNotFoundException{
        File f = new File(filePath);
        if (!f.exists()) {
             System.out.println(line + "There is no record of a previous task list. I shall create one for you\n" + line);
        } else {
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String task = sc.nextLine();
                String[] split = task.split("]");
                String taskType = split[0].substring(1);
                Boolean isDone = split[1].equals("[ ")? false: true;
                String desc = split[2].trim();
                if (taskType.equals("T")) {
                    ToDo todo = new ToDo(desc);
                    todo_list.add(todo);
                    if (isDone) {
                        todo.mark();
                    }
                } else if (taskType.equals("D")) {
                    String[] split1 = desc.split("\\(by:");
                    String name = split1[0].trim();
                    String by = split1[1].trim();

                    Deadline deadline = new Deadline(name, by.substring(0, by.length() - 1));
                    todo_list.add(deadline);
                    if (isDone) {
                        deadline.mark();
                    }
                } else {
                    String[] split1 = desc.split("\\(from:");
                    String[] split2 = split1[1].split("to:");
                    String name = split1[0].trim();
                    String from = split2[0].trim();
                    String to = split2[1].trim();
                    Event event = new Event(name, from, to.substring(0, to.length() - 1));
                    todo_list.add(event);
                    if (isDone) {
                        event.mark();
                    }
                }

            }
            sc.close();
        }
    }

    private static void saveTaskList() throws IOException{
        FileWriter fw = new FileWriter(filePath);
        String text = "";
        for (Task task: todo_list) {
            text += task.toString() + "\n";
        }
        fw.write(text);
        fw.close();
    }
    private static void list() {
        System.out.println(line);

        for (int i = 0; i < todo_list.size(); i++) {
            System.out.println(i + 1 + "." + todo_list.get(i));
        }

        System.out.println(line);
    }
    private static ToDo addTodo(String name) {
        ToDo todo = new ToDo(name);
        todo_list.add(todo);
        try {
            saveTaskList();
        } catch (IOException e) {

        }
        System.out.println(line + " added: " + todo + "\n Now you have " + todo_list.size() + " tasks in the list\n" +
                line);
        return todo;
    }

    private static Deadline addDeadline(String name, String by) {
        Deadline deadline = new Deadline(name, by);
        todo_list.add(deadline);
        try {
            saveTaskList();
        } catch (IOException e) {

        }
        System.out.println(line + " added: " +
                deadline + "\n Now you have " + todo_list.size() + " tasks in the list\n" +
                line);

        return deadline;
    }

    private static Event addEvent(String name, String from, String to) {
        Event event = new Event(name, from, to);
        todo_list.add(event);
        try {
            saveTaskList();
        } catch (IOException e) {

        }
        System.out.println(line + " added: " +
                event + "\n Now you have " + todo_list.size() + " tasks in the list\n" +
                line);
        return event;
    }

    public static void main(String[] args) {
        String greeting = line +
                "Hello! I'm Thames and I'll be your assistant chatbot.\n" +
                "What can I do for you today?\n" +
                line;
        System.out.println(greeting);
<<<<<<< HEAD
        Scanner sc = new Scanner(System.in);
        try {
            initializeTaskList();
        } catch(FileNotFoundException e) {

        }
=======
>>>>>>> parent of 20724fb (Add automated testing for chatbot)

        while (true) {
            try {
                Scanner sc = new Scanner(System.in);

                String input = sc.next();
                if (input.equals("bye")) break;
                else if (input.equals("list")) {
                    list();
                } else if (input.equals("mark")) {
                    int index = sc.nextInt();
                    if (index > todo_list.size() || index <= 0) throw new Exception("Index has to be within list size!");
                    Task task = todo_list.get(index - 1);
                    task.mark();
                    try {
                        saveTaskList();
                    } catch (IOException e) {

                    }
                    System.out.println(line + "Nice! I've marked this task as done:\n " + task + "\n" + line);
                } else if (input.toLowerCase().equals("unmark")) {
                    int index = sc.nextInt();
                    if (index > todo_list.size() || index <= 0) throw new Exception("Index has to be within list size!");
                    Task task = todo_list.get(index - 1);
                    task.unmark();

                    try {
                        saveTaskList();
                    } catch (IOException e) {

                    }
                    System.out.println(line + "Ok, I've marked this task as not done yet:\n " + task + "\n" + line);
                } else if (input.toLowerCase().equals("delete")) {
                    int index = sc.nextInt();
                    if (index > todo_list.size() || index <= 0) throw new Exception("Index has to be within list size!");
                    Task task = todo_list.remove(index - 1);
                    try {
                        saveTaskList();
                    } catch (IOException e) {

                    }
                    System.out.println(line +
                            "Noted. I've removed this task from the list:\n " + task +
                            "\nNow you have " + todo_list.size() + " tasks in the list.\n" +
                            line);
                } else if (input.toLowerCase().equals("todo")) {
                    String str = sc.nextLine().trim();
                    if (str.length() == 0) throw new Exception("Todo task cannot be empty!\n");
                    addTodo(str);
                } else if (input.toLowerCase().equals("deadline")) {
                    String s = sc.nextLine();
                    if (s.split("/by").length != 2) {
                        throw new Exception("Please provide your deadline task in the following format:\n" +
                                "Deadline <description> /by <description>\n");
                    }
                    String name = s.split("/by")[0].trim();
                    String by = s.split("/by")[1].trim();
                    if (name.length() == 0 || by.length() == 0 ) {
                        throw new Exception(("Event names/to/from cannot be empty\n"));
                    }

                    addDeadline(name, by);
                } else if (input.toLowerCase().equals("event")) {
                    String s = sc.nextLine();
                    String[] split1 = s.split("/from");
                    if (split1.length != 2) {
                        throw new Exception("Please provide your event task in the following format:\n" +
                                "Event <description> /from <description> /to <description>\n");
                    }
                    String[] split2 = split1[1].split("/to");
                    if (split2.length != 2) {
                        throw new Exception("Please provide your event task in the following format:\n" +
                                "Event <description> /from <description> /to <description>\n");
                    }
                    String name = split1[0].trim();
                    String from = split2[0].trim();
                    String to = split2[1].trim();
                    if (name.length() == 0 || from.length() == 0 || to.length() == 0) {
                        throw new Exception(("Event names/to/from cannot be empty\n"));
                    }


                    addEvent(name, from, to);
                } else {
                    throw new Exception("Sorry! I do not understand what this means!\n");
                }
            } catch (Exception e) {
                System.out.println(line + e.getMessage() +
                        line);
            }
        }

        String exit = line +
                "Bye. Hope to see you soon!\n" +
                line;
        System.out.println(exit);
    }
}
