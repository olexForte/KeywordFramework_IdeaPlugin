// This is a generated file. Not intended for manual editing.
package sctlang.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;
import  sctlang.SCTNamedElement;

public class SCTVisitor extends PsiElementVisitor {

  public void visitScriptfile(@NotNull SCTScriptfile o) {
    visitNamedElement(o);
  }

  public void visitNamedElement(@NotNull SCTNamedElement o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
