package silvycakes.delve;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.EntityRendererDispatcher;
import net.minecraft.client.render.TileEntityRenderDispatcher;
import net.minecraft.client.render.block.color.BlockColorDispatcher;
import net.minecraft.client.render.block.model.BlockModelCrossedSquares;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.block.model.generic.BlockModelGenericFullyRotatable;
import net.minecraft.client.render.entity.MobRendererBiped;
import net.minecraft.client.render.entity.MobRendererBipedArmored;
import net.minecraft.client.render.entity.MobRendererZombie;
import net.minecraft.client.render.item.model.ItemModelDispatcher;
import silvycakes.delve.block.DelveBlocks;
import silvycakes.delve.entity.miner.MobMiner;
import silvycakes.delve.entity.miner.MobRendererMiner;
import turniplabs.halplibe.helper.ModelHelper;
import turniplabs.halplibe.util.ModelEntrypoint;

@Environment(EnvType.CLIENT)
public class DelveModels implements ModelEntrypoint {

	@Override
	public void initBlockModels(BlockModelDispatcher dispatcher) {
		dispatcher.addDispatch(DelveBlocks.BOULDER, new BlockModelCrossedSquares<>(DelveBlocks.BOULDER).setAllTextures("delve:block/boulder"));
		dispatcher.addDispatch(DelveBlocks.PETRIFIED_PLANKS, new BlockModelStandard<>(DelveBlocks.PETRIFIED_PLANKS).setAllTextures("delve:block/petrified_planks"));
	}

	@Override
	public void initItemModels(ItemModelDispatcher dispatcher) {

	}

	@Override
	public void initEntityModels(EntityRendererDispatcher dispatcher) {
		ModelHelper.setEntityModel(MobMiner.class, new MobRendererMiner(0.5F));
	}

	@Override
	public void initTileEntityModels(TileEntityRenderDispatcher dispatcher) {

	}

	@Override
	public void initBlockColors(BlockColorDispatcher blockColorDispatcher) {

	}

}
