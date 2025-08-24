package rip.fanclub.namelengthbypass.mixin;

import net.minecraft.util.StringHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import rip.fanclub.namelengthbypass.Constants;

@Mixin(StringHelper.class)
public class StringHelperMixin {

    @Redirect(
            method = "isValidPlayerName",
            at = @At(value = "INVOKE", target = "Ljava/lang/String;length()I")
    )
    private static int redirectIsValidPlayerName(String name) {
        return Math.min(name.length(), Constants.OLD_LENGTH_LIMIT);
    }
}
