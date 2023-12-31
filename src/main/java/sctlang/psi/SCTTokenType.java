package sctlang.psi;

import com.intellij.psi.tree.IElementType;
import  sctlang.SCTLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class SCTTokenType extends IElementType {
    public SCTTokenType(@NotNull @NonNls String debugName) {
        super(debugName,SCTLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "SCTTokenType." + super.toString();
    }
}