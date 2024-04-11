package command;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class ArchiveCmd extends Command {
    private File file;
    public String execute() {
        try {
            createNewFileIfNeeded();
            save();
            tasks.clear();
            response = ui.ArchivedResponse();
            return response;
        } catch (IOException e) {
            return e.getMessage();
        }
    }
    public void createNewFileIfNeeded() throws IOException {
        File dir = new File("archive");
        File file = new File("archive/archived.txt");
        if (!dir.exists() && !file.exists()) {
            dir.mkdir();
            file.createNewFile();
        } else if(!file.exists()) {
            assert dir.exists(): "How can file exist without diectory existing?";
            file.createNewFile();
        }
    }

    public void save() throws IOException {
        FileWriter fw = new FileWriter("archive/archived.txt");
        fw.write(tasks.toDataFormat());
        fw.close();
    }
    public ArchiveCmd() {

    }
}
