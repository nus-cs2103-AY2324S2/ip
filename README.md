# Dook

This is a chat bot that can help you keep track of your tasks!

## Features

### Date and time formatting
All date and time inputs have to be in the format ```yyyy-MM-dd HHmm```.
Example: ```2023-01-01 2359```.

### Adding tasks

There are 4 task types. ```deadline```, ```doafter```,
```event```, and ```todo``` 

The syntax to add a task is ```[task type] [task description] [other parameters, indicated by a forward slash]```

For ```deadline```, the syntax is ```deadline [task description] /by [datetime]```.
Example: ```deadline homework /by 2023-01-01 2359```.

For ```doafter```, the syntax is ```doafter [task description] /after [datetime]```.
Example: ```doafter homework /after 2023-01-01 2350```.

For ```event```, the syntax is ```event [task description] /from [datetime] /to [datetime]```.
Example: ```event homework /from 2023-01-01 2359 /to 2023-01-02 2350```.

For ```todo```, the syntax is ```todo [task description]```
Example: ```todo homework```.

### Indicating task completion

Dook supports 2 commands to indicate a task is done - ```mark``` and ```unmark```.

The syntax is ```mark [index]``` and ```unmark [index]```.
Example: ```mark 2```.

```index``` has to lie between 1 and the total number of tasks Dook has stored.

### Deleting a task

Dook supports task deletion using ```delete```.

The syntax is ```delete [index]```.
Example: ```delete 2```.

```index``` has to lie between 1 and the total number of tasks Dook has stored.

### Listing all tasks

You can list out all the tasks by the command ```list```. No additional arguments required.

### Finding tasks

To find a task, use ```find```. The syntax is ```find [keyword]```. Only exact matches will be listed.
Example: ```find homework```.

### Exiting the application

You can exit the application by entering ```bye```. No additional arguments required.

### Easter eggs

Try ```meow``` and ```dance```!

## Launching Dook

Prerequisites: JDK 11

1. Download ```Dook.jar```
2. Navigate to the file directory containing ```Dook.jar```
3. Launch by entering ```java -jar Dook.jar```
   ```
    ____              _  
   |  _ \  ___   ___ | | __      ╱|
   | | | |/ _ \ / _ \| |/ /    (˚. 。7  
   | |_| | |_| | |_| |   <      |、˜〵
   |____/ \___/ \___/|_|\_\     じし_,)ノ
   ```
