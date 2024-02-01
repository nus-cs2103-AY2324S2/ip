package hirwan;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Parser {
    public static int translate(String text) {

        if (text.equals("list")) {
            return 1;
        } else if (text.startsWith("todo")) {
            return 2;
        } else if (text.startsWith("deadline")) {
            return 3;
        } else if (text.startsWith("event")) {
            return 4;
        } else if (text.startsWith("mark")) {
            return 5;
        } else if (text.startsWith("unmark")) {
            return 6;
        } else if (text.startsWith("delete")) {
            return 7;
        } else if (text.equals("bye")) {
            return 8;
        } else {
            return 9;
        }
    }
}