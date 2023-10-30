package ctlang;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiReferenceProvider;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import sctlang.SCTReference;
import sctlang.psi.SCTScriptfile;

public class CTReferenceProvider extends PsiReferenceProvider {
    @NotNull
    @Override
    public PsiReference @NotNull [] getReferencesByElement(@NotNull PsiElement element, @NotNull ProcessingContext context) {
                String value = element.getText() != null ?
                    (String) element.getText() : null;
            if (value != null) {
                TextRange range;
                if(element.getText().contains("Optional "))
                    range = new TextRange(9, value.length());
                else if(element.getText().contains("Final "))
                    range = new TextRange(6, value.length());
                else if(element.getText().contains("Initial "))
                    range = new TextRange(8, value.length());
                else range = new TextRange(0, value.length());
                return new PsiReference[]{new CTCommandReference(element, range)};
            }
            return PsiReference.EMPTY_ARRAY;
        }


//        @NotNull
//        @Override
//        public PsiReference[] getReferencesByElement(@NotNull PsiElement element,
//                @NotNull ProcessingContext context) {
//            SCTScriptfile literalExpression = (SCTScriptfile) element;
//            String value = literalExpression.getValue() instanceof String ?
//                    (String) literalExpression.getValue() : null;
//            if ((value != null && value.startsWith(""))) {
//                TextRange property = new TextRange( 0,
//                        value.length() );
//                return new PsiReference[]{new SCTReference(element, property)};
//            }
//            return PsiReference.EMPTY_ARRAY;
//        }
}
