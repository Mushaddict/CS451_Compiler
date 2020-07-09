package jminusminus;

import static jminusminus.CLConstants.*;
import jminusminus.CLEmitter;
import java.util.ArrayList;

class JForStatement extends JStatement {

    ArrayList<JStatement> forInit;
    JExpression expression;
    ArrayList<JStatement> forUpdate;
    JStatement statement;
    private LocalContext context;

    public JForStatement (int line, ArrayList<JStatement> forInit,
                          JExpression expression,
                          ArrayList<JStatement> forUpdate,
                          JStatement statement) {
        super(line);
        this.forInit = forInit;
        this.expression = expression;
        this.forUpdate = forUpdate;
        this.statement = statement;
    }

    public JForStatement analyze(Context context) {

        this.context = new LocalContext(context);
        if (forInit != null) {
            for (JStatement init : forInit) {
                init.analyze(this.context);
            }
        }
        expression = (JExpression)expression.analyze(this.context);
        expression.type().mustMatchExpected(line(), Type.BOOLEAN);
        if (forUpdate != null) {
            for (JStatement update : forUpdate) {
                update.analyze(this.context);
            }
        }
        statement = (JStatement)statement.analyze(this.context);

        return this;
    }

    public void codegen(CLEmitter output) {
        String test = output.createLabel();
        String out = output.createLabel();

        if (forInit != null) {
            for (JStatement init : forInit) {
                init.codegen(output);
            }
        }
        output.addLabel(test);

        expression.codegen(output, out, false);

        statement.codegen(output);

        if (forUpdate != null) {
            for (JStatement update : forUpdate) {
                update.codegen(output);
            }
        }
        output.addBranchInstruction(GOTO, test);
        output.addLabel(out);
    }

    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JForStatement line=\"%d\">\n", line());
        p.indentRight();

        //forinit part
        p.printf("<InitialExpression>\n");
        p.indentRight();
        if (!forInit.isEmpty()) {
            for (JStatement forinit : forInit) {
                forinit.writeToStdOut(p);
            }
        }
        p.indentLeft();
        p.printf("</InitialExpression>\n");

        //expression part
        p.printf("<TestExpression>\n");
        p.indentRight();
        if(expression != null) {
            expression.writeToStdOut(p);
        }
        p.indentLeft();
        p.printf("</TestExpression>\n");

        //forUpdate
        p.printf("<UpdateExpression>\n");
        p.indentRight();
        if (!forUpdate.isEmpty()) {
            for (JStatement forupdate : forUpdate) {
                forupdate.writeToStdOut(p);
            }
        }
        p.indentLeft();
        p.printf("</UpdateExpression>\n");

        p.indentLeft();
        p.printf("</JForStatement>\n");

        p.printf("<Statement>\n");
        p.indentRight();
        statement.writeToStdOut(p);
        p.indentLeft();
        p.printf("</Statement>\n");

    }

}

//This class is used to output the JVariableDeclaration inside forInit part.
class JForInitDeclaration extends JStatement {

    private ArrayList<JVariableDeclarator> declarators;
    private LocalContext context;

    public JForInitDeclaration(int line, ArrayList<JVariableDeclarator> declarators) {
        super(line);
        this.declarators = declarators;
    }

    public JForInitDeclaration analyze(Context context) {
//        this.context = new LocalContext(context);
//        for (JVariableDeclarator decl : declarators) {
//            decl.analyze(this.context);
//        }
        return this;
    }

    public void codegen (CLEmitter output) {
//        for (JVariableDeclarator decl : declarators) {
//            decl.codegen(output);
//        }
    }

    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JVariableDeclaration>\n");
        p.indentRight();

        p.printf("<Modifiers>\n");
        p.printf("</Modifiers>\n");

        p.printf("<VariableDeclarators>\n");
        for (JVariableDeclarator dec : declarators) {
            p.indentRight();
            dec.writeToStdOut(p);
            p.indentLeft();
        }
        p.printf("</VariableDeclarators>\n");

        p.indentLeft();
        p.printf("</JVariableDeclaration>\n");
    }

}