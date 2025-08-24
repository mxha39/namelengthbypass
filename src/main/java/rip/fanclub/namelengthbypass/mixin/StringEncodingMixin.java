package rip.fanclub.namelengthbypass.mixin;

import net.minecraft.network.encoding.StringEncoding;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import rip.fanclub.namelengthbypass.Constants;

@Mixin(StringEncoding.class)
public class StringEncodingMixin {

    @Redirect(
            method = "encode",
            at = @At(value = "INVOKE", target = "Ljava/lang/CharSequence;length()I",ordinal = 0)
    )
    private static int lazy1(CharSequence instance) {
        if (instance.length() == Constants.NEW_LENGTH_LIMIT) {
            return Constants.OLD_LENGTH_LIMIT;
        }
        return instance.length();
    }

    @ModifyArg(
            method = "decode",
            at = @At(value = "INVOKE", target = "Lio/netty/buffer/ByteBufUtil;utf8MaxBytes(I)I")
    )
    private static int lazy2(int maxLength) {
        if (maxLength == 16) {
            return 18;
        }
        return maxLength;
    }

    @Redirect(
            method = "decode",
            at = @At(value = "INVOKE", target = "Ljava/lang/String;length()I",ordinal = 0)
    )
    private static int lazy3(String instance) {
        if (instance.length() == Constants.NEW_LENGTH_LIMIT) {
            return Constants.OLD_LENGTH_LIMIT;
        }
        return instance.length();
    }

}
