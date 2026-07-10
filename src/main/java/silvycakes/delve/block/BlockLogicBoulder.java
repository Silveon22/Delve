package silvycakes.delve.block;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicFlower;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePos;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BlockLogicBoulder extends BlockLogicFlower {
	public BlockLogicBoulder(@NotNull Block<?> block) {
		super(block);
		this.setBlockBounds((double)0.1F, (double)0.0F, (double)0.1F, (double)0.9F, (double)0.8F, (double)0.9F);
	}

	public boolean canStay(@NotNull World world, @NotNull TilePosc tilePos) {
		return this.mayPlaceOn(world.getBlockType(tilePos.down(new TilePos())));
	}

	public boolean mayPlaceOn(@NotNull Block<?> block) {
		return block.isSolidRender();
	}

	public @NotNull ItemStack @Nullable [] getBreakResult(@NotNull World world, @NotNull EnumDropCause dropCause, int meta, @Nullable TileEntity tileEntity) {
		ItemStack[] var10000;
		switch (dropCause) {
			case SILK_TOUCH:
			case PICK_BLOCK:
				var10000 = new ItemStack[]{new ItemStack(this)};
				break;
			default:
				int pebbles = 1 + world.rand.nextInt(2);
				var10000 = world.rand.nextInt(4) == 0 ? new ItemStack[]{new ItemStack(Items.AMMO_PEBBLE, pebbles), new ItemStack(Items.COAL)} : new ItemStack[]{new ItemStack(Items.AMMO_PEBBLE, pebbles)};
		}

		return var10000;
	}
}
