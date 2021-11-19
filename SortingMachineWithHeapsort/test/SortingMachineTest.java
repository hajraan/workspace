import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import org.junit.Test;

import components.sortingmachine.SortingMachine;

/**
 * JUnit test fixture for {@code SortingMachine<String>}'s constructor and
 * kernel methods.
 *
 * @author Hajraan Hussain && Oybek Kamolov
 *
 */
public abstract class SortingMachineTest {

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * implementation under test and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorTest = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorTest(
            Comparator<String> order);

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * reference implementation and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorRef = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorRef(
            Comparator<String> order);

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the
     * implementation under test type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsTest = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsTest(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorTest(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the reference
     * implementation type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsRef = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsRef(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorRef(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     * Comparator<String> implementation to be used in all test cases. Compare
     * {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            return s1.compareToIgnoreCase(s2);
        }

    }

    /**
     * Comparator instance to be used in all test cases.
     */
    private static final StringLT ORDER = new StringLT();

    @Test
    public final void testConstructor() {
        /*
         * Set up variables and call method under test
         */
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    @Test
    public final void testAddEmpty() {
        /*
         * Set up variables and call method under test
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "a");

        /*
         * Call method under test
         */
        m.add("a");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    @Test
    public final void testAddAtRoot() {
        /*
         * Set up variables and call method under test
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "g",
                "x", "c", "t", "s", "r", "m", "d", "p", "n", "l");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "g", "x", "c", "t", "s", "r", "m", "d", "p", "n", "l", "a");

        /*
         * Call method under test
         */
        m.add("a");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    @Test
    public final void testAddAtLast() {
        /*
         * Set up variables and call method under test
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "g",
                "c", "t", "a", "s", "r", "m", "d", "p", "n", "l");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "g", "c", "t", "a", "s", "r", "m", "d", "p", "n", "l", "x");

        /*
         * Call method under test
         */
        m.add("x");
        System.out.println(mExpected.size() + "   " + m.size());
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);

    }

    @Test
    public final void testAddAnywhere() {
        /*
         * Set up variables and call method under test
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "g",
                "x", "c", "t", "a", "s", "r", "m", "d", "n", "l");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "g", "x", "c", "t", "a", "s", "r", "m", "d", "n", "l", "p");

        /*
         * Call method under test
         */
        m.add("p");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    @Test
    public final void testChangeToExtractionMode() {
        /*
         * Set up variables and call method under test
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "g",
                "x", "c", "t", "a", "s", "r", "m", "d", "p", "n", "l");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "g", "x", "c", "t", "a", "s", "r", "m", "d", "p", "n", "l");

        /*
         * Call method under test
         */
        m.changeToExtractionMode();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    @Test
    public final void testRemoveFirst() {
        /*
         * Set up variables and call method under test
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "g",
                "x", "c", "t", "a", "s", "r", "m", "d", "p", "n", "l");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "g", "x", "c", "t", "s", "r", "m", "d", "p", "n", "l");

        /*
         * Call method under test
         */
        String val = m.removeFirst();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals("a", val);
    }

    @Test
    public final void testRemoveFirstEmpty() {
        /*
         * Set up variables and call method under test
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "a");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);

        /*
         * Call method under test
         */
        String val = m.removeFirst();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals("a", val);
    }

    @Test
    public final void testIsInInsertionModeTrue() {
        /*
         * Set up variables and call method under test
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);

        /*
         * Call method under test
         */
        boolean insMode = m.isInInsertionMode();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(true, insMode);
    }

    @Test
    public final void testIsInInsertionModeFalse() {
        /*
         * Set up variables and call method under test
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);

        /*
         * Call method under test
         */
        boolean insMode = m.isInInsertionMode();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(false, insMode);
    }

    @Test
    public final void testOrderInsMode() {
        /*
         * Set up variables and call method under test
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "g",
                "x", "c", "t", "a", "s", "r", "m", "d", "p", "n", "l");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "g", "x", "c", "t", "a", "s", "r", "m", "d", "p", "n", "l");

        /*
         * Call method under test
         */
        Comparator<String> order = m.order();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(ORDER, order);
    }

    @Test
    public final void testOrderExtMode() {
        /*
         * Set up variables and call method under test
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "g",
                "x", "c", "t", "a", "s", "r", "m", "d", "p", "n", "l");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "g", "x", "c", "t", "a", "s", "r", "m", "d", "p", "n", "l");

        /*
         * Call method under test
         */
        Comparator<String> order = m.order();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(ORDER, order);
    }

    @Test
    public final void testSizeInsMode() {
        /*
         * Set up variables and call method under test
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "g",
                "x", "c", "t", "a", "s", "r", "m", "d", "p", "n", "l");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "g", "x", "c", "t", "a", "s", "r", "m", "d", "p", "n", "l");

        /*
         * Call method under test
         */
        int size = m.size();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(12, size);
    }

    @Test
    public final void testSizeOneInsMode() {
        /*
         * Set up variables and call method under test
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "g");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "g");

        /*
         * Call method under test
         */
        int size = m.size();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(1, size);
    }

    @Test
    public final void testSizeEmptyInsMode() {
        /*
         * Set up variables and call method under test
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);

        /*
         * Call method under test
         */
        int size = m.size();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(0, size);
    }

    @Test
    public final void testSizeExtMode() {
        /*
         * Set up variables and call method under test
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "g",
                "x", "c", "t", "a", "s", "r", "m", "d", "p", "n", "l");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "g", "x", "c", "t", "a", "s", "r", "m", "d", "p", "n", "l");

        /*
         * Call method under test
         */
        int size = m.size();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(12, size);
    }

    @Test
    public final void testSizeOneExtMode() {
        /*
         * Set up variables and call method under test
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false, "g");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "g");

        /*
         * Call method under test
         */
        int size = m.size();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(1, size);
    }

    @Test
    public final void testSizeEmptyExtMode() {
        /*
         * Set up variables and call method under test
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);

        /*
         * Call method under test
         */
        int size = m.size();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(0, size);
    }
}
