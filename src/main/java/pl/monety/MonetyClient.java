package pl.monety;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

public class MonetyClient implements ClientModInitializer {

    // Kolor tekstu HUD (żółty)
    private static final int TEXT_COLOR = 0xFFFF55;
    private static final int MARGIN_X = 8;
    private static final int MARGIN_Y = 8;

    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register(this::renderHud);
    }

    private void renderHud(DrawContext context, RenderTickCounter tickCounter) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (client.player == null || client.options.hudHidden) {
            return;
        }

        int totalMoney = calculateTotalMoney(client);
        String display = "Kasa: " + totalMoney + " zł";

        int screenWidth = context.getScaledWindowWidth();
        int textWidth = client.textRenderer.getWidth(display);

        // Rysowanie w prawym górnym rogu ekranu
        int x = screenWidth - textWidth - MARGIN_X;
        int y = MARGIN_Y;

        context.drawTextWithShadow(client.textRenderer, Text.literal(display), x, y, TEXT_COLOR);
    }

    private int calculateTotalMoney(MinecraftClient client) {
        int total = 0;

        // Główny ekwipunek (razem z paskiem szybkiego dostępu)
        for (ItemStack stack : client.player.getInventory().main) {
            total += valueOf(stack);
        }

        // Druga ręka (offhand)
        for (ItemStack stack : client.player.getInventory().offHand) {
            total += valueOf(stack);
        }

        return total;
    }

    private int valueOf(ItemStack stack) {
        if (stack.isEmpty()) {
            return 0;
        }
        if (stack.getItem() == ModItems.MONETA_1ZL) {
            return stack.getCount() * 1;
        }
        if (stack.getItem() == ModItems.MONETA_50ZL) {
            return stack.getCount() * 50;
        }
        if (stack.getItem() == ModItems.MONETA_100ZL) {
            return stack.getCount() * 100;
        }
        return 0;
    }
}
