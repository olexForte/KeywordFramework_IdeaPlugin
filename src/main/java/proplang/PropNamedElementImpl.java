package proplang;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiReference;
import com.intellij.psi.impl.source.resolve.reference.ReferenceProvidersRegistry;
import org.jetbrains.annotations.NotNull;

public abstract class PropNamedElementImpl extends ASTWrapperPsiElement implements PropNamedElement {
        public PropNamedElementImpl(@NotNull ASTNode node) {
            super(node);
        }

    @Override
    public PsiReference[] getReferences() {
        return ReferenceProvidersRegistry.getInstance().getReferencesFromProviders(this);
    }
    }
