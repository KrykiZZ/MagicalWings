package dev.kirakun.magicalwings.layers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import dev.kirakun.magicalwings.LeatherWings;
import dev.kirakun.magicalwings.Wings;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.pipeline.VertexBufferConsumer;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class WingsLayer<T extends LivingEntity, M extends EntityModel<T>> extends LayerRenderer<T, M>
{
    private static final ResourceLocation TEXTURE_NETHERITE = new ResourceLocation("magicalwings", "textures/entity/netherite_wings.png");
    private static final ResourceLocation TEXTURE_DIAMOND = new ResourceLocation("magicalwings", "textures/entity/diamond_wings.png");
    private static final ResourceLocation TEXTURE_IRON = new ResourceLocation("magicalwings", "textures/entity/iron_wings.png");
    private static final ResourceLocation TEXTURE_GOLD = new ResourceLocation("magicalwings", "textures/entity/gold_wings.png");
    private static final ResourceLocation TEXTURE_LEATHER = new ResourceLocation("magicalwings", "textures/entity/leather_wings.png");

    private final WingsModel<T> modelElytra = new WingsModel<>();

    public WingsLayer (IEntityRenderer<T, M> renderer)
    {
        super(renderer);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
    {
        ItemStack itemstack = entitylivingbaseIn.getItemBySlot(EquipmentSlotType.CHEST);
        Item item = itemstack.getItem();
        if (item instanceof Wings)
        {
            ResourceLocation resourceLocation;
            switch (item.getRegistryName().getPath())
            {
                case "netherite_wings": { resourceLocation = TEXTURE_NETHERITE; break; }
                case "diamond_wings": { resourceLocation = TEXTURE_DIAMOND; break; }
                case "iron_wings": { resourceLocation = TEXTURE_IRON; break; }
                case "gold_wings": { resourceLocation = TEXTURE_GOLD; break; }
                case "leather_wings": { resourceLocation = TEXTURE_LEATHER; break; }
                default: { resourceLocation = TEXTURE_LEATHER; break; }
            }

            matrixStackIn.pushPose();
            matrixStackIn.translate(0.0D, 0.0D, 0D);
            this.getParentModel().copyPropertiesTo(this.modelElytra);
            this.modelElytra.setupAnim(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            IVertexBuilder ivertexbuilder = ItemRenderer.getArmorFoilBuffer(bufferIn, RenderType.armorCutoutNoCull(resourceLocation), false, itemstack.hasFoil());

            if (item instanceof LeatherWings)
            {
                LeatherWings lwings = (LeatherWings)item;
                int color = lwings.getColor(itemstack);

                float red = (float) (color >> 16 & 255) / 255.0F;
                float green = (float) (color >> 8 & 255) / 255.0F;
                float blue = (float) (color & 255) / 255.0F;
                this.modelElytra.renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, red, green, blue, 1.0F);
            }
            else
            {
                this.modelElytra.renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            }
            matrixStackIn.popPose();
        }
    }
}
