// This is a generated file. Not intended for manual editing.
package ctlang.psi.impl;

import java.util.List;

import com.intellij.openapi.util.NlsSafe;
import com.intellij.util.IncorrectOperationException;
import ctlang.CTElementFactory;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static ctlang.psi.CTTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import ctlang.psi.*;
import sctlang.psi.impl.SCTPsiImplUtil;

public class CTCommandPartImpl extends ASTWrapperPsiElement implements CTCommandPart {

  public CTCommandPartImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CTVisitor visitor) {
    visitor.visitCommandPart(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CTVisitor) accept((CTVisitor)visitor);
    else super.accept(visitor);
  }

//  @Override
//  public @Nullable PsiElement getNameIdentifier() {
//    ASTNode keyNode = getNode().findChildByType(COMMAND_PART);
//    if (keyNode != null) {
//      return keyNode.getPsi();
//    } else {
//      return null;
//    }
//  }
//
//  @Override
//  public PsiElement setName(@NlsSafe @NotNull String name) throws IncorrectOperationException {
//    ASTNode keyNode = getNode().findChildByType(CTTypes.KEY);
//    if (keyNode != null) {
//
//      CTProperty property = CTElementFactory.createProperty(getProject(), name);
//      ASTNode newKeyNode = property.getFirstChild().getNode();
//      getNode().replaceChild(keyNode, newKeyNode);
//    }
//    return this;
//  }
}
