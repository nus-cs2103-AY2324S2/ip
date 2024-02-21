# Eueu User Guide

![Ui.png](Ui.png)

Eueu is a ~~chatbot~~ lovebot that can keep track of all your tasks (TODOs, DEADLINEs, EVENTs) as well as some of your key contacts.

## Adding Todos

User types keyword ```todo```, followed by a task. Press Enter to add it to the tasklist. 

```todo TASK``` : Adds a Todo task ```TASK``` to the Tasklist.

Example:

**Input**

```
> todo ip
```
**Expected output**
```
Got it. I've added this task:
[E][] ip
Now you have 1 tasks in the list.
```

## Adding Deadlines

User types keyword ```deadline```, followed by a task, and its corresponding deadline which is separated from task by ```/```. Press Enter to add it to the tasklist.

```deadline DEADLINE/by DDL``` : Adds a Deadline task ```DEADLINE``` to the Tasklist with the deadline ```DDL```

Example:

**Input** 
```
> deadline ip/by tonight
```
**Expected output** 
```
Got it. I've added this task:
[D][] ip (by: tonight)
Now you have 1 tasks in the list.
```
## Adding Events

User types keyword ```event```, followed by a task, and its corresponding event timings which is separated from task by ```/```. Press Enter to add it to the tasklist.

```event EVENT/from START/to END``` : Adds a Event task ```EVENT``` to the Tasklist with the timings ```START``` to ```END```.

Example:

**Input** 
```
> event ip/from mon/to wed
```
**Expected output** 
```
Got it. I've added this task:
[E][] ip (from: mon to: wed)
Now you have 1 tasks in the list.
```

## Other features

1. Listing all tasks: ```list```
     ```
     Here are your tasks:
       1. [T][] ip
     ```
2. Mark task as done: ```mark 1```
     ``` 
     Nice! I've marked this task as done:
     [T][X] ip
     ```
3. Unmark task as not done: ```unmark 1```
     ```
     Ok, I've marked this task as not done yet:
     [T][] ip
     ```
4. Find task: ```find ip```
     ```
     Here are the matching tasks in your list:
      1. [T][] ip
     ```
5. Delete task: ```delete 1```
     ```
     Noted. I've removed this task:
     [T][] ip
     Now you have 0 tasks in the current list.
     ```
6. Clear tasklist: ```clear list```
     ```
     YAY BB! your list is cleared! :)
     ```
     
## Contacts

User types keyword ```cont```, followed by a Contact command to add, delete, group, find group, and list.

1. Add contact: ```cont -a NAME/NUMBER/[NOTES]``` where ```NOTES``` is an optional field to fill in.
2. Delete contact: ```cont -d NAME```
3. Group contacts together: ```cont group GROUP_NAME/CONTACT_INDEX_1/CONTACT_INDEX_2.../CONTACT_INDEX_N``` where `CONTACT_INDEX``` refers to the index of the contact in the contact list
4. Find group: ```cont find group GROUP_NAME```
5. List all contacts: ```cont ls```
   
