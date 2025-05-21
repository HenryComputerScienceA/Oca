# Technical Details

Disclaimer: some details may be out of date

## Current Restrictions

- Variables can only be an integer or a string
- You have to make it a variable in order to print something
- You can only use integers when using a math keyword
- No support for nested ```con``` statements
- Only 1 condition can be checked in a ```con``` statement
- ```con``` statements and ```op``` methods have to be multi-line

## Custom Syntax

### Variables
```
assign x 100
assign exampleString example
```
To create variables you use the ```assign``` keyword. The keyword for creating any data type is always ```assign```.

**Overview:** assign > variable name > value

### Printing to the console
```
print x
print exampleString
```
To print to the console use the ```print``` keyword. As long as you create the variable before the print statement, you can use it to print any data type.

**Overview:** print > variable

### Math
```
add a -> x y
sub s -> x y
div d -> x y
mul m -> x y
```
To do math use any of these keywords: ```add``` to add, ```sub``` to subtract, ```div``` to divide, ```mul``` to multiply. When you do math you create a new variable that has the name you specify and the value after adding, subtracting, dividing, or multiplying the 2 variables.

**Overview:** math keyword > new variable > '->' symbol (makes it more readable) > first variable > second variable

### Control Flow
```
con x same y >
  print x
<
con x not y >
  print y
<
con x more y >
  print x
<
con x less y >
  print y
<
```
To execute code when a condition is met use the ```con``` keyword. Then you can compare 2 choosen integers using any of these keywords: ```same``` to check if variable one and variable two equal eachother, ```not``` to check variable one doesn't equals variable two, ```more``` to check if variable one is more than variable two, ```less``` to check if variable one is less than variable two.

**Overview:** con > first variable > compare condition > second variable > '>' symbol > inner code > '<' symbol to signify the end of the statement

### Calling and Creating Methods
```
call exampleMethod<>

op exampleMethod<>
  print exampleString
end
```
To call a method use the ```call``` keyword. You can call a method whenever in the code.

**Overview:** call > method name + "<>"

To create a method use the ```op``` keyword. when creating a method, add "<>" anywhere in the method name. Placing "<>" at the end helps with readablility. To close a method use ```end```.

**Overview:** op > method name + "<>" > inner code > end

### Graphics: Creating Windows
```
window exampleWindow 600 400
```
To create a window use the ```window``` keyword.

**Overview:*** window > window name > width > height

### Graphics: Create Squares
```
square exampleSquare 50 50 200 200
```
To create a square use the ```square``` keyword.

**Overview:** square > square name > width > height > x position > y position

## The Code

### Non-Specific Details
- Every keyword is stored in a String[] array.
- Every keyword has an int value that stores the number of words the code expects when you use the keyword
- Most methods take in a ```String[]``` array and calls it **line**

___

```
String line = "";

<a name="my-custom-anchor-point"></a>
try (BufferedReader reader = new BufferedReader(new FileReader("code.txt"));)
```

