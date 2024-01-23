import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cortana {

    public static class Response {

        public static String greet(String name) {
            StringBuilder sb = new StringBuilder();
            sb.append("What's up chief? " + name + " here!\n");
            sb.append("What can I do for you?");
            return sb.toString();
        }
    
        public static String bye() {
            return "Bye. Hope to see you again soon!";
        }

        public static String addTaskSuccess(Task task, int numTasks) {
            StringBuilder sb = new StringBuilder();
            sb.append("Got it. I've added this task:\n");
            sb.append("  " + task.toString() + "\n");
            sb.append("Now you have " + numTasks + " tasks in the list.");
            return sb.toString();
        }

        public static String markTask(Task task) {
            StringBuilder sb = new StringBuilder();
            sb.append("Nice! I've marked this task as done:\n");
            sb.append("  " + task.toString());
            return sb.toString();
        }

        public static String unmarkTask(Task task) {
            StringBuilder sb = new StringBuilder();
            sb.append("OK, I've marked this task as not done yet:\n");
            sb.append("  " + task.toString());
            return sb.toString();
        }

        public static String listTasks(ArrayList<Task> tasks, int numTasks) {
            StringBuilder sb = new StringBuilder();
            sb.append("Here are the tasks in your list:\n");
            for (int i = 0; i < numTasks; i++) {
                sb.append((i + 1) + ". " + tasks.get(i).toString() + "\n");
            }
            return sb.toString();
        }
        
    }

    private String name = "Cortana";
    private Memory memory;

    Cortana() {
        this.memory = new Memory();
    }

    public void run() {
        output(Response.greet(this.name));
        echo();
        output(Response.bye());
    }

    public static void indent() {
        System.out.print("    ");
    }

    public static void drawLine() {
        indent();
        System.out.println("____________________________________________________________");
    }

    public static void output(String sentences) {
        drawLine();
        String[] arr = sentences.split("\n");
        for (String sentence : arr) {
            indent();
            System.out.println(sentence);
        }
        drawLine();
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches(); 
    }

    public void echo() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        boolean success;
        ArrayList<Task> tasks; 
        int numTasks;
        Task curr_task;
        String response;
        while (!input.equals("bye")) {
            if (input.startsWith("todo")) {
                if (input.length() > 4) {
                    curr_task = new TodoTask(input.substring(5));
                    success = this.memory.add(curr_task);
                    response = Response.addTaskSuccess(curr_task, this.memory.getNumTasks());
                } else {
                    response = "Please enter a valid todo!";
                }
            } else if (input.startsWith("deadline")) {
                if (input.length() > 8) {
                    String[] arr = input.substring(9).split("/by");
                    if (arr.length == 2) {
                        curr_task = new DeadlineTask(arr[0].trim(), arr[1].trim());
                        success = this.memory.add(curr_task);
                        response = Response.addTaskSuccess(curr_task, this.memory.getNumTasks());
                    } else {
                        response = "Please enter a valid deadline!";
                    }
                } else {
                    response = "Please enter a valid deadline!";
                }
            } else if (input.startsWith("event")) {
                if (input.length() > 5) {
                    String[] arr = input.substring(6).split("/from");
                    if (arr.length == 2) {
                        String[] arr2 = arr[1].split("/to");
                        if (arr2.length == 2) {
                            curr_task = new EventTask(arr[0].trim(), arr2[0].trim(), arr2[1].trim());
                            success = this.memory.add(curr_task);
                            response = Response.addTaskSuccess(curr_task, this.memory.getNumTasks());
                        } else {
                            response = "Please enter a valid event!";
                        }   
                    } else {
                        response = "Please enter a valid event!";
                    }
                } else {
                    response = "Please enter a valid event!";
                }
            } else if (input.startsWith("mark")) {
                if (input.length() > 4) {
                    String suffix = input.substring(5);
                    if (isNumeric(suffix)) {
                        int index = Integer.parseInt(suffix) - 1;
                        curr_task = this.memory.markTask(index);
                        response = Response.markTask(curr_task);
                    } else {
                        response = "Please enter a valid number!";
                    }
                } else {
                    response = "Please enter a valid number!";
                }
            } else if (input.startsWith("unmark")) {
                if (input.length() > 6) {
                    String suffix = input.substring(7);
                    if (isNumeric(suffix)) {
                        int index = Integer.parseInt(suffix) - 1;
                        curr_task = this.memory.unmarkTask(index);
                        response = Response.unmarkTask(curr_task);
                    } else {
                        response = "Please enter a valid number!";
                    }
                } else {
                    response = "Please enter a valid number!";
                }
            } else if (input.equals("list")) {
                tasks = this.memory.getTasks();
                numTasks = this.memory.getNumTasks();
                response = Response.listTasks(tasks, numTasks);
            } else {
                response = "I'm sorry, but I don't know what that means :-(";
            }
            output(response);
            input = sc.nextLine();
        }
        sc.close();
    }

}
