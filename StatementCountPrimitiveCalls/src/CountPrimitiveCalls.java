import components.statement.Statement;
import components.statement.StatementKernel.Condition;

/**
 * Utility class with method to count the number of calls to primitive
 * instructions (move, turnleft, turnright, infect, skip) in a given
 * {@code Statement}.
 *
 * @author Hajraan Hussain && Oybek Kamolov
 *
 */
public final class CountPrimitiveCalls {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CountPrimitiveCalls() {
    }

    /**
     * Reports the number of calls to primitive instructions (move, turnleft,
     * turnright, infect, skip) in a given {@code Statement}. *
     *
     * @param s
     *            the {@code Statement}
     * @return the number of calls to primitive instructions in {@code s}
     * @ensures <pre>
     * countOfPrimitiveCalls =
     * [number of calls to primitive instructions in s]
     * </pre>
     */
    public static int countOfPrimitiveCalls(Statement s) {
        int count = 0;
        switch (s.kind()) {
            case BLOCK: { /*
                           * Add up the number of calls to primitive
                           * instructions * in each nested statement in the
                           * BLOCK.
                           */
                for (int i = 0; i < s.lengthOfBlock(); i++) {
                    Statement sl = s.removeFromBlock(i);
                    count += countOfPrimitiveCalls(sl);
                    s.addToBlock(i, sl);
                }
                break;
            }
            case IF: { /*
                        * Find the number of calls to primitive instructions in
                        * * the body of the
                        */
                Statement sl = s.newInstance();
                Condition condition = s.disassembleIf(sl);
                count += countOfPrimitiveCalls(sl);
                s.assembleIf(condition, sl);
                break;
            }

            case IF_ELSE: { /*
                             * Add up the number of calls to primitive
                             * instructions in * the "then" and "else" bodies of
                             * the IF_ELSE.
                             */

                Statement slIf = s.newInstance();
                Statement slElse = s.newInstance();
                Condition condition = s.disassembleIfElse(slIf, slElse);
                count += countOfPrimitiveCalls(slIf)
                        + countOfPrimitiveCalls(slElse);
                s.assembleIfElse(condition, slIf, slElse);
                break;
            }
            case WHILE: {
                /*
                 * Find the number of calls to primitive instructions in * the
                 * body of the WHILE.
                 */
                Statement sl = s.newInstance();
                Condition condition = s.disassembleWhile(sl);
                count += countOfPrimitiveCalls(sl);
                s.assembleWhile(condition, sl);
                break;
            }
            case CALL: { /*
                          * This is a leaf: the count can only be 1 or 0.
                          * Determine * whether this is a call to a primitive
                          * instruction or not.
                          */
                String call = s.disassembleCall();
                if (call.equals("move") || call.equals("turnright")
                        || call.equals("turnleft") || call.equals("infect")
                        || call.equals("skip")) {

                    count++;
                }
                s.assembleCall(call);
                break;
            }
            default: {
                // this will never happen...can you explain why? No
                break;
            }
        }
        return count;
    }
}