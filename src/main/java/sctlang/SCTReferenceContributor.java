package sctlang;

import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import ctlang.CTReferenceProvider;
import ctlang.psi.CTCommandPart;
import org.jetbrains.annotations.NotNull;
import  sctlang.psi.SCTScriptfile;

import static com.intellij.patterns.PlatformPatterns.psiElement;


public class SCTReferenceContributor extends PsiReferenceContributor {

    @Override
    public void registerReferenceProviders(@NotNull PsiReferenceRegistrar registrar) {

        registrar.registerReferenceProvider(  psiElement(SCTScriptfile.class).withLanguage(SCTLanguage.INSTANCE),
                 new STCReferenceProvider());
    }
}
