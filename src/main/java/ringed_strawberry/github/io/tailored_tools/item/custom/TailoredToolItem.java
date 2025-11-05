package ringed_strawberry.github.io.tailored_tools.item.custom;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipData;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;
import ringed_strawberry.github.io.tailored_tools.item.component.ModItemComponents;
import ringed_strawberry.github.io.tailored_tools.util.ToolUtil;

import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

public class TailoredToolItem extends Item {
    public TailoredToolItem(Settings settings) {
        super(settings);
    }

    @Override
    public float getMiningSpeed(ItemStack stack, BlockState state) {
        return ToolUtil.getMiningSpeed(stack, state);
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {
        stack.set(ModItemComponents.MAX_DURABILITY, ToolUtil.getMaxDurability(stack));
        super.inventoryTick(stack, world, entity, slot);
    }


    @Override
    public float getBonusAttackDamage(Entity target, float baseAttackDamage, DamageSource damageSource) {
        return ToolUtil.getAttackDamage(damageSource.getWeaponStack())-baseAttackDamage;
    }

    //Durability
    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return !Objects.equals(stack.getOrDefault(ModItemComponents.DURABILITY, 0), stack.getOrDefault(ModItemComponents.MAX_DURABILITY, 0));
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        float ratio = (float) stack.getOrDefault(ModItemComponents.DURABILITY, 1) /stack.getOrDefault(ModItemComponents.MAX_DURABILITY, 1);
        return (int) (ratio*13);
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        return ColorHelper.getArgb(255, 0, 0);
    }


    //UI
    @Override
    public Text getName(ItemStack stack) {
        return Text.of( ToolUtil.getMaterialName(stack, "head", true) + " Tailored Tool");
    }

    @Override
    public Optional<TooltipData> getTooltipData(ItemStack stack) {

        return super.getTooltipData(stack);
    }


}
