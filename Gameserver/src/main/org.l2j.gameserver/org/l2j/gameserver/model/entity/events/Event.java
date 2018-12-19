package org.l2j.gameserver.model.entity.events;

import gnu.trove.iterator.TIntObjectIterator;
import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.hash.TIntObjectHashMap;
import org.l2j.commons.collections.MultiValueSet;
import org.l2j.commons.listener.Listener;
import org.l2j.commons.listener.ListenerList;
import org.l2j.commons.util.TroveUtils;
import org.l2j.gameserver.ThreadPoolManager;
import org.l2j.gameserver.dao.ItemsDAO;
import org.l2j.gameserver.instancemanager.ReflectionManager;
import org.l2j.gameserver.listener.event.OnStartStopListener;
import org.l2j.gameserver.model.*;
import org.l2j.gameserver.model.base.RestartType;
import org.l2j.gameserver.model.entity.Reflection;
import org.l2j.gameserver.model.entity.events.objects.DoorObject;
import org.l2j.gameserver.model.entity.events.objects.InitableObject;
import org.l2j.gameserver.model.entity.events.objects.SpawnableObject;
import org.l2j.gameserver.model.entity.events.objects.ZoneObject;
import org.l2j.gameserver.model.items.ItemInstance;
import org.l2j.gameserver.network.l2.components.IBroadcastPacket;
import org.l2j.gameserver.network.l2.components.SystemMsg;
import org.l2j.gameserver.network.l2.s2c.L2GameServerPacket;
import org.l2j.gameserver.network.l2.s2c.SystemMessagePacket;
import org.l2j.gameserver.stats.conditions.Condition;
import org.l2j.gameserver.templates.item.ItemTemplate;
import org.l2j.gameserver.utils.ItemFunctions;
import org.l2j.gameserver.utils.Location;
import org.l2j.gameserver.utils.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Future;

import static java.util.Objects.isNull;

/**
 * @author VISTALL
 * @date 12:54/10.12.2010
 */
public abstract class Event {

	public static final String EVENT = "event";

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected final TIntObjectMap<List<EventAction>> _onTimeActions = new TIntObjectHashMap<>();
	protected final List<EventAction> _onStartActions = new ArrayList<EventAction>(0);
	protected final List<EventAction> _onStopActions = new ArrayList<EventAction>(0);
	protected final List<EventAction> _onInitActions = new ArrayList<EventAction>(0);
	// objects
	protected final Map<Object, List<Object>> _objects = new HashMap<Object, List<Object>>(0);

	protected final int _id;
	protected final String _name;

	protected final ListenerListImpl _listenerList = new ListenerListImpl();

	protected TIntObjectMap<ItemInstance> _banishedItems = TroveUtils.emptyIntObjectMap();

	private List<Future<?>> _tasks = null;

	protected Event(MultiValueSet<String> set)
	{
		this(set.getInteger("id"), set.getString("name"));
	}

	protected Event(int id, String name)
	{
		_id = id;
		_name = name;
	}

	public void initEvent()
	{
		callActions(_onInitActions);

		reCalcNextTime(true);

		printInfo();
	}

	public void startEvent()
	{
		callActions(_onStartActions);

		_listenerList.onStart();
	}

	public void stopEvent(boolean force) {
		callActions(_onStopActions);

		_listenerList.onStop();
	}

	public void printInfo() {
		final long startSiegeMillis = startTimeMillis();

		if(startSiegeMillis == 0) {
			logger.info("{} time - undefined", getName());
		} else {
			logger.info("{} time - {}", getName(), TimeUtils.toSimpleFormat(startSiegeMillis));
		}
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "[" + getId() + ";" + getName() + "]";
	}


	protected void callActions(List<EventAction> actions) {
		for(EventAction action : actions)
			action.call(this);
	}

	public void addOnStartActions(List<EventAction> start) {
		_onStartActions.addAll(start);
	}

	public void addOnStopActions(List<EventAction> start)
	{
		_onStopActions.addAll(start);
	}

	public void addOnInitActions(List<EventAction> start)
	{
		_onInitActions.addAll(start);
	}

