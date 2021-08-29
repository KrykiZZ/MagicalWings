package dev.kirakun.magicalwings;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod("magicalwings")
public class MagicalWings
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "magicalwings");

    /*
    <-------- Any  Wings -------->
    | FEATHER  | DUST | FEATHER  |
    | SHEATING | STAR | SHEATING |
    | FEATHER  |      | FEATHER  |

    <----------- Fairy Dust ----------->
    | BLAZE    | GLOWSTONE  | BLAZE    |
    | REDSTONE | ENDER DUST | REDSTONE |
    | BLAZE    | GLOWSTONE  | BLAZE    |
     */
    public static final RegistryObject<Item> NETHERITE_WINGS = ITEMS.register("netherite_wings", () -> new Wings("netherite_wings", ArmorMaterial.NETHERITE, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> DIAMOND_WINGS = ITEMS.register("diamond_wings", () -> new Wings("diamond_wings", ArmorMaterial.DIAMOND, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> GOLD_WINGS = ITEMS.register("gold_wings", () -> new Wings("gold_wings", ArmorMaterial.GOLD, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> IRON_WINGS = ITEMS.register("iron_wings", () -> new Wings("iron_wings", ArmorMaterial.IRON, EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> LEATHER_WINGS = ITEMS.register("leather_wings", () -> new LeatherWings("leather_wings", ArmorMaterial.LEATHER, EquipmentSlotType.CHEST));

    public static final RegistryObject<Item> FAIRY_DUST = ITEMS.register("fairy_dust",  () -> new UniversalItem(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> FLOATING_FEATHER = ITEMS.register("floating_feather",  () -> new UniversalItem(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> FLOATING_LEATHER = ITEMS.register("floating_leather",  () -> new UniversalItem(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> ENDER_DUST = ITEMS.register("ender_dust",  () -> new UniversalItem(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));

    public static final RegistryObject<Item> NETHERITE_SHEATING = ITEMS.register("netherite_sheating",  () -> new UniversalItem(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> DIAMOND_SHEATING = ITEMS.register("diamond_sheating",  () -> new UniversalItem(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> GOLD_SHEATING = ITEMS.register("gold_sheating",  () -> new UniversalItem(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> IRON_SHEATING = ITEMS.register("iron_sheating",  () -> new UniversalItem(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
    public static final RegistryObject<Item> LEATHER_SHEATING = ITEMS.register("leather_sheating",  () -> new UniversalItem(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));

    public MagicalWings()
    {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        MinecraftForge.EVENT_BUS.register(this);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
    }

    private void doClientStuff(final FMLClientSetupEvent event)
    {

    }
}
