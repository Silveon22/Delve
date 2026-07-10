package silvycakes.delve.mixin;

import net.minecraft.core.entity.SpawnListEntry;
import net.minecraft.core.entity.monster.MobSlime;
import net.minecraft.core.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import silvycakes.delve.entity.miner.MobMiner;

import java.util.List;

@Mixin(value = Biome.class, remap = false)
public class BiomeMixin {
	@Shadow
	protected List<SpawnListEntry> spawnableMonsterList;

	@Inject(method = "<init>", at = @At("TAIL"))
	private void injectMethod(String key, CallbackInfo ci) {
		this.spawnableMonsterList.add(new SpawnListEntry(MobMiner.class, 10));

	}
}
