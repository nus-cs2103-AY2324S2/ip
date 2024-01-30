import java.util.*;
public class Duke {
    static String line = "    ____________________________________________________________";
    static String indent = "     ";
    static String name = "Alfred";

    public static void separate(){
        System.out.println(line);
    }
    public static void spacing() {
        separate();
        System.out.println("");
    }

    public static void intro() {
        separate();
        System.out.println(indent + "Hello! I'm " + name + "\n     What can I do for you?");
        spacing();
    }
    public static void leave() {
        System.out.println(indent + "Bye. Hope to see you again soon!");
        spacing();
    }

    public int finder(String checker, String[] list) {
        for (int i = 0; i < list.length; i++) {
            if (list[i].equals(checker)) {
                return i;
            }
        }
        return -1;
    }
    public Task identify(String request) throws taskException {
        if (request.startsWith("todo")) {
            String[] reqList = request.split(" ");
            if (reqList.length < 2) {
                throw new taskException("What do you want to do? Description of todo cannot be empty.");
            }
            String desc = String.join(" ", Arrays.copyOfRange(reqList, 1, reqList.length));
            Todo current = new Todo(desc);
            return current;

        } else if (request.startsWith("deadline")) {
            String[] reqList = request.split(" ");
            if (Arrays.asList(reqList).contains("/by")) {
                int byIndex = finder("/by", reqList);
                String desc = String.join(" ", Arrays.copyOfRange(reqList, 1, byIndex));
                String time = String.join(" ", Arrays.copyOfRange(reqList, byIndex + 1, reqList.length));
                Deadline current = new Deadline(desc, time);
                return current;
            } else{
                throw new taskException("Please specify when is the deadline.");
            }

        } else if (request.startsWith("event")) {
            String[] reqList = request.split(" ");
            if (Arrays.asList(reqList).contains("/from") && Arrays.asList(reqList).contains("/to")){
                int fromIndex = finder("/from", reqList);
                int toIndex = finder("/to", reqList);
                String desc = String.join(" ", Arrays.copyOfRange(reqList, 1, fromIndex));
                String start = String.join(" ", Arrays.copyOfRange(reqList, fromIndex + 1, toIndex));
                String end = String.join(" ", Arrays.copyOfRange(reqList, toIndex + 1, reqList.length));

                Event current = new Event(desc, start, end);
                return current;
            } else if (Arrays.asList(reqList).contains("/from")){
                throw new taskException("Please specify when the event ends.");
            } else if (Arrays.asList(reqList).contains("/to")){
                throw new taskException("Please specify when the event starts.");
            } else {
                throw new taskException("Please specify the event timeframe.");
            }

        } else {
            throw new taskException("Apologies, I don't understand you. Please try again");
        }
    }

    public void start(){
        intro();
        Scanner input = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<Task>();
        while(true) {
            String current = input.nextLine();
            if(current.equals("bye")) {
                separate();
                leave();
                break;
            } else if(current.equals("list")) {
                separate();
                System.out.println(indent + "Here are the tasks in your list:");
                int count = 1;
                for (Task i : taskList) {
                    System.out.println(indent + Integer.toString(count) + "." + i.getStatus());
                    count++;
                }
                spacing();
            } else if (current.startsWith("mark")) {
                String[] marking = current.split(" ");
                int position = Integer.parseInt(marking[1]) - 1;
                Task curr = taskList.get(position);
                curr.makeDone();

                separate();
                System.out.println(indent + "Nice! I've marked this task as done:");
                System.out.println(indent + taskList.get(position).getStatus());
                spacing();

            } else if (current.startsWith("unmark")) {
                String[] marking = current.split(" ");
                int position = Integer.parseInt(marking[1]) - 1;
                Task curr = taskList.get(position);
                curr.makeUndone();

                separate();
                System.out.println(indent + "OK, I've marked this task as not done yet:");
                System.out.println(indent + taskList.get(position).getStatus());
                spacing();
            } else if (current.startsWith("delete")){
                String[] marking = current.split(" ");
                int position = Integer.parseInt(marking[1]) - 1;

                separate();
                System.out.println(indent + "Noted. I've removed this task:");
                System.out.println(indent + "  " + taskList.get(position).getStatus());
                taskList.remove(position);
                System.out.println(indent + "Now you have " + Integer.toString(taskList.size()) +
                        " tasks in the list.");
                spacing();


            } else {
                try {
                    Task newTask = identify(current);
                    taskList.add(newTask);
                    separate();
                    System.out.println(indent + "Got it. I've added this task:");
                    System.out.println(indent + "  " + newTask.getStatus());
                    System.out.println(indent + "Now you have " + Integer.toString(taskList.size()) +
                            " tasks in the list.");
                    spacing();
                } catch (taskException e) {
                    separate();
                    System.out.println(indent +e.getMessage());
                    spacing();
                }
            }
        }
    }

    public class taskException extends Exception {
        public taskException(String message) {
            super(message);
        }
    }

    public static void main(String[] args){
        Duke duke = new Duke();
        duke.start();
    }
}
