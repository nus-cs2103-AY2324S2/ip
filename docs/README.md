# Jimmy User Guide

<img width="302" alt="Ui" src="https://github.com/yuechen2001/ip/assets/64315146/2a59010c-4d83-4124-bd4e-8265e6f9c284">

Jimmy is a chatbot named after my favourite host Jimmy Fallon.
Jimmy is a desktop app for managing your notes and tasks via entering commands (case-sensitive).
This user guide will teach about Jimmy's features and how to use Jimmy.

#### Credits for the markdown sample goes to: https://github.com/Cikguseven/ip

## Features

### 📅 Add a Deadline

Add a Deadline task to the item list by using the following command:

```
deadline [task] /by dd-MM-yyyy
```

### 📆 Add an Event

Add an Event task to the item list by using the following command:

```
event [task] /from dd-MM-yyyy /to dd-MM-yyyy
```

### ✅ Add a Todo

Add a Todo task to the item list by using the following command:

```
todo [task]
```

### 🗑️ Delete an item

Delete an item from the item list by using the following command:

```
delete [item index in the list]
```

### 📋️ List all items

List all items stored by Jimmy by using the following command:

```
list
```

### 🔔 View schedules

View your schedule for any day by using the following command:

```
schedule dd-MM-yyyy
```

### 🔍 Find items

Find items containing a keyword by using the following command:

```
find [keyword]
```

### ✔ Mark a task as done

Mark a task (Deadline/Event/Todo) as done by using the following command:

```
mark [item number]
```

### ❌ Mark a task as not done

Mark a task (Deadline/Event/Todo) as not done by using the following command:

```
unmark [item number]
```

### 👋 Exit programme

Exit the programme after a short delay by using the following command:

```
bye
```

## Usage

Type in any of the above commands into the chatbox and hit Enter or click the 'Send' button.

Example of usage:

`deadline Submit project /by 11-09-2024`

Expected outcome:

Jimmy adds your deadline task to the list of items. Jimmy then returns the following confirmation message.

```
Jimmy has added:
...
[D][] Submit project (by: 11 Sep 2024)
...

You now have X items in the list.
```
