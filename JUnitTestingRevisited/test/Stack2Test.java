import components.stack.Stack;
import components.stack.Stack2;
import components.stack.Stack3;

/**
 * Customized JUnit test fixture for {@code Stack1L}.
 */
public class Stack2Test extends StackTest {

    @Override
    protected final Stack<String> constructorTest() {

        Stack<String> s1 = new Stack2<String>();

        return s1;
    }

    @Override
    protected final Stack<String> constructorRef() {

        Stack<String> s1 = new Stack3<String>();

        return s1;
    }

}