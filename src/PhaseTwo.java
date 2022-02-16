import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class PhaseTwo {
     static List<BufferedReader> listOfFiles = null;
     static List<Integer> firstElementsOfFiles = null;
     static int numberOfPasses = 0;
     static int lastFileNumberIndex = 0;
    static int numberOfFileFromFirstPass = 0;

    public static void start() {
        System.out.println("\n**********Phase 2 started*********\n");
        try {
            readFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void readFiles() throws IOException {

        //reinitialize
         listOfFiles = new ArrayList<>();
         firstElementsOfFiles = new ArrayList<>();
         numberOfPasses = 0;
         lastFileNumberIndex = 0;
         numberOfFileFromFirstPass = Math.min(PhaseOne.fileNumber, MergeSort.memorySize);


        System.out.println("Number of files from first pass"+numberOfFileFromFirstPass);
        boolean checkIfMergeOrNot = true;
        while (checkIfMergeOrNot) {
            for (int i = 0; i < numberOfFileFromFirstPass; i++) {
                listOfFiles.add(new BufferedReader(new FileReader("sortedFiles/Sorted" + (i + lastFileNumberIndex + 1) +
                        ".txt")));
                firstElementsOfFiles.add(Integer.parseInt(listOfFiles.get(i).readLine()));
            }
            int minNumberIndex = -1;
            int minNumber = Integer.MAX_VALUE;
            boolean elementsAvailable = true;
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("sortedFiles/Sorted" + (PhaseOne.fileNumber + 1) + ".txt"));
            while (elementsAvailable) {
                for (int i = 0; i < firstElementsOfFiles.size(); i++) {
                    if (minNumber > firstElementsOfFiles.get(i)) {
                        minNumber = firstElementsOfFiles.get(i);
                        minNumberIndex = i;
                    }
                }
                bufferedWriter.write(Integer.toString(minNumber));
                bufferedWriter.write("\n");
//                System.out.println(minNumberIndex + " " + minNumber);
                String newValue = listOfFiles.get(minNumberIndex).readLine();
                if (newValue == null) {
                    BufferedReader removeElement = listOfFiles.remove(minNumberIndex);
                    removeElement.close();
                    firstElementsOfFiles.remove(minNumberIndex);
                } else {
                    firstElementsOfFiles.set(minNumberIndex, Integer.parseInt(newValue));
                }
                if (listOfFiles.size() == 0) {
                    elementsAvailable = false;
                }
                minNumber = Integer.MAX_VALUE;
            }
            bufferedWriter.close();
            PhaseOne.fileNumber++;

            System.out.println("Intermediate pass result");
            MergeSort.displayRandomList("sortedFiles/"+"Sorted"+PhaseOne.fileNumber+".txt");

            numberOfPasses++;
            lastFileNumberIndex = numberOfPasses * MergeSort.memorySize;
            numberOfFileFromFirstPass = Math.min(MergeSort.memorySize,
                    (PhaseOne.fileNumber - lastFileNumberIndex));
            checkIfMergeOrNot = numberOfFileFromFirstPass > 0;
            firstElementsOfFiles.clear();
            listOfFiles.clear();
        }
        System.out.println("Total number of passes: " + numberOfPasses+"\n");
    }
}
