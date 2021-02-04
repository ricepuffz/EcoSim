package de.ricepuffz.ecosim.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import de.ricepuffz.ecosim.EcoSim;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.foregroundFPS = 120;
		config.backgroundFPS = 30;

		new LwjglApplication(new EcoSim(), config);
	}
}
