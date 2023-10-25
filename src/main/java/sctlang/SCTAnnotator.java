package sctlang;

import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;

import org.jetbrains.annotations.NotNull;
import  sctlang.psi.SCTScriptfile;

import java.util.List;


public class SCTAnnotator implements Annotator {

    @Override
    public void annotate(@NotNull final PsiElement element, @NotNull AnnotationHolder holder) {

        if (!(element instanceof SCTScriptfile)) return;
        String value = element.getText();
        if ((value == null)) return;

        TextRange keyRange = null;
        try {
             keyRange = new TextRange(element.getTextRange().getStartOffset(), element.getTextRange().getEndOffset() );
        }catch(Exception e){
            System.out.println();

        }
        // Get the list of properties from the Project
        String possibleProperties = value;//.substring(CT_PREFIX_STR.length() + CT_SEPARATOR_STR.length());
        Project project = element.getProject();
        List<String> properties = SCTUtil.findStories(project, possibleProperties);

        // Set the annotations using the text ranges.
        holder.newAnnotation(HighlightSeverity.INFORMATION, "Found").range(keyRange).textAttributes(DefaultLanguageHighlighterColors.KEYWORD).create();

        if (properties.isEmpty()) {
            // No well-formed property found following the key-separator
            holder.newAnnotation(HighlightSeverity.INFORMATION, "File not found").range(keyRange).textAttributes(SCTSyntaxHighlighter.BAD_CHARACTER).newFix(new SCTCreateStoryfileQuickFix(possibleProperties)).registerFix().create();
//            Annotation badProperty = holder.createErrorAnnotation(keyRange, "File not found");
//            badProperty.setTextAttributes(SCTSyntaxHighlighter.BAD_CHARACTER);
//            // ** Tutorial step 18.3 - Add a quick fix for the string containing possible properties
//            badProperty.registerFix(new SCTCreateStoryfileQuickFix(possibleProperties));
        } else {
            // Found at least one property
            holder.newAnnotation(HighlightSeverity.INFORMATION, "Found").range(keyRange).textAttributes(SCTSyntaxHighlighter.VALUE).create();
        }
    }

}

