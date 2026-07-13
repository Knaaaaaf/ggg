package pl.monety;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonetyMod implements ModInitializer {

    public static final String MOD_ID = "monety";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Inicjalizacja moda Monety...");
        ModItems.registerModItems();
    }
}
