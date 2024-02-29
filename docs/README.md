# ChillChief User Guide

> "Simplicity boils down to two steps: identify the essential, eliminate the rest."– Leo Babauta [(source)](https://dansilvestre.com/productivity-quotes/)

ChillChief is your go to assistant 'bro' for managing tasks in your life. It is
- Super **_Chill_**
- Super _Easy_
- Super _Useful_

All you need to do is,
1. Download it from [here](https://github.com/HusseinSafwan02/ip/releases/tag/A-Jar).
2. Double-click it.
3. Add your tasks.
4. Let it manage your tasks for you 😉

And it is **FREE**

## Features:
- [x] _Add_, _Delete_, and _Find_ your tasks
- [ ] Manage _Deadlines_
- [ ] Manage _Events_
- [ ] Manage  _Todo_'s

## Here is a table with a list of all commands
| Command | Syntax |
| ------  | ----------- |
| Todo    | `todo [TASK_DESCRIPTION]` |
| Deadline  | `deadline [TASK_DESCRIPTION] /by [YYYY-MM-DD HHmm]` |
| Event     | `event [TASK_DESCRIPTION] /from [YYYY-MM-DD HHmm] /to [YYYY-MM-DD HHmm]` |
| Mark     | `mark [TASK_NUMBER]` |
| Unmark     | `unmark [TASK_NUMBER]` |
| Delete     | `delete [TASK_NUMBER]` |
| List     | `list` |

> [!TIP]
> Type _help_ in ChillChief for a list of useful commands!


## Adding a Todo:
Add a Todo task to your task list.
Syntax: `todo [TASK_DESCRIPTION]`
Here is an example: 
- [ ] `todo buy groceries`
~~~
============================================================
 Got it, I have added this task:
    [T][ ] buy groceries
 Now you have 1 task in the list.
============================================================
~~~
> [!TIP]
> Instead of typing _todo_ you can simply type _t_.


## Adding a Deadline:
Add a Deadline task to your task list.
Syntax: `deadline [TASK_DESCRIPTION] /by [YYYY-MM-DD HHmm]`
Here is an example: 
- [ ] `deadline return book /by 2024-02-19 1800`
~~~
============================================================
 Got it, I have added this task:
    [D][ ] return book (by: Feb 19 2024 | 1800)
 Now you have 2 tasks in the list.
============================================================
~~~
> [!TIP]
> Instead of typing _deadline_ you can simply type _d_.


## Adding an Event:
Add a Deadline task to your task list.
Syntax: `event [TASK_DESCRIPTION] /from [YYYY-MM-DD HHmm] /to [YYYY-MM-DD HHmm]`
Here is an example: 
- [ ] `event meeting /from 2024-02-19 1800 /to 2024-02-19 1900`
~~~
============================================================
 Got it, I have added this task:
    [E][ ] meeting (start time: 19 Feb 2024 06:00 PM) (end time: 19 Feb 2024 07:00 PM)
 Now you have 3 tasks in the list.
============================================================
~~~
> [!TIP]
> Instead of typing _event_ you can simply type _e_.


## Marking Tasks:
Mark a task in your task list.
Syntax: `mark [TASK_NUMBER]`
Here is an example: 
- [ ] `mark 1`
~~~
____________________________________________________________
 Nice! I have marked this task as done:
   [T][X] buy groceries
____________________________________________________________
~~~
> [!WARNING]
> Ensure the task number you mark is valid!


## Unmarking Tasks:
Unmark a task in your task list.
Syntax: `mark [TASK_NUMBER]`
Here is an example: 
- [ ] `unmark 1`
~~~
____________________________________________________________
 OK, I have marked this task as not done yet:
   [T][ ] buy groceries
____________________________________________________________
~~~
> [!WARNING]
> Ensure the task number you mark is valid!


## Deleting Tasks:
Delete a task in your task list.
Syntax: `delete [TASK_NUMBER]`
Here is an example: 
- [ ] `delete 1`
~~~
============================================================
 Noted. I have removed this task:
   [T][ ] buy groceries
Now you have 2 tasks in the list.
============================================================
~~~
>[!TIP]
>Instead of typing _delete_ you can simply type _d_

>[!WARNING]
> Ensure the task number you mark is valid!


## Finding Tasks:
Find a task in your task list.
Syntax: `delete [TASK_NUMBER]`
Here is an example: 
- [ ] `find return`
~~~
____________________________________________________________
Here are the matching tasks in your list:
  1.[D][ ] return book (by: Feb 19 2024 | 1800)
____________________________________________________________
~~~
>[!TIP]
>Instead of typing _find_ you can simply type _f_
