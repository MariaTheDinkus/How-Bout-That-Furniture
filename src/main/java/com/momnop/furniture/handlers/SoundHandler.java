package com.momnop.furniture.handlers;

import java.util.ArrayList;

import com.momnop.furniture.info.ModInfo;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SoundHandler {
	public static SoundEvent doorbell;
	public static SoundEvent lightSwitchOn, lightSwitchOff;
	
	public static ArrayList<SoundEvent> sounds = new ArrayList<SoundEvent>();
	
	/**
	 * Register the {@link SoundEvent}s.
	 */
	public static void registerSounds() {
		doorbell = registerSound("furniture.doorbell");
		
		lightSwitchOn = registerSound("furniture.light.switch.on");
		lightSwitchOff = registerSound("furniture.light.switch.off");
	}

	/**
	 * Register a {@link SoundEvent}.
	 *
	 * @param soundName The SoundEvent's name without the testmod3 prefix
	 * @return The SoundEvent
	 */
	private static SoundEvent registerSound(String soundName) {
		final ResourceLocation soundID = new ResourceLocation(ModInfo.MODID, soundName);
		sounds.add(new SoundEvent(soundID).setRegistryName(soundID));
		return GameRegistry.register(new SoundEvent(soundID).setRegistryName(soundID));
	}
}
