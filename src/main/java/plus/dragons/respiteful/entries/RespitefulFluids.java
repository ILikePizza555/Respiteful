package plus.dragons.respiteful.entries;

import com.teamabnormals.neapolitan.core.registry.NeapolitanItems;
import com.tterrag.registrate.builders.FluidBuilder;
import com.tterrag.registrate.providers.ProviderType;
import com.tterrag.registrate.util.entry.FluidEntry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.ForgeRegistries;
import plus.dragons.respiteful.RespitefulRegistrate;
import umpaz.farmersrespite.common.fluid.TeaFluidType;
import umpaz.farmersrespite.common.registry.FRItems;
import umpaz.farmersrespite.data.builder.KettlePouringRecipeBuilder;
import umpaz.farmersrespite.data.builder.KettleRecipeBuilder;

import static plus.dragons.respiteful.Respiteful.REGISTRATE;

public class RespitefulFluids {
    public static final FluidEntry<ForgeFlowingFluid.Flowing> MINT_GREEN_TEA = tea("mint_green_tea", 0x3CA874)
        .setData(ProviderType.RECIPE, (ctx, prov) -> {
            var source = ctx.get().getSource();
            var sourceId = ForgeRegistries.FLUIDS.getKey(source);
            assert sourceId != null;
            KettleRecipeBuilder
                .kettleRecipe(
                    new FluidStack(Fluids.WATER, 1000),
                    new FluidStack(ctx.get().getSource(), 1000),
                    2400,
                    0.35F
                )
                .addIngredient(NeapolitanItems.MINT_LEAVES.get())
                .addIngredient(FRItems.GREEN_TEA_LEAVES.get())
                .build(prov, sourceId.withPrefix("brewing/"));
            prov.accept(kettlePouring(sourceId, source, 250, Items.GLASS_BOTTLE, RespitefulItems.MINT_GREEN_TEA));
        })
        .register();

    public static final FluidEntry<ForgeFlowingFluid.Flowing> VANILLA_MILK_TEA = tea("vanilla_milk_tea", 0xD6AA73)
        .setData(ProviderType.RECIPE, (ctx, prov) -> {
            var source = ctx.get().getSource();
            var sourceId = ForgeRegistries.FLUIDS.getKey(source);
            assert sourceId != null;
            KettleRecipeBuilder
                .kettleRecipe(
                    new FluidStack(ForgeMod.MILK.get(), 1000),
                    new FluidStack(ctx.get().getSource(), 1000),
                    2400,
                    0.35F
                )
                .addIngredient(NeapolitanItems.DRIED_VANILLA_PODS.get())
                .addIngredient(FRItems.YELLOW_TEA_LEAVES.get())
                .build(prov, sourceId.withPrefix("brewing/"));
            prov.accept(kettlePouring(sourceId, source, 250, Items.GLASS_BOTTLE, RespitefulItems.VANILLA_MILK_TEA));
        })
        .register();

    public static final FluidEntry<ForgeFlowingFluid.Flowing> ADZUKI_MILK_TEA = tea("adzuki_milk_tea", 0xC16E6B)
        .setData(ProviderType.RECIPE, (ctx, prov) -> {
            var source = ctx.get().getSource();
            var sourceId = ForgeRegistries.FLUIDS.getKey(source);
            assert sourceId != null;
            KettleRecipeBuilder
                .kettleRecipe(
                    new FluidStack(ForgeMod.MILK.get(), 1000),
                    new FluidStack(ctx.get().getSource(), 1000),
                    2400,
                    0.35F
                )
                .addIngredient(NeapolitanItems.ROASTED_ADZUKI_BEANS.get())
                .addIngredient(FRItems.BLACK_TEA_LEAVES.get())
                .build(prov, sourceId.withPrefix("brewing/"));
            prov.accept(kettlePouring(sourceId, source, 250, Items.GLASS_BOTTLE, RespitefulItems.ADZUKI_MILK_TEA));
        })
        .register();

    public static final FluidEntry<ForgeFlowingFluid.Flowing> MOCHA_COFFEE = tea("mocha_coffee", 0x492B1B)
        .setData(ProviderType.RECIPE, (ctx, prov) -> {
            var source = ctx.get().getSource();
            var sourceId = ForgeRegistries.FLUIDS.getKey(source);
            assert sourceId != null;
            KettleRecipeBuilder
                .kettleRecipe(
                    new FluidStack(ForgeMod.MILK.get(), 1000),
                    new FluidStack(ctx.get().getSource(), 1000),
                    2400,
                    0.35F
                )
                .addIngredient(NeapolitanItems.CHOCOLATE_BAR.get())
                .addIngredient(FRItems.COFFEE_BEANS.get())
                .build(prov, sourceId.withPrefix("brewing/"));
            prov.accept(kettlePouring(sourceId, source, 250, Items.GLASS_BOTTLE, RespitefulItems.MOCHA_COFFEE));
        })
        .register();

    public static void register(IEventBus modBus) {
    }

    @SuppressWarnings("UnstableApiUsage")
    private static FluidBuilder<ForgeFlowingFluid.Flowing, RespitefulRegistrate> tea(String name, int tint) {
        return REGISTRATE.fluid(
            name,
            TeaFluidType.FLUID_STILL_TEXTURE,
            TeaFluidType.FLUID_FLOWING_TEXTURE,
            (prop, still, flow) -> new TeaFluidType(tint)
        ).noBlock().noBucket();
    }

    private static KettlePouringRecipeBuilder.Result kettlePouring(ResourceLocation id, Fluid fluid, int amount, ItemLike container, ItemLike result) {
        return new KettlePouringRecipeBuilder.Result(id.withPrefix("pouring/"), new ItemStack(container), fluid, amount, new ItemStack(result));
    }

}
