import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Oybek Kamolov & Hajraan Hussain
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new set
     * @ensures constructorRef = {}
     */
    protected abstract Set<String> constructorRef();

    /**
     * Creates and returns a {@code Set<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsTest = [entries in args]
     */
    private Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Creates and returns a {@code Set<String>} of the reference implementation
     * type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsRef = [entries in args]
     */
    private Set<String> createFromArgsRef(String... args) {
        Set<String> set = this.constructorRef();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    @Test
    public final void testNoArgumentConstructor() {
        /*
         * Set up variables and call method under test
         */
        Set<String> s = this.constructorTest();
        Set<String> sExpected = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    @Test
    public final void testAdd() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("b", "f", "a", "c", "e", "g");
        Set<String> sExpected = this.createFromArgsRef("d", "b", "f", "a", "c",
                "e", "g");

        /*
         * Call method under test
         */
        s.add("d");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    @Test
    public final void testAddToEmpty() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef("d");

        /*
         * Call method under test
         */
        s.add("d");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    @Test
    public final void testRemove() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("d", "b", "f", "a", "c", "e",
                "g");
        Set<String> sExpected = this.createFromArgsRef("d", "b", "f", "a", "e",
                "g");
        String valExpected = "c";

        /*
         * Call method under test
         */
        String val = s.remove("c");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(valExpected, val);
    }

    @Test
    public final void testRemoveAgain() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("d", "b", "f", "a", "c", "e",
                "g");
        Set<String> sExpected = this.createFromArgsRef("b", "f", "a", "c", "e",
                "g");
        String valExpected = "d";

        /*
         * Call method under test
         */
        String val = s.remove("d");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(valExpected, val);
    }

    @Test
    public final void testRemoveRtEmpty() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("d", "b", "a", "c");
        Set<String> sExpected = this.createFromArgsRef("b", "a", "c");
        String valExpected = "d";

        /*
         * Call method under test
         */
        String val = s.remove("d");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(valExpected, val);
    }

    @Test
    public final void testRemoveEmpty() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("d");
        Set<String> sExpected = this.createFromArgsRef();
        String valExpected = "d";

        /*
         * Call method under test
         */
        String val = s.remove("d");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(valExpected, val);
    }

    @Test
    public final void testRemoveAny() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("d", "b", "f", "a", "c", "e",
                "g");
        Set<String> sExpected = this.createFromArgsRef("d", "b", "f", "a", "c",
                "e", "g");

        /*
         * Call method under test
         */
        String val = s.removeAny();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(true, sExpected.contains(val));

        String valExpected = sExpected.remove(val);

        assertEquals(valExpected, val);
        assertEquals(sExpected, s);
    }

    @Test
    public final void testRemoveAnyEmpty() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("d");
        Set<String> sExpected = this.createFromArgsRef("d");

        /*
         * Call method under test
         */
        String val = s.removeAny();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(true, sExpected.contains(val));

        String valExpected = sExpected.remove(val);

        assertEquals(valExpected, val);
        assertEquals(sExpected, s);
    }

    @Test
    public final void testContains() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("d", "b", "f", "a", "c", "e",
                "g");
        Set<String> sExpected = this.createFromArgsRef("d", "b", "f", "a", "c",
                "e", "g");
        /*
         * Call method under test
         */
        boolean contain = s.contains("d");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(true, contain);
    }

    @Test
    public final void testContainsNot() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("d", "b", "f", "a", "c", "e",
                "g");
        Set<String> sExpected = this.createFromArgsRef("d", "b", "f", "a", "c",
                "e", "g");
        /*
         * Call method under test
         */
        boolean containNot = s.contains("z");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(false, containNot);
    }

    @Test
    public final void testEmptyContains() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef();
        boolean containNotExpected = false;
        /*
         * Call method under test
         */
        boolean containNot = s.contains("d");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(containNotExpected, containNot);
    }

    @Test
    public final void testSize() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("d", "b", "f", "a", "c", "e",
                "g");
        Set<String> sExpected = this.createFromArgsRef("d", "b", "f", "a", "c",
                "e", "g");
        int sizeExpected = sExpected.size();

        /*
         * Call method under test
         */
        int size = s.size();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sizeExpected, size);
        assertEquals(sExpected, s);
    }

    @Test
    public final void testEmptySize() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef();
        int sizeExpected = sExpected.size();

        /*
         * Call method under test
         */
        int size = s.size();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sizeExpected, size);
        assertEquals(sExpected, s);
    }

}