	public void addOnTimeAction(int time, EventAction action)
	{
		List<EventAction> list = _onTimeActions.get(time);
		if(list != null)
			list.add(action);
		else
		{
			List<EventAction> actions = new ArrayList<EventAction>(1);
			actions.add(action);
			_onTimeActions.put(time, actions);
		}
	}

	public void addOnTimeActions(int time, List<EventAction> actions)
	{
		if(actions.isEmpty())
			return;

		for(EventAction action : actions) {
			addOnTimeAction(time, action);
		}
	}

	public void timeActions(int time) {
		List<EventAction> actions = _onTimeActions.get(time);
		if(isNull(actions)) {
			logger.info("Undefined time : {}", time);
			return;
		}

		callActions(actions);
	}

	public int[] timeActions() {
		return _onTimeActions.keySet().toArray();
	}


	public synchronized void registerActions() {
		final long t = startTimeMillis();
		if(t == 0) {
			return;
		}

		if(isNull(_tasks)) {
			_tasks = new ArrayList<>(_onTimeActions.size());
		}

		final long c = System.currentTimeMillis();
		for(int key : _onTimeActions.keySet().toArray())
		{
			long time = t + key * 1000L;
			EventTimeTask wrapper = new EventTimeTask(this, key);
			if(time <= c)
				ThreadPoolManager.getInstance().execute(wrapper);
			else
			{
				Future<?> task = ThreadPoolManager.getInstance().schedule(wrapper, time - c);
				if(task != null)
					_tasks.add(task);
			}
		}
	}

	public synchronized void clearActions()
	{
		if(_tasks == null)
			return;

		for(Future<?> f : _tasks)
			f.cancel(false);

		_tasks.clear();
	}

	//===============================================================================================================
	//												Objects
	//===============================================================================================================
	public boolean containsObjects(Object name)
	{
		return _objects.get(name) != null;
	}

	@SuppressWarnings("unchecked")
	public <O> List<O> getObjects(Object name)
	{
		List<Object> objects = _objects.get(name);
		return objects == null ? Collections.<O>emptyList() : (List<O>)objects;
	}

	@SuppressWarnings("unchecked")
	public <O> O getFirstObject(Object name)
	{
		List<Object> objects = getObjects(name);
		return objects.size() > 0 ? (O) objects.get(0) : null;
	}

	public void addObject(Object name, Object object)
	{
		if(object == null)
			return;

		List<Object> list = _objects.get(name);
		if(list != null)
			list.add(object);
		else
		{
			list = new CopyOnWriteArrayList<Object>();
			list.add(object);
			_objects.put(name, list);
		}
	}

	public void removeObject(Object name, Object o)
	{
		if(o == null)
			return;

		List<Object> list = _objects.get(name);
		if(list != null)
			list.remove(o);
	}

	@SuppressWarnings("unchecked")
	public <O> List<O> removeObjects(Object name)
	{
		List<Object> objects = _objects.remove(name);
		return objects == null ? Collections.<O>emptyList() : (List<O>)objects;
	}

	public void addObjects(Object name, List<?> objects)
	{
		if(objects.isEmpty())
			return;

		List<Object> list = _objects.get(name);
		if(list != null)
			list.addAll(objects);
		else
			_objects.put(name, new CopyOnWriteArrayList<Object>(objects));
	}


	public Map<Object, List<Object>> getObjects() {
		return _objects;
	}

	public void spawnAction(Object name, boolean spawn) {
		List<Object> objects = getObjects(name);
		if(objects.isEmpty()) {
			logger.info("Undefined objects: {}", name);
			return;
		}

		for(Object object : objects) {
			if (object instanceof SpawnableObject) {
				if (spawn) {
					((SpawnableObject) object).spawnObject(this);
				} else {
					((SpawnableObject) object).despawnObject(this);
				}
			}
		}
	}

	public void respawnAction(Object name) {
		List<Object> objects = getObjects(name);
		if(objects.isEmpty()) {
			logger.info("Undefined objects: {}", name);
			return;
		}

		for(Object object : objects) {
			if (object instanceof SpawnableObject) {
				((SpawnableObject) object).respawnObject(this);
			}
		}
	}

