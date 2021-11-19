import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

/**
 * بسم الله الرحمن الرحيم takes words from a file and arranges them
 * alphabetically as well as counts the occurences of the word, making a tag
 * cloud using an html file.
 *
 * @author Hajraan Hussain
 * @date 11/24/2020
 */
public final class TagCloudGenerator2 {

    // comparator to order numerically
    private static class Numerically
            implements Comparator<Map.Entry<String, Integer>> {
        @Override
        public int compare(Map.Entry<String, Integer> o1,
                Map.Entry<String, Integer> o2) {
            return o2.getValue().compareTo(o1.getValue());
        }
    }

    // comparator to order alphabetically
    private static class Alphabetically
            implements Comparator<Map.Entry<String, Integer>> {

        @Override
        public int compare(Map.Entry<String, Integer> o1,
                Map.Entry<String, Integer> o2) {
            return o1.getKey().compareToIgnoreCase(o2.getKey());
        }
    }

    // to determine the max count from all the words
    private static int MAX = 0;

    //max font minus the offset
    private static int MAX_FONT = 37;

    private static int OFFSET = 11;

    /**
     * Takes the count and makes a proportional font size
     *
     * @param count
     *            count of the specific word
     * @requires
     * @ensures font Size = (37(count))/max + 11
     *
     * @returns int appropriate font size for the html
     */
    private static int findFont(int count) {
        // 48 - 11 = 37, x/37 = count/max, x(max) = 37(count), x = (37(count))/max + 11
        int fontSize = ((MAX_FONT * count) / MAX) + OFFSET;

        return fontSize;
    }

    /**
     * this method takes the words from the input file and inserts them into a
     * map and into a sorting machine.
     *
     * @param in
     *            the input file
     * @param words
     *            a map with the words as well as their count
     * @param sort
     *            sorting queue that sorts numerically
     * @requires
     * @ensures all words from the inputFile are stored numerically in sort
     * @throws IOException
     */
    private static void countWords(BufferedReader in,
            Map<String, Integer> words, Queue<Map.Entry<String, Integer>> sort)
            throws IOException {

        // to loop through the file
        String line = "  ";
        while (in.ready()) {
            // the extra space is so the last word can be read by the code
            line = in.readLine() + " ";
            StringBuilder word = new StringBuilder();
            // to go through each line
            for (int i = 0; i < line.length(); i++) {
                // if its a letter it will become a word
                if (Character.isLetter(line.charAt(i))) {
                    // to avoid problems with cases
                    char c = line.charAt(i);
                    c = Character.toLowerCase(c);
                    word.append(c);
                    // if there is no letter it means a new word in the list
                } else {

                    if (words.containsKey(word.toString())) {
                        int x = words.get(word.toString());
                        x++;
                        words.put(word.toString(), x);
                    } else {
                        // so there won't be an empty space in the list
                        if (word.length() != 0) {
                            words.put(word.toString(), 1);
                        }
                    }
                    // reset StringBuilder word
                    word.setLength(0);
                }
            }
        }

        // To sort numerically the words
        Set<Map.Entry<String, Integer>> set = words.entrySet();
        for (Map.Entry<String, Integer> pair : set) {
            sort.add(pair);
        }

    }

    /**
     * this method creates an html that makes a tag cloud of the words and the
     * count of the input file.
     *
     * @param out
     *            this is output file
     * @param file
     *            the title of the input file
     * @param sort
     *            this is the correct order of the words, alphabetically, with
     *            there counts
     * @requires
     * @ensures a tag cloud html file is printed onto the outputFile
     */
    private static void createHtml(PrintWriter out, String file,
            Queue<Map.Entry<String, Integer>> sort) {
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println(
                "<title>Top " + sort.size() + " words in " + file + "</title>");
        out.println(
                "<link href=\"http://web.cse.ohio-state.edu/software/2231/web-sw2/assignments/projects/tag-cloud-generator/data/tagcloud.css\" rel=\"stylesheet\" type=\"text/css\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Top " + sort.size() + " words in " + file + "</h2>");
        out.println("<hr>");
        out.println("<div class = \"cdiv\">");
        out.println("<p class = \"cbox\">");
        // making the words and there size into a tag cloud with html
        while (sort.size() != 0) {
            Map.Entry<String, Integer> p = sort.remove();

            out.println("<span style = \"cursor:default\" class = \"f"
                    + findFont(p.getValue()) + "\" title = count: "
                    + p.getValue() + "\">" + p.getKey() + "</span>");
        }
        out.println("</p>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     *
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BufferedReader inputFile;
        PrintWriter outputFile;
        String iFileString;

        int N = 0;
        // ask the user for valid inputs
        try {
            System.out.print("Please insert an input file: ");
            iFileString = sc.nextLine();
            inputFile = new BufferedReader(new FileReader(iFileString));
            System.out.print("Please insert an output file: ");
            outputFile = new PrintWriter(
                    new BufferedWriter(new FileWriter(sc.nextLine())));
        } catch (IOException e) {
            System.err.println("Error opening the file!");
            sc.close();
            return;
        }
        System.out.print(
                "Please insert the number of words to be included in the generated tag cloud: ");
        N = sc.nextInt();
        sc.nextLine();
        while (N < 1) {
            System.out.println(
                    "ERROR! Number of words should be a positive integer");
            System.out.print(
                    "Please insert the number of words to be included in the generated tag cloud: ");
            N = sc.nextInt();
            sc.nextLine();
        }

        Map<String, Integer> words = new HashMap<>();

        //numerically ordered queue
        Comparator<Map.Entry<String, Integer>> numerical = new Numerically();
        Queue<Map.Entry<String, Integer>> numerically = new PriorityQueue<>(
                numerical);

        //prepare the words and the count and the order
        try {
            countWords(inputFile, words, numerically);
        } catch (IOException e) {
            System.err.println("Error with inputFile in countWords method!");
            sc.close();
            outputFile.close();
            return;
        }

        //alphabetically ordered queue
        Comparator<Map.Entry<String, Integer>> alphabetical = new Alphabetically();
        Queue<Map.Entry<String, Integer>> alphabetically = new PriorityQueue<>(
                alphabetical);

        // to make sure N isn't bigger than the actual word count
        if (N > numerically.size()) {
            N = numerically.size();
        }
        // take out the N number of most frequent words needed for the tag cloud and sort them alphabetically
        for (int i = N; i > 0; i--) {
            Map.Entry<String, Integer> p = numerically.remove();
            if (i == N) {
                MAX = p.getValue();
            }

            alphabetically.add(p);

        }
        createHtml(outputFile, iFileString, alphabetically);

        sc.close();
        try {
            outputFile.close();
            inputFile.close();
        } catch (IOException e) {
            System.err.println("Error with closing Files!");
            return;
        }

    }

}
