package handler;

import java.util.Arrays;

public class DataHandler {
    public static final DataHandler instance = new DataHandler();
    private static String[] data = new String[100];
    private static int index = 0;
    private DataHandler() {};

    public void handleData(String msg) {
        if (index >= 100) return;
        data[index] = msg;
        index++;
        // Todo: Exception handling
    }

    public String[] getData() {
        return Arrays.copyOfRange(data, 0, index);
    }
}