	public void doorAction(Object name, boolean open) {
		List<Object> objects = getObjects(name);
		if(objects.isEmpty()) {
			logger.info("Undefined objects: {}", name);
			return;
		}

		for(Object object : objects) {
			if(object instanceof DoorObject) {
				if(open) {
					((DoorObject) object).open(this);
				} else {
					((DoorObject) object).close(this);
				}
			}
		}
	}

	public void zoneAction(Object name, boolean active) {
		List<Object> objects = getObjects(name);
		if(objects.isEmpty()) {
			logger.info("Undefined objects: {}", name);
			return;
		}

		for(Object object : objects)
			if(object instanceof ZoneObject)
				((ZoneObject) object).setActive(active, this);
	}

	public void initAction(Object name) {
		List<Object> objects = getObjects(name);
		if(objects.isEmpty()) {
			logger.info("Undefined objects: " + name);
			return;
		}

		for(Object object : objects) {
			if (object instanceof InitableObject) {
				((InitableObject) object).initObject(this);
			}
		}
	}

	public void action(String name, boolean start) {
		if(name.equalsIgnoreCase(EVENT)) {
			if(start) {
				startEvent();
			} else {
				stopEvent(false);
			}
		}
	}

	public void refreshAction(Object name) {
		List<Object> objects = getObjects(name);
		if(objects.isEmpty()) {
			logger.info("Undefined objects: {}", name);
			return;
		}

		for(Object object : objects) {
			if (object instanceof SpawnableObject) {
				((SpawnableObject) object).refreshObject(this);
			}
		}
	}


	//===============================================================================================================
	//												Broadcast
	//===============================================================================================================
	public void broadcastToWorld(IBroadcastPacket packet)
	{
		for(Player player : GameObjectsStorage.getPlayers())
			if(player != null)
				player.sendPacket(packet);
	}

	public void broadcastToWorld(L2GameServerPacket packet)
	{
		for(Player player : GameObjectsStorage.getPlayers())
			if(player != null)
				player.sendPacket(packet);
	}

	//===============================================================================================================
	//												Getters & Setters
	//===============================================================================================================
	public int getId()
	{
		return _id;
	}

	public String getName()
	{
		return _name;
	}

	public GameObject getCenterObject()
	{
		return null;
	}

	public Reflection getReflection()
	{
		return ReflectionManager.MAIN;
	}

	public int getRelation(Player thisPlayer, Player target, int oldRelation)
	{
		return oldRelation;
	}

	public int getUserRelation(Player thisPlayer, int oldRelation)
	{
		return oldRelation;
	}

	public void checkRestartLocs(Player player, Map<RestartType, Boolean> r)
	{
		//
	}

	public Location getRestartLoc(Player player, RestartType type)
	{
		return null;
	}

	public boolean canAttack(Creature target, Creature attacker, Skill skill, boolean force, boolean nextAttackCheck)
	{
		return false;
	}

	public SystemMsg checkForAttack(Creature target, Creature attacker, Skill skill, boolean force)
	{
		return null;
	}

	public SystemMsg canUseItem(Player player, ItemInstance item)
	{
		return null;
	}

	public boolean canUseSkill(Creature caster, Creature target, Skill skill)
	{
		return true;
	}

	public boolean canUseTeleport(Player player)
	{
		return true;
	}

	public boolean isInProgress()
	{
		return false;
	}

	public void findEvent(Player player)
	{
		//
	}

	public void announce(SystemMsg msgId, int a, int time)
	{
		throw new UnsupportedOperationException(getClass().getName() + " not implemented announce");
	}

	public void teleportPlayers(String teleportWho)
	{
		throw new UnsupportedOperationException(getClass().getName() + " not implemented teleportPlayers");
	}

	public boolean ifVar(String name)
	{
		throw new UnsupportedOperationException(getClass().getName() + " not implemented ifVar");
	}

	public List<Player> itemObtainPlayers()
	{
		throw new UnsupportedOperationException(getClass().getName() + " not implemented itemObtainPlayers");
	}

