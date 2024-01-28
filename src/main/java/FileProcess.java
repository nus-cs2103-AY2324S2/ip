import java.io.*;

public class FileProcess {
    private static File openFile = null;

    public static void fileOpen(String filePath) {
        FileProcess.openFile = new File(filePath);
        File parentDir = FileProcess.openFile.getParentFile();
        if (!parentDir.exists()) {
            if (parentDir.mkdirs()) {
                System.out.println("Required directories created successfully!");
            } else {
                System.out.println("Required directories are found!");
            }
        }
        try {
            if (FileProcess.openFile.createNewFile()) {
                System.out.println("Task List file created successfully!");
            } else {
                System.out.println("Existing Task List file is found!");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Error occurred when creating new file for Task List!");
        }
    }

    public static int fileWrite(String text) {
        if (openFile == null) {
            return -1;
        } else {
            try {
                FileOutputStream fos = new FileOutputStream(FileProcess.openFile);
                OutputStreamWriter osw = new OutputStreamWriter(fos);
                BufferedWriter writer = new BufferedWriter(osw);
                try {
                    writer.write(text);
                    writer.close();
                    osw.close();
                    fos.close();
                    return 0;
                } catch (IOException e) {
                    System.out.println("Error occurred when writing to Task List file!");
                    return -1;
                }
            } catch (FileNotFoundException e) {
                System.out.println("Required file does not exist!");
                return -1;
            }
        }
    }

    public static String fileRead() {
        try {
            FileInputStream fis = new FileInputStream(FileProcess.openFile);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);
            try {
                String line;
                StringBuilder res = new StringBuilder();
                System.out.println("Loading data...");
                while((line = reader.readLine()) != null && !line.equals("\n")) {
                    res.append(line).append(" ");
                }
                reader.close();
                isr.close();
                fis.close();
                return res.toString();
            } catch (IOException e) {
                System.out.println("Error occurred when reading file content!");
                return "";
            }
        } catch (FileNotFoundException e) {
            System.out.println("Required file does not exist!");
            return "";
        }
    }
}
