# Podz

Podz is a **task managing desktop app** designed to enhance your productivity with a blend Command Line Interface (CLI) efficiency and the user-friendly appeal of a Graphical User Interface (GUI). By seamlessly combining the benefits of CLI speed with the intuitive GUI, Podz empowers users to complete tasks effortlessly.

![Podz GUI](docs/Ui.png "Podz GUI")

## Quick Start

1. Ensure you have Java `11` or above installed.
2. Download the latest `podz.jar` [here](https://github.com/raysonchia/ip/releases).
3. Copy the file to the folder you want to use as the *home folder* for Podz.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar podz.jar` command to run the application.
A GUI similar to the above should appear in a few seconds.
1. Type a command in the command box and press Enter to execute it. 
2. Refer to the [user guide](docs/README.md) for details of each command.

## Command Summary

| Command | Format | Example |
|---------|--------|---------|
| List tasks | `list` | `list` |
| Add todo | `todo DESCRIPTION` | `todo borrow book` |
| Add deadline | `deadline DESCRIPTION /by YYYY-MM-DD [HHmm]`  | `deadline return book /by 2024-01-05 1800` |
| Add event | `event DESCRIPTION /from YYYY-MM-DD [HHmm] /to YYYY-MM-DD [HHmm]` | `event project meeting /from 2024-02-01 1200 /to 2024-02-01 1600` |
| Mark task | `mark INDEX` | `mark 1` |
| Unmark task | `unmark INDEX` | `unmark 1` |
| Delete task | `delete INDEX` | `delete 2` |
| Find tasks | `find KEYWORD(s)` | `find book` |
| Exit program | `bye` | `bye` |
