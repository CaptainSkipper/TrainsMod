package alemax.trainsmod.items;

import java.util.List;

import alemax.trainsmod.entities.EntityBR143;
import alemax.trainsmod.entities.EntityICRailcar;
import alemax.trainsmod.entities.EntityICWagon;
import alemax.trainsmod.entities.EntityRailcar;
import alemax.trainsmod.entities.EntitySNexasRailcar;
import alemax.trainsmod.entities.EntitySNexasWagon;
import alemax.trainsmod.init.ModBlocks;
import alemax.trainsmod.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import trackapi.lib.ITrack;
import trackapi.lib.ITrackBlock;

public class ItemICWagon extends Item {
	
	public ItemICWagon() {
		setRegistryName("item_ic_wagon");
		setUnlocalizedName("item_ic_wagon");
		setCreativeTab(CreativeTabs.TRANSPORTATION);
		
		ModItems.ITEMS.add(this);
	}
		
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote) {
			if((worldIn.getBlockState(pos).getBlock() instanceof ITrackBlock || worldIn.getTileEntity(pos) instanceof ITrack) && !(worldIn.getBlockState(pos).getBlock().getRegistryName().equals(ModBlocks.AM_RAIL_CURVED.getRegistryName()))) {
				EntityICWagon railcar = new EntityICWagon(worldIn, pos);
				AxisAlignedBB railcarBox = railcar.getBoundBox().offset(railcar.posX, railcar.posY, railcar.posZ);
				List<EntityRailcar> trains = worldIn.getEntitiesWithinAABB(EntityRailcar.class, new AxisAlignedBB(-30 + railcarBox.minX, -10 + railcarBox.minY, -30 + railcarBox.minZ, 30 + railcarBox.maxX, 10 + railcarBox.maxY, 30 + railcarBox.maxZ)); 
				boolean intersect = false;
				for(EntityRailcar e : trains) {
					if(e.getEntityBoundingBox().intersects(railcarBox)) {
						intersect = true;
						break;
					}
				}
				if(!intersect) {
					worldIn.spawnEntity(railcar);
				}
			}
		}
			
		
		return EnumActionResult.SUCCESS;
	}
	
}