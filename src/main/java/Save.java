import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Save {
    public String filePath = "data/duke.txt";

    public Save(String f) {
        this.filePath = f;
    }
    public void saveData(Storage s) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int i=0; i<s.size(); i++) {
                Task t = s.get(i);
                String desc = t.description;
                String isDone;
                if (t.isDone) {
                    isDone = "1";
                } else {
                    isDone = "0";
                }
                System.out.println(t.toString());
                String taskType = t.toString().split("")[1];
                if (taskType.equals("T")) {
                    String line = "T" + " | " + isDone + " | " + desc;
                    writer.write(line);
                    writer.newLine();
                } else if (taskType.equals("D")) {
                    String str = t.toString();
                    String insideParenthesis = str.substring(str.indexOf("(")+1, str.indexOf(")"));
                    String[] subString = insideParenthesis.split(" ");
                    String deadline = "";
                    for (int j=1; j<subString.length; j++) {
                        deadline += subString[j];
                    }
                    String line = "D" + " | " + isDone + " | " + desc + " | " + deadline;
                    writer.write(line);
                    writer.newLine();
                } else if (taskType.equals("E")) {
                    String str = t.toString();
                    String insideParenthesis = str.substring(str.indexOf("(")+1, str.indexOf(")"));
                    System.out.println(insideParenthesis);
                    String[] subString = insideParenthesis.split(" ");
                    List<String> arrayList = new ArrayList<>(Arrays.asList(subString));
                    int index1 = arrayList.indexOf("to:");
                    String from = "";
                    String to = "";
                    for (int j=1; j<index1; j++) {
                        from += subString[j];
                        from += " ";
                    }
                    from = from.trim();
                    for (int j=index1+1; j<subString.length; j++) {
                        to += subString[j];
                        to += " ";
                    }
                    to = to.trim();
                    String line = "E" + " | " + isDone + " | " + desc + " | " + from + " | " + to;
                    writer.write(line);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Something went wrong with writing file!");
        }
    }

    public void loadData(Storage s) {
        try(FileInputStream fis = new FileInputStream(filePath)) {
            byte[] buffer = new byte[1024]; // You can adjust the buffer size according to your needs

            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                // Process the read data
                String data = new String(buffer, 0, bytesRead);
                String[] tasks = data.split("\n");
                for (String task : tasks)  {
                    String[] parts = task.split("\\s*\\|\\s*");
                    boolean isDone;
                    if (parts[1].equals("1")) {
                        isDone = true;
                    } else {
                        isDone = false;
                    }
                    if (parts[0].equals("T")) {
                        ToDos t = new ToDos(parts[2]);
                        if (isDone) {
                            t.markAsDone();
                        }
                        s.add(t);
                    } else if (parts[0].equals("E")) {
                        Events e = new Events(parts[2], parts[3], parts[4]);
                        if (isDone) {
                            e.markAsDone();
                        }
                        s.add(e);
                    } else if (parts[0].equals("D")) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HHmm");
                        LocalDateTime localDateTime = LocalDateTime.parse(parts[3], formatter);
                        Deadlines d = new Deadlines(parts[2], localDateTime);
                        if (isDone) {
                            d.markAsDone();
                        }
                        s.add(d);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Something went wrong!");
        }
    }
}
