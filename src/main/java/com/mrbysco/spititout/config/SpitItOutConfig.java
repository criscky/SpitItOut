package com.mrbysco.spititout.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class SpitItOutConfig {
	public static class Client {
		public final ForgeConfigSpec.BooleanValue onlyLogCause;

		Client(ForgeConfigSpec.Builder builder) {
			builder.comment("Client settings")
					.push("Client");

			onlyLogCause = builder
					.comment("Setting this to false will log the full stacktrace (Default: true)")
					.define("onlyLogCause", true);

			builder.pop();
		}
	}

	public static final ForgeConfigSpec clientSpec;
	public static final Client CLIENT;

	static {
		final Pair<Client, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Client::new);
		clientSpec = specPair.getRight();
		CLIENT = specPair.getLeft();
	}
}
