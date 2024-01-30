import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;
public class Storage {
    private ArrayList<Task> tasklist;
    private File f;

    public Storage (ArrayList<Task> tasklist, File f) {
        this.tasklist = tasklist;
        this.f = f;
    }
    public void getList() {
        System.out.println("    All tasks:");
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                System.out.println(s.nextLine());
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found! try again xx");
        }
        System.out.println("    Current tasks: ");
        for (int i = 0; i < tasklist.size(); i++) {
            int j = i + 1;
            System.out.println("        " + j + ". " + tasklist.get(i).getCat()
                    + tasklist.get(i).marked() + " "
                    + tasklist.get(i).getTask() + tasklist.get(i).getDetails());
        }
    }

}
