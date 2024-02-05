import java.util.*;

import Exceptions.EveExceptions;
import FileStorage.Storage;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


public class Eve {
    
    public static void main(String[] args) {
        Commands.commandHello();
        Commands.commandListener();

    }
}
