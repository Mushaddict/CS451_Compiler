package jminusminus;

import static jminusminus.CLConstants.*;
import jminusminus.CLEmitter;
import java.util.ArrayList;

class JThrowStatement extends JStatement {

    private JExpression condition;

    public JThrowStatement(int line, JExpression condtion) {
        super(line);
        this.condition = condtion;
    }

    public JThrowStatement analyze(Context context) {
        return null;
    }

    public void codegen(CLEmitter output) {

    }

    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JThrowStatement>\n");
        p.indentRight();
        condition.writeToStdOut(p);
        p.indentLeft();
        p.printf("</JThrowStatement>\n");
    }

}