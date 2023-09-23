package useless.legacyui.Gui.Slots;

import net.minecraft.core.achievement.stat.StatList;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.IInventory;
import net.minecraft.core.player.inventory.InventoryPlayer;
import net.minecraft.core.player.inventory.slot.Slot;
import useless.prismaticlibe.gui.slot.IResizable;

public class SlotLCE extends Slot implements IResizable {

    protected final int slotIndex;
    protected final IInventory inventory;
    public int id;
    public int xDisplayPosition;
    public int yDisplayPosition;
    public boolean discovered = true;
    public int width;


    public SlotLCE(IInventory inventory, int id, int x, int y, final int width) {
        super(inventory, id, x, y);
        this.inventory = inventory;
        this.slotIndex = id;
        this.xDisplayPosition = x;
        this.yDisplayPosition = y;
        this.width = width;
    }

    public int getWidth() {
        return this.width;
    }

    public ItemStack decrStackSize(int i) {
        return this.inventory.decrStackSize(this.slotIndex, i);
    }

    public int getBackgroundIconIndex() {
        return -1;
    }

    public boolean hasStack() {
        return this.getStack() != null;
    }

    public int getSlotStackLimit() {
        return this.inventory.getInventoryStackLimit();
    }

    public ItemStack getStack() {
        return this.inventory.getStackInSlot(this.slotIndex);
    }

    public boolean isHere(IInventory iinventory, int i) {
        return iinventory == this.inventory && i == this.slotIndex;
    }

    public boolean canPutStackInSlot(ItemStack itemstack) {
        return true;
    }

    public void onPickupFromSlot(ItemStack itemstack) {
        this.onSlotChanged();
    }

    public void onSlotChanged() {
        this.inventory.onInventoryChanged();
    }

    public IInventory getInventory() {
        return this.inventory;
    }

    public void putStack(ItemStack itemstack) {
        this.inventory.setInventorySlotContents(this.slotIndex, itemstack);
        this.onSlotChanged();
        if (this.inventory instanceof InventoryPlayer) {
            InventoryPlayer inventoryPlayer = (InventoryPlayer) this.inventory;
            if (itemstack != null) {
                inventoryPlayer.player.addStat(StatList.pickUpItemStats[itemstack.itemID], 1);
            }
        }

    }

    public boolean enableDragAndPickup() {
        return true;
    }

    public boolean allowItemInteraction() {
        return true;
    }
}