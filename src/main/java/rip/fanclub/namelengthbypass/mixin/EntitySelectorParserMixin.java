package rip.fanclub.namelengthbypass.mixin;

import net.minecraft.command.EntitySelectorReader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import rip.fanclub.namelengthbypass.Constants;

@Mixin(EntitySelectorReader.class)
public class EntitySelectorParserMixin {

    @Redirect(
            method = "readRegular",
            at = @At(value = "INVOKE", target = "Ljava/lang/String;length()I")
    )
    public int readRegular(String instance) {
        return Math.min(instance.length(), Constants.OLD_LENGTH_LIMIT);
    }
}