The [BufferedReader](https://www.geeksforgeeks.org/java-io-bufferedreader-class-java/) object, reader, reads the text given by [FileReader](https://www.geeksforgeeks.org/java-io-filereader-class/). Using a BufferedReader to read the file is efficient as it buffers the characters.

```
while ((line = reader.readLine()) != null) {
  String[] splitSpaces = line.split(" ");
  ArrayList<String> splitPeriod = new ArrayList<>();

  for (String s : splitSpaces) {
        
    if (s.contains(".")) {
      String[] again = s.split("\\.");
          
      for (String i : again) {
        splitPeriod.addAll(Arrays.asList(again));
              
      }
    }

    if (s.contains("#")) {
      // handle comments if you want
    }
        
}
```

1. The ```while``` loop reads each line from **reader**
2. It creates a ```String[]``` array called **splitSpaces** that is filled with the parts the **line** string thats split wherever a space is
3. Then it creates an ```ArrayList<String>``` called **splitPeriod** that will be used later
4. After, uses an advanced ```for``` loop to go through **splitSpaces** and check if one of the strings contains a period
5. If it does, then create a ```String[]``` array called **again** that splits up the words wherever a period is (the "\\" is necessary because "." is a special character
6. Then go through the **again** array and add it to the **splitPeriod** ArrayList

```
String firstWord = (splitSpaces[0].equals(null)) ? "" : splitSpaces[0];
```

Creates a ```String``` called **firstWord** with a value of either empty or the first part in the **splitSpaces** array\
*This uses a [ternary operator](https://www.geeksforgeeks.org/java-ternary-operator/)*

```
if (!firstWord.equals(null) || !splitSpaces[0].equals(""))
```

If **firstWord** isn't null or the first part in **splitSpaces** isn't empty, continue.

```
for (String v : variables) {
  if (v.equals(firstWord)) {
  //System.out.println("firstWord is a variable");

  handleVariables(splitSpaces);

  }
}

for (String c : consoleCommunication) {
  if (c.equals(firstWord)) {
  //System.out.println("firstWord wants to talk to the console");

  handleConsole(splitSpaces);

  }
}

for (String m : mathKeywords) {
  if (m.equals(firstWord)) {
  //System.out.println("firstWord wants to do math");

  handleMath(splitSpaces);

  }
}
```
```
for (String g : graphicsKeywords) {
  //System.out.println("making graphics: ");

  if (g.equals(firstWord)) {
    handleGraphics(splitSpaces);
  }

}
```

1. The program goes through each keyword category ```String[]``` array
2. Then it check to see if **firstWord** equals the current item its looking at in the array
3. If it is, it then calls the method associated with the keyword and passes on the **splitSpaces** array to be broken apart and processed independently\
*Keep reading for the code on how it deals with finding the rest of the keywords*

### Variables
```
static HashMap<String, String> variableStorage = new HashMap<String, String>();
```
Every variable is stored in a [HashMap](https://www.geeksforgeeks.org/java-util-hashmap-in-java-with-examples/). Regardless of whether the value is a string or number, both the name and value are stored as strings inside the [HashMap](https://www.geeksforgeeks.org/java-util-hashmap-in-java-with-examples/).
___

```
public static void handleVariables(String[] line) {

  //System.out.println("handleVariables line: " + Arrays.toString(line));

  if (line.length == variableExpect) {
    //System.out.println("valid variable declaration");

    variableStorage.put(line[1], line[2]);

  } else {
    System.out.println("invalid variable declaration on line: " + Arrays.toString(line));
  }

}
```

1. If the length of **line** is the same as what **variableExpect** specifies, add to the [HashMap](https://www.geeksforgeeks.org/java-util-hashmap-in-java-with-examples/) **variableStorage** **line[1]** as the key, and **line[2]** as the value
2. If the length of **line** is not the same as what **variableExpect** specifies, then print the error

### Printing to the console
```
public static void handleConsole(String[] line) {

  if (line.length == consoleCommExpect) {

    String result = (variableStorage.containsKey(line[1])) ? variableStorage.get(line[1]) : "variableStorage does not contain the requested key on line: " + Arrays.toString(line);
    System.out.println(result);

  } else {
    System.out.println("invalid console communcation on line: " + Arrays.toString(line));
  }

}
```

1. Check if the length of **line** is the same as what **consoleCommExpect** specifies
2. If it does then create a ```String``` called **result** which uses a [ternary operator](https://www.geeksforgeeks.org/java-ternary-operator/) to check if **variableStorage** contains a key with the same name as the value of **line[1]**
3. If it does then it sets **result** to the value of that key
4. If it doesn't then it sets **result** to an error message
5. Then it prints the error


### Math
```
public static void handleMath(String[] line) {

  String newVariableName = line[1];

  String sVar1 = line[3];
  String sVar2 = line[4];

  if (line.length == mathExpect) {

    if (line[0].equals(mathKeywords[0]) && line[2].equals("->")) {

      if (variableStorage.containsKey(sVar1) && variableStorage.containsKey(sVar2)) {

        int var1 = Integer.parseInt(variableStorage.get(sVar1));
        int var2 = Integer.parseInt(variableStorage.get(sVar2));

        variableStorage.put(newVariableName, String.valueOf(var1 + var2));
      }

    } else if (line[0].equals(mathKeywords[1]) && line[2].equals("->")) { // subtract


      if (variableStorage.containsKey(sVar1) && variableStorage.containsKey(sVar2)) {

        int var1 = Integer.parseInt(variableStorage.get(sVar1));
        int var2 = Integer.parseInt(variableStorage.get(sVar2));

        variableStorage.put(newVariableName, String.valueOf(var1 - var2));

      }

    } else if (line[0].equals(mathKeywords[2]) && line[2].equals("->")) { // divide


      if (variableStorage.containsKey(sVar1) && variableStorage.containsKey(sVar2)) {

        int var1 = Integer.parseInt(variableStorage.get(sVar1));
        int var2 = Integer.parseInt(variableStorage.get(sVar2));

        variableStorage.put(newVariableName, String.valueOf(var1 / var2));

      }

    } else if (line[0].equals(mathKeywords[3]) && line[2].equals("->")) { // multiply


      if (variableStorage.containsKey(sVar1) && variableStorage.containsKey(sVar2)) {

        int var1 = Integer.parseInt(variableStorage.get(sVar1));
        int var2 = Integer.parseInt(variableStorage.get(sVar2));

        variableStorage.put(newVariableName, String.valueOf(var1 * var2));

      }

    }

  } else {
    System.out.println("invalid math declaration on line: " + Arrays.toString(line));
  }

}
```

1. It starts by creating 3 new ```String``` variables, **newVariableName**, ***sVar1**, and **sVar2**
2. It set them equal to **line[1]**, **line[3]**, and **line[4]** respectively
*The following will apply for all math keywords (e.g., add, sub)*
3. Then it checks if the first word in the line (**line[0]**), is a math keyword and what keyword it is
4. Then it checks if **variableStorage** contains keys with the same name as the value of **sVar1** and **sVar2**
5. If it does it parses both **sVar1** and **sVar2** from strings to ints **var1** and **var2** respectively
6. It then adds to **variableStorage** the key of **newVariableName** with the value of the result of the specified operation between **var1** and **var2**


### Control Flow
```
for (String i : controlFlow) {
  if (i.equals(firstWord)) {

    boolean stop = false;
    ArrayList<String[]> blockLines = new ArrayList<>();

    while (!stop && (line = reader.readLine()) != null) {

      if (line.trim().equals("<")) {
        stop = true;
      } else {
        String trimmedLine = line.trim(); // get rid of whitespaces infront and after the line
        if (!trimmedLine.isEmpty()) {
          blockLines.add(trimmedLine.split(" "));
        }
      }

    }

    // run block only if condition is true
    if (handleControlFlow(splitSpaces)) {
      for (String[] blockLine : blockLines) {
        getGiver(blockLine);
      }
    }

  }
}
```

1. It first checks if **firstWord** equals the current item in the array
2. It then creates a ```boolean``` called **stop** and sets it to false, it also creates a ```ArrayList``` of string arrays and calls it **blockLines**
3. Then it runs a ```while``` loop that keeps looping as long as **stop** equals false and the current line its reading is not null
4. Inside the ```while``` loop it has an ```if``` statement whose condition is set to true if after it removes the whitespace in front of and at the end of the line, the text leftover is "<"
5. If the condition is true then it sets **stop** to true, ending the ```while``` loop
6. If the condition is false then it creates a string called **trimmedLine*** and sets it the line after trimming off the leading and trailing whitespaces *This is to allow for spaces or tabs in front of the inner lines*
7. Then it checks if **trimmedLine** is empty
8. If its not it splits up **trimmedLine** at the spaces and adds it to **blockLines**


### Calling and Creating Methods
```
for (String o : logicHolders) {
  if (o.equals(firstWord)) {

    boolean stop = false;
    ArrayList<String[]> blockLines = new ArrayList<>();

    String[] splitFirstLine = line.split(" ");

    if (splitFirstLine[1].contains("<>")) {
      handleOps(line);

      while (!stop && (line = reader.readLine()) != null) {
                
      if (line.equals("end")) {
        handleOps(line);
        stop = true;
      } else {
        handleOps(line);
      }
                
     }
    }

  }
}
```

1. First it checks if **firstWord** is equal to the current item in the array
2. It then creates a ```boolean``` called **stop** and sets it to false, it also creates a ```ArrayList``` of string arrays and calls it **blockLines**


### Graphics: Creating Windows
```

```

### Graphics: Create Squares
```

```
