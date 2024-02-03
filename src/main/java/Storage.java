import java.io.*;
import java.util.ArrayList;

public class Storage {

    private final String FILE_PATH = "./data/bit.txt";
    private File file = new File(FILE_PATH);

    public Storage() {
        boolean isCreated = createFile(file);

    }

    public boolean createFile(File myFile) {
        try {
            if (!myFile.exists()) {
                myFile.getParentFile().mkdirs();
                myFile.createNewFile();
            }
            return true;
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }

    }

    public void loadFile(Tasklist list) {

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String next;
            while ((next = br.readLine()) != null) {
                String[] parts = next.split("/");
                if (parts.length == 1) {
                    return;
                }
                System.out.println(parts[0]);
                System.out.println(parts[1]);
                if (parts[0].equals("T")) {
                    Task t = new Todo(parts[1]);
                    if (parts[2].equals("M")) {
                        t.complete();
                    }
                    list.addFromStorage(t);
                } else if (parts[0].equals("D")) {
                    Task d = new Deadline(parts[1], parts[2]);
                    if (parts[3].equals("M")) {
                        d.complete();
                    }
                    list.addFromStorage(d);
                } else if (parts[0].equals("E")) {
                    Task e = new Event(parts[1], parts[2], parts[3]);
                    if (parts[4].equals("M")) {
                        e.complete();
                    }
                    list.addFromStorage(e);
                }
            }
        } catch (IOException e) {
            System.out.println("Errorrrr...");
        }
    }

    public   void saveAll(Tasklist tasklist) {

        for (int i = 0; i < tasklist.getSize(); i++) {
            Task t = tasklist.getTask(i);
            if (t instanceof Todo) {
                saveToFile(t.isDone, t.description);
            } else if (t instanceof Deadline) {
                saveToFile(t.isDone, t.description, ((Deadline) t).getDeadline());
            } else if (t instanceof Event) {
                saveToFile(t.isDone, t.description, ((Event) t).getStart(), ((Event) t).getEnd());
            }

        }
    }

    public void  saveToFile(boolean marked, String description) {
        try {
            FileWriter myWriter = new FileWriter(FILE_PATH, true);
            myWriter.write("T/" + description);
            if (marked) {
                myWriter.write("/M\n");
            } else {
                myWriter.write("/U\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("I was unable to add that to the list!");
        }
    }

    public void saveToFile(boolean marked, String description, String deadline) {
        try {
            FileWriter myWriter = new FileWriter(FILE_PATH, true);
            myWriter.write(("D/" + description + "/" + deadline));
            if (marked) {
                myWriter.write("/M\n");
            } else {
                myWriter.write("/U\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("I was unable to add that to the list!");
        }
    }

    public  void saveToFile(boolean marked, String description, String start, String end) {
        try {
            FileWriter myWriter = new FileWriter(FILE_PATH, true);
            myWriter.write("E/" + description + "/" + start + "/" + end);
            if (marked) {
                myWriter.write("/M\n");
            } else {
                myWriter.write("/U\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("I was unable to add that to the list!");
        }
    }

    public  void cleanList() {
        try {
            FileWriter myCleaner = new FileWriter(FILE_PATH);
            myCleaner.write("");
            myCleaner.close();
        } catch (IOException e) {
            return;
        }
    }
}

