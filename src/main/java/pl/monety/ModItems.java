package pl.monety;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item MONETA_1ZL = registerItem("moneta_1zl");
    public static final Item MONETA_50ZL = registerItem("moneta_50zl");
    public static final Item MONETA_100ZL = registerItem("moneta_100zl");

    private static Item registerItem(String path) {
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(MonetyMod.MOD_ID, path));
        Item item = new Item(new Item.Settings().registryKey(key).maxCount(64));
        return Registry.register(Registries.ITEM, key, item);
    }

    public static void registerModItems() {
        // Dodaje monety do zakładki "Składniki" w ekwipunku kreatywnym
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(MONETA_1ZL);
            entries.add(MONETA_50ZL);
            entries.add(MONETA_100ZL);
        });
    }
}
