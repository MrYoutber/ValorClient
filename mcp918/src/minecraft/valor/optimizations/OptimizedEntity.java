package valor.optimizations;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class OptimizedEntity extends Entity {
	public OptimizedEntity(World worldIn) {
		super(worldIn);
		// TODO Auto-generated constructor stub
	}

	private int tickCounter = 0;

	@Override
	public void onUpdate() {
		tickCounter++;
		if (tickCounter % 10 == 0) { // Update every 10 ticks
			super.onUpdate();
		}
	}

	@Override
	protected void entityInit() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound tagCompund) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound tagCompound) {
		// TODO Auto-generated method stub

	}
}
