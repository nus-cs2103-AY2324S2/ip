import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    private ArrayList<Task> tasks;
    public static void main(String[] args) {
        Duke greg = new Duke();
    }

    public Duke() {
        tasks = new ArrayList<Task>();
        greet();
        listen();
    }
    public void greet() {
        fillerLine();
        System.out.println("    Hello! I am Greg.\n    What can I do for you?");
        fillerLine();
    }

    public void bye() {
        fillerLine();
        System.out.println("    Goodbye! Hope to see you again soon!");
        fillerLine();
    }

    public static void fillerLine() {
        System.out.println("    _______________________________________");
    }

    public void listen() {
        Scanner sc = new Scanner(System.in);
        String request = sc.next();

        while (true) {
            if (request.equals("bye")) {
                bye();
                break;
            } else {
                switch (request) {
                    case "list":
                        list();
                        String reset = sc.nextLine();
                        break;
                    case "mark": {
                        int index = sc.nextInt() - 1;
                        if(index > tasks.size() - 1 || index < 0) {
                            System.out.println("Invalid index!");
                        } else {
                            tasks.get(index).mark();
                            String reset2 = sc.nextLine();
                        }
                        break;
                    }
                    case "unmark": {
                        int index = sc.nextInt() - 1;

                        if(index > tasks.size() - 1 || index < 0) {
                            System.out.println("Invalid index!");
                        } else {
                            tasks.get(index).unmark();
                            String reset3 = sc.nextLine();
                        }
                        break;
                    }
                    case "delete": {
                        int index = sc.nextInt() - 1;

                        if(index > tasks.size() - 1 || index < 0) {
                            System.out.println("Invalid index!");
                        } else {
                            Task task = tasks.get(index);
                            System.out.println("Alright, removing this task");
                            System.out.println(task.toString());
                            tasks.remove(index);
                            System.out.println(String.format("You now have %s tasks left", tasks.size()));
                            String reset4 = sc.nextLine();
                        }
                        break;
                    }
                    case "todo": {
                        String name = sc.nextLine();
                        if (name.equals("")) {
                            System.out.println("The name cannot be empty!");
                        } else {
                            add(name.substring(1));
                        }
                        break;
                    }
                    case "deadline": {
                        StringBuilder name = new StringBuilder();
                        String testString = sc.next();

                        while(!Objects.equals(testString, "/by")) {
                            name.append(testString).append(" ");
                            testString = sc.next();
                        }

                        String deadline = sc.nextLine();
                        add(name.toString(), deadline);
                        break;
                    }
                    case "event": {
                        StringBuilder name = new StringBuilder();
                        String testString = sc.next();

                        while(!Objects.equals(testString, "/from")) {
                            name.append(testString).append(" ");
                            testString = sc.next();
                        }

                        testString = sc.next();
                        StringBuilder startDate = new StringBuilder();

                        while(!Objects.equals(testString, "/to")) {
                            startDate.append(testString).append(" ");
                            testString = sc.next();
                        }

                        String endDate = sc.nextLine();
                        if (endDate.equals("")) {
                            System.out.println("The end date cannot be empty!");
                        } else {
                            add(name.toString(), startDate.toString(), endDate);
                        }
                        break;
                    }

                    default:
                        String item = sc.nextLine();
                        System.out.println("Sorry, I don't know this command :(");
                        break;
                }
                request = sc.next();
            }
        }
    }

    public void list() {
        for(int i = 0; i < tasks.size(); i++) {
            String str = "";
            str = String.format("%s: %s",i + 1, tasks.get(i));
            System.out.println(str);
        }
    }

    public void add(String name) {
        System.out.println("Alright. Adding this task:");
        tasks.add(new ToDo(name));
        String str = "";
        str = String.format("You now have %s tasks", tasks.size());
        System.out.println(str);
    }

    public void add(String name, String deadline) {
        System.out.println("Alright. Adding this task:");
        tasks.add(new Deadline(name, deadline));
        String str = "";
        str = String.format("You now have %s tasks", tasks.size());
        System.out.println(str);
    }

    public void add(String name, String startDate, String endDate) {
        System.out.println("Alright. Adding this task:");
        tasks.add(new Event(name, startDate, endDate));
        String str = "";
        str = String.format("You now have %s tasks", tasks.size());
        System.out.println(str);
    }
}
