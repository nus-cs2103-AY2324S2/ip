package rick;

import rick.tasks.Deadline;
import rick.tasks.Event;
import rick.tasks.Item;
import rick.tasks.ToDo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


public class Storage {
    private Path directoryPath;
    private Path filePath;
    private ArrayList<Item> items = new ArrayList<>();

    /**
     * Creates a new instance of Storage with designated filePath.
     * @param filePath the filePath to store local data.
     */
    public Storage (String filePath) {
        this.filePath = Paths.get(filePath);
        this.directoryPath = this.filePath.getParent();
    }

    /**
     * Returns an ArrayList that contains the list of items stored in local data.
     */
    public ArrayList<Item> load() throws RickException {
        try {
            //If directory data does not exist, create it
            if (!Files.isDirectory(directoryPath)) {
                Files.createDirectory(directoryPath);
            }
            //If file tasks.txt does not exist, create it
            if (!Files.exists(filePath)) {
                Ui. reply("Thank you for using rick.Rick assistant! 어서 와, 리크은 처음이지?\n" +
                        "We are setting up your device for the first time!");
                Files.createFile(filePath);
            }
            BufferedReader reader = Files.newBufferedReader(filePath);
            String line;
            while((line = reader.readLine()) != null) {
                String[] splited = line.split("\\|");
                //T|[ ]|name
                //D|[ ]|name|by
                //E|[ ]|name|from|to
                switch (splited[0]) {
                case ("T"):
                    if (splited.length != 3) {
                        throw new Exception("T length wrong");}
                    this.items.add(new ToDo(splited[2], splited[1]));
                    break;
                case ("D"):
                    if (splited.length != 4) {throw new Exception("D length wrong");}
                    this.items.add(new Deadline(splited[2], splited[1], splited[3]));
                    break;
                case ("E"):
                    if (splited.length != 5) {throw new Exception("E length wrong");}
                    this.items.add(new Event(splited[2], splited[1], splited[3], splited[4]));
                    break;
                default:
                    throw new Exception("starting letter wrong");
                }
            }
        } catch (Exception e) {
            throw new rick.RickException("There's something wrong with your local data... You might want to [check the file], " +
            "or [clear local data]");
        }
        return this.items;
    }

    /**
     * Updates the local data with the Items in the current list.
     * @throws RickException when there is a problem with updating the local data.
     */
    public void update() throws RickException {
        try {
            BufferedWriter writer = Files.newBufferedWriter(filePath);
            for (Item i : items) {
                writer.write(i.store());
                writer.newLine();
            }
            writer.close();
        } catch (Exception e) {
            throw new RickException("There's something wrong with saving the data ㅜㅜ");
        }
    }
}
