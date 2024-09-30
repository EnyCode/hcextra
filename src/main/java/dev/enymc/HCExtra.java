package dev.enymc;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.minecraft.text.MutableText;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class HCExtra implements ClientModInitializer {
	public static final String MOD_ID = "hcextra";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitializeClient() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");

		ClientReceiveMessageEvents.MODIFY_GAME.register((message, overlay) -> {
			LOGGER.info("Received message: " + message);
			if (true) {
				Text[] components = message.getSiblings().toArray(Text[]::new);
				MutableText modified = Text.empty();

				for (int i = 0; i < components.length; i++) {
					Text component = components[i];
					LOGGER.info(component.getString());
					if (component.getString().contains(":yay:")) {
						LOGGER.info("i found the yay");
						LOGGER.info(component.getString());
						LOGGER.info(component.getStyle().toString());

						String[] parts = component.getString().split(":yay:");

						for (int x = 0; x < parts.length; x++) {
							modified.append(Text.literal(parts[x]).setStyle(component.getStyle()));
							if (x < parts.length - 1) {
									modified.append(Text.literal(":yay:").setStyle(component.getStyle().withFont(Identifier.of("alt"))));
							}
						}
					} else {
						modified.append(component);
					}
				}

				return modified;
			}

			return message;
		});
	}
}