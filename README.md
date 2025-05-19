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


