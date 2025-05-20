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


### Variables
```
static HashMap<String, String> variableStorage = new HashMap<String, String>();
static String[] variables = {"assign"};
static int variableExpect = 3;
```
Every variable is stored in a HashMap. Regardless of whether the value is a string or number, both the name and value are stored as strings inside the HashMap.

```

```

### Printing to the console
```

```

### Math
```

```

### Control Flow
```

```

### Calling and Creating Methods
```

```

### Graphics: Creating Windows
```

```

### Graphics: Create Squares
```

```
