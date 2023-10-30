package ctlang;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
//import  ctlang.CTCreatePropertyQuickFix;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiLiteralExpression;
import  ctlang.psi.*;
import org.jetbrains.annotations.NotNull;
import  proplang.psi.PropProp;

import java.util.List;


public class CTAnnotator implements Annotator {

    @Override
    public void annotate(@NotNull final PsiElement element, @NotNull AnnotationHolder holder) {
        // Ensure the Psi Element is an expression
        //if (!(element instanceof PsiLiteralExpression)) return;

        if (element instanceof CTTag) {
            System.out.println("TAG - " + element.getText());
            processTag(element, holder);
            return;
        }

        if (element instanceof CTCommandPart) {
            System.out.println("CommandPart - " + element.getText());
            processCommand(element, holder);
            return;
        }

        if ((element instanceof CTProperty)) {
            System.out.println("Property - " + element.getText());
            processProperty(element, holder);
            return;
        }

//        if (element.getNode().getElementType().toString().equals("CTTokenType.KEY")){
//            processPropertyKey(element, holder);
//            return;
//        }

        if (element instanceof CTCommand){
            System.out.println("Command - " + element.getText());
            return;
        }

    }

    private void processProperty(PsiElement element, AnnotationHolder holder) {
        String value = element.getText(); //literalExpression.getValue() instanceof String ? (String) literalExpression.getValue() : null;

        if ((value == null)) return;
        TextRange keyRange = new TextRange(element.getTextRange().getStartOffset(), element.getTextRange().getEndOffset());

        Project project = element.getProject();

        if( value.toUpperCase().startsWith(CTUtil.GLOBAL_FILE_NAME)
                || value.toUpperCase().startsWith(CTUtil.RANDOM_FILE_NAME)
                || value.toUpperCase().startsWith(CTUtil.SAVED_FILE_NAME)
                || !value.contains(".")) {
            holder.newAnnotation(HighlightSeverity.INFORMATION, "Found").range(keyRange).textAttributes(CTSyntaxHighlighter.VALUE).create();
        }else {

            List<PropProp> properties = CTUtil.findProperties(project, element);

            if (properties.isEmpty()) {
                // No well-formed property found following the key-separator
                holder.newAnnotation(HighlightSeverity.WARNING, "Property not found").range(keyRange).textAttributes(CTSyntaxHighlighter.BAD_CHARACTER).create();
            } else {
                // Found at least one property
                holder.newAnnotation(HighlightSeverity.INFORMATION, "Found").range(keyRange).textAttributes(CTSyntaxHighlighter.VALUE).create();
            }
        }
    }

    private void processCommand(PsiElement element, AnnotationHolder holder) {
        String value = element.getText()
                .replace("Optional ", "")
                .replace("Final ", "")
                .replace("Initial ", "");

        if ((value == null)) return;
        TextRange keyRange = new TextRange(element.getTextRange().getStartOffset(), element.getTextRange().getEndOffset());

        String possibleProperties = value.trim();
        Project project = element.getProject();
        List<PsiLiteralExpression> commands =  CTUtil.findCommands(project, possibleProperties);
        List<PsiFile> commandsFromFiles = CTUtil.findActionFiles(project, possibleProperties.replace(" ", "_"));

        if (commands.isEmpty() && commandsFromFiles.isEmpty()) {
            // No well-formed property found following the key-separator
            holder.newAnnotation(HighlightSeverity.ERROR, "Command/Action not found").range(keyRange).textAttributes(CTSyntaxHighlighter.BAD_CHARACTER).create();
        } else {
            // Found at least one property
            holder.newAnnotation(HighlightSeverity.INFORMATION, "Found").range(keyRange).textAttributes(CTSyntaxHighlighter.KEY).create();
        }
    }

    private void processTag(PsiElement element, AnnotationHolder holder){
        String value = element.getText().replace(",","");

        if ((value == null)) return;
        TextRange keyRange = new TextRange(element.getTextRange().getStartOffset(), element.getTextRange().getEndOffset());

        String possibleProperties = value.trim();
        Project project = element.getProject();
        List<String> tags =  CTUtil.findTags(project, possibleProperties);

        if (tags.isEmpty()) {
            // No well-formed property found following the key-separator
            holder.newAnnotation(HighlightSeverity.ERROR, "Tag not found").range(keyRange).textAttributes(CTSyntaxHighlighter.BAD_CHARACTER).create();
        } else {
            // Found at least one property
            holder.newAnnotation(HighlightSeverity.INFORMATION, "Property not found").range(keyRange).textAttributes(CTSyntaxHighlighter.TAGS).create();
        }
    }
}

