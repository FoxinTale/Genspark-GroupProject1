package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        /*
        I'm thinking we'll get how many columns to split it into from the user.

        Checks should be in place that if the read data is only one line it basically just goes "no".
         */

        String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "texts" + File.separator;
        String[] fileContent = readFile(filePath + "file1.txt");

        //   String[] fileContent = readFile(filePath + "file1.txt");

        if (fileContent != null) {
            //      splitList(fileContent, 5);
            //        //Rest of logic here.
            handleArray(fileContent, 2);
        } else {
            //         System.out.println("Something went wrong with the file processing.");
        }
    }

    public static void handleArray(String[] content, int columns) {
        System.out.println(content.length);


        int columnCount = content.length / columns; // for 10 items and a column count of 5 this is 2.

        System.out.println(columnCount);
        int temp = 0;

        ArrayList<Integer> positions = new ArrayList<>();


        for (int i = 0; i < content.length; i++) {
            // 0-1 2-3 4-5 6-7 8-9
            if (temp < content.length) {
                positions.add(temp);
                temp += columnCount;
            }
        }




        System.out.println();
    }


    public static String[] readFile(String filePath) {
        File fileToRead = new File(filePath);
        String[] contentArray;

        try {
            String content = new String(Files.readAllBytes(fileToRead.toPath()));
            contentArray = content.split("\r\n");

            return contentArray;
        } catch (IOException IOE) {
            System.out.println("The given file was not found.");
        }
        return null;
    }


    public static void splitList(String[] content, int columns) {

        int columnSize = content.length / columns;
        /*
        If it is even (checked via modulo), then that is how many items per column we use.
        If it is not, then the first column is the items per column plus one, and the rest are up to that size.
         */

        // For some godforsaken reason, it would only print the last item unless this nonsense was done.
        List<String> trimmedContent = Arrays.stream(content).map(String::trim).collect(Collectors.toList());
        if (columns == 1) {
            System.out.println(Arrays.toString(trimmedContent.toArray()).replace("[", "").replace(",", "\n").replace("]", ""));
            return; //We return here otherwise it breaks.
        } else {
            // Other logic goes here.
        }

        String[][] splitWithColumns = new String[columns][columnSize + 1];
        String[] item;
        int x = 0;
        for (int i = 0; i < trimmedContent.size(); i++) {
            if (i % columnSize == 0) {
                item = trimmedContent.subList(i, i + columnSize).toString().split(",");
                for (int j = 0; j < item.length; j++) {
                    splitWithColumns[x][j] = item[j];
                }
                x++;
            }
        }


        int length = splitWithColumns.length;

        String line = "";
        for (int i = 0; i < splitWithColumns.length; i++) {
            for (int j = 0; j < columnSize; j++) {
                line += splitWithColumns[i][j] + " | ";
                System.out.println(line);
            }
            line = "";
        }

        //    System.out.println(Arrays.toString(splitWithColumns));

        System.out.println();
        // System.out.println(Arrays.stream(splitWithColumns).map(n -> splitWithColumns[n]);

       /*
        if(size % columns == 0){

            // If it is modulo (even).
            for(int i = 0; i < columnSize; i++){
    //            line = trimmedContent.get(i);
                line = trimmedContent.get(i + columns);

         //       for(int j = 1; j < columns; j++){
      //              line += "  |  " + trimmedContent.get(tmpInt);
  //              }
//
                System.out.println(line);
                line = "";
                tmpInt++;
            }
        } else {
            System.out.println("Not evenly split.");
        }

        */
    }

    public static void verifyInput(String s) {

    }


}