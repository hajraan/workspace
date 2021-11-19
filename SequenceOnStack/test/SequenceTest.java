import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.sequence.Sequence;

/**
 * JUnit test fixture for {@code Sequence<String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class SequenceTest {

    /**
     * Invokes the appropriate {@code Sequence} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new sequence
     * @ensures constructorTest = <>
     */
    protected abstract Sequence<String> constructorTest();

    /**
     * Invokes the appropriate {@code Sequence} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new sequence
     * @ensures constructorRef = <>
     */
    protected abstract Sequence<String> constructorRef();

    /**
     *
     * Creates and returns a {@code Sequence<String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the entries for the sequence
     * @return the constructed sequence
     * @ensures createFromArgsTest = [entries in args]
     */
    private Sequence<String> createFromArgsTest(String... args) {
        Sequence<String> sequence = this.constructorTest();
        for (String s : args) {
            sequence.add(sequence.length(), s);
        }
        return sequence;
    }

    /**
     *
     *
     * Creates and returns a {@code Sequence<String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the entries for the sequence
     * @return the constructed sequence
     * @ensures createFromArgsRef = [entries in args]
     */
    private Sequence<String> createFromArgsRef(String... args) {
        Sequence<String> sequence = this.constructorRef();
        for (String s : args) {
            sequence.add(sequence.length(), s);
        }
        return sequence;
    }

    @Test
    public final void testNoArgumentConstructor() {
        /*
         * Set up variables and call method under test
         */
        Sequence<String> s = this.constructorTest();
        Sequence<String> sExpected = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    @Test
    public final void testAddZero() {
        /*
         * Set up variables
         */
        Sequence<String> s = this.createFromArgsTest("green", "blue");
        Sequence<String> sExpected = this.createFromArgsRef("red", "green",
                "blue");

        /*
         * Call method under test
         */
        s.add(0, "red");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    @Test
    public final void testAddOne() {
        /*
         * Set up variables
         */
        Sequence<String> s = this.createFromArgsTest("red", "blue");
        Sequence<String> sExpected = this.createFromArgsTest("red", "green",
                "blue");

        /*
         * Call method under test
         */
        s.add(1, "green");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    @Test
    public final void testAddLast() {
        /*
         * Set up variables
         */
        Sequence<String> s = this.createFromArgsTest("red", "green");
        Sequence<String> sExpected = this.createFromArgsRef("red", "green",
                "blue");

        /*
         * Call method under test
         */
        s.add(s.length(), "blue");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    @Test
    public final void testRemoveZero() {
        /*
         * Set up variables
         */
        Sequence<String> s = this.createFromArgsTest("red", "green", "blue");
        Sequence<String> sExpected = this.createFromArgsRef("green", "blue");
        String sTop = s.entry(0);

        /*
         * Call method under test
         */
        String sTopExpected = s.remove(0);

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(sTopExpected, sTop);
    }

    @Test
    public final void testRemoveOne() {
        /*
         * Set up variables
         */
        Sequence<String> s = this.createFromArgsTest("red", "green", "blue");
        Sequence<String> sExpected = this.createFromArgsRef("red", "blue");
        String sTop = s.entry(1);

        /*
         * Call method under test
         */
        String sTopExpected = s.remove(1);

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(sTopExpected, sTop);
    }

    @Test
    public final void testRemoveLast() {
        /*
         * Set up variables
         */
        Sequence<String> s = this.createFromArgsTest("red", "green", "blue");
        Sequence<String> sExpected = this.createFromArgsRef("red", "green");
        String sTop = s.entry(s.length() - 1);

        /*
         * Call method under test
         */
        String sTopExpected = s.remove(s.length() - 1);

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(sTopExpected, sTop);
    }

    @Test
    public final void testLength() {
        /*
         * Set up variables
         */
        Sequence<String> s = this.createFromArgsTest("green", "blue");
        Sequence<String> sEmpty = this.createFromArgsRef();
        int sLen = s.length();
        int sEmptyLen = sEmpty.length();

        /*
         * Call method under test
         */
        int sLenExpected = 2;
        int sEmptyLenExpected = 0;

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sLenExpected, sLen);
        assertEquals(sEmptyLenExpected, sEmptyLen);
    }

}
