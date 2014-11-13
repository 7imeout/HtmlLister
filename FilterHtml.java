/**
 * @version 1.23
 * @author Mike Ryu
 */

import java.util.*;
import java.io.*;

public class FilterHtml {
   private static final String VERSION_NUM = "1.23";
   private static final int COL_WIDTH = 80;
   private static final int NUM_CHARS_TO_CHECK = 5;

   private static PrintStream writer = null;
   private static String buffer = "";

   public static void main(String[] args) {
      Scanner scanFile = null;
      PrintStream writer = null;
      String fileName = "";
      String extension = "";
      String tempLine = "";
      String dirTitle = "";
      String titleFiller = "";
      File original = new File(args[0]);
      File filtered = null;
      int numHtmls = 0;
      int dotIndex = 0;
      
      System.out.println("\nFiltering Started   : Java core running!");

      appendHeader(VERSION_NUM);

      try {
         scanFile = new Scanner(original);
         filtered = new File(original + ".filtered.txt");
         writer = new PrintStream(filtered.getName());
      }
      catch (FileNotFoundException e) {
         System.out.println("File operation error; check file name.");
         System.exit(0);
      }

      filtered = new File(original + ".filtered.txt");

      while (scanFile.hasNext()) {
         tempLine = scanFile.nextLine();
         dotIndex = tempLine.lastIndexOf('.');
         
         if (dotIndex != -1)
            extension = tempLine.substring(dotIndex);
         else
            extension = "";

         if (extension.contains("htm")) {
            buffer += "\t"+ tempLine + "\n";
            numHtmls++;
         }
         if (tempLine.isEmpty()) {
            deleteEmptyDir();
            tempLine = scanFile.nextLine();
            tempLine = tempLine.substring(0, tempLine.length() - 1);
            dirTitle = "\n[Directory: " + tempLine + "] ";
            titleFiller = "";
            buffer += dirTitle;
            for (int i = 0; i < COL_WIDTH - dirTitle.length(); i++) {
               buffer += ":";
            }
            buffer += "\n";
         }
      }

      deleteEmptyDir();
      buffer += "\nTotal of " + numHtmls + " HTM(L) files listed above.\n";
      writer.print(buffer);

      System.out.println("Filtering Successful: " + numHtmls 
                                                  + " HTML files found.");
      System.out.println();
   }

   private static void appendHeader(String version) {
      buffer += "List Generated by: HTML List Generator version ";
      buffer += version + "\n";
      buffer += "Program by Mike Ryu (doryu@calpoly.edu)\n\n";
      buffer += "New features in this version:\n";

      /* CHANGELOG FOR USER BELOW */

      buffer += "\tProgram now properly checks file extensions for HTM(L)s.\n";
      buffer += "\tProgram correctly omits listing empty directories.\n";
      buffer += "\tOther minor changes and bug fixes.\n";

      /* CHANGELOG FOR USER ABOVE */
      
      buffer += "\n";
   }

   private static void deleteEmptyDir() {
      int bufferLength = buffer.length();
      String charsToCheck = buffer.substring(bufferLength - NUM_CHARS_TO_CHECK);
      
      if (charsToCheck.contains(":") || charsToCheck.contains("]")) {
         buffer = buffer.substring(0, bufferLength - (COL_WIDTH + 1));
         if (buffer.charAt(buffer.length() - 1) != '\n')   
            buffer = buffer.substring(0, buffer.lastIndexOf('[') - 1);
      }
   }
}
