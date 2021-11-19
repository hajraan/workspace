import java.util.Comparator;

import components.map.Map;
import components.map.Map.Pair;
import components.map.Map1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.sortingmachine.SortingMachine;
import components.sortingmachine.SortingMachine1L;

/**
 * بسم الله الرحمن الرحيم takes words from a file and arranges them
 * alphabetically as well as counts the occurences of the word, making a tag
 * cloud using an html file.
 *
 * @author Hajraan Hussain
 * @date 11/24/2020
 */
public final class TagCloudGenerator {

    // comparator to order numerically

    private static Comparator<Map.Pair<String, Integer>> Numerically = new Comparator<Map.Pair<String, Integer>>() {
        @Override
        public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {
            return o2.value().compareTo(o1.value());
        }
    };
    // comparator to order alphabetically
    private static Comparator<Map.Pair<String, Integer>> Alphabetically = new Comparator<Map.Pair<String, Integer>>() {
        @Override
        public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {
            return o1.key().compareToIgnoreCase(o2.key());
        }
    };

    // to determine the max count from all the words
    private static int MAX = 0;

    /**
     * Takes the count and makes a proportional font size
     *
     * @param count
     *            count of the specific word
     */
    private static int findFont(int count) {
        // 48 - 11 = 37, x/37 = count/max, x(max) = 37(count), x = (37(count))/max + 11
        int fontSize = ((37 * count) / MAX) + 11;

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
     *            sorting machine that sorts numerically
     */
    private static void countWords(SimpleReader in, Map<String, Integer> words,
            SortingMachine<Map.Pair<String, Integer>> sort) {
        // to loop through the file
        while (!in.atEOS()) {
            // the extra space is so the last word can be read by the code
            String sentance = in.nextLine() + " ";
            StringBuilder word = new StringBuilder();
            // to go through each line
            for (int i = 0; i < sentance.length(); i++) {
                // if its a letter it will become a word
                if (Character.isLetter(sentance.charAt(i))) {
                    // to avoid problems with cases
                    char c = sentance.charAt(i);
                    c = Character.toLowerCase(c);
                    word.append(c);
                    // if there is no letter it means a new word in the list
                } else {

                    if (words.hasKey(word.toString())) {
                        int x = words.value(word.toString());
                        x++;
                        words.replaceValue(word.toString(), x);
                    } else {
                        // so there won't be an empty space in the list
                        if (word.length() != 0) {
                            words.add(word.toString(), 1);
                        }
                    }
                    // reset StringBuilder word
                    word.setLength(0);
                }
            }
        }
        // To sort numerically the words
        int x = words.size();
        for (int j = 0; j < x; j++) {
            sort.add(words.removeAny());
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
     * @param alphabetically
     *            this is the correct order of the words, alphabetically, with
     *            there counts
     */
    private static void createHtml(SimpleWriter out, String file,
            SortingMachine<Map.Pair<String, Integer>> sort) {
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
            Map.Pair<String, Integer> p = sort.removeFirst();

            out.println("<span style = \"cursor:default\" class = \"f"
                    + findFont(p.value()) + "\" title = count: " + p.value()
                    + "\">" + p.key() + "</span>");
        }
        out.println("</p>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }

    /**
     * This method is to check if the user inputs are valid or not
     *
     * @param in
     *            the input file name
     * @param out
     *            the output file name
     * @param stringN
     *            the tag cloud size in string form
     * @return
     */
    private static boolean isValidInput(String inF, String outF,
            String stringN) {
        SimpleWriter out = new SimpleWriter1L();
        boolean isValid = true;

        if (!inF.contains(".txt")) {
            out.println("ERROR! Input file must contain .txt");
            isValid = false;
        }
        if (!outF.contains(".html")) {
            out.println("ERROR! Output file must contain .html");
            isValid = false;
        }
        int N = Integer.parseInt(stringN);
        if (N < 0 || stringN.contains(".")) {
            out.println("ERROR! Number of words should be a positive integer");
            isValid = false;
        }
        if (!isValid) {
            out.println("Please re-enter valid inputs");
        }

        return isValid;

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        SimpleReader in = new SimpleReader1L();
        boolean isValid = false;
        String iFileString = "";
        String oFileString = "";
        String stringN = "";
        int N = 0;
        // ask the user for valid inputs
        while (!isValid) {
            out.print("Please insert an input file: ");
            iFileString = in.nextLine();
            out.print("Please insert an output file: ");
            oFileString = in.nextLine();
            out.print(
                    "Please insert the number of words to be included in the generated tag cloud: ");
            stringN = in.nextLine();

            isValid = isValidInput(iFileString, oFileString, stringN);
        }
        SimpleReader inputFile = new SimpleReader1L(iFileString);
        SimpleWriter outputFile = new SimpleWriter1L(oFileString);
        N = Integer.parseInt(stringN);

        Map<String, Integer> words = new Map1L<>();
        SortingMachine<Map.Pair<String, Integer>> numerically = new SortingMachine1L<>(
                Numerically);
        //prepare the words and the count and the order
        countWords(inputFile, words, numerically);
        SortingMachine<Map.Pair<String, Integer>> alphabetically = new SortingMachine1L<>(
                Alphabetically);

        numerically.changeToExtractionMode();
        // to make sure N isn't bigger than the actual word count
        if (N > numerically.size()) {
            N = numerically.size();
        }
        // take out the N number of most frequent words needed for the tag cloud and sort them alphabetically
        for (int i = N; i > 0; i--) {
            Map.Pair<String, Integer> p = numerically.removeFirst();
            if (i == N) {
                MAX = p.value();
            }

            alphabetically.add(p);

        }
        alphabetically.changeToExtractionMode();
        // to carry into the createHtml method
        createHtml(outputFile, iFileString, alphabetically);

        outputFile.close();
        inputFile.close();
        in.close();
        out.close();
    }

}
