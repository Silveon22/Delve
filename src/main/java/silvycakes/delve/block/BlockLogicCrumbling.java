package silvycakes.delve.block;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.material.Materials;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePos;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class BlockLogicCrumbling extends BlockLogic {
	public BlockLogicCrumbling(@NotNull Block<?> block) {
		super(block, Materials.WOOD);
		block.setTicking(true);
	}

	public void updateTick(@NotNull World world, @NotNull TilePosc tilePos, @NotNull Random rand, boolean isRandomTick) {
		world.setBlockTypeNotify(tilePos, Blocks.AIR);
	}

	public @NotNull ItemStack @Nullable [] getBreakResult(@NotNull World world, @NotNull EnumDropCause dropCause, int data, @Nullable TileEntity tileEntity) {
		ItemStack[] var10000;
		switch (dropCause) {
			case SILK_TOUCH:
			case PICK_BLOCK:
				var10000 = new ItemStack[]{new ItemStack(this)};
				break;
			default:
				var10000 = null;
		}

		return var10000;
	}
}
