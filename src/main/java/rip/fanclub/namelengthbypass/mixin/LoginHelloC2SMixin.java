package rip.fanclub.namelengthbypass.mixin;

import net.minecraft.network.packet.c2s.login.LoginHelloC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import rip.fanclub.namelengthbypass.Constants;

@Mixin(LoginHelloC2SPacket.class)
public class LoginHelloC2SMixin {

    @ModifyArg(
            method = "<init>(Lnet/minecraft/network/PacketByteBuf;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/network/PacketByteBuf;readString(I)Ljava/lang/String;")
    )
    private static int changeLength(int maxLength) {
        return Constants.newLimit;
    }

    @ModifyArg(
            method = "write",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/network/PacketByteBuf;writeString(Ljava/lang/String;I)Lnet/minecraft/network/PacketByteBuf;"),
            index = 1
    )
    public int changeLength2(int maxLength) {
        return Constants.newLimit;
    }
}
