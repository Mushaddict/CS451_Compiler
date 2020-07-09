package jminusminus;

import static jminusminus.CLConstants.*;
import jminusminus.CLEmitter;
import java.util.ArrayList;

class JSwitchStatement extends JStatement {

    private JExpression parExpression;
    private ArrayList<JSwitchBlockStatement> switchBlockStatement;

    public JSwitchStatement(int line,
                            JExpression parExpression,
                            ArrayList<JSwitchBlockStatement> switchBlockStatement) {
        super(line);
        this.parExpression = parExpression;
        this.switchBlockStatement = switchBlockStatement;
    }

    public JSwitchStatement analyze(Context context) {
        return null;
    }

    public void codegen(CLEmitter output) {

    }

    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JSwitchStatement line=\"%d\">\n", line());
        p.indentRight();

        p.printf("<TestExpression>\n");
        p.indentRight();
        parExpression.writeToStdOut(p);
        p.indentLeft();
        p.printf("</TestExpression>\n");

        for (JSwitchBlockStatement blocks: switchBlockStatement) {
            p.printf("<SwitchBlockStatementGroup>\n");
            p.indentRight();
            blocks.writeToStdOut(p);
            p.indentLeft();
            p.printf("</SwitchBlockStatementGroup>\n");
        }

        p.indentLeft();
        p.printf("</JSwitchStatement>\n");
    }

}

class JSwitchBlockStatement extends JAST {

    private ArrayList<JExpression> switchLabel;
    private ArrayList<JStatement> blockStatement;
//    private ArrayList<JStatement> defaultStatement;

    public JSwitchBlockStatement(int line,
                                 ArrayList<JExpression> switchLabel,
                                 ArrayList<JStatement> blockStatement) {
        super(line);
        this.switchLabel = switchLabel;
        this.blockStatement = blockStatement;

    }

    public JAST analyze(Context context) {
        return null;
    }

    public void codegen(CLEmitter output) {

    }

    public void writeToStdOut(PrettyPrinter p) {

        for (JExpression expression : switchLabel) {
            if (expression != null) {
                p.printf("<CaseLabel>\n");
                p.indentRight();
                expression.writeToStdOut(p);
                p.indentLeft();;
                p.printf("</CaseLabel>\n");
            }
        }

        for (JStatement block: blockStatement) {
            p.printf("<Body>");
            p.indentRight();
            block.writeToStdOut(p);
            p.indentLeft();
            p.printf("</Body>\n");
        }

    }

}

class JDefaultStatement extends JStatement {

    public JDefaultStatement(int line) {
        super(line);
    }

    public JAST analyze(Context context) {
        return null;
    }

    public void codegen(CLEmitter output) {

    }

    public void writeToStdOut(PrettyPrinter p) {
        p.printf("</DefaultLabel>\n");
    }

}