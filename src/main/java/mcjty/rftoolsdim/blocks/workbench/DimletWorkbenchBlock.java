package mcjty.rftoolsdim.blocks.workbench;

import mcjty.lib.api.Infusable;
import mcjty.lib.container.GenericGuiContainer;
import mcjty.rftoolsdim.RFToolsDim;
import mcjty.rftoolsdim.blocks.GenericRFToolsBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class DimletWorkbenchBlock extends GenericRFToolsBlock<DimletWorkbenchTileEntity, DimletWorkbenchContainer> implements Infusable {

    public DimletWorkbenchBlock() {
        super(Material.IRON, DimletWorkbenchTileEntity.class, DimletWorkbenchContainer.class, "dimlet_workbench", true);
        setDefaultState(this.blockState.getBaseState());
    }

    @Override
    public boolean hasNoRotation() {
        return true;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public Class<? extends GenericGuiContainer> getGuiClass() {
        return GuiDimletWorkbench.class;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean whatIsThis) {
        super.addInformation(itemStack, player, list, whatIsThis);

        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
            list.add(TextFormatting.WHITE + "With this workbench you can deconstruct dimlets");
            list.add(TextFormatting.WHITE + "into individual parts and also reconstruct new dimlets");
            list.add(TextFormatting.WHITE + "out of these parts.");
            list.add(TextFormatting.YELLOW + "Infusing bonus: increased chance of getting");
            list.add(TextFormatting.YELLOW + "all parts out of the deconstructed dimlet.");
        } else {
            list.add(TextFormatting.WHITE + RFToolsDim.SHIFT_MESSAGE);
        }
    }

    @Override
    public int getGuiID() {
        return RFToolsDim.GUI_DIMLET_WORKBENCH;
    }
}
