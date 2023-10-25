package ctlang;

import com.intellij.openapi.util.TextRange;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.util.ProcessingContext;
import ctlang.psi.CTCommand;
import  ctlang.psi.CTCommandPart;
import  ctlang.psi.CTProperty;
import  ctlang.psi.CTTag;
import org.jetbrains.annotations.NotNull;
import  proplang.PropReference;
import  proplang.psi.PropProp;
import  sctlang.SCTLanguage;
import  sctlang.STCReferenceProvider;
import  sctlang.psi.SCTScriptfile;

import static com.intellij.patterns.PlatformPatterns.psiElement;


public class CTReferenceContributor extends PsiReferenceContributor {
    @Override
    public void registerReferenceProviders(@NotNull PsiReferenceRegistrar registrar) {

        registrar.registerReferenceProvider(PlatformPatterns.psiElement(CTCommandPart.class).withLanguage(CTLanguage.INSTANCE),
                new CTReferenceProvider());

//        registrar.registerReferenceProvider(PlatformPatterns.psiElement(CTCommand.class).withLanguage(CTLanguage.INSTANCE),
//                new CTReferenceProvider());

//        registrar.registerReferenceProvider(  psiElement(SCTScriptfile.class).withLanguage(SCTLanguage.INSTANCE),
//                 new STCReferenceProvider());

        registrar.registerReferenceProvider(PlatformPatterns.psiElement(CTProperty.class).withLanguage(CTLanguage.INSTANCE),
                new PsiReferenceProvider() {
                    @NotNull
                    @Override
                    public PsiReference @NotNull [] getReferencesByElement(@NotNull PsiElement element,
                                                                           @NotNull ProcessingContext context) {
                        String value = element.getText() != null ?
                                (String) element.getText() : null;
                        if (value != null) {
                            TextRange range = new TextRange(0, value.length());
                            return new PsiReference[]{new CTPropertyReference(element, range)};
                        }
                        return PsiReference.EMPTY_ARRAY;
                    }
                });

        registrar.registerReferenceProvider(PlatformPatterns.psiElement(PropProp.class).withLanguage(CTLanguage.INSTANCE),
                new PsiReferenceProvider() {
                    @NotNull
                    @Override
                    public PsiReference @NotNull [] getReferencesByElement(@NotNull PsiElement element,
                                                                           @NotNull ProcessingContext context) {
                        String value = element.getText() != null ?
                                (String) element.getText() : null;
                        if (value != null) {
                            TextRange range = new TextRange(0,
                                    value.length());
                            return new PsiReference[]{new PropReference(element, range)};
                        }
                        return PsiReference.EMPTY_ARRAY;
                    }
                });

        registrar.registerReferenceProvider(PlatformPatterns.psiElement(CTTag.class).withLanguage(CTLanguage.INSTANCE),
                new PsiReferenceProvider() {
                    @NotNull
                    @Override
                    public PsiReference[] getReferencesByElement(@NotNull PsiElement element,
                                                                 @NotNull ProcessingContext context) {
                        String value = element.getText() instanceof String ?
                                (String) element.getText() : null;
                        if (value != null) {
                            TextRange range = new TextRange(0,
                                    value.length());
                            return new PsiReference[]{new CTTagReference(element, range)};
                        }
                        return PsiReference.EMPTY_ARRAY;
                    }
                });
    }
}
