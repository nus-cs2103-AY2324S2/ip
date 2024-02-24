# Iris User Guide

Iris is a chatbot that helps you manage your tasks. It is a simple and easy-to-use chatbot that can help you keep track of your tasks and deadlines.

## Quick Start

1. Download the latest version of Iris from [here](https://github.com/jxunze/ip/releases/tag/Level-10).
2. Run the following command in the terminal to start Iris:
   ```
   java -jar iris.jar
   ```
## Table of Contents

1. [Adding events](#adding-events)
2. [Adding deadlines](#adding-deadlines)
3. [Adding todos](#adding-todos)
4. [Listing all tasks](#listing-all-tasks)
5. [Marking tasks as done](#marking-tasks-as-done)
6. [Unmarking tasks as done](#unmarking-tasks-as-done)
6. [Deleting tasks](#deleting-tasks)
7. [Finding tasks](#finding-tasks)
8. [Exiting the program](#exiting-the-program)
9. [Create a folder](#create-a-folder)
10. [List all folders](#list-all-folders)
11. [Switch to a folder](#switch-to-a-folder)

## Adding events
Adds an event to the task list.

```event <description> /from <datetime> /to <datetime>```

## Adding deadlines
Adds a deadline to the task list.

```deadline <description> /by <datetime>```

## Adding todos
Adds a todo to the task list.

```todo <description>```

## Listing all tasks
Lists all tasks in the task list.

```list```

## Marking tasks as done
Marks a task as done.

```mark <task number>```

## Unmarking tasks as done
Unmarks a task as done.

```unmark <task number>```

## Deleting tasks
Deletes a task from the task list.

```delete <task number>```

## Finding tasks
Finds tasks that match the given keyword.

```find <keyword>```

## Exiting the program
Exits the program.

```bye```

## Create a folder
Creates a folder to store the task list.

```mkdir <folder name>```

## List all folders
Lists all folders in the current directory.

```ls```

## Switch to a folder
Switches to a folder to store the task list.

```cd <folder name>```