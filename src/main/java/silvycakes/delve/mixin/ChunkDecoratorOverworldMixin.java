package silvycakes.delve.mixin;

import com.mojang.logging.LogUtils;
import net.minecraft.core.block.BlockLogicFallingBlock;
import net.minecraft.core.world.World;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.biome.Biomes;
import net.minecraft.core.world.chunk.Chunk;
import net.minecraft.core.world.generate.chunk.perlin.overworld.ChunkDecoratorOverworld;
import net.minecraft.core.world.generate.feature.WorldFeatureFlowers;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import silvycakes.delve.block.DelveBlocks;

import java.util.Random;

@Mixin(value = ChunkDecoratorOverworld.class, remap = false)
public class ChunkDecoratorOverworldMixin {
	@Shadow
	@Final
	private World world;

	@Unique
	private static final @NotNull Logger LOGGER = LogUtils.getLogger();

	@Inject(method = "decorate(Lnet/minecraft/core/world/chunk/Chunk;)V", at = @At(value = "TAIL"))
	public void addCustomOre(Chunk chunk, CallbackInfo ci) {
		BlockLogicFallingBlock.fallInstantly = true;

		int chunkX = chunk.pos.x;
		int chunkZ = chunk.pos.z;
		int minY = this.world.getWorldType().getMinY(world);
		int maxY = this.world.getWorldType().getMaxY(world);
		int rangeY = maxY + 1 - minY;
		float oreHeightModifier = (float) rangeY / 128.0F;
		Random rand = new Random((long) chunkX * 341873898712L + (long) chunkZ * 132696981241L);

		BlockLogicFallingBlock.fallInstantly = true;
		int x = chunkX * 16;
		int z = chunkZ * 16;
		int y = this.world.getHeightValue(x + 16, z + 16);
		Biome biome = this.world.getBlockBiome(x + 16, y, z + 16);
		BlockLogicFallingBlock.fallInstantly = false;

//		delve_boulders
		try {

			int boulderChance = 2;

			if (biome == Biomes.OVERWORLD_DESERT) {
				boulderChance = 6;
			}

			if (biome == Biomes.OVERWORLD_PLAINS) {
				boulderChance = 4;
			}

			if (biome == Biomes.OVERWORLD_GRASSLANDS) {
				boulderChance = 4;
			}

			if (biome == Biomes.OVERWORLD_CAATINGA_PLAINS) {
				boulderChance = 4;
			}

			for(int i14 = 0; i14 < boulderChance; ++i14) {
				int k14 = x + rand.nextInt(16) + 8;
				int l16 = minY + rand.nextInt(rangeY);
				int k19 = z + rand.nextInt(16) + 8;
				(new WorldFeatureFlowers(DelveBlocks.BOULDER.id(), 32 + rand.nextInt(32), false)).place(this.world, rand, k14, l16, k19);
			}
		}  catch (Throwable t) {
			LOGGER.error("Decoration step 'delve_boulders' failed at chunk ({}, {}); continuing.", new Object[]{chunkX, chunkZ, t});
		}
	}
}
