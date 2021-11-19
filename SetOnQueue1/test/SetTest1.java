import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Put your name here
 *
 */
public abstract class SetTest1 {

    /**
     * Invokes the appropriate {@code Set} constructor and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor and returns the result.
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
        Set<String> set = this.createFromArgsRef();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    @Test
    public final void testAdd() {
        Set<String> q = this.createFromArgsTest("I", "am");
        Set<String> qe = this.createFromArgsRef("I", "am");

        q.add("cool");
        qe.add("cool");

        assertEquals(qe, q);
    }

    @Test
    public final void testRemove() {
        Set<String> q = this.createFromArgsTest("I", "am", "cool");
        Set<String> qe = this.createFromArgsRef("I", "am", "cool");

        q.remove("am");
        qe.remove("am");

        assertEquals(qe, q);
    }

    @Test
    public final void testContains() {
        Set<String> q = this.createFromArgsTest("I", "am", "cool");

        boolean x = q.contains("am");
        boolean x1 = q.contains("no");

        assertEquals(true, x);
        assertEquals(false, x1);

    }

    @Test
    public final void testRemoveAny() {
        Set<String> q = this.createFromArgsTest("I", "am", "cool");
        Set<String> qe = this.createFromArgsRef("I", "am", "cool");

        String s = q.removeAny();
        boolean worked = qe.contains(s);
        qe.remove(s);

        assertEquals(2, q.size());
        assertEquals(false, q.contains(s));
        assertEquals(true, worked);
        assertEquals(qe, q);
    }
}
