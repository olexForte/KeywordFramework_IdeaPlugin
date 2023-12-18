package ctlang;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiReferenceProvider;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

public class CTTagReferenceProvider extends PsiReferenceProvider {
    @NotNull
    @Override
    public PsiReference @NotNull [] getReferencesByElement(@NotNull PsiElement element, @NotNull ProcessingContext context) {
        String value = element.getText() != null ?
                (String) element.getText() : null;
        System.out.println(value);
        if (value != null) {
            TextRange range = new TextRange(0,
                    value.length());
            return new PsiReference[]{new CTTagReference(element, range)};
        }
        return PsiReference.EMPTY_ARRAY;
    }
}
