# Andelu Task Bot
> [!NOTE]
> Life lacks excitement without tasks - Andelu

Andelu Task Bot is here to help you keep track of all your tasks, ensuring your life is both meaningful and seamless.
It is:

- Text based
- Easy to learn 
- ~~Fast~~ _SUPER SUPER FAST_ to use

  [Getting Started](#Getting Started)

## Getting Started 💻

To use  Andelu Bot, follow these steps:

1. Ensure you have Java version `11` or above installed on your Computer.
2. Download the `latest` jar file from [here](https://github.com/AndrewOng2066/ip/releases) (`jar` File)
3. Run the `jar` file (double click it) or Open a command terminal and run the command `java -jar AndeluLauncher.jar` in the directory that contains the jar file.
4. Add your tasks (Todo, Deadline, Event) via Command Line Interface (CLI)
5. Let Andelu Bot manage your tasks😉
![Ui](https://github.com/AndrewOng2066/ip/assets/156929179/5edfabda-b64a-4fe0-8328-510581945530)

And it is **FREE** to use! 🚫💵

## Features

### ▶️ Listing all the existing tasks in the list/ txt file: `list`
List down all the tasks in the list/txt file. <br>
Format: `list`<br>

<br>

### ▶️ Adding a new todo task: `todo`
Add a new todo task to the list, and it will be stored in a txt file.<br>
Format: `todo DESCRIPTION`<br>

<br>

### ▶️ Adding a new deadline task: `deadline`
Add a new deadline task to the list, and it will be stored in a txt file.<br>
Format: `deadline DESCRIPTION /by yyyy-MM-dd HH:mm`<br>

<br>

### ▶️ Adding a new event task: `event`
Add a new event task to the list, and it will be stored in a txt file.<br>
Format: `event DESCRIPTION /from yyyy-MM-dd HH:mm /to yyyy-MM-dd HH:mm`<br>

<br>

### ▶️ Deleting a task: `delete`
Delete an existing task in the list, and it will be removed from the txt file.<br>
Format: `delete INDEX`<br>
📝The `INDEX` refers to the index number of a task in the list.<br> 
⚠️: `INDEX` must be positive and not exceed the size of the list.

<br>

### ▶️ Marking a task: `mark`
Mark a task in the list as completed. <br>
Format: `mark INDEX`<br>
📝The `INDEX` refers to the index number of a task in the list. <br>
⚠️: `INDEX` must be positive and not exceed the size of the list.<br>

<br>

### ▶️ Unmarking a task: `unmark`
Unmark a task in the list as incomplete. <br>
Format: `unmark INDEX`<br>
📝The `INDEX` refers to the index number of a task in the list. <br>
⚠️: `INDEX` must be positive and not exceed the size of the list.<br>

<br>

### ▶️ Searching tasks based on the description: `search`
Find all tasks with matching keywords for description. <br>
Format: `search DESCRIPTION`<br>

<br>

### ▶️ Searching tasks based on the date: `date`
Find all tasks with the matching date. <br>
Format: `date yyyy-MM-dd`<br>

<br>

### ▶️ Setting priority level
Add a priority level to each new task.<br>
📝: `DEFAULT` if not stated explicitly.<br>
🎚️Priority Levels: `HIGH`, `MEDIUM`, `LOW`, `DEFAULT`<br>
Format: `todo DESCRIPTION /priority LEVEL`<br>

<br>

### ▶️ Exiting the program: `bye`
Exit the program.<br>
Format: `bye`<br>

<br>
<br>

## Command Summary
| Action | Command Format, Examples |
| --- | --- | 
| List content | `List` |
| Add todo | `todo DESCRIPTION` <br>e.g., `todo read book` |
| Add deadline | `deadline DESCRIPTION /by yyyy-MM-dd HH:mm` <br>e.g., `deadline return book /by 2024-02-20 23:59` | 
| Add event | `event DESCRIPTION /from yyyy-MM-dd HH:mm /to yyyy-MM-dd HH:mm` <br>e.g., `event meeting /from 2024-02-20 15:00 /to 2024-02-20 17:00` |
| Delete task | `delete INDEX` <br>e.g., `delete 2` <br>The `INDEX` refers to the index number of a task in the list. <br>⚠️: `INDEX` must be positive and not exceed the size of the list. |
| Mark for completed | `mark INDEX` <br>e.g., `mark 2` <br>The `INDEX` refers to the index number of a task in the list. <br>⚠️: `INDEX` must be positive and not exceed the size of the list. |
| UnMark for incomplete | `unmark INDEX` <br>e.g., `unmark 2` <br>The `INDEX` refers to the index number of a task in the list.  <br> ⚠️: `INDEX` must be positive and not exceed the size of the list. |
| Search task based on description | `search DESCRIPTION` <br>e.g., `search book` |
| Search task based on date | `date yyyy-MM-dd` <br>e.g., `date 2024-02-20` |
| Setting priority | `todo DESCRIPTION /priority LEVEL` <br>e.g., `todo join club /priority medium` <br>📝: `DEFAULT` if not stated explicitly <br> Set the priority level for each task. <br> Levels: `HIGH`, `MEDIUM`, `LOW`, `DEFAULT`|
| Exit | `bye` |


## Java Developer Guide
Entry point for Andelu Bot (Duke.java):
```
public class Launcher {

    public static void main(String[] args) {
        Application.launch(MainGui.class, args);
    }
}
```
