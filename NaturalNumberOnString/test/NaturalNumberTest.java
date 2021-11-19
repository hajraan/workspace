import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods.
 *
 * @author Oybek Kamolov & Hajraan Hussain
 *
 */
public abstract class NaturalNumberTest {

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new number
     * @ensures constructorTest = 0
     */
    protected abstract NaturalNumber constructorTest();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorTest = i
     */
    protected abstract NaturalNumber constructorTest(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorTest)
     */
    protected abstract NaturalNumber constructorTest(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorTest = n
     */
    protected abstract NaturalNumber constructorTest(NaturalNumber n);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @return the new number
     * @ensures constructorRef = 0
     */
    protected abstract NaturalNumber constructorRef();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorRef = i
     */
    protected abstract NaturalNumber constructorRef(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorRef)
     */
    protected abstract NaturalNumber constructorRef(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorRef = n
     */
    protected abstract NaturalNumber constructorRef(NaturalNumber n);

    // TODO - add test cases for four constructors, multiplyBy10, divideBy10, isZero
    @Test
    public final void testNoArgumentConstructor() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber s = this.constructorTest();
        NaturalNumber sExpected = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    @Test
    public final void testIntArgumentConstructor() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber s = this.constructorTest(123);
        NaturalNumber sExpected = this.constructorRef(123);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    @Test
    public final void testIntArgumentConstructorZero() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber s = this.constructorTest(0);
        NaturalNumber sExpected = this.constructorRef(0);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    @Test
    public final void testStringArgumentConstructor() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber s = this.constructorTest("123");
        NaturalNumber sExpected = this.constructorRef("123");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    @Test
    public final void testStringArgumentConstructorZeroInside() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber s = this.constructorTest("102030");
        NaturalNumber sExpected = this.constructorRef("102030");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    @Test
    public final void testStringArgumentConstructorZero() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber s = this.constructorTest("0");
        NaturalNumber sExpected = this.constructorRef("0");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    @Test
    public final void testNNArgumentConstructor() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber s = this.constructorTest(new NaturalNumber2(123));
        NaturalNumber sExpected = this.constructorRef(new NaturalNumber2(123));
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    @Test
    public final void testNNArgumentConstructorZero() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber s = this.constructorTest(new NaturalNumber2(0));
        NaturalNumber sExpected = this.constructorRef(new NaturalNumber2(0));
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    @Test
    public final void testMultiplyBy10() {
        /*
         * Set up variables
         */
        NaturalNumber s = this.constructorTest(123);
        NaturalNumber sExpected = this.constructorRef(1234);

        /*
         * Call method under test
         */
        s.multiplyBy10(4);

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    @Test
    public final void testMultiplyBy10Large() {
        /*
         * Set up variables
         */
        NaturalNumber s = this.constructorTest("1234567890123456789");
        NaturalNumber sExpected = this.constructorRef("12345678901234567890");

        /*
         * Call method under test
         */
        s.multiplyBy10(0);

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    @Test
    public final void testMultiplyBy10Zero() {
        /*
         * Set up variables
         */
        NaturalNumber s = this.constructorTest();
        NaturalNumber sExpected = this.constructorRef();

        /*
         * Call method under test
         */
        s.multiplyBy10(0);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    @Test
    public final void testMultiplyBy10Empty() {
        /*
         * Set up variables
         */
        NaturalNumber s = this.constructorTest();
        NaturalNumber sExpected = this.constructorRef(1);

        /*
         * Call method under test
         */
        s.multiplyBy10(1);

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    @Test
    public final void testDivideBy10() {
        /*
         * Set up variables
         */
        NaturalNumber s = this.constructorTest(123);
        NaturalNumber sExpected = this.constructorRef(12);

        int remExpected = 3;

        /*
         * Call method under test
         */
        int rem = s.divideBy10();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(remExpected, rem);
    }

    @Test
    public final void testDivideBy10Large() {
        /*
         * Set up variables
         */
        NaturalNumber s = this.constructorTest("12345678901234567890");
        NaturalNumber sExpected = this.constructorRef("1234567890123456789");

        int remExpected = 0;

        /*
         * Call method under test
         */
        int rem = s.divideBy10();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(remExpected, rem);
    }

    @Test
    public final void testDivideBy10EmptyAfter() {
        /*
         * Set up variables
         */
        NaturalNumber s = this.constructorTest(1);
        NaturalNumber sExpected = this.constructorRef();

        int remExpected = 1;

        /*
         * Call method under test
         */
        int rem = s.divideBy10();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(remExpected, rem);
    }

    @Test
    public final void testDivideBy10Empty() {
        /*
         * Set up variables
         */
        NaturalNumber s = this.constructorTest();
        NaturalNumber sExpected = this.constructorRef();

        int remExpected = 0;

        /*
         * Call method under test
         */
        int rem = s.divideBy10();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(remExpected, rem);
    }

    @Test
    public final void testIsZeroTrue() {
        /*
         * Set up variables
         */
        NaturalNumber s = this.constructorTest(0);
        NaturalNumber sExpected = this.constructorRef(0);

        boolean zeroExpected = true;

        /*
         * Call method under test
         */
        boolean zero = s.isZero();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(zeroExpected, zero);
    }

    @Test
    public final void testIsZeroFalse() {
        /*
         * Set up variables
         */
        NaturalNumber s = this.constructorTest(123);
        NaturalNumber sExpected = this.constructorRef(123);

        boolean zeroExpected = false;

        /*
         * Call method under test
         */
        boolean zero = s.isZero();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(zeroExpected, zero);
    }
}
