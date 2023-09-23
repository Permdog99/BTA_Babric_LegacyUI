package useless.legacyui.Gui;

import net.minecraft.client.option.GameSettings;
import net.minecraft.client.render.FontRenderer;
import net.minecraft.client.render.RenderEngine;

public class LCEFontRenderer extends FontRenderer {

    public LCEFontRenderer(GameSettings gameSettings, String fontPath, RenderEngine renderEngine) {
        super(gameSettings, fontPath, renderEngine);
    }
    public void FontRenderer() {
        this.fontHeight = 6;
    }

    public void drawStringNoShadowLCE(LCEFontRenderer lcefr, String string, int x, int y, int argb) {
        this.drawStringLCE(string, x, y, argb);
    }
    public void drawStringLCE(String string, int x, int y, int argb) {
    }
}
