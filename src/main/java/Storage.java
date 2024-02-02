import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {
    private File cacheFile;
    
    public Storage(String filePath) {
        cacheFile = new File(filePath);
        if (!cacheFile.exists()) {
            try {
                cacheFile.createNewFile();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }   
        }
    }

    public ArrayList<Command> load() throws PandaException {
        ArrayList<Command> clist = new ArrayList<>();
        try {
            Scanner myReader = new Scanner(cacheFile);
            int idx = 1;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] parts = data.split("\\|");
                for (int i = 0; i < parts.length; i++) {
                    parts[i] = parts[i].trim();
                }
                if(parts[0].equals("T")) clist.add(new NewTaskCommand(new Todo(parts[2])));
                else if(parts[0].equals("D")) clist.add(new NewTaskCommand(new Deadline(parts[2], parts[3])));
                else if(parts[0].equals("E")) clist.add(new NewTaskCommand(new Event(parts[2],  parts[3], parts[4])));

                if(parts[1].equals("1")) clist.add(new AlterMarkCommand(idx, true));
                idx = idx + 1;
            }
            myReader.close();
        } catch (IOException e) {
            // System.out.println("Something went wrong\n");
            throw new CorruptedFileException();
        }
        // System.out.println("inserted " + clist.size() + " tasks");
        return clist;
    }

    public void save(TaskList tlist) {
        try (FileWriter writer = new FileWriter("./src/main/list.txt", false)) {
            writer.write(tlist.saveString());
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
