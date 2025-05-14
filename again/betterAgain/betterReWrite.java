package again.betterAgain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class betterReWrite {
  
  // keywords
  static HashMap<String, String> variableStorage = new HashMap<String, String>();
  static String[] variables = {"assign"};
  static int variableExpect = 3;

  // control flow
  static String[] controlFlow = {"if"};

  // talking with the console
  static String[] consoleCommunication = {"print"};
  static int consoleCommExpect = 2;

  // math
  static String[] mathKeywords = {"add", "sub", "div", "mul"};
  static int mathExpect = 5; // example: add (new variable name) -> (var1) (var2)
                             // "->" is a spacer to make it easier to read


  public static void main(String[] args) {
    
    String line = "";

    try {

      BufferedReader reader = new BufferedReader(new FileReader("again/code.txt"));
      
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
              System.out.println("first word wants to do math");

              handleMath(splitSpaces);

            }
          }

        } else {
            System.out.println("line is either null or empty");
        }

      }
        
    } catch (IOException e) {
      e.printStackTrace();
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
          System.out.println("variableStorage after addition: " + variableStorage);

        }

      } else if (line[0].equals(mathKeywords[1])) { // subtract

        //System.out.println("user wants to subtract");

        if (variableStorage.containsKey(sVar1) && variableStorage.containsKey(sVar2)) {

          int var1 = Integer.parseInt(variableStorage.get(sVar1));
          int var2 = Integer.parseInt(variableStorage.get(sVar2));

          variableStorage.put(newVariableName, String.valueOf(var1 - var2));
          System.out.println("variableStorage after addition: " + variableStorage);

        }

      } else if (line[0].equals(mathKeywords[2])) { // divide

        //System.out.println("user wants to divide");

        if (variableStorage.containsKey(sVar1) && variableStorage.containsKey(sVar2)) {

          int var1 = Integer.parseInt(variableStorage.get(sVar1));
          int var2 = Integer.parseInt(variableStorage.get(sVar2));

          variableStorage.put(newVariableName, String.valueOf(var1 / var2));
          System.out.println("variableStorage after addition: " + variableStorage);

        }

      } else if (line[0].equals(mathKeywords[3])) { // multiply

        //System.out.println("user wants to multiply");

        if (variableStorage.containsKey(sVar1) && variableStorage.containsKey(sVar2)) {

          int var1 = Integer.parseInt(variableStorage.get(sVar1));
          int var2 = Integer.parseInt(variableStorage.get(sVar2));

          variableStorage.put(newVariableName, String.valueOf(var1 * var2));
          System.out.println("variableStorage after addition: " + variableStorage);

        }

      }

    } else {
      System.out.println("invalid math declaration on line: " + Arrays.toString(line));
    }

  }

}
