import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
public class FileManipulation {
    private static final String FILEPATH = "./data/Jux.txt";
    private File f;
    private boolean doesExist;
    public FileManipulation () {
        this.f = new File(FILEPATH);
        this.doesExist = f.exists();
        File parentDirectory = f.getParentFile();
        if (!parentDirectory.exists()) {
            parentDirectory.mkdirs(); // Creates parent directories if they don't exist
        }
        if (!f.exists()) {
            try{
                f.createNewFile();
            } catch(java.io.IOException e) {
                e.printStackTrace();
            }

        }
    }
    public boolean getDoesExist() {
        return this.doesExist;
    }
    public ArrayList<Task> loadFile(ArrayList<Task> list) throws FileNotFoundException{
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String data = s.nextLine();
            char isDoneChar = data.charAt(2);
            boolean isDone = isDoneChar == 1;
            switch(data.charAt(0)) {
                case 'T':
                    list.add(new Todo(data.substring(4), isDone));
                    break;
                case 'D':
                    int dateIndex = Util.findNthDivider(data, 3);
                    list.add(new Deadline(data.substring(4,dateIndex), isDone, data.substring(dateIndex)));
                        break;
                case 'E':
                    int dateStartIndex = Util.findNthDivider(data, 3);
                    int dateEndIndex = Util.findNthDivider(data,4);
                    list.add(new Event(data.substring(4,dateStartIndex), isDone,
                            data.substring(dateStartIndex, dateEndIndex), data.substring(dateEndIndex)));
                    break;
                default:
                    break;

            }

        }
        return list;
    }
    static public void saveFile (ArrayList<Task> list) {
        String toSave = "";
        for (int i = 0; i < list.size(); i ++) {
            toSave += list.get(i).saveStorage() + "\n";
        }
        try{
            FileWriter fw = new FileWriter(FILEPATH);
            fw.write(toSave);
            fw.close();
        } catch(java.io.IOException e ) {
            e.printStackTrace();
        }

    }
}
