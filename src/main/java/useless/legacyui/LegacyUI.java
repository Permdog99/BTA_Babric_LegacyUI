package useless.legacyui;

import io.github.prospector.modmenu.ModMenu;
import io.github.prospector.modmenu.api.ModMenuApi;
import io.github.prospector.modmenu.impl.ModMenuApiImpl;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.options.GuiOptionsPageGeneral;
import net.minecraft.client.gui.options.GuiOptionsPageTexturePacks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import useless.legacyui.Helper.IconHelper;
import useless.legacyui.Sorting.LegacyCategoryManager;
import useless.prismaticlibe.helper.SoundHelper;

import java.util.function.Function;

public class LegacyUI implements ModInitializer {
    public useless.legacyui.Gui.LCEFontRenderer LCEfontRenderer;
    static {
        //this is here to possibly fix some class loading issues, do not delete
        try {
            Class.forName("net.minecraft.core.block.Block");
            Class.forName("net.minecraft.core.item.Item");
        } catch (ClassNotFoundException ignored) {}
    }
    public static final String MOD_ID = "legacyui";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    @Override
    public void onInitialize() {
        SoundHelper.addSound(MOD_ID, "ui/back.wav");
        SoundHelper.addSound(MOD_ID, "ui/craft.wav");
        SoundHelper.addSound(MOD_ID, "ui/craftfail.wav");
        SoundHelper.addSound(MOD_ID, "ui/focus.wav");
        SoundHelper.addSound(MOD_ID, "ui/press.wav");
        SoundHelper.addSound(MOD_ID, "ui/scroll.wav");
        SoundHelper.addSound(MOD_ID, "ui/achievement.wav");
        String[] iconTextures = new String[]{"armor.png", "bricks.png", "grass.png", "health.png", "lever.png", "modded.png", "painting.png", "planks.png", "rail.png", "redstonerail.png", "tools.png"};
        for (String texture: iconTextures) {
            IconHelper.getOrCreateIconTexture(MOD_ID, texture);
        }
        LOGGER.info("LegacyUI initialized.");
    }
}
