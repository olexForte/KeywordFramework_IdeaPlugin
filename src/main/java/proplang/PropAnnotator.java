package proplang;

import com.intellij.lang.annotation.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;

import ctlang.CTSyntaxHighlighter;
import  ctlang.psi.CTProperty;
import  proplang.psi.PropProp;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;

import java.util.List;


public class PropAnnotator implements Annotator {
    // Define strings for the CT language prefix - used for annotations, line markers, etc.
    public static final String CT_PREFIX_STR = "ct";
    public static final String CT_SEPARATOR_STR = ":";

    @Override
    public void annotate(@NotNull final PsiElement element, @NotNull AnnotationHolder holder) {
        // Ensure the Psi Element is an expression
        //if (!(element instanceof PsiLiteralExpression)) return;
        if (!(element instanceof PropProp)) return;
        // Ensure the Psi element contains a string that starts with the key and separator
        //PsiLiteralExpression literalExpression = (PsiLiteralExpression) element;
        String value = element.getText(); //literalExpression.getValue() instanceof String ? (String) literalExpression.getValue() : null;
        //if ((value == null) || !value.startsWith(CT_PREFIX_STR + CT_SEPARATOR_STR)) return;
        if ((value == null)) return;
        // Define the text ranges (start is inclusive, end is exclusive)
        // "ct:key"
        //  01234567890
        //TextRange prefixRange = TextRange.from(element.getTextRange().getStartOffset(), CT_PREFIX_STR.length() + 1);
        //TextRange separatorRange = TextRange.from(prefixRange.getEndOffset(), CT_SEPARATOR_STR.length());
        TextRange keyRange = null;
        keyRange = new TextRange(element.getTextRange().getStartOffset(), element.getTextRange().getEndOffset() );

        // Get the list of properties from the Project
        String valuePart = value;//.substring(CT_PREFIX_STR.length() + CT_SEPARATOR_STR.length());
        Project project = element.getProject();
        List<CTProperty> properties = PropUtil.findProperties(project, valuePart, element.getContainingFile());

        if (properties.isEmpty()) {
            // No well-formed property found following the key-separator
            holder.newAnnotation(HighlightSeverity.WARNING, "File not found").range(keyRange).textAttributes(CTSyntaxHighlighter.BAD_CHARACTER).newFix(new PropCreatePropertyQuickFix(valuePart)).registerFix().create();

            //Annotation badProperty = holder.createErrorAnnotation(keyRange, "File not found");
            //badProperty.setTextAttributes(PropSyntaxHighlighter.BAD_CHARACTER);
            // ** Tutorial step 18.3 - Add a quick fix for the string containing possible properties
            //badProperty.registerFix(new PropCreatePropertyQuickFix(valuePart));
        } else {
            // Found at least one property
            holder.newAnnotation(HighlightSeverity.INFORMATION, "Found").range(keyRange).textAttributes(PropSyntaxHighlighter.VALUE).create();
        }
    }

}

