import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class betterReWrite {
  
  // keywords
  static HashMap<String, String> variableStorage = new HashMap<String, String>(); // store variables in here
  static String[] variables = {"assign"};
  static int variableExpect = 3;

  // control flow
  static String[] controlFlow = {"con"};
  static int controlExpect = 5; // example: con var1 *symbol* var2 > *inner text* <
  static String[] allowedComparisons = {"same"/*=*/, "not"/*!=*/, "more"/*>*/, "less"/*<*/};

  // talking with the console
  static String[] consoleCommunication = {"print"};
  static int consoleCommExpect = 2;

  // math
  static String[] mathKeywords = {"add", "sub", "div", "mul"};
  static int mathExpect = 5; // example: add (new variable name) -> (var1) (var2)
                             // "->" is a spacer to make it easier to read

  // logic holders
  static String[] logicHolders = {"op"};
  static int logicExpect = 2; // example: op name<> *inner text* end
  static String[] methodCallers = {"call"};
  static int methodCallExpect = 2; // example: call name<>
  static ArrayList<String> methodCallsStorage = new ArrayList<>();

  // graphics
  static String[] graphicsKeywords = {"window", "square"};
  static int windowExpect = 4;
  static int squareExpect = 6;
  static graphics currentGraphics;

  // input detection
  static String[] inputKeyword = {"input"};
  static int inputExpect = 2;

  // looping
  static String[] loopKeywords = {"loop"};
  static int loopExpect = 4;


  public static void main(String[] args) {

    /* clears the file */
    try (PrintWriter pw = new PrintWriter("methodStorage.txt")) {

    } catch (IOException p) {
      p.printStackTrace();
    }
    
    String line = "";

    try (BufferedReader reader = new BufferedReader(new FileReader("testingCode.txt"));) {

       // read the file with code in it
      
      while ((line = reader.readLine()) != null) {
        // split input into parts -> spaces, periods
        String[] splitSpaces = line.split(" ");
        ArrayList<String> splitPeriod = new ArrayList<>();

        //System.out.println("line: " + line);
        //System.out.println("splitSpaces: " + Arrays.toString(splitSpaces));

        for (String s : splitSpaces) {
          //System.out.println(s);
        
          if (s.contains(".")) {
            String[] again = s.split("\\.");
          
            for (String i : again) {
              splitPeriod.addAll(Arrays.asList(again)); // Add to ArrayList
              
            }
          }

          if (s.contains("#")) {
            // handle comments if you want
          }
        
        }

        String firstWord = (splitSpaces[0].equals(null)) ? "" : splitSpaces[0];

        if (!firstWord.equals(null) || !splitSpaces[0].equals("")) {

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

          for (String i : controlFlow) {
            if (i.equals(firstWord)) {
              //System.out.println("firstWord wants to control the flow");

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

          // method detection
          for (String o : logicHolders) {
            if (o.equals(firstWord)) {
              //System.out.println("firstWord wants to hold logic");

              boolean stop = false;
              ArrayList<String[]> blockLines = new ArrayList<>();

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

  public static void handleVariables(String[] line) {

    //System.out.println("handleVariables line: " + Arrays.toString(line));

    if (line.length == variableExpect) {
      //System.out.println("valid variable declaration");

      variableStorage.put(line[1], line[2]);

    } else {
      System.out.println("invalid variable declaration on line: " + Arrays.toString(line));
    }

  }

  public static void handleConsole(String[] line) {

    if (line.length == consoleCommExpect) {

      String result = (variableStorage.containsKey(line[1])) ? variableStorage.get(line[1]) : "variableStorage does not contain the requested key on line: " + Arrays.toString(line);
      System.out.println(result);

    } else {
      System.out.println("invalid console communcation on line: " + Arrays.toString(line));
    }

  }

  public static void handleMath(String[] line) { // example: add (new variable name) -> (var1) (var2)

    String newVariableName = line[1];

    String sVar1 = line[3];
    String sVar2 = line[4];

    if (line.length == mathExpect) {

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

  public static boolean handleControlFlow(String[] line) {
    
    //System.out.println("each line for con: " + Arrays.toString(line));

    String var1 = variableStorage.get(line[1]);
    String var2 = variableStorage.get(line[3]);

    //System.out.println("v1: " + var1 + "v2: " + var2);

    boolean isTrue = false;

    if (var1 != null && var2 != null) { // check if var1 and var2 exist
      try {
        //System.out.println("both variables are numbers");
        int v1I = Integer.parseInt(var1);
        int v2I = Integer.parseInt(var2);

        if (line[2].equals(allowedComparisons[0])) { // same "="
          isTrue = (v1I == v2I) ? true : false;
        } else if (line[2].equals(allowedComparisons[1])) { // not "!="
          isTrue = (v1I != v2I) ? true : false;
        } else if (line[2].equals(allowedComparisons[2])) { // more ">"
          isTrue = (v1I > v2I) ? true : false;
        } else if (line[2].equals(allowedComparisons[3])) { // less "<"
          isTrue = (v1I < v2I) ? true : false;
        }
      } catch (NumberFormatException e) {
        // skip if not a numbers
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
      int width = Integer.parseInt(line[2]);
      int height = Integer.parseInt(line[3]);
      int x = Integer.parseInt(variableStorage.get(line[4]));
      int y = Integer.parseInt(variableStorage.get(line[5]));

      currentGraphics.createSquare(name, width, height, x, y);

    }

  }

  public static void runMethods(String method) {

    //System.out.println("running methods");

    String line = "";

    try (BufferedReader reader = new BufferedReader(new FileReader("methodStorage.txt"));) {

      while ((line = reader.readLine()) != null) {

        String lineTrim = line.trim();
        String[] lineSpace = lineTrim.split(" ");

        //System.out.println("method runner lineSpace: " + Arrays.toString(lineSpace));

        getGiver(lineSpace);

      }

    } catch  (IOException mr) {
      mr.printStackTrace();
    }

  }

  public static void getGiver(String[] splitSpaces) {

    String firstWord = (splitSpaces[0].equals(null)) ? "" : splitSpaces[0];

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

          /* no support for nested if statements for now */

          /*for (String i : controlFlow) {
            if (i.equals(firstWord)) {
              //System.out.println("firstWord wants to control the flow");

              boolean stop = false;

              while (!stop && (line = reader.readLine()) != null) {

                if (line.equals("<")) {
                  stop = true;
                } else {
                  handleControlFlow(splitSpaces);
                }

              }

            }
          }*/

    for (String mc : methodCallers) {

            String trimmedFirstWord = splitSpaces[0].trim();
            //System.out.println("firstWord: " + trimmedFirstWord);

            if (mc.equals(firstWord) || mc.equals(trimmedFirstWord)) {

              methodCallsStorage.add(splitSpaces[1]);
              //System.out.println("methodCallsStorage: " + methodCallsStorage);

            }
          }
          
          

  }

}
