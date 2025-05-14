package again;

import java.util.Arrays;
import java.util.HashMap;
import java.io.IOException;

import java.io.FileReader;
import java.io.BufferedReader;

public class runner {

  public static void main(String[] args) {
    String codeLine = "";

    String result = "";

    HashMap<String, String> lets = new HashMap<>();

    try {
      BufferedReader reader = new BufferedReader(new FileReader("again/code.txt"));

      boolean handleIf = false;

      while ((codeLine = reader.readLine()) != null) {
        //System.out.println("codeLine: " + codeLine);

        String[] splitLine = codeLine.split(" "); // split the current line up at each space, then add it to an array

        if (splitLine[0].equals("let")) { // declaring variables
          lets.put(splitLine[1], splitLine[2]);

        } else if (splitLine[0].equals("out")) { // printing

          if (lets.containsKey(splitLine[1])) { // if lets[] contains splitLine[1], print splitLine[1]
            result = lets.get(splitLine[1]);
            System.out.println(result);

          } else {
            System.out.println(splitLine[1]);
          }
          
        } else if (splitLine[0].equals("add")) { // adding numbers and variables
          String sX = splitLine[1];
          String sY = splitLine[2];

          if (lets.containsKey(sX) || lets.containsKey(sY)) { // check if lets[] contains sX
            int x = Integer.parseInt(lets.get(sX));
            int y = Integer.parseInt(lets.get(sY));

            System.out.println(addition(x, y));
            
          } else {
            int x = Integer.parseInt(sX);
            int y = Integer.parseInt(sY);

            System.out.println(addition(x, y));

          }

        } else if (splitLine[0].equals("con")) { // handle 'if' statements
          // example
          // con x = y >
          //  out x
          // <

          String var1 = splitLine[1];
          String symbol = splitLine[2];
          String var2 = splitLine[3];

          boolean conMet = evaluateCondition(var1, symbol, var2, lets);

          //System.out.println("conMet: " + conMet);

          if (conMet) {
            handleIf = true;
            //System.out.println("handleIf: " + handleIf);
          }

        }

        if (handleIf) {
          //System.out.println("1 handling if statements"); TEST

          // loop through the codeLines
          while ((codeLine = reader.readLine()) != null && !codeLine.equals("<")) {
            // split inner codeLines
            String[] innerLine = codeLine.trim().split(" ");
            

            //System.out.println("2 inside while loop"); TEST

            //System.out.println("innerLine: " + Arrays.toString(innerLine));

            if (innerLine.length == 0 || innerLine[0].isEmpty()) {
              System.out.println("empty line found");
              continue;
            }
            // execute inner codeLines
            if (innerLine[0].equals("out")) { // check if the user wants to print something

              //System.out.println("3 inside first if statement"); TEST

              if (lets.containsKey(innerLine[1])) { // check if lets contains innerLine[1]

                //System.out.println("4 inside second if statement"); TEST

                //Syssem.out.println("if out works");
                System.out.println(lets.get(innerLine[1]));

              } else {

                System.out.println(innerLine[1]);

                //System.out.println("4 inside second if statement"); TEST
              }
            } else if (innerLine[0].equals("let")) {
              lets.put(innerLine[1], innerLine[2]);
            }

            
          }
          handleIf = false;
        }

      }

      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static int addition(int x, int y) {

    int result = x + y;

    return result;
    
  }

  public static boolean evaluateCondition(String var1, String symbol, String var2, HashMap lets) {
    boolean returnVar = false;

    //System.out.println(var1 + " " + symbol + " " + var2 + " " + lets);

    if (symbol.equals("=")) {
      if (lets.containsKey(var1) && lets.containsKey(var2)) {
        //System.out.println("var1: " + lets.get(var1) + " | " + "var2: " + lets.get(var2));
        
        int x = Integer.parseInt((String) lets.get(var1)); // var 1 is stored as a String in lets[] so you have to parse it to turn it into a int
        int y = Integer.parseInt((String) lets.get(var2)); // var 2 is stored as a String in lets[] so you have to parse it to turn it into a int

        if (x == y) {
          returnVar = true;
        }
      }
    }


    return returnVar;
  }

}
