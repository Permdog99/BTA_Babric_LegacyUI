package useless.legacyui.Gui.Slots;

import net.minecraft.core.Global;
import net.minecraft.core.achievement.AchievementList;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemArmor;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.block.ItemBlock;
import net.minecraft.core.player.inventory.ContainerPlayer;
import net.minecraft.core.player.inventory.IInventory;
import net.minecraft.core.player.inventory.slot.Slot;
import net.minecraft.core.player.inventory.slot.SlotArmor;
import useless.legacyui.Gui.Slots.SlotLCE;
import useless.prismaticlibe.gui.slot.IResizable;

public class SlotArmorLCE extends SlotLCE implements IResizable {
    final int armorType;
    public static int TEXTURE_ATLAS_WIDTH_TILES_LCE = 12;
    final ContainerPlayer inventory;

    public SlotArmorLCE(ContainerPlayer containerplayer, IInventory iinventory, int i, int j, int k, int l, final int width) {
        super(iinventory, i, j, k, l);
        this.inventory = containerplayer;
        this.armorType = l;
        this.width = width;
    }
    public int getSlotStackLimit() {
        return 1;
    }

    public boolean canPutStackInSlot(ItemStack itemstack) {
        if (itemstack.getItem() instanceof ItemArmor) {
            return ((ItemArmor)itemstack.getItem()).armorPiece == this.armorType;
        } else if (itemstack.getItem() instanceof ItemBlock) {
            return this.armorType == 0;
        } else if (itemstack.getItem().id != Item.armorQuiverGold.id && itemstack.getItem().id != Item.armorQuiver.id) {
            return false;
        } else {
            return this.armorType == 1;
        }
    }

    public void onSlotChanged() {
        super.onSlotChanged();
        int count = 0;

        for(int i = 0; i < this.inventory.inventorySlots.size(); ++i) {
            if (this.inventory.inventorySlots.get(i) instanceof SlotArmor) {
                ItemStack stack = this.inventory.inventorySlots.get(i).getStack();
                if (stack != null && (stack.itemID == Item.armorBootsChainmail.id || stack.itemID == Item.armorHelmetChainmail.id || stack.itemID == Item.armorChestplateChainmail.id || stack.itemID == Item.armorLeggingsChainmail.id)) {
                    ++count;
                }
            }
        }

        if (count == 4) {
            this.inventory.playerInv.player.triggerAchievement(AchievementList.GET_CHAINMAIL);
        }

    }
}
