```
    //    //  //  //==\\  //      //  //||    //||   //
   //____//  //  //  //  //      //  // ||   // ||  //
  //----//  //  //=\\   //  //  //  //==||  //  || //
 //    //  //  //   \\ //==//==//  //   || //   ||//
```
 
 #HIRWAN

 ##Commands

```
list
```
List command returns the entire list of tasks.

```
todo [description]
```
Todo command adds a todo task with a description.

```
deadline [description] /by [date in format dd/mm/yyyy HHmm]
```
Deadline command specifies a deadline task with the given description and by the given date. Date has to be entered in the format shown above.

```
event [description] /from  [date in format dd/mm/yyyy HHmm] /to  [date in format dd/mm/yyyy HHmm]
```
Event command specifies an event task with the given description and which occurs from the given dates. Date has to entered in the formnat shown above.

```
period [description] /between  [date in format dd/mm/yyyy HHmm] /and  [date in format dd/mm/yyyy HHmm]
```
Period command specifies a period task with the given description and which takes place over course of the given dates. Date has to be entered in the format shown above.

```
mark [index of task to mark]
```
Mark command marks the specified task at the index given in the list.

```
unmark [index of task to unmark]
```
Unmark command unmarks the specified task at the index given in the list.

```
delete [index of task to delete]
```
Delete command removes the task specified at the given index provided from the list.

```
find [search text]
```
Find command searches for the task that contains the substring supplied.

```
bye
```
Bye command closes the chatbot.
