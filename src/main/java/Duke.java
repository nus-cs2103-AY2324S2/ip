import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        String greeting = "Hi, I'm Lighthouse.\nHow can I help you?";
        System.out.println(greeting);
        System.out.println("____________________________________________________________");
        Scanner scan = new Scanner(System.in);
        boolean continueRun = true;
        ArrayList<Task> taskList = new ArrayList<>();

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
                Task task = taskList.get(itemNo);
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
                taskList.set(itemNo, task);
            } else if (info.equals("list")) {
                String output = "";
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for(int i=0; i < taskList.size(); i++) {
                    System.out.println(taskList.get(i).printOutput());
                }
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
                int count = (int) taskList.stream().filter(obj -> obj.getTaskType().equals("T")).count();
                System.out.println("Now you have "+taskList.size()+" tasks in the list");
                System.out.println("____________________________________________________________");
            } else if (info.startsWith("delete")) {
                int index = info.indexOf(" ");
                String argVal = info.substring(index+1);
                int itemNo = Integer.parseInt(argVal) - 1;
                Task task = taskList.get(itemNo);
                taskList.remove(itemNo);
                System.out.println("____________________________________________________________");
                System.out.println("Noted. I've removed this task:\n");
                System.out.println(task.printOutput());
                int count = (int) taskList.stream().filter(obj -> obj.getTaskType().equals("T")).count();
                System.out.println("Now you have "+taskList.size()+" tasks in the list");
                System.out.println("____________________________________________________________");
            } else {
                System.out.println("____________________________________________________________");
                System.out.println("Oh dear! I do not understand this command! Try again!");
                System.out.println("____________________________________________________________");
//                Task4 task = new Task4(info, false, taskList.size()+1);
//                taskList.add(task);
//                System.out.println("added: "+info);
            }
        }
    }

    private static Task handleTodoEventDeadline(String info, ArrayList<Task> taskList) throws DukeException {
        int index = info.indexOf(" ");
        if (index <= 0) {
            throw new DukeException("OMG! Description is empty. Cannot accept.");
        }
        String argVal = info.substring(index+1);
        Task todo = null;
        if (info.startsWith("todo")) {
            todo = new Todo(argVal, false, taskList.size() + 1, "T");
        } else if (info.startsWith("event")) {
            int indfrom = argVal.indexOf("/from");
            String eventname = argVal.substring(0,indfrom);
            String eventFromStr = argVal.substring(indfrom+6, argVal.indexOf("/to"));
            int indto = argVal.indexOf("/to");
            String eventToStr = argVal.substring(indto+4);
            todo = new Event(eventname, false, taskList.size() + 1, "E", eventFromStr, eventToStr);
        } else if (info.startsWith("deadline")) {
            int indfrom = argVal.indexOf("/by");
            String eventname = argVal.substring(0,indfrom);

            String deadlineStr = argVal.substring(indfrom+4);
            todo = new Deadline(eventname, false, taskList.size() + 1, "D", deadlineStr);
        }
        return todo;
    }
}