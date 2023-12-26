package useless.legacyui;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.SoundHelper;
import turniplabs.halplibe.util.GameStartEntrypoint;
import useless.legacyui.Helper.IconHelper;
import useless.legacyui.Settings.ILegacyOptions;

public class LegacyUI implements ModInitializer {
    public static final String MOD_ID = "legacyui";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static ILegacyOptions modSettings;

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
