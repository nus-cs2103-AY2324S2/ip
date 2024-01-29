import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/* Handles loading and storing of data in local files */
public class FileManager {
    
    public String StorageFileName = "StorageData.txt";
    public String FileDirectory = "bin" + File.pathSeparator + "SavedData";

    // General save method
    public void Save(String _data, String _fileName) throws IOException {
        try {
            File file = new File(_fileName);
            if (!file.exists()) {
                file.createNewFile();
            }

            PrintWriter pw = new PrintWriter(new File(_fileName));
            
            pw.write(_data);
            
            pw.flush();
            pw.close();
        } catch (Exception e) {
            throw new IOException("FileManager cannot save data to " + _fileName, e);
        }
    }
    
    // Saves data in storage file
    public void SaveStorageData(String _data) throws IOException {
        try {
            Save(_data, FileDirectory + StorageFileName);
        } catch (Exception e) {
            throw e;
        }
    }

    // General load method
    public String Load(String _fileName) throws IOException {
        try {
            File file = new File(_fileName);
            if (!file.exists()) {
                file.createNewFile();
            }

            FileReader fr = new FileReader(_fileName);
            BufferedReader br = new BufferedReader(fr);
            String line;
            String result = "";

            while((line = br.readLine()) != null) {
                result += line;
                result += '\n';
            }

            br.close();
            fr.close();

            return result;
        } catch (IOException e) {
            throw new IOException("FileManager cannot load data from " + _fileName, e);
        }
    }

    // Loads data in storage file
    public String LoadStorageData() throws IOException {
        try {
            return Load(FileDirectory + StorageFileName);
        } catch (IOException e) {
            throw e;
        }
    }
}
