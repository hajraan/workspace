import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class MapTest {

    /**
     * Invokes the appropriate {@code Map} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new map
     * @ensures constructorTest = {}
     */
    protected abstract Map<String, String> constructorTest();

    /**
     * Invokes the appropriate {@code Map} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new map
     * @ensures constructorRef = {}
     */
    protected abstract Map<String, String> constructorRef();

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsTest = [pairs in args]
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsRef = [pairs in args]
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorRef();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    @Test
    public final void testNoArgumentConstructor() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> s = this.constructorTest();
        Map<String, String> sExpected = this.constructorRef();
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
        Map<String, String> sExpected = this.createFromArgsRef("1", "red", "2",
                "green", "3", "blue");
        Map<String, String> s = this.createFromArgsTest("2", "green", "3",
                "blue");

        /*
         * Call method under test
         */
        s.add("1", "red");

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
        Map<String, String> sExpected = this.createFromArgsRef("1", "red");
        Map<String, String> s = this.createFromArgsTest();

        /*
         * Call method under test
         */
        s.add("1", "red");

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
        Map<String, String> sExpected = this.createFromArgsRef("2", "green",
                "3", "blue");
        Map<String, String> s = this.createFromArgsTest("1", "red", "2",
                "green", "3", "blue");

        String keyExpected = "1";
        String valueExpected = "red";

        /*
         * Call method under test
         */

        Map.Pair<String, String> pair = s.remove("1");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(keyExpected, pair.key());
        assertEquals(valueExpected, pair.value());
    }

    @Test
    public final void testRemoveAgain() {
        /*
         * Set up variables
         */
        Map<String, String> sExpected = this.createFromArgsRef("1", "red", "3",
                "blue");
        Map<String, String> s = this.createFromArgsTest("1", "red", "2",
                "green", "3", "blue");

        String keyExpected = "2";
        String valueExpected = "green";

        /*
         * Call method under test
         */

        Map.Pair<String, String> pair = s.remove("2");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(keyExpected, pair.key());
        assertEquals(valueExpected, pair.value());
    }

    @Test
    public final void testRemoveEmpty() {
        /*
         * Set up variables
         */
        Map<String, String> sExpected = this.createFromArgsRef();
        Map<String, String> s = this.createFromArgsTest("1", "red");

        String keyExpected = "1";
        String valueExpected = "red";

        /*
         * Call method under test
         */

        Map.Pair<String, String> pair = s.remove("1");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(keyExpected, pair.key());
        assertEquals(valueExpected, pair.value());
    }

    @Test
    public final void testRemoveAny() {
        /*
         * Set up variables
         */
        Map<String, String> sExpected = this.createFromArgsRef("1", "red", "2",
                "green", "3", "blue");
        Map<String, String> s = this.createFromArgsTest("1", "red", "2",
                "green", "3", "blue");

        /*
         * Call method under test
         */

        Map.Pair<String, String> pair = s.removeAny();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(true, sExpected.hasKey(pair.key()));
        assertEquals(pair.value(), sExpected.value(pair.key()));

        Map.Pair<String, String> valExpected = sExpected.remove(pair.key());

        assertEquals(valExpected, pair);
        assertEquals(sExpected, s);
    }

    @Test
    public final void testRemoveAnyEmpty() {
        /*
         * Set up variables
         */
        Map<String, String> sExpected = this.createFromArgsRef("1", "red");
        Map<String, String> s = this.createFromArgsTest("1", "red");

        /*
         * Call method under test
         */

        Map.Pair<String, String> pair = s.removeAny();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(true, sExpected.hasKey(pair.key()));
        assertEquals(pair.value(), sExpected.value(pair.key()));

        Map.Pair<String, String> valExpected = sExpected.remove(pair.key());

        assertEquals(valExpected, pair);
        assertEquals(sExpected, s);
    }

    @Test
    public final void testValue() {
        /*
         * Set up variables
         */
        Map<String, String> sExpected = this.createFromArgsRef("1", "red", "2",
                "green", "3", "blue");
        Map<String, String> s = this.createFromArgsTest("1", "red", "2",
                "green", "3", "blue");

        String valExpected = sExpected.value("1");

        /*
         * Call method under test
         */

        String val = s.value("1");

        /*
         * Assert that values of variables match expectations
         */

        assertEquals(sExpected, s);
        assertEquals(valExpected, val);
    }

    @Test
    public final void testValueAgain() {
        /*
         * Set up variables
         */
        Map<String, String> sExpected = this.createFromArgsRef("1", "red", "2",
                "green", "3", "blue");
        Map<String, String> s = this.createFromArgsTest("1", "red", "2",
                "green", "3", "blue");

        String valExpected = sExpected.value("2");

        /*
         * Call method under test
         */

        String val = s.value("2");

        /*
         * Assert that values of variables match expectations
         */

        assertEquals(sExpected, s);
        assertEquals(valExpected, val);
    }

    @Test
    public final void testHasKey() {
        /*
         * Set up variables
         */
        Map<String, String> sExpected = this.createFromArgsRef("1", "red", "2",
                "green", "3", "blue");
        Map<String, String> s = this.createFromArgsTest("1", "red", "2",
                "green", "3", "blue");

        boolean containKeyExpected = true;

        /*
         * Call method under test
         */

        boolean containKey = s.hasKey("1");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(containKeyExpected, containKey);
    }

    @Test
    public final void testHasKeyNot() {
        /*
         * Set up variables
         */
        Map<String, String> sExpected = this.createFromArgsRef("1", "red", "2",
                "green", "3", "blue");
        Map<String, String> s = this.createFromArgsTest("1", "red", "2",
                "green", "3", "blue");

        boolean containKeyExpected = false;

        /*
         * Call method under test
         */

        boolean containKey = s.hasKey("4");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(containKeyExpected, containKey);
    }

    @Test
    public final void testEmptySetHasKey() {
        /*
         * Set up variables
         */
        Map<String, String> sExpected = this.createFromArgsRef();
        Map<String, String> s = this.createFromArgsTest();

        boolean containKeyExpected = false;

        /*
         * Call method under test
         */

        boolean contain = s.hasKey("1");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(containKeyExpected, contain);
    }

    @Test
    public final void testSize() {
        /*
         * Set up variables
         */
        Map<String, String> sExpected = this.createFromArgsRef("1", "red", "2",
                "green", "3", "blue");
        Map<String, String> s = this.createFromArgsTest("1", "red", "2",
                "green", "3", "blue");

        int sizeExpected = 3;

        /*
         * Call method under test
         */

        int size = s.size();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(sizeExpected, size);
    }

    @Test
    public final void testEmptySize() {
        /*
         * Set up variables
         */
        Map<String, String> sExpected = this.createFromArgsRef();
        Map<String, String> s = this.createFromArgsTest();

        int sizeExpected = sExpected.size();

        /*
         * Call method under test
         */

        int size = s.size();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(sizeExpected, size);
    }

}
