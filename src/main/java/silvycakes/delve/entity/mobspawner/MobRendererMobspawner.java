package silvycakes.delve.entity.mobspawner;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.MobRendererBiped;
import net.minecraft.core.util.helper.MathHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.useless.dragonfly.models.entity.BoneTransform;
import org.useless.dragonfly.models.entity.StaticEntityModel;
import silvycakes.delve.entity.miner.MobMiner;

@Environment(EnvType.CLIENT)
public class MobRendererMobspawner extends MobRendererBiped<MobMobspawner> {
	public MobRendererMobspawner(float shadowSize) {
		super(shadowSize);
	}

	@Override
	protected @Nullable StaticEntityModel getActiveModel(@NotNull MobMobspawner mobMobspawner)  {
		return this.getModel("main");
	}

}
