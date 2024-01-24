import java.util.Objects;
import java.util.Scanner;
public class Duke {
    private Task[] tasks;
    private int no_of_tasks;
    public static void main(String[] args) {
        Duke greg = new Duke();
    }

    public Duke() {
        tasks = new Task[100];
        no_of_tasks = 0;
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
                        tasks[index].mark();
                        String reset2 = sc.nextLine();
                        break;
                    }
                    case "unmark": {
                        int index = sc.nextInt() - 1;
                        tasks[index].unmark();
                        String reset3 = sc.nextLine();
                        break;
                    }
                    case "todo": {
                        String name = sc.nextLine();
                        add(name.substring(1));
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
                        add(name.toString(), startDate.toString(), endDate);

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
        for(int i = 0; i < no_of_tasks; i++) {
            String str = "";
            str = String.format("%s: %s",i + 1, tasks[i]);
            System.out.println(str);
        }
    }

    public void add(String name) {
        System.out.println("Alright. Adding this task:");
        tasks[no_of_tasks] = new ToDo(name);
        no_of_tasks++;
        String str = "";
        str = String.format("You now have %s tasks", no_of_tasks);
        System.out.println(str);
    }

    public void add(String name, String deadline) {
        System.out.println("Alright. Adding this task:");
        tasks[no_of_tasks] = new Deadline(name, deadline);
        no_of_tasks++;
        String str = "";
        str = String.format("You now have %s tasks", no_of_tasks);
        System.out.println(str);
    }

    public void add(String name, String startDate, String endDate) {
        System.out.println("Alright. Adding this task:");
        tasks[no_of_tasks] = new Event(name, startDate, endDate);
        no_of_tasks++;
        String str = "";
        str = String.format("You now have %s tasks", no_of_tasks);
        System.out.println(str);
    }
}
