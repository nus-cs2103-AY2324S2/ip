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

    enum Command {
        TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, LIST, BYE, INVALID;
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

    public static Command parseCommand(String input) {
        if (input.startsWith("todo")) {
            return Command.TODO;
        } else if (input.startsWith("deadline")) {
            return Command.DEADLINE;
        } else if (input.startsWith("event")) {
            return Command.EVENT;
        } else if (input.startsWith("mark")) {
            return Command.MARK;
        } else if (input.startsWith("unmark")) {
            return Command.UNMARK;
        } else if (input.startsWith("delete")) {
            return Command.DELETE;
        } else if (input.equals("list")) {
            return Command.LIST;
        } else if (input.equals("bye")) {
            return Command.BYE;
        } else {
            return Command.INVALID;
        }
    }

    public String validateInput(Command command, String input) {
        if (command == Command.TODO) {
            if (input.length() > 4) {
                return null;
            } else {
                return "Please enter a valid todo! Tip: todo <description> \nMissing description";
            }
        } else if (command == Command.DEADLINE) {
            if (input.length() > 8) {
                String[] arr = input.substring(9).split("/by");
                if (arr.length == 2) {
                    return null;
                } else {
                    return "Please enter a valid deadline! Tip: deadline <description> /by <deadline> \nMissing /by";
                }
            } else {
                return "Please enter a valid deadline! Tip: deadline <description> /by <deadline> \nMissing description";
            }
        } else if (command == Command.EVENT) {
            if (input.length() > 5) {
                String[] arr = input.substring(6).split("/from");
                if (arr.length == 2) {
                    String[] arr2 = arr[1].split("/to");
                    if (arr2.length == 2) {
                        return null;
                    } else {
                        return "Please enter a valid event! Tip: event <description> /from <start> /to <end> \nMissing /to";
                    }   
                } else {
                    return "Please enter a valid event! Tip: event <description> /from <start> /to <end> \nMissing /from";
                }
            } else {
                return "Please enter a valid event! Tip: event <description> /from <start> /to <end> \nMissing description";
            }
        } else if (command == Command.MARK) {
            if (input.length() > 4) {
                String suffix = input.substring(5);
                if (isNumeric(suffix)) {
                    int index = Integer.parseInt(suffix) - 1;
                    if (this.memory.getNumTasks() > index) {
                        return null;
                    } else {
                        return "Please enter a valid number! Tip: mark <number> \nNumber out of range";
                    }
                } else {
                    return "Please enter a valid number! Tip: mark <number> \nMissing number";
                }
            } else {
                return "Please enter a valid number! Tip: mark <number> \nMissing number";
            }
        } else if (command == Command.UNMARK) {
            if (input.length() > 6) {
                String suffix = input.substring(7);
                if (isNumeric(suffix)) {
                    int index = Integer.parseInt(suffix) - 1;
                    if (this.memory.getNumTasks() > index) {
                        return null;
                    } else {
                        return "Please enter a valid number! Tip: unmark <number> \nNumber out of range";
                    }
                } else {
                    return "Please enter a valid number! Tip: unmark <number> \nMissing number";
                }
            } else {
                return "Please enter a valid number! Tip: unmark <number> \nMissing number";
            }
        } else if (command == Command.DELETE) {
            if (input.length() > 6) {
                String suffix = input.substring(7);
                if (isNumeric(suffix)) {
                    int index = Integer.parseInt(suffix) - 1;
                    if (this.memory.getNumTasks() > index) {
                        return null;
                    } else {
                        return "Please enter a valid number! Tip: delete <number> \nNumber out of range";
                    }
                } else {
                    return "Please enter a valid number! Tip: delete <number> \nMissing number";
                }
            } else {
                return "Please enter a valid number! Tip: delete <number> \nMissing number";
            }
        } else {
            return null;
        }
    }

    public void echo() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        Command command = parseCommand(input);
        boolean success;
        ArrayList<Task> tasks; 
        int numTasks;
        Task curr_task;
        String response;
        String errorMsg;
        while (command != Command.BYE) {
            errorMsg = validateInput(command, input);
            if (errorMsg == null) {
                switch (command) {
                    case TODO:
                        curr_task = new TodoTask(input.substring(5));
                        success = this.memory.add(curr_task);
                        response = Response.addTaskSuccess(curr_task, this.memory.getNumTasks());
                        break;
                    case DEADLINE:
                        String[] arr = input.substring(9).split("/by");
                        curr_task = new DeadlineTask(arr[0].trim(), arr[1].trim());
                        success = this.memory.add(curr_task);
                        response = Response.addTaskSuccess(curr_task, this.memory.getNumTasks());
                        break;
                    case EVENT:
                        String[] arr2 = input.substring(6).split("/from");
                        String[] arr3 = arr2[1].split("/to");
                        curr_task = new EventTask(arr2[0].trim(), arr3[0].trim(), arr3[1].trim());
                        success = this.memory.add(curr_task);
                        response = Response.addTaskSuccess(curr_task, this.memory.getNumTasks());
                        break;
                    case MARK:
                        int index = Integer.parseInt(input.substring(5)) - 1;
                        curr_task = this.memory.markTask(index);
                        response = Response.markTask(curr_task);
                        break;
                    case UNMARK:
                        int index2 = Integer.parseInt(input.substring(7)) - 1;
                        curr_task = this.memory.unmarkTask(index2);
                        response = Response.unmarkTask(curr_task);
                        break;
                    case DELETE:
                        int index3 = Integer.parseInt(input.substring(7)) - 1;
                        StringBuilder sb = new StringBuilder();
                        curr_task = this.memory.deleteTask(index3);
                        sb.append("Noted. I've removed this task:\n");
                        sb.append("  " + curr_task.toString() + "\n");
                        sb.append("Now you have " + this.memory.getNumTasks() + " tasks in the list.");
                        response = sb.toString();
                        break;
                    case LIST:
                        tasks = this.memory.getTasks();
                        numTasks = this.memory.getNumTasks();
                        response = Response.listTasks(tasks, numTasks);
                        break;
                    default:
                        response = "I'm sorry, but I don't know what that means :-(";
                }
            } else {
                response = errorMsg;
            }
            output(response);
            input = sc.nextLine();
            command = parseCommand(input);
        }
        sc.close();
    }

}