	public void giveItem(Player player, int itemId, long count)
	{
		switch(itemId)
		{
			case ItemTemplate.ITEM_ID_FAME:
				player.setFame(player.getFame() + (int)count, toString(), true);
				break;
			default:
				ItemFunctions.addItem(player, itemId, count);
				break;
		}
	}

	public List<Player> broadcastPlayers(int range)
	{
		throw new UnsupportedOperationException(getClass().getName() + " not implemented broadcastPlayers");
	}

	/**
	 * @param active   кто ресает
	 * @param target   кого ресает
	 * @param force    ктрл зажат ли
	 * @param quiet  если тру, мессаги об ошибке непосылаются
	 * @return  возращает можно ли реснуть цень
	 */
	public boolean canResurrect(Creature active, Creature target, boolean force, boolean quiet)
	{
		throw new UnsupportedOperationException(getClass().getName() + " not implemented canResurrect");
	}

	//===============================================================================================================
	//											setEvent helper
	//===============================================================================================================
	public void onAddEvent(GameObject o)
	{
		//
	}

	public void onRemoveEvent(GameObject o)
	{
		//
	}

	//===============================================================================================================
	//											Banish items
	//===============================================================================================================
	public void addBanishItem(ItemInstance item)
	{
		if(_banishedItems == TroveUtils.<ItemInstance>emptyIntObjectMap())
			_banishedItems = new TIntObjectHashMap<>();

		_banishedItems.put(item.getObjectId(), item);
	}

	public void removeBanishItems()
	{
		TIntObjectIterator<ItemInstance> iterator = _banishedItems.iterator();
		while(iterator.hasNext())
		{
			iterator.advance();

			ItemInstance item = ItemsDAO.getInstance().load(iterator.key());
			if(item != null)
			{
				if(item.getOwnerId() > 0)
				{
					GameObject object = GameObjectsStorage.findObject(item.getOwnerId());
					if(object != null && object.isPlayable())
					{
						((Playable)object).getInventory().destroyItem(item);
						object.getPlayer().sendPacket(SystemMessagePacket.removeItems(item));
					}
				}
				item.delete();
			}
			else
				item = iterator.value();

			item.deleteMe();
		}
	}

	//===============================================================================================================
	//											 Listeners
	//===============================================================================================================
	public void addListener(Listener<Event> l)
	{
		_listenerList.add(l);
	}

	public void removeListener(Listener<Event> l)
	{
		_listenerList.remove(l);
	}

	public void cloneTo(Event e)
	{
		for(EventAction a : _onInitActions)
			e._onInitActions.add(a);

		for(EventAction a : _onStartActions)
			e._onStartActions.add(a);

		for(EventAction a : _onStopActions)
			e._onStopActions.add(a);

		_onTimeActions.forEachEntry((key, value) -> {
			e.addOnTimeActions(key, value);
			return true;
		});
	}

	public String getVisibleName(Player player, Player observer) {
		return null;
	}

	public String getVisibleTitle(Player player, Player observer)
	{
		return null;
	}

	public Integer getVisibleNameColor(Player player, Player observer)
	{
		return null;
	}

	public Integer getVisibleTitleColor(Player player, Player observer)
	{
		return null;
	}

	public boolean isPledgeVisible(Player player, Player observer)
	{
		return true;
	}

	public boolean checkCondition(Creature creature, Class<? extends Condition> conditionClass)
	{
		return true;
	}

	public boolean isInZoneBattle(Creature creature) {
		return false;
	}

	public abstract void reCalcNextTime(boolean onInit);

	public abstract EventType getType();

	protected abstract long startTimeMillis();

	private class ListenerListImpl extends ListenerList<Event> {
		public void onStart() {
			for(Listener<Event> listener : getListeners())
				if(listener instanceof OnStartStopListener)
					((OnStartStopListener) listener).onStart(Event.this);
		}

		public void onStop() {
			for(Listener<Event> listener : getListeners())
				if(listener instanceof OnStartStopListener)
					((OnStartStopListener) listener).onStop(Event.this);
		}
	}
}