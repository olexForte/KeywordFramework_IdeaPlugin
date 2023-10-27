// This is a generated file. Not intended for manual editing.
package ctlang.psi.impl;

import java.util.List;

import com.intellij.openapi.util.NlsSafe;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static ctlang.psi.CTTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import ctlang.psi.*;
import proplang.PropElementFactory;
import proplang.psi.PropProp;
import proplang.psi.PropTypes;

public class CTTagImpl extends ASTWrapperPsiElement implements CTTag {

  public CTTagImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CTVisitor visitor) {
    visitor.visitTag(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CTVisitor) accept((CTVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  public @Nullable PsiElement getNameIdentifier() {
    ASTNode keyNode = getNode().findChildByType(PropTypes.KEY);
    if (keyNode != null) {
      return keyNode.getPsi();
    } else {
      return null;
    }
  }

  @Override
  public PsiElement setName(@NlsSafe @NotNull String name) throws IncorrectOperationException {
    ASTNode keyNode = getNode().findChildByType(PropTypes.KEY);
    if (keyNode != null) {

      PropProp property = PropElementFactory.createProperty(getProject(), name);
      ASTNode newKeyNode = property.getFirstChild().getNode();
      getNode().replaceChild(keyNode, newKeyNode);
    }
    return this;
  }
}
