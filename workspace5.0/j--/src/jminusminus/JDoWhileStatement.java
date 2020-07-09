package jminusminus;

import static jminusminus.CLConstants.*;
import jminusminus.CLEmitter;

class JDoWhileStatement extends JStatement {

    /** Test expression. */
    private JExpression condition;
    /** The body. */
    private JStatement body;

    /**
     * Constructs an AST node for a while-statement given its line number, the
     * test expression, and the body.
     */
    public JDoWhileStatement(int line, JExpression condition, JStatement body) {
        super(line);
        this.condition = condition;
        this.body = body;
    }

    /**
     * Analysis involves analyzing the test, checking its type and analyzing the
     * body statement.
     *
     * @param context
     *            context in which names are resolved.
     * @return the analyzed (and possibly rewritten) AST subtree.
     */
    public JDoWhileStatement analyze(Context context) {
        condition = condition.analyze(context);
        condition.type().mustMatchExpected(line(), Type.BOOLEAN);
        body = (JStatement) body.analyze(context);
        return this;
    }

    public void codegen(CLEmitter output) {
        // Need two labels
        String test = output.createLabel();
        String out = output.createLabel();

        // Branch out of the loop on the test condition
        // being false
        output.addLabel(test);
        condition.codegen(output, out, false);

        // Codegen body
        body.codegen(output);

        // Unconditional jump back up to test
        output.addBranchInstruction(GOTO, test);

        // The label below and outside the loop
        output.addLabel(out);
    }

    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JDoWhileStatement line=\"%d\">\n", line());
        p.indentRight();

        p.printf("<Body>\n ");
        p.indentRight();
        body.writeToStdOut(p);
        p.indentLeft();
        p.printf("</Body>\n");

        p.printf("<TestExpression>\n");
        p.indentRight();
        condition.writeToStdOut(p);
        p.indentLeft();
        p.printf("</TestExpression>\n");

        p.indentLeft();
        p.printf("</JDoWhileStatement>\n");
    }


}