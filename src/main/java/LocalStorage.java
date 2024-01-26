import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.List;

public class LocalStorage {
        boolean createdNewFile;
        PrintWriter printWriter;
        Scanner scan;
        String fileLocation;
        File saveFile;

        public LocalStorage(String fileLocation) throws IOException {
            this.fileLocation = fileLocation;
            this.saveFile = new File(fileLocation);
            this.createdNewFile = saveFile.createNewFile();
            this.printWriter = new PrintWriter(new FileOutputStream(saveFile, true));
            this.scan = new Scanner(saveFile);
        }

        public boolean createdNewFile() {return createdNewFile;}

        public ArrayList<String> load() {
            ArrayList<String> list = new ArrayList<>();
            while (scan.hasNextLine()) {
				String encodedTask = scan.nextLine();
                list.add(encodedTask);
			}
            return list;
        }

        public void save(Stream<String> encodedTasks) throws IOException {
            clearAll();
            printWriter = new PrintWriter(new FileOutputStream(saveFile, true));
            List<String> encodedTasksList = encodedTasks.collect(Collectors.toList());
            for (String string : encodedTasksList) {
                printWriter.println(string);
            }
            printWriter.close();
        }

        public void clearAll() throws IOException {
            printWriter = new PrintWriter(new FileOutputStream(saveFile, false));
            printWriter.close();
        }

        public void create(Task task) {}
        public void read(int i) {}
        public void update(int i, Task newTask) {}
        public void delete(int i) {}


}
