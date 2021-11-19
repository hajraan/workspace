import java.util.Arrays;

import components.map.Map;
import components.map.Map1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.stack.Stack;
import components.stack.Stack1L;

/**
 * بسم الله الرحمن الرحيم takes words from a file and arranges them
 * alphabetically as well as counts the occurences of the word, making a table
 * using an html file.
 *
 * @author Hajraan Hussain
 * @date 9/1/2020
 */
public final class WordCounter {

    /**
     * Takes the words and sorts them alphabetically.
     *
     * @param terms
     *            words from the input file
     */
    private static void sort(Stack<String> terms) {
        int x = terms.length();
        String[] arr = new String[x];
        for (int i = 0; i < x; i++) {
            arr[i] = terms.pop();
        }
        Arrays.sort(arr);
        for (int i = x - 1; i >= 0; i--) {
            terms.push(arr[i]);
        }
    }

    /**
     * this method takes the words from the input file and inserts them into a
     * map and into a stack.
     *
     * @param in
     *            the input file
     * @param words
     *            a map with the words as well as their count
     * @param alphabetically
     *            the stack that will organize the words alphabetically
     */
    private static void countWords(SimpleReader in, Map<String, Integer> words,
            Stack<String> alphabetically) {
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
                        // won't be an empty space in the list
                        if (word.length() != 0) {
                            words.add(word.toString(), 1);
                            alphabetically.push(word.toString());
                            sort(alphabetically);
                        }
                    }
                    // reset word
                    word.setLength(0);
                }
            }
        }

    }

    /**
     * this method creates an html that tables out the word and the count of the
     * input file.
     *
     * @param out
     *            this is output file
     * @param words
     *            these are the words and the count from the input file
     * @param alphabetically
     *            this is the correct order of the words, alphabetically
     */
    private static void createHtml(SimpleWriter out, Map<String, Integer> words,
            Stack<String> alphabetically) {
        String file = alphabetically.pop();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<title>Words counted in " + file + "</title>");
        out.println("<body>");
        out.println("<h1>Words counted in " + file + "</h1>");
        out.println("<table border = \"1\">");
        out.println("<tr>");
        out.println("<th>Word</th>");
        out.println("<th>count</th>");
        out.println("</tr>");
        // making the words and counts into a table with html
        while (alphabetically.length() != 0) {
            String word = alphabetically.pop();
            out.println("<tr>");
            out.println("<td>" + word + "</td>");
            out.println("<td>" + words.value(word) + "</td>");
            out.println("</tr>");
        }
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
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
        out.print("Please insert an input file: ");
        String file = in.nextLine();
        SimpleReader inputFile = new SimpleReader1L(file);
        out.print("Please insert an output file: ");
        SimpleWriter outputFile = new SimpleWriter1L(in.nextLine());
        Map<String, Integer> words = new Map1L<>();
        Stack<String> alphabetically = new Stack1L<String>();

        //prepare the words and the count and the order
        countWords(inputFile, words, alphabetically);

        // to carry into the createHtml method
        alphabetically.push(file);
        createHtml(outputFile, words, alphabetically);

        outputFile.close();
        inputFile.close();
        in.close();
        out.close();
    }

}
