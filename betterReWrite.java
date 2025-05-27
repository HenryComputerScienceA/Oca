import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.awt.event.*;
import javax.swing.*;

public class betterReWrite {

  static HashMap<Character, Integer> alphabetMap = new HashMap<>();
  static {
    for (char c = 'a'; c <= 'z'; c++) {
      alphabetMap.put(c, c - 'a' + 1);
    }
  }

  //? keywords
  static HashMap<String, String> variableStorage = new HashMap<String, String>(); //? store variables in here
  static String[] variables = {"assign"};
  static int variableExpect = 3;

  //? control flow
  static String[] controlFlow = {"con"};
  static int controlExpect = 5; //? example: con var1 *symbol* var2 > *inner text* <
  static String[] allowedComparisons = {"same"/*=*/, "not"/*!=*/, "more"/*>*/, "less"/*<*/};

  //? talking with the console
  static String[] consoleCommunication = {"print"};
  static int consoleCommExpect = 2;

  //? math
  static String[] mathKeywords = {"add", "sub", "div", "mul"};
  static int mathExpect = 5; //? example: add (new variable name) -> (var1) (var2)
                             //? "->" is a spacer to make it easier to read

  //? logic holders
  static String[] logicHolders = {"op"};
  static int logicExpect = 2; //? example: op name<> *inner text* end
  static String[] methodCallers = {"call"};
  static int methodCallExpect = 2; //? example: call name<>
  static ArrayList<String> methodCallsStorage = new ArrayList<>();

  //? graphics
  static String[] graphicsKeywords = {"window", "square"};
  static int windowExpect = 4;
  static int squareExpect = 6;
  static graphics currentGraphics;

  //? input detection
  static String[] inputKeyword = {"input"};
  static int inputExpect = 2;
  static char currentPressedKey = ' ';


  //? looping
  static String[] loopKeywords = {"loop"};
  static int loopExpect = 4;


  static boolean currentlyInsideCon = false;
  static boolean conIsTrue = false;


  static String line = "";

