package de.mcreator.emeraldsplus.procedures;

import net.minecraft.core.GlobalPos;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class LuckJobSiteTracker {
	public record Entry(UUID playerId, int toolLevel) {}

	public static final Map<GlobalPos, Entry> TRACKED = new ConcurrentHashMap<>();
}