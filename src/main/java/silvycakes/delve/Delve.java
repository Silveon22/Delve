package silvycakes.delve;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import silvycakes.delve.block.DelveBlocks;
import silvycakes.delve.entity.DelveEntities;
import turniplabs.halplibe.HalpLibe;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;

public class Delve implements ModInitializer, GameStartEntrypoint, RecipeEntrypoint {
	public static final String MOD_ID = HalpLibe.registerMod("delve", true);
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Delve! initialized.");
	}

	@Override
	public void beforeGameStart() {
		DelveBlocks.init();
		DelveEntities.init();
	}

	@Override
	public void afterGameStart() {

	}

	@Override
	public void onRecipesReady() {

	}

	@Override
	public void initNamespaces() {

	}
}
