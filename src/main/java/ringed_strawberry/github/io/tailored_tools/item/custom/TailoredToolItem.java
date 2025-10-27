package ringed_strawberry.github.io.tailored_tools.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.Nullable;
import ringed_strawberry.github.io.tailored_tools.item.component.ModItemComponents;
import ringed_strawberry.github.io.tailored_tools.util.ToolUtil;

public class TailoredToolItem extends Item {
    public TailoredToolItem(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {
        stack.set(ModItemComponents.MAX_DURABILITY, ToolUtil.getMaxDurability(stack));
        super.inventoryTick(stack, world, entity, slot);
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return super.isItemBarVisible(stack);
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        return MathHelper.clamp(Math.round(13.0F - (float)stack.get(ModItemComponents.DURABILITY)* 13.0F / (float)stack.get(ModItemComponents.MAX_DURABILITY)), 0, 13);
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        return super.getItemBarColor(stack);
    }

    @Override
    public float getBonusAttackDamage(Entity target, float baseAttackDamage, DamageSource damageSource) {
        return ToolUtil.getAttackDamage(damageSource.getWeaponStack())-baseAttackDamage;
    }
}
