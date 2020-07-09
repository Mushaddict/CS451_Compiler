package jminusminus;

import static jminusminus.CLConstants.*;
import jminusminus.CLEmitter;

class JContinueStatement extends JStatement {

    private String identifier;

    public JContinueStatement(int line, String identifier) {
        super(line);
        this.identifier = identifier;
    }

    public JAST analyze(Context context) {
        return this;
    }

    public void codegen(CLEmitter output) {
        String start = output.createLabel();
        String end = output.createLabel();

        output.addLabel(start);

        output.addBranchInstruction(GOTO, start);

        output.addLabel(end);
    }

    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JContinueStatement line=\"%d\">\n", line());
        p.printf("</JContinueStatement>\n");
    }

}
