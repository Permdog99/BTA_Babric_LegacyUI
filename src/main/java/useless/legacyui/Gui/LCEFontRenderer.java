package useless.legacyui.Gui;

import net.minecraft.client.option.GameSettings;
import net.minecraft.client.render.FontRenderer;
import net.minecraft.client.render.RenderEngine;

public class LCEFontRenderer extends FontRenderer {
    public LCEFontRenderer(GameSettings gameSettings, String fontPath, RenderEngine renderEngine) {
        super(gameSettings, fontPath, renderEngine);
        this.fontHeight = 6;
    }
}
