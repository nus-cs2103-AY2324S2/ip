package me.ruibin.leto;

import me.ruibin.leto.parser.Parser;
import me.ruibin.leto.tasklist.TaskList;
import me.ruibin.leto.ui.Ui;

public class Main {
    public static void main(String[] args) {
        Ui.letoLogo();
        TaskList.initFromFile();
        Parser.readCommandAndExecute();
    }
}
