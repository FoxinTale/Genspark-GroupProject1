package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "texts" + File.separator;
        String[] fileContentArr = readFile("./src/inputTwo.txt"); //
        ArrayList<String> fileContent = new ArrayList<>();
        int columns = 1;

        System.out.println("Input the number of columns to display.");
        Scanner userInput = new Scanner(System.in);
        String result;
        String input;
        boolean inputValid = false;

        while (!inputValid) {
            input = userInput.nextLine();
            result = handleInput(input);
            if (!result.isEmpty()) {
                System.out.println(result);
            } else {
                columns = Integer.parseInt(input);
                inputValid = true;
            }
        }

        if (fileContentArr != null) {
            fileContent.addAll(List.of(fileContentArr));

            ArrayList<List<String>> columnText = splitContent(fileContent, columns);
            printList(columnText, columns, fileContentArr.length);
        } else {
            System.out.println("Something went wrong with the file processing.");
        }

    }

    public static void printList(ArrayList<List<String>> list, int columns, int contentSize) {
        String line = "";

        if (columns > 1) {
            int size = list.get(1).size();
            int index = 0;

            try {
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < list.size(); j++) {
                        if ((j < list.size() - 1)) {
                            line += list.get(j).get(index) + "  |  ";
                        } else {
                            line += list.get(j).get(index);
                        }
                    }

                    if (index < list.get(1).size()) {
                        index++;
                    } else {
                        break;
                    }

                    System.out.println(line);
                    line = "";
                }
            } catch (IndexOutOfBoundsException IOOBE) {
                IOOBE.printStackTrace();
            }
            if (contentSize % columns == 1) {
                System.out.println(list.get(0).get(list.get(0).size() - 1));
            }
        } else {
            // This needs cleaning up. But I'm at the end of my rope.
            System.out.println(Arrays.toString(list.toArray()).replace("[", "").replace(",", "\n"));
        }
    }

    /*
    Splits the list up into sublists based off of how many columns.

     */
    public static ArrayList<List<String>> splitContent(ArrayList<String> list, int columns) {
        ArrayList<List<String>> columnText = new ArrayList<>();

        int size = list.size();
        int columnCount = size / columns;
        int start = 0;
        int end = columnCount;

        if (size % columns == 0) { // the size of the list fits evenly into the columns.
            for (int i = 0; i < columns; i++) {
                columnText.add(list.subList(start, end));
                start += columnCount;
                end += columnCount;
            }
        } else { // the size of the list does not evenly fit into the columns.
            columnCount += 1;
            columnText.add(list.subList(0, columnCount));
            start = columnCount;
            end = start + columnCount;

            for (int i = 1; i < columns - 1; i++) {
                if (end < size) {
                    columnText.add(list.subList(start, end - 1));
                    start += columnCount;
                    end += columnCount;
                }

            }

            columnText.add(list.subList(size - (columnCount - 1), size));
        }
        return columnText;
    }

    public static String[] readFile(String filePath) {
        File fileToRead = new File(filePath);
        String[] contentArray;

        try {
            String content = new String(Files.readAllBytes(fileToRead.toPath()));
            if (content.contains("\r\n")) {
                contentArray = content.split("\r\n");
            } else {
                contentArray = content.split("\n");
            }


            return contentArray;
        } catch (IOException IOE) {
            System.out.println("The given file was not found.");
        }
        return null;
    }

    public static String handleInput(String s) {

        if (s.isEmpty() || s.isBlank()) {
            return "That's an empty space. Not a number of columns.";
        }
        try {
            int num = Integer.parseInt(s);
            if (num <= 0) {
                return "Cannot have zero or less columns";
            } else {
                return "";
            }
        } catch (NumberFormatException NFE) {
            return "That's a string, not a number.";
        }
    }
}