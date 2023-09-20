package useless.legacyui.Mixins.Gui;

import net.minecraft.client.gui.GuiTrommel;
import net.minecraft.client.render.FontRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import useless.legacyui.Settings.ModSettings;

@Mixin(value = GuiTrommel.class, remap = false)
public class GuiTrommelMixin {
    @Redirect(method = "drawGuiContainerForegroundLayer()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/FontRenderer;drawString(Ljava/lang/String;III)V"))
    private void changeColor(FontRenderer instance, String text, int x, int y, int color) {
        instance.drawString(text,x,y, ModSettings.Colors.GuiLabelColor());
    }
}
