import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String filepath = "./duke.txt";

        String greeting = "Hi, I'm Lighthouse.\nHow can I help you?";
        System.out.println("____________________________________________________________");
        System.out.println(greeting);
        System.out.println("____________________________________________________________");
        Scanner scan = new Scanner(System.in);
        boolean continueRun = true;
        TaskList taskList = new TaskList();
        //taskList.addPropertyChangeListener();
        PropertyChangeListener pcs = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                saveTaskListToFile(filepath, taskList.toString());
            }
        };
        taskList.addPropertyChangeListener(pcs);

        while(continueRun) {
            String info = scan.nextLine();
            if (info.equals("bye")) {
                continueRun = false;
                System.out.println("____________________________________________________________");
                System.out.println("Goodbye! See you next time!");
                System.out.println("____________________________________________________________");
                break;
            } else if (info.contains("mark")) {
                int index = info.indexOf(" ");
                String argVal = info.substring(index+1);
                int itemNo = Integer.parseInt(argVal) - 1;
                Task task = (Task) taskList.getItemFromListByIndex(itemNo);
                System.out.println("____________________________________________________________");
                if (info.startsWith("mark")) {
                    task.setMarked(true);
                    System.out.println("Nice! I've marked this task as done:");
                } else if (info.startsWith("unmark")) {
                    task.setMarked(false);
                    System.out.println("OK, I've marked this task as note done yet:");
                }
                System.out.println(task.printOutput());
                System.out.println("____________________________________________________________");
                taskList.updateTaskInList(itemNo, task);
            } else if (info.equals("list")) {
                String output = "";
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                String taskoutput = taskList.printOutput();
                System.out.println(taskoutput);
                System.out.println("____________________________________________________________");
            } else if (info.startsWith("todo") || info.startsWith("event") || info.startsWith("deadline")) {
                Task todo = null;
                try {
                    todo = handleTodoEventDeadline(info, taskList);
                }catch(DukeException de){
                    System.out.println("____________________________________________________________");
                    System.out.println(de.getMessage());
                    System.out.println("____________________________________________________________");
                    continue;
                }
                taskList.add(todo);
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:\n");
                System.out.println(todo.printOutput());
                int count = taskList.getTaskList().size();
                System.out.println("Now you have "+count+" tasks in the list");
                System.out.println("____________________________________________________________");
            } else if (info.startsWith("delete")) {
                int count = taskList.getTaskList().size()-1;
                int index = info.indexOf(" ");
                String argVal = info.substring(index+1);
                int itemNo = Integer.parseInt(argVal) - 1;
                Task task = (Task) taskList.getItemFromListByIndex(itemNo);
                taskList.remove(index);
                System.out.println("____________________________________________________________");
                System.out.println("Noted. I've removed this task:\n");
                System.out.println(task.printOutput());
                System.out.println("Now you have "+count+" tasks in the list");
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("____________________________________________________________");
                System.out.println("Oh dear! I do not understand this command! Try again!");
                System.out.println("____________________________________________________________");
            }
        }
    }

    private static Task handleTodoEventDeadline(String info, TaskList taskList) throws DukeException {
        int index = info.indexOf(" ");
        if (index <= 0) {
            throw new DukeException("OMG! Description is empty. Cannot accept.");
        }
        String argVal = info.substring(index+1);
        Task todo = null;
        if (info.startsWith("todo")) {
            todo = new Todo(argVal, false, taskList.getTaskList().size() + 1, "T");
        } else if (info.startsWith("event")) {
            int indfrom = argVal.indexOf("/from");
            String eventname = argVal.substring(0,indfrom);
            String eventFromStr = argVal.substring(indfrom+6, argVal.indexOf("/to"));
            eventFromStr = eventFromStr.trim();
            int indto = argVal.indexOf("/to");
            String eventToStr = argVal.substring(indto+4);
            eventToStr = eventToStr.trim();
            todo = new Event(eventname, false, taskList.getTaskList().size() + 1, "E", eventFromStr, eventToStr);
        } else if (info.startsWith("deadline")) {
            int indfrom = argVal.indexOf("/by");
            String eventname = argVal.substring(0,indfrom);
            String deadlineStr = argVal.substring(indfrom+4);
            todo = new Deadline(eventname, false, taskList.getTaskList().size() + 1, "D", deadlineStr);
        }
        return todo;
    }

    private static void saveTaskListToFile(String path, String content) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            fw.write(content);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}