package com.mrbysco.spititout.mixin;

import com.mrbysco.spititout.SpitItOut;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.Connection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Connection.class)
public class ConnectionMixin {

	@Inject(method = "exceptionCaught(Lio/netty/channel/ChannelHandlerContext;Ljava/lang/Throwable;)V",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/network/Connection;getCurrentProtocol()Lnet/minecraft/network/ConnectionProtocol;",
					shift = At.Shift.BEFORE,
					ordinal = 0
			)
	)
	public void spititout_exceptionCaught(ChannelHandlerContext context, Throwable throwable, CallbackInfo ci) {
		SpitItOut.logThrowable(throwable);
	}

}
