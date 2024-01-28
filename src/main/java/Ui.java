import java.util.Scanner;
import java.io.InputStream;

public class Ui {
    private Scanner sc; 

    public Ui(InputStream inputstream) {
        this.sc = new Scanner(inputstream); 
    }

    public String getInstr() {
        return this.sc.nextLine(); 
    }

    public void close() {
        this.sc.close();
    }

    public String greet() {
        return "Hello! I'm YLEXI. \nWhat can I do for you?"; 
    }

    public String exit() {
        return "Bye. Hope to see you again soon!";
    }
}
