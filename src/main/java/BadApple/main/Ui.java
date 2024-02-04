package BadApple.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class Ui {
    public static void showWelcome() {
        String logo = " __      __.__    .__  __             _________                          \n" +
                "/  \\    /  \\  |__ |__|/  |_  ____    /   _____/__________    ____  ____  \n" +
                "\\   \\/\\/   /  |  \\|  \\   __\\/ __ \\   \\_____  \\\\____ \\__  \\ _/ ___\\/ __ \\ \n" +
                " \\        /|   Y  \\  ||  | \\  ___/   /        \\  |_> > __ \\\\  \\__\\  ___/ \n" +
                "  \\__/\\  / |___|  /__||__|  \\___  > /_______  /   __(____  /\\___  >___  >\n" +
                "       \\/       \\/              \\/          \\/|__|       \\/     \\/    \\/ ";


        System.out.println("Welcome to");
        System.out.println(logo);
        System.out.println("You booted up your laptop and wonder what to do...");
    }
}
