// This is a generated file. Not intended for manual editing.
package ctlang.psi.impl;

import java.util.List;

import com.intellij.psi.PsiReference;
import com.intellij.psi.impl.source.resolve.reference.ReferenceProvidersRegistry;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static ctlang.psi.CTTypes.*;
import ctlang.psi.*;

public class CTPropertyImpl extends CTNamedElementImpl implements CTProperty {

  public CTPropertyImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull CTVisitor visitor) {
    visitor.visitProperty(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof CTVisitor) accept((CTVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  public String getKey() {
    return CTPsiImplUtil.getKey(this);
  }

  @Override
  public String getValue() {
    return CTPsiImplUtil.getValue(this);
  }

  @Override
  public String getName() {
    return CTPsiImplUtil.getName(this);
  }

  @Override
  public PsiElement setName(String newName) {
    return CTPsiImplUtil.setName(this, newName);
  }

  @Override
  public PsiElement getNameIdentifier() {
    return CTPsiImplUtil.getNameIdentifier(this);
  }

  //VERY IMPORTANT !!!!!!!!!
  //Added manually as workaround
  @Override
  public PsiReference @NotNull [] getReferences() {
    return ReferenceProvidersRegistry.getInstance().getReferencesFromProviders(this);
  }
}
