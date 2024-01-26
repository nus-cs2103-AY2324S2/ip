package DukeIO;

import CONSTANTS.FILEPATH;



public class File {
    public static boolean isExist() {
        java.io.File file = new java.io.File(FILEPATH.PATH);
        System.out.println("file exists?: " + file.exists());
        return file.exists();
    }
    public static void main(String[] args) {
    }
}
