import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
  public static void main(String[] args) throws IOException {
    String input;
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      String str = ""; 
      System.out.println("Enter the Strings and when you want to stop entering the Strings, type ‘exit’");
      while(!str.equals("exit")) { 
          System.out.println("Enter a method: "); 
          str = br.readLine(); 

        // TODO: your logic here

        if(str.contentEquals("exit")) {
              System.out.println("FullStop!!!");
        }
      } 
    }
  }
}
