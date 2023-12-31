package sctlang;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFileFactory;
import  sctlang.psi.SCTFile;
import  sctlang.psi.SCTScriptfile;


public class SCTElementFactory {
    public static SCTScriptfile createScriptfile(Project project, String name) {
        final SCTFile file = createFile(project, name);
        return (SCTScriptfile) file.getFirstChild();
    }

    public static SCTScriptfile createScriptfile(Project project, String name, String value) {
        final SCTFile file = createFile(project, name + " = " + value);
        return (SCTScriptfile) file.getFirstChild();
    }

    public static SCTFile createFile(Project project, String text) {
        String name = "dummy.sct";
        return (SCTFile) PsiFileFactory.getInstance(project).
                createFileFromText(name, SCTFileType.INSTANCE, text);
    }

    public static PsiElement createCRLF(Project project) {
        final SCTFile file = createFile(project, "\n");
        return file.getFirstChild();
    }
}
