package plus.dragons.respiteful.core.mixin;

import net.minecraft.world.effect.MobEffectInstance;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(MobEffectInstance.class)
public interface MobEffectInstanceAccessor {

    @Accessor
    @Nullable
    MobEffectInstance getHiddenEffect();

}
