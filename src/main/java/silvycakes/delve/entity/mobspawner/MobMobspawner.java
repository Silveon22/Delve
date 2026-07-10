package silvycakes.delve.entity.mobspawner;

import com.mojang.nbt.tags.CompoundTag;
import net.minecraft.core.WeightedRandomLootObject;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.monster.*;
import net.minecraft.core.entity.projectile.ProjectileFireball;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.world.World;
import org.jetbrains.annotations.NotNull;
import silvycakes.delve.entity.miner.MobMiner;

public class MobMobspawner extends MobMonster {
	int timeUntilSpawn;
	int spawns;
	public MobMobspawner(@NotNull World world) {
		super(world);
		this.setTextureIdentifier("delve", "mobspawner");
		this.scoreValue = 1000;
		this.setSize(0.8F, 3F);
		this.moveSpeed = 1.5F;
		this.speed = 0.15F;
		this.footSize = 1F;
		this.spawns = random.nextInt(8,16);
		this.mobDrops.add(new WeightedRandomLootObject(Items.GUNPOWDER.getDefaultStack(), 0, 2));
	}

	protected void attackBlockedEntity(@NotNull Entity entity, float f) {
		if (!this.world.isClientSide) {
			if (this.timeUntilSpawn > 0) {
				--this.timeUntilSpawn;
			}

		}
	}

	public void onLivingUpdate() {
		super.onLivingUpdate();
		if (this.target != null) {
		}
	}

	public void tick() {
		if (this.world.isClientSide) {
			if (this.timeUntilSpawn < 0) {
				this.timeUntilSpawn = 0;
			}

			if (this.timeUntilSpawn >= 60) {
				this.timeUntilSpawn = 60;
			}
		}

		super.tick();
	}

	public void addAdditionalSaveData(@NotNull CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putInt("spawns", this.spawns);

	}

	public void readAdditionalSaveData(@NotNull CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		this.spawns = tag.getInteger("spawns");
	}

	protected void attackEntity(@NotNull Entity entity, float distance) {
		if (!this.world.isClientSide) {
			if (distance < 10.0F && spawns > 0) {

				++this.timeUntilSpawn;
				if (this.timeUntilSpawn >= 60) {
					this.world.playSoundAtEntity((Entity)null, this, "mob.ghast.fireball", this.getSoundVolume(), (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
					this.timeUntilSpawn = 0;
					MobMonster enemy = new MobZombie(this.world);
					int mob = random.nextInt(0,5);
					switch (mob) {
						case 5:
							enemy = new MobMiner(this.world);
						case 4:
							enemy = new MobCreeper(this.world);
						case 3:
							enemy = new MobSnowman(this.world);
						case 2:
							enemy = new MobSpider(this.world);
						case 1:
							enemy = new MobSkeleton(this.world);
						case 0:
							enemy = new MobZombie(this.world);
					}

					enemy.setPos(this.x, this.y + (double)(this.bbHeight / 2.0F), this.z);

					double d = entity.x - this.x + (random.nextFloat(4F) - 2F);
					double d1 = entity.z - this.z + (random.nextFloat(4F) - 2F);
					float f2 = MathHelper.sqrt(d * d + d1 * d1);
					enemy.xd = d / (double)f2 * (double)0.5F * (double)0.8F + this.xd * 0.4;
					enemy.zd = d1 / (double)f2 * (double)0.5F * (double)0.8F + this.zd * 0.4;
					enemy.yd = (double)0.4F + random.nextFloat(0.4F);

					this.world.entityJoinedWorld(enemy);
				}
				if (distance < 7.0F) {
					this.hasAttacked = true;
				}
			} else {
				--this.timeUntilSpawn;
				if (this.timeUntilSpawn < 0) {
					this.timeUntilSpawn = 0;
				}
			}

		}
	}
}
