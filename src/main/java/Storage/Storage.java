package Storage;
import java.io.File;

public class Storage {

    protected String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public boolean storageFileExist() {
        try {
            File file = new File(this.filepath);
            return file.exists();
        } catch (SecurityException e) {
            System.err.println("SecurityException occurred." );
            return false;
        } catch (NullPointerException e) {
            System.err.println("NullPointerException occurred. ");
            return false;
        } catch (Exception e) {
            System.err.println("An error occurred with Function storageFileExist(). ");
            return false;
        }
    }
}
