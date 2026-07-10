package silvycakes.delve.entity.miner;

import net.minecraft.core.WeightedRandomLootObject;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.material.MaterialLiquid;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.IArmorWearing;
import net.minecraft.core.entity.IItemHolding;
import net.minecraft.core.entity.monster.MobMonster;
import net.minecraft.core.entity.monster.MobSkeleton;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.world.World;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.biome.Biomes;
import net.minecraft.core.world.pos.TilePos;
import net.minecraft.core.world.pos.TilePosc;
import net.minecraft.core.world.season.Seasons;
import net.minecraft.core.world.weather.Weathers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Implements;
import silvycakes.delve.block.DelveBlocks;

public class MobMiner extends MobMonster implements IItemHolding {
	private static final ItemStack DEFAULT_HELD_ITEM;
	public MobMiner(@NotNull World world) {
		super(world);
		this.setTextureIdentifier("delve", "miner");
		this.moveSpeed = 0.5F;
		this.attackStrength = 5;
		this.scoreValue = 500;
		this.bbHeight = 2.2F;
		this.mobDrops.add(new WeightedRandomLootObject(Items.CLOTH.getDefaultStack(), 0, 2));
	}

	public void tick() {
		super.tick();
		this.moveSpeed = 0.5F;
		if (this.target != null && this.yd >= -0.2 && !this.isInWater() && !this.dead) {
			if (this.target.y > this.y + 1.5) {
				this.moveSpeed = 0F;
				if (this.onGround) {
					this.yd = this.jumpHeight;
				}
				buildBlock();
			}
			else if (!(this.target.y < this.y)) {
				buildBlock();
				if (this.distanceTo(this.target) < 10F && this.canEntityBeSeen(this.target) && this.xd < 0.01 && this.zd < 0.01 && this.xd > -0.01 && this.zd > -0.01) {
					if (this.onGround) {
						double d = this.target.x - this.x;
						double d1 = this.target.z - this.z;
						float f2 = MathHelper.sqrt(d * d + d1 * d1);
						this.xd = d / (double)f2 * (double)0.22F + this.xd;
						this.zd = d1 / (double)f2 * (double)0.22F + this.zd;
						this.yd = 0.2;
					}
				}
			}
		}
	}

	public void buildBlock() {
		TilePos block = new TilePos();
		block.x = (int) MathHelper.floor (this.x);
		block.y = (int) MathHelper.floor (this.y - 1F);
		block.z = (int) MathHelper.floor (this.z);
		if (block.inBounds(this.world) && this.world.getBlockType(block) == Blocks.AIR) {
			this.world.setBlockTypeNotify(block, DelveBlocks.PETRIFIED_PLANKS);
		}

	}

	public String getLivingSound() {
		return "mob.zombie";
	}

	protected String getHurtSound() {
		return "mob.zombiehurt";
	}

	protected String getDeathSound() {
		return "mob.zombiedeath";
	}

	public boolean canSpawnHere() {
		int y = (int)this.y;
		int oceanY = this.world.getWorldType().getOceanY();
		if (y < (oceanY)) {
			return super.canSpawnHere();
		}
		else {
			if (random.nextInt(8) == 0) {
				return super.canSpawnHere();
			}
			else {
				return false;
			}
		}
	}

	static {
		DEFAULT_HELD_ITEM = new ItemStack(Items.TOOL_PICKAXE_IRON, 1);
	}

	@Override
	public @Nullable ItemStack getHeldItem() {
		return DEFAULT_HELD_ITEM;
	}

	@Override
	public void setHeldItem(@Nullable ItemStack itemStack) {

	}

	@Override
	public boolean isLeftHanded() {
		return false;
	}
}
