import java.io.*;
import java.util.ArrayList;

/**
 * Stores two files: 1 stored as input streams not meant to be read but for easier loading, the other is stored
 * in readable format
 */
public class Storage {
    String filePathMeta;
    String filePathRead;

    public Storage(String filePathMeta, String filePathRead){
        this.filePathMeta = filePathMeta;
        this.filePathRead = filePathRead;
    }

    public ArrayList<Task> load() throws GandalfException{
        ArrayList<Task> data = new ArrayList<>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.filePathMeta))){
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
        //store as readable format
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePathRead))){
            for(int i = 0; i < arrayList.size(); i++) {
                Task action = arrayList.get(i);
                writer.write((i + 1) + ". " + action);
                writer.newLine();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.filePathMeta, false))) {
            oos.writeObject(arrayList);
        } catch (IOException e) {
            e.getMessage();
        }
    }
}