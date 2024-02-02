import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {
    ArrayList<String> listStates= new ArrayList<>();
    private static String listFilePath = "./data/duke.txt";
    public ArrayList<Task> loadTasks(){
        ArrayList<Task> temp = new ArrayList<>();
        this.initializeListFile();
        File f = new File(listFilePath);
        try{
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String str = s.nextLine();
                listStates.add(str);
                Parser.initializeTask(str,temp);
            }
        }catch (FileNotFoundException e){
            System.out.println("FILE NOT FOUND");
        }
        return temp;
    }
    private void writeList() {
        this.initializeListFile();
        try {
            FileWriter fw = new FileWriter(listFilePath);
            for (int i = 0; i < listStates.size(); i++) {
                fw.write(listStates.get(i) + "\n");
            }
            fw.close();
        }catch(IOException e){
            System.out.println("File Saving failed..." + e);
        }
    }
    public void removeListStateRecord(int index){
        this.listStates.remove(index);
        this.writeList();
    }
    public void addListStateRecord(String type, String[] data){
        String newRecord = "";
        switch (type){
        case "todo":
            String[] recordT = {"T", "0", data[0]};
            newRecord = String.join(" | ", recordT);
            break;
        case "deadline":
            String[] recordD = {"D", "0", data[0], data[1]};
            newRecord = String.join(" | ", recordD);
            break;
        case "event":
            String[] record = {"E", "0", data[0], data[1], data[2]};
            newRecord = String.join(" | ", record);
            break;
        default:
            break;
        }
        this.listStates.add(newRecord);
        this.writeList();
    }
    public void modifyStateRecord(boolean isMarking, int index){
        String[] record = this.listStates.get(index).split(" \\| ");
        record[1] = isMarking ? "1" : "0";
        String newRecord = String.join(" | ", record);
        this.listStates.set(index, newRecord);
        this.writeList();
    }
    public void initializeListFile(){
        try{
            File dir = new File("./data/");
            if (!dir.exists()) {
                if (dir.mkdirs()) {
//                    System.out.println("Initializaing....");
                } else {
//                    System.out.println("Failed to create the directory.");
                    return;
                }
            }
            File file = new File(listFilePath);
                if (!file.exists()) {
                    if (file.createNewFile()) {
                        //System.out.println("File created: " + file.getAbsolutePath());
                    } else {
                        //System.out.println("Failed to create the file.");
                    }
                }
        }catch(IOException e){
            System.out.println("FILE Creation failed" + e);
        }
    }
}
