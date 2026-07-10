package silvycakes.delve.entity;


import net.minecraft.core.entity.EntityDispatcher;
import net.minecraft.core.util.collection.NamespaceID;
import silvycakes.delve.entity.miner.MobMiner;

import static silvycakes.delve.Delve.MOD_ID;

public final class DelveEntities {
	private static boolean hasInit = false;

	public static void init() {
		if (!hasInit) {
			hasInit = true;
			initializeEntities();
		}

	}

	public static void initializeEntities() {
		EntityDispatcher dispatcher = EntityDispatcher.getInstance();

		dispatcher.addMapping(
			MobMiner.class,
			NamespaceID.fromPool(MOD_ID, "miner"),
			MobMiner::new,
			"guidebook.section.mob.delve.miner.name"
		);
	}
}
