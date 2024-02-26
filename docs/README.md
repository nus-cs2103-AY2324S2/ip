# Yippee User Guide
## Yippee Screenshot
![img.png](Ui.png)

## Yippe intro
Yippe is a chatbot operating through a *Graphic User Interface (GUI)* 
that helps you organize your todo list. 
You can **add, delete, mark and unmark** tasks in your list.

Example: `todo something`

// A description of the expected outcome goes here

```
Got it! I added:
    [T][] something
You have 2 tasks in your list :D
```
## Display all tasks
All stored tasks will be displayed in a numbered list indicating their status and details.

Command format: ```list```
## Adding tasks
There are 3 types of tasks you can add to the list.
1. Todo
2. Deadline
3. Event
### Todo tasks
Simple task that has no start or end date.

Command format: ```todo <name of task>```
### Deadline tasks
Tasks that have end dates, but not start dates.

Command format: ```deadline /by <date>```
> [!NOTE]
> Deadline is marked using the ```/by```
> 
> Date format is yyyy-mm-dd

### Event tasks
Tasks that have start dates and end dates.

Command format: ```event /from <date> /to <date>```
> [!NOTE]
> Event start date is marked using ```/from``` and end date is marked using ```/to```
> 
> Date format is yyyy-mm-dd

## Deleting tasks
Tasks can be deleted by referencing the index on the list from the ```list``` command.

Command format: ```delete <index>```

## Marking tasks as complete/incomplete
Tasks' completion status are indicated in the 2nd box shown in each task entry.

Completed task:
> [T][X] something

Incomplete task:
> [T][ ] something

To mark a task as complete, command format:
```mark <index>```

To mark a task as incomplete, command format: ```unmark <index>```

## Find tasks
You can find tasks containing keywords/characters from your list. All relevant tasks will be returned as a numbered list.

Command format: ```find <keyword/characters>```

## View statistics
Statistics of actions such as creation of task, deletion of tasks, and mark/unmark tasks will be displayed.

Command format: ```stats```

Sample reply:
```
Here are some statistics of your current session so far!
      Total created tasks: 1
      Total created todo tasks: 0
      Total created deadline tasks: 1
      Total created event tasks: 0
You also performed these actions:
      Marked complete 0 times
      Marked incomplete 0 times
      Deleted tasks 0 times
```