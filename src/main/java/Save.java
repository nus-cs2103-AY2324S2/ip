import java.io.*;

public class Save {
    File file;
    FileReader fr;
    FileWriter fw;

    public Save() throws IOException, FileNotFoundException {
        file = new File("data/data.txt");

        try {
            fr = new FileReader(file);
            fw = new FileWriter(file);
        } catch (FileNotFoundException f) {
            try {
                file.createNewFile();
            } catch(IOException e) {
                new File("./data").mkdirs();
            } finally {
                file.createNewFile();
            }
        } finally {
            fr = new FileReader(file);
            fw = new FileWriter(file);
        }
    }

    public void getFilePath() {
        System.out.println("full path: " + file.getAbsolutePath());
        System.out.println("file exists?: " + file.exists());
        System.out.println("is Directory?: " + file.isDirectory());
    }
}
