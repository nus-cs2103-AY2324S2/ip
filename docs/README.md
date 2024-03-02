# Grizzly User Guide

![Kiku](Ui.png)

Hi, Welcome to the Grizzly user guide.

Grizzly is a chatbot that is capable of storing, managing and saving Tasks and Contacts.

Here is a list of all the features included with Grizzly

- [Adding Tasks](#adding-tasks)
    - [Todo](#todo)
    - [Deadline](#deadline)
    - [Event](#event)
- [Adding Contacts](#adding-contacts)
- [Listing items](#listing-items)
- [Marking/Unmarking Tasks](#markingunmarking-tasks)
- [Deleting items](#deleting-items)
- [Finding items](#finding-items)
- [Exiting](#deleting-items)

## Adding Tasks

Grizzly currently supports 3 different tasks types - Todo, Deadline and Event

### Todo
Basic Task with no other information.

Use the following formatting to add a Todo task:
```
todo [task name]
```
Note: exclude the square brackets
### Deadline
Task with a due date and time.

Use the following formatting to add a Todo task:
```
deadline [task name] /by [datetime]
```

Note: Insert your date and time with this formatting: `DD/MM/YYYY, HH:MM`
### Event
Task with a starting and ending date and time.

Use the following formatting to add a Todo task:
```
event [task name] /from [datetime] /to [datetime]
```
Note: Insert your date and time with this formatting: `DD/MM/YYYY, HH:MM`
## Adding Contacts
Each contact contains a name, email and phone number.

Use the following formatting to add a Contact:
```
contact [name] /email [email address] /number [phone number]
```
## Listing items
To list both tasks and contacts together, use:
```
list
```
To list just tasks, use:
```
list tasks
```
To list just contacts, use:
```
list contacts
```
## Marking/Unmarking Tasks

To mark a particular task, use:
```
mark [index of task]
```
Note: indexes of tasks can be found with the `list` command
## Deleting items

To delete a task, use:
```
delete task [index of task]
```
To delete a contact, use:
```
delete contact [index of contact]
```
Note: indexes of tasks and contactscan be found with the `list` command

## Finding items

To find an item, use:
```
find [description]
```
Note: Grizzly will return you list of tasks and contacts where the task description and contact name contains your description as a substring. E.g. if I have a contact with the name "James", using find with an input of "ames" will return that contact.

## Exiting
To exit Grizzly, use:
```
bye
```
This will save all the tasks and contacts that is currently stored within grizzly.


