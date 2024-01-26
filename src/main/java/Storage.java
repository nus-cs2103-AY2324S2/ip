import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Storage {

    String fileLocation;
    LocalStorage local;
    TempStorage temp;

    public Storage(String fileLocation) throws StartUpException {
        this.fileLocation = fileLocation;
        temp = new TempStorage();

        try {
            local = new LocalStorage(fileLocation);
            if (local.createdNewFile()) {
                saveFileNotFound();
            } else {
                saveFileFound();
                ArrayList<String> loadList = local.load();
                temp.load(loadList); //Load from local into temp
            }

        } catch (IOException | ProcessingException e) {
            String message = "An error occurred with the save file. Try again";
            e.printStackTrace();
            throw new StartUpException(message);
        }

    }
    private void saveFileNotFound() {
        System.out.println("Save file not found! Created an new one!");
    }    
    
    private void saveFileFound() {
        System.out.println("Save file found! Loading old save...");
    }

    public void update() throws ProcessingException {
        try {
            Stream<String> stringStream = temp.save();
            local.save(stringStream);

        } catch (IOException e) {
            String message = "An error occurred while trying to update the save file. Try again";
            e.printStackTrace();
            throw new ProcessingException(message);
        }
    }

    public void add(Task task) throws ProcessingException {
        temp.add(task);
    }
    public void delete(int i) throws ProcessingException {
        temp.delete(i);
    }
    public void mark(int i) throws ProcessingException {
        temp.mark(i);
    }
    public void unmark(int i) throws ProcessingException {
        temp.unmark(i);
    }
    public void displayList() {
        temp.displayList();
    }


    public void create(Task task) {}
    public void read(int idx) {}
    public void update(int idx, Task task) {}  

}
