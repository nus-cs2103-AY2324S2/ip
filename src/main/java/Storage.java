import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Storage {
    String filepath;

    public Storage(String filepath){
        this.filepath = filepath;
    }

    public ArrayList<Task> load() throws GandalfException{
        ArrayList<Task> data = new ArrayList<>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filepath))){
            data = (ArrayList<Task>) ois.readObject();
        }
        catch(FileNotFoundException e){
            throw new GandalfException("The file is lost in time");
        }
        catch(IOException | ClassNotFoundException e){
            throw new GandalfException("Error with IO or class");
        }
        return data;
    }
    public void store(ArrayList<Task> arrayList) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.filepath, false))) {
            oos.writeObject(arrayList);
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
/**
    public static void writeToFile(ArrayList<Task> list){
        File docsFolder = new File("docs");
        if (!docsFolder.exists()) {
            docsFolder.mkdir();
        }
        String filePath = "docs/gandalfData.txt";
        File file = new File(filePath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
            for(int i = 0; i < list.size(); i++){
                Task action = list.get(i);
                writer.write((i + 1) + ". " + action);
                writer.newLine();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
*/