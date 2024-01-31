import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello it's a-me! Mario!\nWhat-a can I do fo' ya!");
        Scanner scanner = new Scanner(System.in);
        State state = new State();
        while(true) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            String command = parts[0];
            if (line.equals("bye")) {
                System.out.println("Ciao! Mamma-Mia!");
                break;
            }
            switch(command) {
                case "list":
                    StringBuilder sb = new StringBuilder();
                    for (int i = 1; i <= state.getTasks().size();i++) {
                        Task task = state.getTask(i - 1);
                        sb.append(String.format("%d: %s\n", i, task));
                    }
                    if (state.getTasks().isEmpty()) {
                        sb.append("No Tasks! Oopsie!");
                    }
                    System.out.println(sb);
                    break;
                case "mark":
                    int taskNo = Integer.parseInt(parts[1]) - 1;
                    Task task = state.getTask(taskNo);
                    if (task == null) {
                        System.out.println("Mamma-mia! This task no exist-o!");
                        break;
                    }
                    task.setDone(true);
                    System.out.println("Mamma-mai! I've marked it done!");
                    System.out.println(task);
                    break;
                case "todo":
                    Task newTodo = new Todo(String.join(" ", parts), false);
                    state.addTask(newTodo);
                    System.out.println("I added!–\n" + newTodo.getDescription() +  "\n–Mamma-mia!");
                    break;
                case "deadline":
                    int found = -1;
                    for (int i = 0; i < parts.length; i++) {
                        if (parts[i].equals("/by")) {
                            found = i;
                            break;
                        }
                    }
                    if (found == -1) {
                        System.out.println("Oh No! Invalid");
                    } else {
                        StringBuilder deadline = new StringBuilder();
                        StringBuilder description = new StringBuilder();
                        for (int i = 0; i < parts.length; i++) {
                            if (i > found) {
                                deadline.append(parts[i]);
                                deadline.append(" ");
                            } else if (i < found) {
                                description.append(parts[i]);
                                description.append(" ");
                            }
                        }

                        Task newDeadline = new Deadline(
                                description.toString(),
                                deadline.toString(),
                                false
                        );
                        state.addTask(newDeadline);
                        System.out.println("I added!–\n" + newDeadline.toString() +  "\n–Mamma-mia!");
                    }
                    break;
                case "event":
                    int found1 = -1;
                    for (int i = 0; i < parts.length; i++) {
                        if (parts[i].equals("/by")) {
                            found1 = i;
                            break;
                        }
                    }
                    if (found1 == -1) {
                        System.out.println("Oh No! Invalid");
                    } else {
                        StringBuilder deadline = new StringBuilder();
                        StringBuilder description = new StringBuilder();
                        for (int i = 0; i < parts.length; i++) {
                            if (i > found1) {
                                deadline.append(parts[i]);
                                deadline.append(" ");
                            } else if (i < found1) {
                                description.append(parts[i]);
                                description.append(" ");
                            }
                        }

                        Task newDeadline = new Deadline(
                                description.toString(),
                                deadline.toString(),
                                false
                        );
                        state.addTask(newDeadline);
                        System.out.println("I added!–\n" + newDeadline.toString() +  "\n–Mamma-mia!");
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
