package again.runnerReWrite;

import java.util.HashMap;
import java.util.Arrays;
import java.util.ArrayList;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class reWrite {

  // store parts in HashMaps
  static HashMap<String, String> variables = new HashMap<String, String>();

  public static void main(String[] args) {
    
    String line = "";
    boolean handlingConStatements = false;

    String firstVar = "";
    String symbol = "";
    String secondVar = "";
    String starter = "";

    try {

      BufferedReader reader = new BufferedReader(new FileReader("again/code.txt"));
      
      while ((line = reader.readLine()) != null) {
        // split input into parts -> spaces, periods
        String[] splitSpaces = line.split(" ");
        ArrayList<String> splitPeriod = new ArrayList<>();

        for (String s : splitSpaces) {
          //System.out.println(s);
        
          if (s.contains(".")) {
            String[] again = s.split("\\.");
          
            for (String i : again) {
              splitPeriod.addAll(Arrays.asList(again)); // Add to ArrayList
            }
          }
        
        }

        //System.out.println("spaces: " + Arrays.toString(splitSpaces) + " periods: " + splitPeriod);

        if (splitSpaces.length < 2) {
          
          System.err.println("invalid input or empty line: " + line);
          continue;

        }

        String command = splitSpaces[0];
        String name = splitSpaces[1];
        String value = splitSpaces[1];

        // gives OutOfBoundsException if there isn't 3 things in splitSpaces
        if (splitSpaces.length > 2) {
          value = splitSpaces[2];
        } else {
          value = splitSpaces[1];
        }

      
        // process parts
        if (command.equals("let")) {
          handleVariables(name, value);
        } else if (command.equals("print")) {
          handlePrinting(name);
        } else if (command.equals("con")) { // con *var1* *symbol* *var2* -> *whatever*

          firstVar = splitSpaces[1];
          symbol = splitSpaces[2];
          secondVar = splitSpaces[3];
          starter = splitSpaces[4];

          handlingConStatements = true;

          while (handlingConStatements && (line = reader.readLine()) != null) {

            if (line.equals("<")) {
              handlingConStatements = false;
              System.out.println("ended con statement");
            } else {
              handleCon(firstVar, symbol, secondVar, starter, line);
            } 

          }

        }

      }
        
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void handleVariables(String name, String value) {
    
    variables.put(name, value);

    //System.out.println("variables: " + variables);

  }

  public static void handlePrinting(String output) {

    if (variables.containsKey(output)) {
      System.out.println(variables.get(output));
    } else {
      System.out.println(output);
    }

  }

  public static void handleCon(String var1, String symbol, String var2, String starter, String currentLine) {

    System.out.println("current line: " + currentLine);

    if (variables.containsKey(var1) && variables.containsKey(var2)) {
      String input1 = variables.get(var1);
      String input2 = variables.get(var2);
      
      boolean conMet = false;

      try {

        int input1Num = Integer.parseInt(input1);
        int input2Num = Integer.parseInt(input2);

        if (symbol.equals("=")) {
          conMet = input1Num == input2Num;
        }

        if (conMet) {
          System.out.println(conMet);

          String[] lineParts = currentLine.split(" ");

          String command = lineParts[0];
          String key = lineParts[1];
          String value = null;
          
          if (lineParts.length > 2) {
            value = lineParts[2];
          } else {
            value = null;
          }
        
          getGiver(command, key, value);

        } else {
          System.out.println(conMet);
        }

      } catch (NumberFormatException e) {
        e.printStackTrace();
      }

    }

  }

  public static void getGiver(String command, String key, String value) {

    if (command.equals("out")) {
      handlePrinting(key);
    } else if (command.equals("let")) {
      handleVariables(key, value);
    }

  }

}
