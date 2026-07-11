package de.mcreator.emeraldsplus.procedures;

import net.minecraft.world.entity.Entity;

public class EmeraldshieldRightclickedProcedure {
	public static boolean eventResult = true;

	public static void execute(Entity entity) {
		if (entity == null)
			return;
		entity.fallDistance = 0;
	}
}