  public static void main(String[] args) {

    System.out.println("alphabet: " + alphabetMap);

    //? clears the file
    try (PrintWriter pw = new PrintWriter("methodStorage.txt")) {

    } catch (IOException p) {
      p.printStackTrace();
    }
    

    try (BufferedReader reader = new BufferedReader(new FileReader("testingCode.txt"));) {


       //? read the file with code in it
      
      while ((line = reader.readLine()) != null) {
        //? split input into parts -> spaces
        String[] splitSpaces = line.split(" ");
        //ArrayList<String> splitPeriod = new ArrayList<>();

        //System.out.println("line: " + line);
        //System.out.println("splitSpaces: " + Arrays.toString(splitSpaces));

        for (String s : splitSpaces) {
          //System.out.println(s);
        
          /*if (s.contains(".")) {
            String[] again = s.split("\\.");
          
            for (String i : again) {
              splitPeriod.addAll(Arrays.asList(again)); // Add to ArrayList
              
            }
          }*/

          if (s.contains("#")) {
            // handle comments if you want
          }
        
        }

        String firstWord = (splitSpaces[0].equals(null)) ? "" : splitSpaces[0];

        if (!firstWord.equals(null) || !splitSpaces[0].equals("")) {

          for (String v : variables) {
            if (v.equals(firstWord)) {
              //System.out.println("firstWord is a variable");

              if (currentlyInsideCon && conIsTrue) {
                handleVariables(splitSpaces);
              } else {
                handleVariables(splitSpaces);
              }

            }
          }

          for (String c : consoleCommunication) {
            if (c.equals(firstWord)) {
              //System.out.println("firstWord wants to talk to the console");

              System.out.println("currentlyInsideCon: " + currentlyInsideCon);
              System.out.println("conIsTrue: " + conIsTrue);

              if (currentlyInsideCon && conIsTrue) {
                handleConsole(splitSpaces);
              }


            }
          }

          for (String m : mathKeywords) {
            if (m.equals(firstWord)) {
              //System.out.println("firstWord wants to do math");

              if (currentlyInsideCon && conIsTrue) {
                handleMath(splitSpaces);
              } else {
                handleMath(splitSpaces);
              }

            }
          }

          for (String i : controlFlow) {
            if (i.equals(firstWord)) {
              //System.out.println("firstWord wants to control the flow");
              currentlyInsideCon = true;
              conIsTrue = false;

              boolean stop = false;
              ArrayList<String[]> blockLines = new ArrayList<>();

              while (!stop && (line = reader.readLine()) != null) {

                if (line.trim().equals("<")) {
                  stop = true;
                } else {
                  String trimmedLine = line.trim(); //? get rid of whitespaces infront and after the line
                  if (!trimmedLine.isEmpty()) {
                    blockLines.add(trimmedLine.split(" "));
                  }
                }

              }

              System.out.println("splitSpaces[1]: " + splitSpaces[1]);

              if (splitSpaces[1].contains("*")) {
                //? run block only if condition is true
                if (handleConInputs(splitSpaces)) {
                  for (String[] blockLine : blockLines) {
                    conIsTrue = true;
                    getGiver(blockLine, reader);
                    currentlyInsideCon = false;
                  }
                } else {
                  conIsTrue = false;
                  System.out.println("handleConInputs() retrned false");
                  currentlyInsideCon = false;
                }
              } else {
                //? run block only if condition is true
                if (handleControlFlow(splitSpaces)) {
                  for (String[] blockLine : blockLines) {
                    conIsTrue = true;
                    getGiver(blockLine, reader);
                    currentlyInsideCon = false;
                  }
                } else {
                  conIsTrue = false;
                  System.out.println("handConInputs() returned false");
                  currentlyInsideCon = false;
                }
              }

            }
          }

          //? method detection
          for (String o : logicHolders) {
            if (o.equals(firstWord)) {
              //System.out.println("firstWord wants to hold logic");

              boolean stop = false;
              //ArrayList<String[]> blockLines = new ArrayList<>();

              String[] splitFirstLine = line.split(" ");

              if (splitFirstLine[1].contains("<>")) {
                handleOps(line);

                while (!stop && (line = reader.readLine()) != null) {
                
                  /*if (line.trim().equals("end")) {
                    stop = true;
                  } else {
                    String trimmedLine = line.trim();
                    if (!trimmedLine.isEmpty()) {
                      blockLines.add(trimmedLine.split(" "));
                    }
                  }*/
                
                  if (line.equals("end")) {
                    handleOps(line);
                    stop = true;
                  } else {
                    //System.out.println("ops line: " + line);
                    handleOps(line);
                  }
                
                }
              }

            }
          }

          for (String g : graphicsKeywords) {

            //System.out.println("making graphics: ");

            if (g.equals(firstWord)) {
              handleGraphics(splitSpaces);
            }

          }
          
          for (String mc : methodCallers) {

            String trimmedFirstWord = splitSpaces[0].trim();
            //System.out.println("firstWord: " + trimmedFirstWord);

            if (mc.equals(firstWord) || mc.equals(trimmedFirstWord)) {

              methodCallsStorage.add(splitSpaces[1]);
              //System.out.println("methodCallsStorage: " + methodCallsStorage);

            }
          }

          for (String i : inputKeyword) {
            if (i.equals(firstWord)) {
              //handleInputs(splitSpaces);
            }
          }

          for (String l : loopKeywords) {
            if (l.equals(firstWord)) {

              String[] loopHeader = splitSpaces;
              boolean stop = false;
              ArrayList<String[]> blockLines = new ArrayList<>();

              while (!stop && (line = reader.readLine()) != null) {

                if (line.equals("end")) {

                  stop = true;

                } else {

                  String trimmedLine = line.trim(); //? get rid of whitespaces infront and after the line
                  if (!trimmedLine.isEmpty()) {
                    blockLines.add(trimmedLine.split(" "));
                  }

                }

              }

              handleLooping(loopHeader, blockLines, reader);



            }
          }

        } else {
            System.out.println("line is either null or empty");
        }

      }
        
    } catch (IOException e) {
      e.printStackTrace();
    }

    ArrayList<String> methodCallsCopy = new ArrayList<>(methodCallsStorage);
    for (String rm : methodCallsCopy) {
      runMethods(rm);
    }
  }

