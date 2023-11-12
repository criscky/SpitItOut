package com.mrbysco.spititout.mixin;

import com.mrbysco.spititout.SpitItOut;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.NetworkManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetworkManager.class)
public class ConnectionMixin {

	@Inject(method = "Lnet/minecraft/network/NetworkManager;exceptionCaught(Lio/netty/channel/ChannelHandlerContext;Ljava/lang/Throwable;)V",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/network/NetworkManager;send(Lnet/minecraft/network/IPacket;Lio/netty/util/concurrent/GenericFutureListener;)V",
					shift = At.Shift.BEFORE,
					ordinal = 0
			),
			remap = false
	)
	public void spititout_exceptionCaught(ChannelHandlerContext context, Throwable throwable, CallbackInfo ci) {
		SpitItOut.logThrowable(throwable);
	}

	@Inject(method = "Lnet/minecraft/network/NetworkManager;exceptionCaught(Lio/netty/channel/ChannelHandlerContext;Ljava/lang/Throwable;)V",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/network/NetworkManager;disconnect(Lnet/minecraft/util/text/ITextComponent;)V",
					shift = At.Shift.BEFORE,
					ordinal = 1
			),
			remap = false
	)
	public void spititout_exceptionCaught2(ChannelHandlerContext context, Throwable throwable, CallbackInfo ci) {
		SpitItOut.logThrowable(throwable);
	}

}
