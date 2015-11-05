package com.skylarkingstudios.nightknight.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;
import com.skylarkingstudios.nightknight.NightKnight;

public class DesktopLauncher {

    public static boolean rebuildAtlas = true;

    public static void main (String[] arg) {

        if(rebuildAtlas) {
            Settings settings = new Settings();
            settings.maxWidth = 1024;
            settings.maxHeight = 1024;
            settings.duplicatePadding = false;
            TexturePacker.process(settings, "assets-raw/skele", "spritesheets", "skeleton");
            TexturePacker.process(settings, "assets-raw/knighty", "spritesheets", "nightknight");
            TexturePacker.process(settings, "assets-raw/level", "spritesheets", "level");
        }

        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Night Knight";
        config.width = 800;
        config.height = 480;
		new LwjglApplication(new NightKnight(), config);
	}
}
