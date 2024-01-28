package chatbot;
import java.io.*;
import java.util.*;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ui {

    protected BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    protected PrintWriter pw = new PrintWriter(System.out, true);

    protected Parser p = new Parser();

    protected TaskList tl;


    public Ui(TaskList tl) {
        this.tl = tl;
    }

    public void start ()throws IOException {
        String prompt = "Hello! I'm TFamilyBot\n"
                + "What can I do for you? \n"
                + "____________________________________________________________\n";   
        pw.println(prompt);  
    }

    public boolean reply (Storage st)throws IOException {
        String io = br.readLine().trim();
        String[] words = io.split("\\s+", 2); 
        String detail = words.length > 1 ? words[1] : ""; 

        pw.println("____________________________________________________________\n");

        boolean done = p.isItDone(words);

        String answer = p.parseThrough(io, words, detail, this.tl, st);

        pw.println(answer);
        pw.println("____________________________________________________________\n");

        if (done) {
            return false;
        }

        return true;


    }

}