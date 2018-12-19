package events;

import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.hash.TIntObjectHashMap;
import org.l2j.commons.collections.MultiValueSet;
import org.l2j.commons.time.cron.SchedulingPattern;
import org.l2j.commons.util.Rnd;
import org.l2j.commons.util.Util;
import org.l2j.gameserver.model.entity.events.EventAction;
import org.l2j.gameserver.model.entity.events.actions.StartStopAction;

import java.util.ArrayList;
import java.util.List;

public class RepeatingFunEvent extends FunEvent
{
	private final SchedulingPattern _repeatPattern;
	private final int _random;
	private TIntObjectMap<List<EventAction>> _storedOnTimeActions = null;

	public RepeatingFunEvent(MultiValueSet<String> set)
	{
		super(set);
		final String repeat = set.getString("repeat_time_pattern", null);
		_repeatPattern = repeat != null ? new SchedulingPattern(repeat) : null;
		_random = set.getInteger("random", 0);
	}

	@Override
	public void reCalcNextTime(boolean onInit)
	{
		final long stopTime = _stopPattern.getTimeInMillis();
		final long currentTime = System.currentTimeMillis();
		if (currentTime >= stopTime) // event already finished ?
			return;

		long startTime = _startPattern.getTimeInMillis();
		if (startTime < currentTime && _repeatPattern != null) // event already started
			startTime = _repeatPattern.next(currentTime + 60000L);

		if (_random > 0)
			startTime += Rnd.get(-_random, _random) * 1000L;
		if (startTime <= currentTime)
			startTime = currentTime + 60000L;

		final long dff = stopTime - startTime;
		if (dff <= 0) // next start time after event finish
			return;

		if (onInit)
		{
			if (!_onTimeActions.isEmpty())
			{
				_storedOnTimeActions = new TIntObjectHashMap<>();
				_onTimeActions.forEachEntry((key, value) -> {
					if(!Util.isNullOrEmpty(value)) {
						List<EventAction> newList = new ArrayList<>(value.size());
						newList.addAll(value);
						_storedOnTimeActions.put(key, newList);
					}
					return true;
				});
			}
		}
		else
		{
			clearActions();
			_onTimeActions.clear();

			if (_storedOnTimeActions != null) {
				_storedOnTimeActions.forEachEntry((key, value) -> {
					List<EventAction> newList = new ArrayList<EventAction>(value.size());
					newList.addAll(value);
					_onTimeActions.put(key, newList);
					return true;
				});
			}
		}

		addOnTimeAction(0, new StartStopAction(StartStopAction.EVENT, true));
		addOnTimeAction((int)(dff / 1000L), new StartStopAction(StartStopAction.EVENT, false));

		_startTime = startTime;
		registerActions();
	}
}