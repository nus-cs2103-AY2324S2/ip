# Hammy User Guide

Hammy chatbot is an application for people to navigate their tasks in daily lives. It consists of a variety of tasks, including Todo, Deadline and Event tasks. Besides, Hammy chatbot also includes other features, such as list, mark and unmark to help you control your tasks more effeciently. Most importantly, it is user-friendly and easy-to-use!

Click [here](https://github.com/jinhanfromNUS/ip/blob/master/docs/README.md) if there are problems with this user guide. (For some reason, it does not show the image/emojis from the website.)

* [📖 How to read?](#how-to-read) 
* [💡 Features](#features)
  * [Viewing all available commands within application](#viewing-all-available-commands-within-application)
  * [Listing all tasks](#listing-all-tasks)
  * [Adding tasks](#adding-tasks)
  * [Deleting tasks](#deleting-tasks)
  * [Marking tasks as done](#marking-tasks-as-done)
  * [Marking tasks as undone](#marking-tasks-as-undone)
  * [Other listing tasks methods](#other-listing-tasks-methods)
  * [Say Hi To Me <3](#say-hi-to-me-3)
  * [Surprised!](#surprised)
* [❓ FAQ](#faq)
* [🐹 Future Updates](#future-updates)
* [🖼️ How does it look like?](#how-does-it-look-like)

## 📖 How to read?
```
[A][B] YOUR_TASK_HERE (Information: date ...)
```

* A: The type of tasks (T for todo task, D for deadline task and E for event task).
* B: Representation of the task is done or not (X as done).
* YOUR_TASK_HERE: The decription of your task.
* Information: The representation of the date in the following.
* date: Just... date (not the romantic one 🌹).

> [!CAUTION]
> Date format represented in list and date format in command is different.
> 
> * Command: YYYY-MM-DD
> 
> * List: DDMMMYYYY

## 💡 Features 

### • Viewing all available commands within application
To view all available commands within application, we can enter `help` to in the user input to list out all commands.

Command:
```
help
```

### • Listing all tasks
You can list all your tasks using the `list` command.

Command:
```
list
```

Shortcut:
```
/ls
```

> [!NOTE]
> If there is no tasks in the list, Hammy will output `There is no tasks currently!` instead


### • Adding tasks
There are three types of tasks
1. todo: A task with a description
2. deadline: A task with a Deadline date
3. event: A task with a From date and a to Date

**Adding todo tasks:**
```
todo YOUR_DESCRIPTION_HERE
```

Shortcut:
```
/t YOUR_DESCRIPTION_HERE
```

Expected outcome:
```
Got it. I have added:
[T][ ] YOUR_DESCRIPTION_HERE
```

**Adding deadline tasks:**
```
deadline [YOUR_DESCRIPTION_HERE] /by: YYYY-MM-DD
```

Shortcut:
```
/d [YOUR_DESCRIPTION_HERE] /by: YYYY-MM-DD
```

Expected outcome:
```
Got it. I have added:
[D][ ] YOUR_DESCRIPTION_HERE (by: DDMMMYYYY)
```

> [!NOTE]
> For deadline tasks, description is optional.

**Adding event tasks:**
```
event [YOUR_DESCRIPTION_HERE] /from: YYYY-MM-DD /to: YYYY-MM-DD
```

Shortcut:
```
/e [YOUR_DESCRIPTION_HERE] /from: YYYY-MM-DD /to: YYYY-MM-DD
```

Expected outcome:
```
Got it. I have added:
[E][ ] YOUR_DESCRIPTION_HERE (from: DDMMMYYYY to: DDMMMYYYY)
```

> [!NOTE]
> Just like deadline tasks, description is optional.


### • Deleting tasks
You can delete certain tasks using `delete` command.
```
delete TASK_INDEX
```

Shotcut:
```
/del TASK_INDEX
```

> [IMPORTANT!]
> Task index must be a valid index. If you wish to know the indexes of each tasks, you can use `list` command.


### • Marking tasks as done
You can mark certain task as done using `done` command.
```
done TASK_INDEX
```


### • Marking tasks as not done
You can mark certain task as not done using `undone` command.
```
undone TASK_INDEX
```


### • Other listing tasks methods
There are various types of ways to list your tasks
1. done: List out only completed tasks
2. undone: List out tasks which are not completed
3. top done: List out completed tasks at top, and incomplete tasks at bottom
4. top undone: List out incomplete tasks at top, and completed tasks at bottom
5. alphabetically: List out your tasks in alphabetical order

**List out only completed tasks**
```
list done
```

**List out tasks which are not completed**
```
list undone
```

**List out completed tasks at top, and incomplete tasks at bottom**
```
list top done
```

**List out incomplete tasks at top, and completed tasks at bottom**
```
list top undone
```

**List out your tasks in alphabetical order**
```
list alphabetically
```

Shortcut:
```
list a
```

> [!TIP]
> all `list` in the command can be shotcutted to `/ls`.
> If the method of `list` is not recognizable, it will list in default.


### Say Hi To Me <3
Say **Hi** to Hammy!

```
Hi
```

Or:
```
Hello
```


### Surprised!
Nothing much! Just some little easter eggs!
Call me maybe🤔? 😏

```
Hammy
```

## ❓ FAQ
1. Where can I check your Github page?
* You can click [here](https://github.com/jinhanfromNUS/ip)

2. What is the date format? Why is my date format wrong?
* Date format should be YYYY-MM-DD (e.g. 2023-01-01). This only applies for entering the date format.

3. Why is my date not working?
* The date format in your list and the date format while adding deadline/event tasks is different. Adding tasks is YYYY-MM-DD, while output list is DDMMMYYYY. You can check [here](#how-to-read).

4. Why is my *delete* / *mark as done* / *mark as not done* not working?
* Please check the task index, it could likely to be invalid task index. You can enter `list` or `/ls` to check the index for each tasks.


5. Why deadline and event tasks even work without a description?
* That is not a bug! It is meant to be like that. Sometimes, you just need a date to remind you about your remaining tasks :).


6. Do I need to create a separate database while using this application?
* No! It is totally fine if there is no database upon using this application. The application will create a text file called **tasklist.txt** store all your tasks. **tasklist.txt** will be in the same directory as your jar file.


## 🐹 Future Updates
I will be adding more features, such as mass delete and mass marking. Besides, I will also enhance the GUI of the application. Moreover, I will try to make customizable available, so that you can customize your own bot name and theme!

## 🖼️ How does it look like?
![Image of the current GUI](Ui.png)
