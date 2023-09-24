package useless.legacyui.Gui.GuiScreens;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiElement;
import net.minecraft.client.gui.GuiInventory;
import net.minecraft.client.gui.options.GuiOptionButton;
import net.minecraft.client.input.InputType;
import net.minecraft.client.option.GameSettings;
import net.minecraft.client.render.EntityRenderDispatcher;
import net.minecraft.client.render.Lighting;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemArmor;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.lang.I18n;
import net.minecraft.core.player.gamemode.Gamemode;
import org.lwjgl.opengl.GL11;
import useless.legacyui.Gui.GuiElements.Buttons.GuiAuditoryButton;
import useless.legacyui.Gui.GuiElements.GuiButtonPrompt;
import useless.legacyui.LegacySoundManager;
import useless.legacyui.Settings.ModSettings;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GuiLegacyInventory extends GuiInventory {
    private static int GUIx;
    private List<Integer> uvs;
    private static final int guiTextureWidth = 435;
    private static int GUIy;

    protected GuiAuditoryButton craftButton;
    protected EntityPlayer player;
    public List<GuiButtonPrompt> prompts = new ArrayList<>();
    private GuiButton armorButton;

    public GuiLegacyInventory(EntityPlayer player) {
        super(player);
        this.player = player;
    }
    public void initGui() {

        // Setup size variables
        xSize = 145;
        ySize = 145;
        GUIx = (this.width - this.xSize) / 2;
        GUIy = (this.height - this.ySize) / 2;

        // Create Crafting Button
        craftButton = new GuiAuditoryButton(10, GUIx + 138, GUIy + 33, 20, 21, "");
        craftButton.visible = false;
        controlList.add(craftButton);
        I18n translator = I18n.getInstance();
        prompts.add(new GuiButtonPrompt( 101, 50, this.height-30, 3,translator.translateKey("legacyui.prompt.select"), new int[]{0}));
        prompts.add(new GuiButtonPrompt( 102, prompts.get(prompts.size()-1).xPosition + prompts.get(prompts.size()-1).width + 3, this.height-30, 3,translator.translateKey("legacyui.prompt.back"), new int[]{1}));
        prompts.add(new GuiButtonPrompt( 102, prompts.get(prompts.size()-1).xPosition + prompts.get(prompts.size()-1).width + 3, this.height-30, 3,translator.translateKey("legacyui.prompt.movestack"), new int[]{2}));
        prompts.add(new GuiButtonPrompt( 102, prompts.get(prompts.size()-1).xPosition + prompts.get(prompts.size()-1).width + 3, this.height-30, 3,translator.translateKey("legacyui.prompt.halfstack"), new int[]{3}));

        boolean enableArmorButton = false;
        this.armorButton = null;
        this.updateOverlayButtons();
    }
    public void updateOverlayButtons() {
        GameSettings settings = this.mc.gameSettings;
        boolean clock = false;
        boolean compass = false;
        boolean rotaryCalendar = false;
        this.overlayButtonsLayout.elements.clear();
    }
    protected void buttonPressed(GuiButton guibutton) {
        super.buttonPressed(guibutton);
        if (guibutton == craftButton){
            openCrafting();
        }
    }
    protected void openCrafting(){
        LegacySoundManager.volume = 0;
        this.onGuiClosed();
        mc.displayGuiScreen(new GuiLegacyCrafting(player, 4));
        LegacySoundManager.volume = 1f;
    }
    public void drawScreen(int x, int y, float renderPartialTicks) {
        super.drawScreen(x, y, renderPartialTicks);

        UtilGui.bindTexture("/assets/legacyui/gui/legacyinventory.png");
        this.drawTexturedModalRect(craftButton.xPosition, craftButton.yPosition, 200, craftButton.isHovered(x,y) ? 77:54, craftButton.width, craftButton.height); // Crafting Button Render
        if (mc.inputType == InputType.CONTROLLER){
            for (GuiButtonPrompt prompt: prompts) {
                prompt.drawPrompt(mc, x, y);
            }
        }
    }

    protected void drawGuiContainerForegroundLayer() {
    }

    protected void drawGuiContainerBackgroundLayer(float renderPartialTick) {
        UtilGui.bindTexture("/assets/legacyui/gui/lce-inventory-ui.png");
        UtilGui.drawTexturedModalRect(this, GUIx, GUIy, 0, 0, this.xSize, this.ySize, 3f/guiTextureWidth);
        renderPlayerDoll();
        renderInvText();
    }

    private void renderInvText() {
        double size = 1.3;
        double size2 = 0.7692307692307692;
        int posX = GUIx+8;
        int posY = GUIy+68;
        GL11.glPushMatrix();
        GL11.glScaled(1/size,1/size,1/size);
        GL11.glTranslated(posX*(1/size2), posY*(1/size2), 0);
        drawStringNoShadow(fontRenderer, I18n.getInstance().translateKey("legacyui.guilabel.inventory"), 0, 0, ModSettings.legacyOptions.getGuiLabelColor().value.value);
        GL11.glPopMatrix();
    }

    private void renderPlayerDoll(){
        GL11.glEnable(32826);
        GL11.glEnable(2903);
        GL11.glEnable(2929);
        GL11.glPushMatrix();
        GL11.glTranslatef(GUIx + 51 + 44 - 14, GUIy + 75 - 5 - 8, 50.0f);
        float f1 = 26.0f;
        GL11.glScalef(-f1, f1, f1);
        GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
        float f2 = this.mc.thePlayer.renderYawOffset;
        float f3 = this.mc.thePlayer.yRot;
        float f4 = this.mc.thePlayer.xRot;
        float f5 = (float)(GUIx + 51 + 44 - 16) - this.xSize_lo;
        float f6 = (float)(GUIy + 75 - 50 - 5) - this.ySize_lo;
        GL11.glRotatef(135.0f, 0.0f, 1.0f, 0.0f);
        Lighting.enableLight();
        GL11.glRotatef(-135.0f, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(-((float)Math.atan(f6 / 40.0f)) * 20.0f, 1.0f, 0.0f, 0.0f);
        this.mc.thePlayer.renderYawOffset = (float)Math.atan(f5 / 40.0f) * 20.0f;
        this.mc.thePlayer.yRot = (float)Math.atan(f5 / 40.0f) * 40.0f;
        this.mc.thePlayer.xRot = -((float)Math.atan(f6 / 40.0f)) * 20.0f;
        this.mc.thePlayer.entityBrightness = 1.0f;
        GL11.glTranslatef(0.0f, this.mc.thePlayer.heightOffset, 0.0f);
        EntityRenderDispatcher.instance.viewLerpYaw = 180.0f;
        EntityRenderDispatcher.instance.renderEntityWithPosYaw(this.mc.thePlayer, 0.0, 0.0, 0.0, 0.0f, 1.0f);
        this.mc.thePlayer.entityBrightness = 0.0f;
        this.mc.thePlayer.renderYawOffset = f2;
        this.mc.thePlayer.yRot = f3;
        this.mc.thePlayer.xRot = f4;
        GL11.glPopMatrix();
        Lighting.disable();
        GL11.glDisable(32826);
    }
}