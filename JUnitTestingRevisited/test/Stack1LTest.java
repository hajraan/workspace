import components.stack.Stack;
import components.stack.Stack1L;
import components.stack.Stack3;

/**
 * Customized JUnit test fixture for {@code Stack1L}.
 */
public class Stack1LTest extends StackTest {

    @Override
    protected final Stack<String> constructorTest() {

        Stack<String> s1 = new Stack1L<String>();

        return s1;
    }

    @Override
    protected final Stack<String> constructorRef() {

        Stack<String> s1 = new Stack3<String>();

        return s1;
    }

}