package rip.fanclub.namelengthbypass.mixin;

import net.minecraft.datafixer.fix.ItemStackComponentizationFix;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import rip.fanclub.namelengthbypass.Constants;

@Mixin(ItemStackComponentizationFix.class)
public class ItemStackComponentizationFixMixin {

    @Redirect(
            method = "isValidUsername",
            at = @At(value = "INVOKE", target = "Ljava/lang/String;length()I")
    )
    private static int a(String instance) {
        return Math.min(instance.length(), Constants.OLD_LENGTH_LIMIT);
    }

}
