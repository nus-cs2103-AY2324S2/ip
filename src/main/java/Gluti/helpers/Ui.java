package Gluti.helpers;

import Gluti.utils.GlutiException;

import java.util.Scanner;

public class Ui {
    private Parser parser;
    private boolean isExit ;
    private Scanner sc;

    public Ui(FileStorage fStorage){
        this.parser = new Parser(fStorage);
        isExit  = false;
        sc = new Scanner(System.in);
    }

    public void run() throws GlutiException {
        parser.parse(sc.nextLine());
        while(!isExit) {
            parser.parse(sc.nextLine());
            isExit = parser.isLooping();
        }
    }
}
