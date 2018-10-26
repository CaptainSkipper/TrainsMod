package alemax.trainsmod.blocks;

import alemax.trainsmod.blocks.tileentities.TileEntityTrackMarking;
import alemax.trainsmod.init.CreativeTabTrainsMod;
import alemax.trainsmod.proxy.CommonProxy;
import alemax.trainsmod.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockTrackMarking extends Block {

	public BlockTrackMarking() {
		super(Material.ROCK);
		setUnlocalizedName(Reference.MODID + ".track_marking");
		setRegistryName("track_marking");
		setCreativeTab(CommonProxy.tab_trainsmod);
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityTrackMarking();
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		TileEntity te = worldIn.getTileEntity(pos);
		if(te instanceof TileEntityTrackMarking) {
			((TileEntityTrackMarking) te).increaseAngle();
		}
		return true;
	}
	
	
	
}
