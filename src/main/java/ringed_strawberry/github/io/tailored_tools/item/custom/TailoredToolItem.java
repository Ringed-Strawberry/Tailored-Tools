package ringed_strawberry.github.io.tailored_tools.item.custom;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipData;
import net.minecraft.text.Text;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.world.World;
import ringed_strawberry.github.io.tailored_tools.item.component.ModItemComponents;
import ringed_strawberry.github.io.tailored_tools.util.ToolUtil;

import java.util.Objects;
import java.util.Optional;

public class TailoredToolItem extends Item {
    public TailoredToolItem(Settings settings) {
        super(settings);
    }

    @Override
    public float getMiningSpeed(ItemStack stack, BlockState state) {
        return ToolUtil.getMiningSpeed(stack, state);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        stack.set(ModItemComponents.MAX_DURABILITY, ToolUtil.getMaxDurability(stack));
        super.inventoryTick(stack, world, entity, slot, selected);
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
        return ColorHelper.Argb.getArgb(255, 0, 0);
    }


    //UI
    @Override
    public Text getName(ItemStack stack) {
        return Text.of( ToolUtil.getMaterialName(stack, "head", true) + " " + ToolUtil.getToolPartName(stack, "head", true));
    }

    @Override
    public Optional<TooltipData> getTooltipData(ItemStack stack) {

        return super.getTooltipData(stack);
    }


}
