package plus.dragons.respiteful.common.item;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import plus.dragons.respiteful.RespitefulConfig;
import plus.dragons.respiteful.core.mixin.MobEffectInstanceAccessor;

public class EffectExtendingDrinkItem extends EffectTransformingDrinkItem {

    public EffectExtendingDrinkItem(MobEffectCategory category, Properties properties) {
        super(category, properties);
    }

    @Override
    protected boolean canTransformEffect(MobEffectInstance effect) {
        return effect.getAmplifier() > 0;
    }

    @Override
    protected MobEffectInstance transformEffect(MobEffectInstance effect) {
        var newEffect = new MobEffectInstance(
            effect.getEffect(),
            Math.min(effect.getDuration() * 2, RespitefulConfig.COMMON.snowTopDrinkEffectMaxDuration.get()),
            effect.getAmplifier() - 1,
            effect.isAmbient(),
            effect.isVisible(),
            effect.showIcon(),
            null,
            effect.getEffect().createFactorData()
        );
        var hiddenEffect = ((MobEffectInstanceAccessor) effect).getHiddenEffect();
        if (hiddenEffect != null) {
            if (this.canTransformEffect(hiddenEffect)) {
                hiddenEffect = this.transformEffect(hiddenEffect);
            }
            newEffect.update(hiddenEffect);
        }
        return newEffect;
    }

}