  public static boolean handleConInputs(String[] line) {

    boolean result = false;

    String var1 = variableStorage.get(line[1]).trim();
    String var2 = line[3].trim();

    System.out.println("var1: " + var1);
    System.out.println("var2: " + var2);

    if (var1.equals(var2)) {
      result = true;
      conIsTrue = true;
      System.out.println("the inputs are the same: " + result);
    } else {
      result = false;
      conIsTrue = false;
      System.out.println("the inputs are not the same: " + result);
    }
    
    return result;

  }

  //? variables
  public static void handleVariables(String[] line) {

    //System.out.println("handleVariables line: " + Arrays.toString(line));

    if (line.length == variableExpect) {
      //System.out.println("valid variable declaration");

      if (line[2].equals("getInput<>")) {

        JFrame window = currentGraphics.getFrame();

        //? reset the current pressed key before waiting for input
        currentPressedKey = ' ';

        //? listen for key inputs
        window.addKeyListener(new KeyAdapter() {

          //? check for key press
          @Override
          public void keyPressed(KeyEvent e) {
            currentPressedKey = e.getKeyChar();
          }

          //? check for key release
          @Override
          public void keyReleased(KeyEvent e) {
            // Optionally reset currentPressedKey or handle key release
            currentPressedKey = ' ';
          }

          @Override
          public void keyTyped(KeyEvent e) {
            // Optionally handle key typed events
          }

        });

        //? make the window the active component
        window.requestFocus();

        //? wait until a key is pressed
        while (currentPressedKey == ' ') {
          try {
            Thread.sleep(50);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }

        //? check if its already a variable
        if (variableStorage.containsKey(line[1])) {
          //? if it is then update it
          variableStorage.replace(line[1], Character.toString(currentPressedKey));
          //System.out.println(variableStorage);
        } else {
          //? if not then create it
          variableStorage.put(line[1], Character.toString(currentPressedKey));
          //System.out.println(variableStorage);
        }

      } else {

        //? if its not getting inputs then create the variable normally
        variableStorage.put(line[1], line[2]);

      }

    } else {
      System.out.println("invalid variable declaration on line: " + Arrays.toString(line));
    }

  }

  //? printing to the console
  public static void handleConsole(String[] line) {

    if (line.length == consoleCommExpect) {

      //? if theres a variable called whatever line[1] is then set result to the value of it, if not then sey the error
      String result = (variableStorage.containsKey(line[1])) ? variableStorage.get(line[1]) : "variableStorage does not contain the requested key on line: " + Arrays.toString(line);

      //? check if theres a variable called whatever line[1] is
      if (variableStorage.containsKey(line[1])) {
        //? if so set result equal to the value of that key (whatever line[1] is)
        result = variableStorage.get(line[1]);
      } else if (line[1].contains("*")) {
        //? if line[1] contains a * then set result to nothing
        result = "";
      } else {
        //? else say the error
        result = "variableStorage does not contain the requested key on line: " + Arrays.toString(line);
      }

      //? print the result
      System.out.println(result);
      
    } else {
      System.out.println("invalid console communcation on line: " + Arrays.toString(line));
    }

  }

  //? math
  public static void handleMath(String[] line) { // example: add (new variable name) -> (var1) (var2)

    //? create a newVariableName String and set it equal to line[1]
    String newVariableName = line[1];

    //? create new String variables and set them equal to line[3] and line[4] respectively
    String sVar1 = line[3];
    String sVar2 = line[4];

    if (line.length == mathExpect) {

      //? check if the line contains a math keyword and "->"
      if (line[0].equals(mathKeywords[0]) && line[2].equals("->")) {

        //System.out.println("user wants to add");

        if (variableStorage.containsKey(sVar1) && variableStorage.containsKey(sVar2)) {

          int var1 = Integer.parseInt(variableStorage.get(sVar1));
          int var2 = Integer.parseInt(variableStorage.get(sVar2));

          variableStorage.put(newVariableName, String.valueOf(var1 + var2));
          //System.out.println("variableStorage after addition: " + variableStorage);

        }

      } else if (line[0].equals(mathKeywords[1]) && line[2].equals("->")) { // subtract

        //System.out.println("user wants to subtract");

        if (variableStorage.containsKey(sVar1) && variableStorage.containsKey(sVar2)) {

          int var1 = Integer.parseInt(variableStorage.get(sVar1));
          int var2 = Integer.parseInt(variableStorage.get(sVar2));

          variableStorage.put(newVariableName, String.valueOf(var1 - var2));
          //System.out.println("variableStorage after addition: " + variableStorage);

        }

      } else if (line[0].equals(mathKeywords[2]) && line[2].equals("->")) { // divide

        //System.out.println("user wants to divide");

        if (variableStorage.containsKey(sVar1) && variableStorage.containsKey(sVar2)) {

          int var1 = Integer.parseInt(variableStorage.get(sVar1));
          int var2 = Integer.parseInt(variableStorage.get(sVar2));

          variableStorage.put(newVariableName, String.valueOf(var1 / var2));
          //System.out.println("variableStorage after addition: " + variableStorage);

        }

      } else if (line[0].equals(mathKeywords[3]) && line[2].equals("->")) { // multiply

        //System.out.println("user wants to multiply");

        if (variableStorage.containsKey(sVar1) && variableStorage.containsKey(sVar2)) {

          int var1 = Integer.parseInt(variableStorage.get(sVar1));
          int var2 = Integer.parseInt(variableStorage.get(sVar2));

          variableStorage.put(newVariableName, String.valueOf(var1 * var2));
          //System.out.println("variableStorage after addition: " + variableStorage);

        }

      }

    } else {
      System.out.println("invalid math declaration on line: " + Arrays.toString(line));
    }

  }

  //? "con" statements
  public static boolean handleControlFlow(String[] line) {
    
    //System.out.println("each line for con: " + Arrays.toString(line));

    //? create the two String variables
    String var1 = null;
    String var2 = null;

    //System.out.println("v1: " + var1 + "v2: " + var2);

    //? this is what gets returned
    boolean isTrue = false;

    //? checks for a * which indicates ithas something to do with inputs
    if (line[1].equals("currentInput")) {
      System.out.println("line[1] equals 'currentInput'");

      //? send the line elsewhere to be handled
      handleConInputs(line);

    } else {
      //? if is not a input then set var1 and var2 to the value of the specified variable
      var1 = variableStorage.get(line[1]);
      var2 = variableStorage.get(line[3]);
    }

    if (var1 != null && var2 != null) { //? check if var1 and var2 exist
      try {
        //System.out.println("both variables are numbers");
        //? parse the strings to turn them into an int
        int v1I = Integer.parseInt(var1);
        int v2I = Integer.parseInt(var2);

        if (line[2].equals(allowedComparisons[0])) { //? same "="
          isTrue = (v1I == v2I) ? true : false;
        } else if (line[2].equals(allowedComparisons[1])) { //? not "!="
          isTrue = (v1I != v2I) ? true : false;
        } else if (line[2].equals(allowedComparisons[2])) { //? more ">"
          isTrue = (v1I > v2I) ? true : false;
        } else if (line[2].equals(allowedComparisons[3])) { //? less "<"
          isTrue = (v1I < v2I) ? true : false;
        }
      } catch (NumberFormatException e) {
        //? non numbers
        handleConInputs(line);
      }
      
    }

    return isTrue;
    
  }

  public static void handleOps(String line) {

    //System.out.println("handle ops here");

    File methodStorageFile = new File("methodStorage.txt");

    try (BufferedWriter bw = new BufferedWriter(new FileWriter(methodStorageFile, true))) { // create a file writer
      //System.out.println("trying to write to file: " + methodStorageFile);
      bw.write(line);
      bw.newLine();
      
    } catch (IOException w) {
      w.printStackTrace();
    }

  }

  public static void handleMethodCalling() {

    System.out.println("handle method calling");

    for (String cs : methodCallsStorage) {
      runMethods(cs);
    }

  }

  public static void handleGraphics(String[] line) {

    graphics g = new graphics();
    
    if (line[0].equals(graphicsKeywords[0]) && line.length == windowExpect) {

      //System.out.println("line: " + Arrays.toString(line));

      String name = line[1];

      

      int width = Integer.parseInt(variableStorage.get(line[2]));
      int height = Integer.parseInt(variableStorage.get(line[3]));

      g.createWindow(name, width, height);

      currentGraphics = g;

    } else if (line[0].equals(graphicsKeywords[1]) && line.length == squareExpect) {

      if (currentGraphics == null) {
        System.out.println("window doesn't exist. create a window first");
        return;
      }

      String name = line[1];
      int width = Integer.parseInt(variableStorage.get(line[2]));
      int height = Integer.parseInt(variableStorage.get(line[3]));
      int x = Integer.parseInt(variableStorage.get(line[4]));
      int y = Integer.parseInt(variableStorage.get(line[5]));

      currentGraphics.createSquare(name, width, height, x, y);

    }

  }

  public static void handleLooping(String[] loopHeader, ArrayList<String[]> blockLines, BufferedReader reader) {

    int iterationAmount = 1;

    System.out.println("looping line: " + line);

    if (loopHeader[1].equals("inf")) {

      System.out.println("while loop");

      while (true) {

        for (String[] blockLine : blockLines) {

          //System.out.println("inside loop line: " + Arrays.toString(blockLine));

          String firstWord = blockLine[0];
          
          if (firstWord.equals("end")) {
            break;
          } else {
            getGiver(blockLine, reader);
          }

        }
      
      }

    } else {

      System.out.println("for loop");

      iterationAmount = Integer.parseInt(loopHeader[1]);

      for (int i = 0; i < iterationAmount; i++) {
        
        for (String[] blockLine : blockLines) {
          getGiver(blockLine, reader);
        }

      }

    }

  }






  public static void runMethods(String method) {

    //System.out.println("running methods");

    String line = "";

    try (BufferedReader reader = new BufferedReader(new FileReader("methodStorage.txt"));) {

      while ((line = reader.readLine()) != null) {

        String lineTrim = line.trim();
        String[] lineSpace = lineTrim.split(" ");
        String firstWord = lineSpace[0];

        //System.out.println("method runner lineSpace: " + Arrays.toString(lineSpace));

        boolean isLoop = false;
        for (String l : loopKeywords) {
          if (l.equals(firstWord)) {
            isLoop = true;
            break;
          }
        }

        if (isLoop) {
          String[] loopHeader = lineSpace;
          ArrayList<String[]> blockLines = new ArrayList<>();
          while ((line = reader.readLine()) != null) { // read each line in the loop
              if (line.trim().equals("end")) break; // stop loop at the end
              if (!line.trim().isEmpty()) blockLines.add(line.trim().split(" ")); // add split lines to blockLines
          }
          handleLooping(loopHeader, blockLines, reader);
        } else {
          getGiver(lineSpace, reader);
        }

      }

    } catch  (IOException mr) {
      mr.printStackTrace();
    }

  }

  public static void getGiver(String[] splitSpaces, BufferedReader reader) {

    String firstWord = (splitSpaces[0] == null) ? "" : splitSpaces[0];

    for (String v : variables) {
            if (v.equals(firstWord)) {
              //System.out.println("firstWord is a variable");

              if (currentlyInsideCon && conIsTrue) {
                handleVariables(splitSpaces);
              } else {
                handleVariables(splitSpaces);
              }

            }
          }

          for (String c : consoleCommunication) {
            if (c.equals(firstWord)) {
              //System.out.println("firstWord wants to talk to the console");

              System.out.println("currentlyInsideCon: " + currentlyInsideCon);
              System.out.println("conIsTrue: " + conIsTrue);

              if (currentlyInsideCon && conIsTrue) {
                handleConsole(splitSpaces);
              }

            }
          }

          for (String m : mathKeywords) {
            if (m.equals(firstWord)) {
              //System.out.println("firstWord wants to do math");

              if (currentlyInsideCon && conIsTrue) {
                handleMath(splitSpaces);
              } else {
                handleMath(splitSpaces);
              }

            }
          }

          for (String i : controlFlow) {
            if (i.equals(firstWord) && reader != null) {
              //System.out.println("firstWord wants to control the flow");
              currentlyInsideCon = true;
              conIsTrue = false;

              boolean stop = false;
              ArrayList<String[]> blockLines = new ArrayList<>();

              try {
                while (!stop && (line = reader.readLine()) != null) {

                  if (line.trim().equals("<")) {
                    stop = true;
                  } else {

                    String trimmedLine = line.trim();
                    if (!trimmedLine.isEmpty()) {
                      blockLines.add(trimmedLine.split(" "));
                    }

                  }

                }

                //System.out.println("splitSpaces[1]: " + splitSpaces[1]);
                if (splitSpaces[1].contains("*")) {
                  //? run block only if condition is true
                  if (handleConInputs(splitSpaces)) {
                    for (String[] blockLine : blockLines) {
                      conIsTrue = true;
                      getGiver(blockLine, reader);
                      currentlyInsideCon = false;
                    }
                  } else {
                    conIsTrue = false;
                    System.out.println("handleConInputs() retrned false");
                    currentlyInsideCon = false;
                  }
                } else {
                  //? run block only if condition is true
                  if (handleControlFlow(splitSpaces)) {
                    for (String[] blockLine : blockLines) {
                      conIsTrue = true;
                      getGiver(blockLine, reader);
                      currentlyInsideCon = false;
                    }
                  } else {
                    conIsTrue = false;
                    currentlyInsideCon = false;
                    System.out.println("handConInputs() returned false");
                  }
                }

                

              } catch (IOException e) {
                e.printStackTrace();
              }
              

            }
          }

        for (String mc : methodCallers) {

          String trimmedFirstWord = splitSpaces[0].trim();
          //System.out.println("firstWord: " + trimmedFirstWord);

          if (mc.equals(firstWord) || mc.equals(trimmedFirstWord)) {

            methodCallsStorage.add(splitSpaces[1]);
            //System.out.println("methodCallsStorage: " + methodCallsStorage);

          }
        }
        
        for (String l : loopKeywords) {
          if (l.equals(firstWord) && reader != null) {
            String[] loopHeader = splitSpaces;
            boolean stop = false;
            ArrayList<String[]> blockLines = new ArrayList<>();

            try {
              while (!stop && (line = reader.readLine()) != null) {

                if (line.equals("end")) {

                  stop = true;

                } else {

                  String trimmedLine = line.trim(); // get rid of whitespaces infront and after the line
                  if (!trimmedLine.isEmpty()) {
                    blockLines.add(trimmedLine.split(" "));
                  }

                }

              }

              handleLooping(loopHeader, blockLines, reader);

            } catch (IOException e) {
              e.printStackTrace();
            }

          }
        }
    }

}
