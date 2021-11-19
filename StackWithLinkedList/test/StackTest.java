import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.stack.Stack;

/**
 * JUnit test fixture for {@code Stack<String>}'s constructor and kernel
 * methods.
 *
 * @author Hajraan Hussain && Oybek Kamolov
 *
 */
public abstract class StackTest {

    /**
     * Invokes the appropriate {@code Stack} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new stack
     * @ensures constructorTest = <>
     */
    protected abstract Stack<String> constructorTest();

    /**
     * Invokes the appropriate {@code Stack} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new stack
     * @ensures constructorRef = <>
     */
    protected abstract Stack<String> constructorRef();

    /**
     *
     * Creates and returns a {@code Stack<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the stack
     * @return the constructed stack
     * @ensures createFromArgsTest = [entries in args]
     */
    private Stack<String> createFromArgsTest(String... args) {
        Stack<String> stack = this.constructorTest();
        for (String s : args) {
            stack.push(s);
        }
        stack.flip();
        return stack;
    }

    /**
     *
     * Creates and returns a {@code Stack<String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the entries for the stack
     * @return the constructed stack
     * @ensures createFromArgsRef = [entries in args]
     */
    private Stack<String> createFromArgsRef(String... args) {
        Stack<String> stack = this.constructorRef();
        for (String s : args) {
            stack.push(s);
        }
        stack.flip();
        return stack;
    }

    @Test
    public final void testNoArgumentConstructor() {
        /*
         * Set up variables and call method under test
         */
        Stack<String> s = this.createFromArgsTest();
        Stack<String> sExpected = this.createFromArgsRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    @Test
    public final void testPushEmpty() {
        /*
         * Set up variables and call method under test
         */
        Stack<String> s = this.createFromArgsTest();
        Stack<String> sExpected = this.createFromArgsRef("a");
        /*
         * Call method under test
         */
        s.push("a");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    @Test
    public final void testPush() {
        /*
         * Set up variables and call method under test
         */
        Stack<String> s = this.createFromArgsTest("a");
        Stack<String> sExpected = this.createFromArgsRef("b", "a");
        /*
         * Call method under test
         */
        s.push("b");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    @Test
    public final void testPopEmpty() {
        /*
         * Set up variables and call method under test
         */
        Stack<String> s = this.createFromArgsTest("a");
        Stack<String> sExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        s.pop();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    @Test
    public final void testPop() {
        /*
         * Set up variables and call method under test
         */
        Stack<String> s = this.createFromArgsTest("a", "b");
        Stack<String> sExpected = this.createFromArgsRef("b");
        /*
         * Call method under test
         */
        String val = s.pop();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals("a", val);

    }

    @Test
    public final void testLengthEmpty() {
        /*
         * Set up variables and call method under test
         */
        Stack<String> s = this.createFromArgsTest();
        /*
         * Call method under test
         */
        int length = s.length();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(0, length);
    }

    @Test
    public final void testLength() {
        /*
         * Set up variables and call method under test
         */
        Stack<String> s = this.createFromArgsTest("a", "b", "c");
        /*
         * Call method under test
         */
        int length = s.length();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(3, length);
    }
}
