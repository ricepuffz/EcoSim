package de.ricepuffz.ecosim.scene.testscene.ui;

import de.ricepuffz.ecosim.EcoSim;
import de.ricepuffz.ecosim.engine.Util;
import de.ricepuffz.ecosim.engine.ui.UILayer;
import de.ricepuffz.ecosim.engine.ui.UIManager;
import de.ricepuffz.ecosim.engine.ui.element.UIButton;

public class TestUIManager extends UIManager {
    private EcoSim main;

    public TestUIManager(EcoSim main) {
        this.main = main;

        init();
    }

    private void init() {
        UILayer testLayer = new UILayer(main, "test", 1);
        this.addLayer(testLayer);
        testLayer.addUIElement(new UIButton("testButton", "Test kek") {
            @Override
            public void onTick() {
                this.translate(60F * Util.deltaTime(), 30F * Util.deltaTime());
            }
        });
    }
}
