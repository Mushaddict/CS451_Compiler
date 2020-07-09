package jminusminus;

import static jminusminus.CLConstants.*;
import jminusminus.CLEmitter;
import java.util.ArrayList;

class JTryCatchFinallyStatement extends JStatement {

    private JStatement tryBlock;
    //several catches can put in one try
    private ArrayList<JStatement> catches;
    //thus will have several exception e's
    private ArrayList<JFormalParameter> exceptions;
    //[finally] statement
    private JStatement finallyStatement;

    //condition contains finally
    public JTryCatchFinallyStatement(int line,
                                     JStatement tryBlock,
                                     ArrayList<JStatement> catches,
                                     ArrayList<JFormalParameter> exceptions,
                                     JStatement finallyStatement) {
        super(line);
        this.tryBlock = tryBlock;
        this.catches = catches;
        this.exceptions = exceptions;
        this.finallyStatement = finallyStatement;
    }

    //condition doesn't contain finally
    public JTryCatchFinallyStatement(int line,
                                     JStatement tryBlock,
                                     ArrayList<JStatement> catches,
                                     ArrayList<JFormalParameter> exceptions) {
        super(line);
        this.tryBlock = tryBlock;
        this.catches = catches;
        this.exceptions = exceptions;
        this.finallyStatement = null;
    }

    public JStatement analyze(Context context) {
        return null;
    }

    public void codegen(CLEmitter output) {

    }

    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JTryCatchFinallyStatement line=\"%d\">\n", line());
        p.indentRight();

        p.printf("<TryBlock>\n");
        p.indentRight();
        tryBlock.writeToStdOut(p);
        p.indentLeft();
        p.printf("</TryBlock>\n");

        for (int i = 0; i < catches.size(); i++) {
            p.printf("<CatchBlock>\n");
            p.indentRight();
            exceptions.get(i).writeToStdOut(p);
            catches.get(i).writeToStdOut(p);
            p.indentLeft();
            p.printf("</CatchBlock>\n");
        }

        if (finallyStatement != null) {
            p.printf("<FinallyBlock>\n");
            p.indentRight();
            finallyStatement.writeToStdOut(p);
            p.indentLeft();
            p.printf("</FinallyBlock>\n");
        }

        p.indentLeft();
        p.printf("<JTryCatchFinallyStatement>\n");
    }

}