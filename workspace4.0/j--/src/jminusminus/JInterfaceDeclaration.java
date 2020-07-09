package jminusminus;

import static jminusminus.CLConstants.*;
import jminusminus.CLEmitter;
import java.util.ArrayList;

class JInterfaceDeclaration extends JAST implements JTypeDecl, JMember {

    private String name;
    private ArrayList<String> mods;
    private ArrayList<TypeName> extend;
    private ArrayList<JMember> interfaceBlock;

    private ArrayList<JFieldDeclaration> instanceFieldInitialization;

    private Context context;
    private Type thisType;
    private Type superType;

    public JInterfaceDeclaration(int line, ArrayList<String> mode,
                                 String name, ArrayList<TypeName> extend,
                                 ArrayList<JMember> interfaceBlock) {
        super(line);
        this.mods = mods;
        this.name = name;
        this.extend = extend;
        this.interfaceBlock = interfaceBlock;
        instanceFieldInitialization = new ArrayList<JFieldDeclaration>();
    }

    public String name() {
        return name;
    }

    public Type superType() {
        return superType;
    }

    public Type thisType() {
        return thisType;
    }

    public void declareThisType(Context context) {

    }

    public void preAnalyze(Context context, CLEmitter partial) {

    }

    public void preAnalyze(Context context) {

    }

    public JAST analyze(Context context) {
        return this;
    }

    public void codegen(CLEmitter output) {

    }

    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JInterfaceDeclaration line=\"%d\" name=\"%s\">\n", line(), name);
        p.indentRight();
        if (context != null) {
            context.writeToStdOut(p);
        }
        if (mods != null) {
            p.printf("<Modifier>\n");
            p.indentRight();
            for (String mod : mods) {
                p.printf("<Modifier name=\"%s\"/>\n", mod);
            }
            p.indentLeft();
            p.printf("</Modifier>\n");
        }
        if (extend != null) {
            p.printf("<Extends>\n");
            p.indentRight();
            for (TypeName extend : extend) {
                p.printf("<Extends name=\"%s\"/>\n", extend.toString());
            }
            p.indentLeft();
            p.printf("</Extends>\n");
        }
        if (interfaceBlock != null) {
            p.printf("<InterfaceBlock>\n");
            for (JMember member : interfaceBlock) {
                ((JAST) member).writeToStdOut(p);
            }
            p.printf("</InterfaceBlock>\n");
        }

        p.indentLeft();
        p.printf("</JInterfaceDeclaration>\n");
    }



}

