package useless.legacyui.Gui.Containers;

import net.minecraft.core.InventoryAction;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.player.inventory.ContainerPlayer;
import net.minecraft.core.player.inventory.InventoryPlayer;
import net.minecraft.core.player.inventory.slot.Slot;
import net.minecraft.core.player.inventory.slot.SlotArmor;
import useless.legacyui.Gui.Slots.SlotNull;

import java.util.List;

public class LegacyContainerPlayerSurvival extends ContainerPlayer {
    public LegacyContainerPlayerSurvival(InventoryPlayer inventory, boolean isSinglePlayer) {
        super(inventory, isSinglePlayer);

        inventorySlots.clear(); // Remove all slots made in super class
        for (int index = 0; index < 5; ++index){ // Null Slots to keep alignment with server
            this.addSlot(new SlotNull(this.playerInv,index, -5000, -5000));
        }
        for (int index = 0; index < 4; ++index) { // Create Armor Slots
            this.addSlot(new SlotArmor(this, inventory, inventory.getSizeInventory() - 1 - index, 127, 27 + index * 42, index));
        }
        for (int row = 0; row < 3; ++row) { // Create Main Inventory Slots
            for (int column = 0; column < 9; ++column) {
                this.addSlot(new Slot(inventory, column + (row + 1) * 9, 24 + column * 42, 240 + row * 42));
            }
        }
        for (int column = 0; column < 9; ++column) { // Create Hotbar slots
            this.addSlot(new Slot(inventory, column, 24 + column * 42, 369));
        }
    }
    @Override
    public List<Integer> getMoveSlots(InventoryAction inventoryAction, Slot slot, int i, EntityPlayer entityPlayer) {
        if (slot.id >= 5 && slot.id <= 8) { // Armor Slots
            return this.getSlots(5, 4, false);
        }
        if (inventoryAction == InventoryAction.MOVE_SIMILAR) { // Search Inventory for similar action
            if (slot.id >= 9 && slot.id <= 44) { // Entire Inventory
                return this.getSlots(9, 36, false);
            }
        } else {
            if (slot.id >= 9 && slot.id <= 35) {  // Main Inventory
                return this.getSlots(9, 27, false);
            }
            if (slot.id >= 36 && slot.id <= 44) { // Hotbar
                return this.getSlots(36, 9, false);
            }
        }
        return null;
    }

    @Override
    public List<Integer> getTargetSlots(InventoryAction inventoryAction, Slot slot, int target, EntityPlayer entityPlayer) {
        if (slot.id >= 9 && slot.id <= 44) { // Entire Inventory
            if (target == 2) { // Target is armor slots
                return this.getSlots(5, 4, false);
            }
            if (slot.id < 36) { // If from Main Inventory goto hotbar
                return this.getSlots(36, 9, false);
            }
            return this.getSlots(9, 27, false); // Else goto main inventory
        }
        return this.getSlots(9, 36, false); // Armor slots to rest of inventory
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer entityPlayer) {
        return super.isUsableByPlayer(entityPlayer);
    }
}
