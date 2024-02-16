package reacher;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    Scanner scanner;

    public Ui(){
        this.scanner = new Scanner(System.in);
    }
    public void printWelcome(){
        System.out.println("Hello!\n" +
                "I'm Reacher.\n" +
                "Give me tasks.\n" +
                "Functions are edit, list, add and bye");
    }
    public void printList(ArrayList<Task> tasks){
        System.out.println("Tasks:");
        int c = 1;
        for (Task task : tasks) {
            System.out.println(c + task.toString());
            c++;
        }
    }
    public String readString() throws ReacherException {
        String input = scanner.nextLine();
        if (input.isEmpty()) {
            throw new ReacherException("pls type a task name.");
        }
        return input;
    }
    public int readInt(){
        int num = scanner.nextInt();
        scanner.nextLine();
        return num;
    }
    public void print(String message){
        System.out.println(message);
    }
}
