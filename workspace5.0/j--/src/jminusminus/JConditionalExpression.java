package jminusminus;
import static jminusminus.CLConstants.*;

class JConditionalExpression extends JExpression {

    private JExpression condition;
    /** if yes, return... */
    private JExpression yesReturn;
    /** if no, return... */
    private JExpression noReturn;

    public JConditionalExpression(int line, JExpression condition,
           JExpression yesReturn, JExpression noReturn) {
        super(line);
        this.condition = condition;
        this.yesReturn = yesReturn;
        this.noReturn = noReturn;
    }

    public JExpression analyze(Context context) {
        condition = (JExpression)condition.analyze(context);
        condition.type().mustMatchExpected(line(), Type.BOOLEAN);
        yesReturn = (JExpression)yesReturn.analyze(context);
        noReturn = (JExpression)noReturn.analyze(context);
        type = yesReturn.type();
        noReturn.type().mustMatchExpected(line(), yesReturn.type());
        return this;
    }

    public void codegen(CLEmitter output) {
        String elseLabel = output.createLabel();
        String end = output.createLabel();
        condition.codegen(output, elseLabel, false);
        yesReturn.codegen(output);
        output.addBranchInstruction(GOTO, end);
        output.addLabel(elseLabel);
        noReturn.codegen(output);
        output.addLabel(end);
    }

    /**
     * {@inheritDoc}
     * */

    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JConditionalExpression line=\"%d\" type=\"%s\" " +
                "operator=\"%s\">\n", line(), ((type == null) ? "" :
                type.toString()), "?");
        p.indentRight();

        p.printf("<TestExpression>\n");
        p.indentRight();
        condition.writeToStdOut(p);
        p.indentLeft();
        p.printf("</TestExpression>\n");

        p.printf("<TrueClause>\n");
        p.indentRight();
        yesReturn.writeToStdOut(p);
        p.indentLeft();
        p.printf("</TrueClause>\n");

        if (noReturn != null) {
            p.printf("<FalseClause>\n");
            p.indentRight();
            noReturn.writeToStdOut(p);
            p.indentLeft();
            p.printf("</FalseClause>\n");
        }
        p.indentLeft();
        p.printf("</JConditionalExpression>\n");
    }

}