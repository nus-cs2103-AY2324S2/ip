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
        System.out.println(format("Heyo, how u doing, Im Quacky. How can I help you?"));
        String command = scanner.nextLine();
        while(!command.equals("bye")){
            if(command.equals("list")) {
                System.out.println(format(tasks.toString()));
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
