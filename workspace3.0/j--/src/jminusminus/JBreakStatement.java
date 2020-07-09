package jminusminus;

import static jminusminus.CLConstants.*;
import jminusminus.CLEmitter;

class JBreakStatement extends JStatement {

    private String identifier;

    public JBreakStatement(int line, String identifier) {
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
        p.printf("<JBreakStatement=\"%d\">\n", line());
        p.printf("</JBreakStatement>\n");
    }

}