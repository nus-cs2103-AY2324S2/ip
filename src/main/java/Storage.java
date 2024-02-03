import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Storage {

  private final String filePath;

  public Storage(String filePath) {
    this.filePath = filePath;
  }

  private static void writeToFile(String filePath, String textToAdd) throws IOException {
    FileWriter fw = new FileWriter(filePath);
    fw.write(textToAdd);
    fw.close();
  }

  public File load() throws BenException, FileNotFoundException {
    // hard-coded
    String targetFolder = "data";

    if (!Files.exists(Paths.get(targetFolder))) {
      throw new BenException("./data/ folder path does not exist.");
    }

    // File does not exist
    if (!Files.exists(Paths.get(this.filePath))) {
      throw new BenException("target file does not exist.");
    }

    File file = new File(this.filePath);
    return file;
  }

  public void save(String saveInput) throws BenException {
    String targetFolder = "data";

    if(!Files.exists(Paths.get(targetFolder))) {
      throw new BenException("./data/ folder path does not exist.");
    }

    // File does not exist
    if (!Files.exists(Paths.get(this.filePath))) {
      throw new BenException("target file does not exist.");
    }

    try {
      writeToFile(this.filePath, saveInput);
    } catch (IOException e) {
      System.out.println("Something went wrong: " + e.getMessage());
    }
  }
}
