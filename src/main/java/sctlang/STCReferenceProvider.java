package sctlang;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiReferenceProvider;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import  sctlang.psi.SCTScriptfile;

public class STCReferenceProvider extends PsiReferenceProvider {

        @NotNull
        @Override
        public PsiReference @NotNull [] getReferencesByElement(@NotNull PsiElement element,
                                                               @NotNull ProcessingContext context) {
            SCTScriptfile literalExpression = (SCTScriptfile) element;
            String value = literalExpression.getValue() != null ?
                    (String) literalExpression.getValue() : null;
            if (value != null) {
                TextRange textRange = new TextRange( 0,
                        value.length() );
                return new PsiReference[]{new SCTReference(element, textRange)};
            }
            return PsiReference.EMPTY_ARRAY;
        }
}
