package gregtech.core.hooks;

import com.mojang.realmsclient.gui.ChatFormatting;
import gregtech.api.util.FluidTooltipUtil;
import gregtech.api.util.LocalizationUtils;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

@SuppressWarnings("unused")
public class JEIHooks {

    public static void addFluidTooltip(List<String> tooltip, Object ingredient) {
        if (ingredient instanceof FluidStack) {
            List<String> formula = FluidTooltipUtil.getFluidTooltip(((FluidStack) ingredient).getFluid());
            if (formula != null && !formula.isEmpty()) {

                if(formula.size() > 2 && !formula.get(2).isEmpty()) {
                    String result = Boolean.parseBoolean(formula.get(2)) ? LocalizationUtils.format("gregtech.fluid.state_gas") :
                            LocalizationUtils.format("gregtech.fluid.state_liquid");
                    tooltip.add(1, result);
                }

                if(formula.size() > 1 && !formula.get(1).isEmpty()) {
                    tooltip.add(1, LocalizationUtils.format("gregtech.fluid.temperature", Integer.parseInt(formula.get(1))));
                }

                if(!formula.get(0).isEmpty()) {
                    tooltip.add(1, ChatFormatting.YELLOW + formula.get(0));
                }
            }
        }
    }
}
