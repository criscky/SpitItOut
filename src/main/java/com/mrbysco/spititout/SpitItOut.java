package com.mrbysco.spititout;

import com.mojang.logging.LogUtils;
import com.mrbysco.spititout.config.SpitItOutConfig;
import io.netty.util.internal.ThrowableUtil;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.slf4j.Logger;

@Mod(SpitItOut.MODID)
public class SpitItOut {
	public static final String MODID = "spititout";
	public static final Logger LOGGER = LogUtils.getLogger();

	public SpitItOut() {
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, SpitItOutConfig.clientSpec);

		//Make sure the mod being absent on the other network side does not cause the client to display the server as incompatible
		ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class, () -> new IExtensionPoint.DisplayTest(() -> "Trans Rights Are Human Rights", (remoteVersionString, networkBool) -> networkBool));
	}

	public static void logThrowable(Throwable throwable) {
		Throwable loggableThrowable = throwable;
		if (SpitItOutConfig.CLIENT.onlyLogCause.get()) {
			loggableThrowable = throwable.getCause();
		}
		SpitItOut.LOGGER.error("Exception caught in connection handler!\n {}", ThrowableUtil.stackTraceToString(loggableThrowable));
	}
}
