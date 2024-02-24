# JeromeGPT 
👋 Welcome to JeromeGPT

![JeromeGPT Image](https://jerome-j.github.io/ip/Ui.png)

## Product Introduction
JeromeGPT is a newbie software designed to aid you with the management of your daily tasks!

> Have something you need to keep track of? Simply note it down with JeromeGPT!


## Commands Supported
### 1. Creating events as deadline tasks 

`deadline eventName /by 2020-01-01`
- **Expected Output**:

```
	 Got it. I've added this task: 
	 [D][ ] eventName [Priority: MEDIUM] (by: Jan 01 2020)
	 Now you have 1 task(s) available!
```


### 2. Creating events as todo tasks.
- **Expected Output**:
`todo this is a task that I need to complete!`

```
	 Got it. I've added this task:
	 [T][ ] this is a task that I need to complete! [Priority: MEDIUM]
	 Now you have 2 task(s) available!
```


### 3. Adding events
`event party all day every /from 2023-02-01 /to 2024-03-09`
- **Expected Output**:

```
    Got it. I've added this task:
    [E][ ] party all day every [Priority: MEDIUM] (from: Feb 01 2023 to: Mar 09 2024)
    Now you have 3 task(s) available!
```

### 4. List _all_ tasks
Simply enter the `list` command to list down all the tasks that you have created! 
They are listed in the order that you initially created them in!
- **Expected Output**:

```
	 1. [D][ ] eventName [Priority: MEDIUM] (by: Jan 01 2020)
	 2. [T][ ] this is a task that I need to complete! [Priority: MEDIUM]
	 3. [E][ ] party all day every [Priority: MEDIUM] (from: Feb 01 2023 to: Mar 09 2024)
```

### 5. Finding for _selected_ task(s)
Use the find command to search for a part of the string that you are looking for. It is case-insensitive.
Run the sample command `find tHiS iS A task` which is case-insensitive
- **Expected Output**:

```
	 2. [T][ ] this is a task that I need to complete! [Priority: MEDIUM]
```

> **Warning:** JeromeGPT ensures that the index is valid, so please do not enter a negative or index that does not exist yet. 
> This instruction apply for all commands that make use of index.
> `mark -999` is invalid and will result in 
> ```
>   This is an invalid index
>   There are 3 tasks available.
> ```


### 6. Marking a task as complete
To mark a task as completed, simply type in `mark 1` where 1 is the index that you intend to indicate as completed.
- **Expected Output**:

```
	 Nice! I've marked this task as done:
	 [D][X] eventName [Priority: MEDIUM] (by: Jan 01 2020)
```


### 7. Marking a task as not complete
Similar to point 6, To mark a task as not completed, simply type in `unmark 1` where 1 is the index that you intend to indicate as completed.
- **Expected Output**:

```
	 Nice! I've marked this task as uncompleted:
	 [D][ ] eventName [Priority: MEDIUM] (by: Jan 01 2020)
```


### 8. Setting priority for tasks (Triage)
Use the command `triage 2 LOW` if you wish to mark task 2 as LOW priority. 
There are 3 different priority levels available: HIGH, MEDIUM, LOW.
- **Expected Output**:

```
    Noted, the priority of this task has been modified:
    [T][ ] this is a task that I need to complete! [Priority: LOW]
```

- **The respective change will be reflected in the `list` command.**

```
	 1. [D][ ] eventName [Priority: MEDIUM] (by: Jan 01 2020)
	 2. [T][ ] this is a task that I need to complete! [Priority: LOW]
	 3. [E][ ] party all day every [Priority: MEDIUM] (from: Feb 01 2023 to: Mar 09 2024)
```

### 9. Deleting a task
To delete a task, simply type `delete 2` where you want to remove a task at index 2.
- **Expected Output**:

```
	 Noted, I have removed this task:
	  [T][ ] this is a task that I need to complete! [Priority: LOW]
	 Now you have 2 task(s) in the list.
```


### 10. Goodbye command
Enter `bye` to end your session. Your data is always automatically saved.
To save you time ⏰, the app is instantly closed!

> **Pro-Tip 🎩:** You can simply delete and re-create the task that you intend to modify.

## Feature 1: Autosave 💾
Once you complete a command, whatever changes that you have made is persisted!
So there is no save button!

## Feature 2: Triage (Set Priority Level) 🏥
You can set the priority level for the tasks that you wish to concentrate on!
