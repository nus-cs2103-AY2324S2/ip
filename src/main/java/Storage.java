import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static String path;
    private static DateTimeFormatter dFormatInp = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm");
    private static DateTimeFormatter dFormatOut = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    private static File data;

    public Storage(String filePath){
        path = filePath;
        data = new File(path);
    }

    public static ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> taskF = new ArrayList<>();
        if(data.exists()){
            Scanner sc = new Scanner(data);
            while (sc.hasNext()){
                String dt = sc.nextLine();
                String[] dtl = dt.split("/");
                switch (dtl[0]) {
                    case "T":
                        taskF.add(new ToDos(dtl[1], dtl[2]));
                        break;
                    case "D":
                        taskF.add(new Deadline(dtl[1], dtl[2], LocalDateTime.parse(dtl[3], dFormatInp)));
                        break;
                    case "E":
                        taskF.add(new Event(dtl[1], dtl[2], LocalDateTime.parse(dtl[3],dFormatInp),
                                LocalDateTime.parse(dtl[4],dFormatInp)));
                }
            }
        } else {
            throw new FileNotFoundException("No saved data before, making new one");
        }
        return taskF;
    }

    public static void write(ArrayList<Task> task) throws IOException {
        FileWriter rf;
        rf = new FileWriter(path);
        for(int i = 0; i < task.size(); i++){
            rf.write(task.get(i).toWrite());
            rf.write("\n");
        }
        rf.close();
    }
}
