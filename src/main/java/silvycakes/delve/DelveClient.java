package silvycakes.delve;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.guidebook.mobs.MobInfoRegistry;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import silvycakes.delve.entity.miner.MobMiner;
import silvycakes.delve.entity.mobspawner.MobMobspawner;
import turniplabs.halplibe.util.ClientStartEntrypoint;

import java.io.IOException;
import java.net.URISyntaxException;

import static silvycakes.delve.Delve.LOGGER;
import static silvycakes.delve.Delve.MOD_ID;

@Environment(EnvType.CLIENT)
public class DelveClient implements ClientModInitializer, ClientStartEntrypoint {

	@Override
	public void beforeClientStart() {

		try {
			TextureRegistry.initializeAllFiles(MOD_ID, TextureRegistry.worldAtlas, false);
		} catch (URISyntaxException | IOException e) {
			LOGGER.error("Failed to initialize textures!");
		}
	}

	@Override
	public void afterClientStart() {
		MobInfoRegistry.register(MobMiner.class, "guidebook.section.mob.miner.name", "guidebook.section.mob.miner.desc",
			20, 500, new MobInfoRegistry.MobDrop[]{new MobInfoRegistry.MobDrop(new ItemStack(Items.CLOTH), 1.0f, 1, 2)});
		MobInfoRegistry.register(MobMobspawner.class, "guidebook.section.mob.mobspawner.name", "guidebook.section.mob.mobspawner.desc",
			20, 1000, new MobInfoRegistry.MobDrop[]{new MobInfoRegistry.MobDrop(new ItemStack(Items.GUNPOWDER), 1.0f, 1, 2)});

	}

	@Override
	public void onInitializeClient() {
		LOGGER.info("Delve client initialized.");
	}
}
