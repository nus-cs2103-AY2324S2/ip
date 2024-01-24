import java.util.Scanner;

public class Quacky {
    private static TaskList tasks = new TaskList();

    private static String format(String text){
        String[] lines = text.split("\n");
        StringBuilder sb = new StringBuilder("\t____________________________________________________________\n");

        for (String line : lines) {
            sb.append("\t").append(line).append("\n");
        }

        sb.append("\t____________________________________________________________");
        return sb.toString();
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(format("Quack! how u doing, Im Quacky. How can I help you?"));
        String command = scanner.nextLine();
        while(!command.equals("bye")){
            if(command.equals("list")) {
                System.out.println(format(tasks.toString()));
            } else if(command.startsWith("mark ")) {
                int space = command.indexOf(' ');
                int taskNumber = Integer.parseInt(command.substring(space + 1)) - 1;
                tasks.markCompleteTask(taskNumber);
                String message = "Quack! I marked this task as done \n\t" + tasks.printTask(taskNumber);
                System.out.println(format(message));
            } else if(command.startsWith("unmark ")) {
                int space = command.indexOf(' ');
                int taskNumber = Integer.parseInt(command.substring(space + 1)) - 1;
                tasks.unmarkCompleteTask(taskNumber);
                String message = "Quack! I marked this task as not \n\t" + tasks.printTask(taskNumber);
                System.out.println(format(message));
            } else {
                Task newTask = new Task(command);
                tasks.addTask(newTask);
                System.out.println(format("added: " + command));
            }
            command = scanner.nextLine();
        }
        System.out.println(format("Quack Quack"));
        scanner.close();
    }
}
