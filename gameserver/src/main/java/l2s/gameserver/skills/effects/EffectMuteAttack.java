package l2s.gameserver.skills.effects;

import l2s.gameserver.model.actor.instances.creature.Abnormal;
import l2s.gameserver.stats.Env;
import l2s.gameserver.templates.skill.EffectTemplate;

public class EffectMuteAttack extends Effect
{
	public EffectMuteAttack(Abnormal abnormal, Env env, EffectTemplate template)
	{
		super(abnormal, env, template);
	}

	@Override
	public void onStart()
	{
		if(getEffected().getFlags().getAMuted().start(this))
		{
			getEffected().abortCast(true, true);
			getEffected().abortAttack(true, true);
		}
	}

	@Override
	public void onExit()
	{
		getEffected().getFlags().getAMuted().stop(this);
	}
}