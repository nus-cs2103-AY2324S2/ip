import java.util.ArrayList;

public class Ui {
    public void printWelcome(){
        System.out.println("Hello!\n" +
                "I'm Reacher.\n" +
                "Give me tasks.\n" +
                "Functions are edit, list, delete and bye");
    }
    public void printList(ArrayList<Task> tasks){
        System.out.println("Tasks:");
        int c = 1;
        for (Task task : tasks) {
            System.out.println(c + task.toString());
            c++;
        }
    }
}
