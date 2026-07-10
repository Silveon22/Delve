package silvycakes.delve.block;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.BlockLogicFallingBlock;
import net.minecraft.core.block.BlockLogicPlanks;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.material.Materials;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.sound.BlockSounds;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.helper.creativeInventory.CreativeInventoryCategory;
import turniplabs.halplibe.helper.creativeInventory.CreativeInventoryPlacement;
import turniplabs.halplibe.util.BlockInitEntrypoint;

import static silvycakes.delve.Delve.MOD_ID;

public final class DelveBlocks implements BlockInitEntrypoint {

	//Spring Crops
	public static Block<?> BOULDER;
	public static Block<?> PETRIFIED_PLANKS	;

	private static boolean hasInit = false;

	public static void init() {
		if (!hasInit) {
			hasInit = true;
			initializeBlocks();
		}
	}

	public static void initializeBlocks() {

		int blockID = 9807;

		// Defining Blocks
		BlockBuilder boulder = new BlockBuilder(MOD_ID)
			.setBlockSound(BlockSounds.STONE)
			.setHardness(1.0F)
			.setTags(BlockTags.BROKEN_BY_FLUIDS, BlockTags.MINEABLE_BY_PICKAXE);

		BlockBuilder petrifiedPlanks = new BlockBuilder(MOD_ID)
			.setBlockSound(BlockSounds.WOOD)
			.setHardness(1.0F)
			.setTags(BlockTags.MINEABLE_BY_AXE);

		// Adding Blocks
		BOULDER = boulder
			.setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.ORGANIC))
			.build("boulder", "boulder", blockID++, BlockLogicBoulder::new);

		PETRIFIED_PLANKS = petrifiedPlanks
			.setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.WOOD))
			.build("petrified.planks", "petrified_planks", blockID++, BlockLogicCrumbling::new);

	}

	@SuppressWarnings("unchecked")
	public static <A> A getLogicAs(Block<?> block) {
		return (A) block.getLogic();
	}

	@Override
	public void afterBlockInit() {
		init();
	}
}
