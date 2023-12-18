package sctlang;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SCTReference extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {
    private final String key;

    public SCTReference(@NotNull PsiElement element, TextRange textRange) {
        super(element, textRange);
        key = element.getText().substring(textRange.getStartOffset(), textRange.getEndOffset());
    }

    @NotNull
    @Override
    public ResolveResult @NotNull [] multiResolve(boolean incompleteCode) {
        Project project = myElement.getProject();
        final List<PsiFile> storyFiles = SCTUtil.findStoryFiles(project, key);
        List<ResolveResult> results = new ArrayList<>();
        for (PsiFile storyFile : storyFiles) {
            results.add(new PsiElementResolveResult(storyFile)); //TODO
        }
        return results.toArray(new ResolveResult[results.size()]);
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        ResolveResult[] resolveResults = multiResolve(false);
        return resolveResults.length == 1 ? resolveResults[0].getElement() : null;
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        Project project = myElement.getProject();
        List<String> storyFiles = SCTUtil.findStories(project);
        List<LookupElement> variants = new ArrayList<>();
        for (final String storyFile : storyFiles) {
            if (storyFile != null && storyFile.length() > 0) {
                variants.add(LookupElementBuilder
                        .create(storyFile).withIcon(SCTIcons.FILE)
                        .withTypeText(storyFile)
                );
            }
        }
        return variants.toArray();
    }
}
