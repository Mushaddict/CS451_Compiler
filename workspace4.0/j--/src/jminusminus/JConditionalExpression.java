package jminusminus;

class JConditionalExpression extends JExpression {

    private JExpression condition;
    /** if yes, return... */
    private JStatement yesReturn;
    /** if no, return... */
    private JStatement noReturn;

    public JConditionalExpression(int line, JExpression condition,
           JStatement yesReturn, JStatement noReturn) {
        super(line);
        this.condition = condition;
        this.yesReturn = yesReturn;
        this.noReturn = noReturn;
    }

    public JExpression analyze(Context context) {
        // from piazza @84
        return null;
    }

    public void codegen(CLEmitter output) {
        //...
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