package sctlang.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import  sctlang.SCTFileType;
import  sctlang.SCTLanguage;
import org.jetbrains.annotations.NotNull;

public class SCTFile extends PsiFileBase {
    public SCTFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, SCTLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return SCTFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "SCT File";
    }
}