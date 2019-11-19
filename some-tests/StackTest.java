import java.util.Scanner;
import java.util.Stack;

public class StackTest {
  enum Options {
    ADD, REMOVE, CHANGE, LIST, EXIT, NONE
  }

  private static Scanner c = new Scanner(System.in);
  private static Stack<Integer> arr = new Stack<>();
  private static Options option = Options.NONE;

  public static void main(String[] args) {

    while (option != Options.EXIT) {
      printMenu();
      readOption();
      int maxIndex = arr.size();
      int index = 0;
      int i = 0; int val = 0; int removed = 0;

      switch(option) {
        case ADD:
          val = read("Input value to be added: ");
          arr.push(val);
          break;

        case REMOVE:
          index = read("What is the index to be removed? (0-"+maxIndex+"): ");
          if (index < 0 || index > maxIndex) {
            log("Selected index ("+index+") out of bounds.");
          } else {
            removed = arr.remove(index);
            log("Removed item at index "+index+": "+removed);
          }
          break;

        case CHANGE:
          index = read("What is the index to change? (0-"+maxIndex+") ");
          if (index < 0 || index > maxIndex) {
            log("Selected index ("+index+") out of bounds.");
          } else {
            log("Value at index ("+index+") is "+arr.get(index));
            val = read("What is the new value? ");
            arr.set(index, val);
            log("Updated value at index ("+index+") to "+val);
          };
          break;

        case LIST:
          log("Contents of the list:");
          for (i=0; i<arr.size(); i++) {
            log("#"+i+": "+arr.get(i)+";");
          }
          log("Total of "+arr.size()+" items.");
          break;

        case EXIT:
          log("Goodbye");
          break;

        case NONE:
          log("Option unrecognized..");
          break;
      }
    }
  }

  public static void printMenu() {
    log("\n========\n  MENU\n========\n");
    log(" 1: ADD - add an item to the list");
    log(" 2: REMOVE - remove an item from the list");
    log(" 3: CHANGE - change an item from the list");
    log(" 4: LIST - list all the items");
    log(" 0: Exit");
  }
  public static void readOption() {
    int opt = read(">: ");
    switch (opt) {
      case 1: option = Options.ADD; break;
      case 2: option = Options.REMOVE; break;
      case 3: option = Options.CHANGE; break;
      case 4: option = Options.LIST; break;
      case 0: option = Options.EXIT; break;
      default: option = Options.NONE; break;
    }
  }
  public static int read(String text) {
    System.out.println(text);
    return c.nextInt();
  }
  public static void log(String text) {
    System.out.println(text);
  }
}