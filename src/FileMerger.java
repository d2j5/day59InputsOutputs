import java.io.*;
import java.util.*;

public class FileMerger {
    public static void main(String[] args) {
        //Files path
        String inputFile1 = "C:\\Users\\Javier\\IdeaProjects\\day59InputsOutputs\\src\\input1.txt";
        String inputFile2 = "C:\\Users\\Javier\\IdeaProjects\\day59InputsOutputs\\src\\input2.txt";

        //Names for the files that are going to be created.
        String mergedFile = "merged.txt";
        String commonFile = "common.txt";

        try {
            List<Integer> list1 = readIntegersFromFile(inputFile1); // Read integers from input1.txt
            List<Integer> list2 = readIntegersFromFile(inputFile2);// Read integers from input2.txt


            List<Integer> mergedList = mergeLists(list1, list2); // Merge the two input lists
            writeIntegersToFile(mergedList, mergedFile);// Write merged list to merged.txt

            List<Integer> commonList = findCommonElements(list1, list2);// Find common integers
            writeIntegersToFile(commonList, commonFile); // Write common integers to common.txt


            System.out.println("Files merged successfully!");
        } catch (FileNotFoundException e) {
            System.err.println("One or more input files not found.");
        } catch (IOException e) {
            System.err.println("An error occurred while reading or writing files.");
        } catch (NumberFormatException e) {
            System.err.println("One or more files contain invalid integers.");
        }
    }

    //Read integers from two text files called "input1.txt" and "input2.txt". Each integer is on a new line in the respective files.
    private static List<Integer> readIntegersFromFile(String filename) throws IOException {
        List<Integer> integers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int number = Integer.parseInt(line.trim());// Parse each line as an integer
                integers.add(number);
            }
        }

        return integers;
    }
    //Merge the contents of the two input files, maintaining the original order of the integers, and write the result to a new text file called "merged.txt".
    private static List<Integer> mergeLists(List<Integer> list1, List<Integer> list2) {
        List<Integer> mergedList = new ArrayList<>();
        mergedList.addAll(list1);// Add all elements from list1
        mergedList.addAll(list2);// Add all elements from list2
        return mergedList;
    }
    //Identify the integers that are present in both input files.
    private static List<Integer> findCommonElements(List<Integer> list1, List<Integer> list2) {
        List<Integer> commonList = new ArrayList<>();

        Set<Integer> set1 = new HashSet<>(list1);
        for (Integer number : list2) {
            if (set1.contains(number)) {
                commonList.add(number);
            }
        }

        return commonList;
    }
//Write the integers that are present in both input files to a new text file called "common.txt".
    private static void writeIntegersToFile(List<Integer> integers, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Integer number : integers) {
                writer.write(number.toString());// Write each integer to the file
                writer.newLine();// Add a new line after each integer
            }
        }
    }
}