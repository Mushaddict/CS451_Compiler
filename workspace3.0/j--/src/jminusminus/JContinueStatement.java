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
        return null;
    }

    public void codegen(CLEmitter output) {
        //
    }

    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JContinueStatement=\"%d\">\n", line());
        p.printf("</JContinueStatement>\n");
    }

}
