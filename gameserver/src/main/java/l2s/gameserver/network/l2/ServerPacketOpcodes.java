package l2s.gameserver.network.l2;

/**
 * @author Bonux
 * @version Ertheia 603
**/
public enum ServerPacketOpcodes
{
	/*0x00*/	DiePacket,
	/*0x01*/	RevivePacket,
	/*0x02*/	AttackOutofRangePacket,
	/*0x03*/	AttackinCoolTimePacket,
	/*0x04*/	AttackDeadTargetPacket,
	/*0x05*/	SpawnItemPacket,
	/*0x06*/	SellListPacket,
	/*0x07*/	BuyListPacket,
	/*0x08*/	DeleteObjectPacket,
	/*0x09*/	CharacterSelectionInfoPacket,
	/*0x0A*/	LoginResultPacket,
	/*0x0B*/	CharacterSelectedPacket,
	/*0x0C*/	NpcInfoPacket,
	/*0x0D*/	NewCharacterSuccessPacket,
	/*0x0E*/	NewCharacterFailPacket,
	/*0x0F*/	CharacterCreateSuccessPacket,
	/*0x10*/	CharacterCreateFailPacket,
	/*0x11*/	ItemListPacket,
	/*0x12*/	SunRisePacket,
	/*0x13*/	SunSetPacket,
	/*0x14*/	TradeStartPacket,
	/*0x15*/	TradeStartOkPacket,
	/*0x16*/	DropItemPacket,
	/*0x17*/	GetItemPacket,
	/*0x18*/	StatusUpdatePacket,
	/*0x19*/	NpcHtmlMessagePacket,
	/*0x1A*/	TradeOwnAddPacket,
	/*0x1B*/	TradeOtherAddPacket,
	/*0x1C*/	TradeDonePacket,
	/*0x1D*/	CharacterDeleteSuccessPacket,
	/*0x1E*/	CharacterDeleteFailPacket,
	/*0x1F*/	ActionFailPacket,
	/*0x20*/	SeverClosePacket,
	/*0x21*/	InventoryUpdatePacket,
	/*0x22*/	TeleportToLocationPacket,
	/*0x23*/	TargetSelectedPacket,
	/*0x24*/	TargetUnselectedPacket,
	/*0x25*/	AutoAttackStartPacket,
	/*0x26*/	AutoAttackStopPacket,
	/*0x27*/	SocialActionPacket,
	/*0x28*/	ChangeMoveTypePacket,
	/*0x29*/	ChangeWaitTypePacket,
	/*0x2A*/	ManagePledgePowerPacket,
	/*0x2B*/	CreatePledgePacket,
	/*0x2C*/	AskJoinPledgePacket,
	/*0x2D*/	JoinPledgePacket,
	/*0x2E*/	VersionCheckPacket,
	/*0x2F*/	MTLPacket,
	/*0x30*/	NSPacket,
	/*0x31*/	CIPacket,
	/*0x32*/	UIPacket,
	/*0x33*/	AttackPacket,
	/*0x34*/	WithdrawalPledgePacket,
	/*0x35*/	OustPledgeMemberPacket,
	/*0x36*/	SetOustPledgeMemberPacket,
	/*0x37*/	DismissPledgePacket,
	/*0x38*/	SetDismissPledgePacket,
	/*0x39*/	AskJoinPartyPacket,
	/*0x3A*/	JoinPartyPacket,
	/*0x3B*/	WithdrawalPartyPacket,
	/*0x3C*/	OustPartyMemberPacket,
	/*0x3D*/	SetOustPartyMemberPacket,
	/*0x3E*/	DismissPartyPacket,
	/*0x3F*/	SetDismissPartyPacket,
	/*0x40*/	MagicAndSkillList,
	/*0x41*/	WareHouseDepositListPacket,
	/*0x42*/	WareHouseWithdrawListPacket,
	/*0x43*/	WareHouseDonePacket,
	/*0x44*/	ShortCutRegisterPacket,
	/*0x45*/	ShortCutInitPacket,
	/*0x46*/	ShortCutDeletePacket,
	/*0x47*/	StopMovePacket,
	/*0x48*/	MagicSkillUse,
	/*0x49*/	MagicSkillCanceled,
	/*0x4A*/	SayPacket2,
	/*0x4B*/	NpcInfoAbnormalVisualEffect,
	/*0x4C*/	DoorInfoPacket,
	/*0x4D*/	DoorStatusUpdatePacket,
	/*0x4E*/	PartySmallWindowAllPacket,
	/*0x4F*/	PartySmallWindowAddPacket,
	/*0x50*/	PartySmallWindowDeleteAllPacket,
	/*0x51*/	PartySmallWindowDeletePacket,
	/*0x52*/	PartySmallWindowUpdatePacket,
	/*0x53*/	TradePressOwnOkPacket,
	/*0x54*/	MagicSkillLaunchedPacket,
	/*0x55*/	FriendAddRequestResult,
	/*0x56*/	FriendAdd,
	/*0x57*/	FriendRemove,
	/*0x58*/	FriendList,
	/*0x59*/	FriendStatus,
	/*0x5A*/	PledgeShowMemberListAllPacket,
	/*0x5B*/	PledgeShowMemberListUpdatePacket,
	/*0x5C*/	PledgeShowMemberListAddPacket,
	/*0x5D*/	PledgeShowMemberListDeletePacket,
	/*0x5E*/	MagicListPacket,
	/*0x5F*/	SkillListPacket,
	/*0x60*/	VehicleInfoPacket,
	/*0x61*/	FinishRotatingPacket,
	/*0x62*/	SystemMessagePacket,
	/*0x63*/	StartPledgeWarPacket,
	/*0x64*/	ReplyStartPledgeWarPacket,
	/*0x65*/	StopPledgeWarPacket,
	/*0x66*/	ReplyStopPledgeWarPacket,
	/*0x67*/	SurrenderPledgeWarPacket,
	/*0x68*/	ReplySurrenderPledgeWarPacket,
	/*0x69*/	SetPledgeCrestPacket,
	/*0x6A*/	PledgeCrestPacket,
	/*0x6B*/	SetupGaugePacket,
	/*0x6C*/	VehicleDeparturePacket,
	/*0x6D*/	VehicleCheckLocationPacket,
	/*0x6E*/	GetOnVehiclePacket,
	/*0x6F*/	GetOffVehiclePacket,
	/*0x70*/	TradeRequestPacket,
	/*0x71*/	RestartResponsePacket,
	/*0x72*/	MoveToPawnPacket,
	/*0x73*/	SSQInfoPacket,
	/*0x74*/	GameGuardQueryPacket,
	/*0x75*/	L2FriendListPacket,
	/*0x76*/	L2FriendPacket,
	/*0x77*/	L2FriendStatusPacket,
	/*0x78*/	L2FriendSayPacket,
	/*0x79*/	ValidateLocationPacket,
	/*0x7A*/	StartRotatingPacket,
	/*0x7B*/	ShowBoardPacket,
	/*0x7C*/	ChooseInventoryItemPacket,
	/*0x7D*/	DummyPacket1,
	/*0x7E*/	MoveToLocationInVehiclePacket,
	/*0x7F*/	StopMoveInVehiclePacket,
	/*0x80*/	ValidateLocationInVehiclePacket,
	/*0x81*/	TradeUpdatePacket,
	/*0x82*/	TradePressOtherOkPacket,
	/*0x83*/	FriendAddRequest,
	/*0x84*/	LogOutOkPacket,
	/*0x85*/	AbnormalStatusUpdatePacket,
	/*0x86*/	QuestListPacket,
	/*0x87*/	EnchantResultPacket,
	/*0x88*/	PledgeShowMemberListDeleteAllPacket,
	/*0x89*/	PledgeInfoPacket,
	/*0x8A*/	PledgeExtendedInfoPacket,
	/*0x8B*/	SummonInfoPacket,
	/*0x8C*/	RidePacket,
	/*0x8D*/	DummyPacket2,
	/*0x8E*/	PledgeShowInfoUpdatePacket,
	/*0x8F*/	ClientActionPacket,
	/*0x90*/	AcquireSkillListPacket,
	/*0x91*/	AcquireSkillInfoPacket,
	/*0x92*/	ServerObjectInfoPacket,
	/*0x93*/	GMHidePacket,
	/*0x94*/	AcquireSkillDonePacket,
	/*0x95*/	GMViewCharacterInfoPacket,
	/*0x96*/	GMViewPledgeInfoPacket,
	/*0x97*/	GMViewSkillInfoPacket,
	/*0x98*/	GMViewMagicInfoPacket,
	/*0x99*/	GMViewQuestInfoPacket,
	/*0x9A*/	GMViewItemListPacket,
	/*0x9B*/	GMViewWarehouseWithdrawListPacket,
	/*0x9C*/	ListPartyWaitingPacket,
	/*0x9D*/	PartyRoomInfoPacket,
	/*0x9E*/	PlaySoundPacket,
	/*0x9F*/	StaticObjectPacket,
	/*0xA0*/	PrivateStoreManageList,
	/*0xA1*/	PrivateStoreList,
	/*0xA2*/	PrivateStoreMsg,
	/*0xA3*/	ShowMinimapPacket,
	/*0xA4*/	ReviveRequestPacket,
	/*0xA5*/	AbnormalVisualEffectPacket,
	/*0xA6*/	TutorialShowHtmlPacket,
	/*0xA7*/	ShowTutorialMarkPacket,
	/*0xA8*/	TutorialEnableClientEventPacket,
	/*0xA9*/	TutorialCloseHtmlPacket,
	/*0xAA*/	ShowRadarPacket,
	/*0xAB*/	WithdrawAlliancePacket,
	/*0xAC*/	OustAllianceMemberPledgePacket,
	/*0xAD*/	DismissAlliancePacket,
	/*0xAE*/	SetAllianceCrestPacket,
	/*0xAF*/	AllianceCrestPacket,
	/*0xB0*/	ServerCloseSocketPacket,
	/*0xB1*/	PetStatusShowPacket,
	/*0xB2*/	MyPetSummonInfoPacket,
	/*0xB3*/	PetItemListPacket,
	/*0xB4*/	PetInventoryUpdatePacket,
	/*0xB5*/	AllianceInfoPacket,
	/*0xB6*/	PetStatusUpdatePacket,
	/*0xB7*/	PetDeletePacket,
	/*0xB8*/	DeleteRadarPacket,
	/*0xB9*/	MyTargetSelectedPacket,
	/*0xBA*/	PartyMemberPositionPacket,
	/*0xBB*/	AskJoinAlliancePacket,
	/*0xBC*/	JoinAlliancePacket,
	/*0xBD*/	PrivateStoreBuyManageList,
	/*0xBE*/	PrivateStoreBuyList,
	/*0xBF*/	PrivateStoreBuyMsg,
	/*0xC0*/	VehicleStartPacket,
	/*0xC1*/	NpcInfoState,
	/*0xC2*/	StartAllianceWarPacket,
	/*0xC3*/	ReplyStartAllianceWarPacket,
	/*0xC4*/	StopAllianceWarPacket,
	/*0xC5*/	ReplyStopAllianceWarPacket,
	/*0xC6*/	SurrenderAllianceWarPacket,
	/*0xC7*/	SkillCoolTimePacket,
	/*0xC8*/	PackageToListPacket,
	/*0xC9*/	CastleSiegeInfoPacket,
	/*0xCA*/	CastleSiegeAttackerListPacket,
	/*0xCB*/	CastleSiegeDefenderListPacket,
	/*0xCC*/	NickNameChangedPacket,
	/*0xCD*/	PledgeStatusChangedPacket,
	/*0xCE*/	RelationChangedPacket,
	/*0xCF*/	EventTriggerPacket,
	/*0xD0*/	MultiSellListPacket,
	/*0xD1*/	SetSummonRemainTimePacket,
	/*0xD2*/	PackageSendableListPacket,
	/*0xD3*/	EarthQuakePacket,
	/*0xD4*/	FlyToLocationPacket,
	/*0xD5*/	BlockListPacket,
	/*0xD6*/	SpecialCameraPacket,
	/*0xD7*/	NormalCameraPacket,
	/*0xD8*/	SkillRemainSecPacket,
	/*0xD9*/	NetPingPacket,
	/*0xDA*/	DicePacket,
	/*0xDB*/	SnoopPacket,
	/*0xDC*/	RecipeBookItemListPacket,
	/*0xDD*/	RecipeItemMakeInfoPacket,
	/*0xDE*/	RecipeShopManageListPacket,
	/*0xDF*/	RecipeShopSellListPacket,
	/*0xE0*/	RecipeShopItemInfoPacket,
	/*0xE1*/	RecipeShopMsgPacket,
	/*0xE2*/	ShowCalcPacket,
	/*0xE3*/	MonRaceInfoPacket,
	/*0xE4*/	HennaItemInfoPacket,
	/*0xE5*/	HennaInfoPacket,
	/*0xE6*/	HennaUnequipListPacket,
	/*0xE7*/	HennaUnequipInfoPacket,
	/*0xE8*/	MacroListPacket,
	/*0xE9*/	BuyListSeedPacket,
	/*0xEA*/	ShowTownMapPacket,
	/*0xEB*/	ObserverStartPacket,
	/*0xEC*/	ObserverEndPacket,
	/*0xED*/	ChairSitPacket,
	/*0xEE*/	HennaEquipListPacket,
	/*0xEF*/	SellListProcurePacket,
	/*0xF0*/	GMHennaInfoPacket,
	/*0xF1*/	RadarControlPacket,
	/*0xF2*/	ClientSetTimePacket,
	/*0xF3*/	ConfirmDlgPacket,
	/*0xF4*/	PartySpelledPacket,
	/*0xF5*/	ShopPreviewListPacket,
	/*0xF6*/	ShopPreviewInfoPacket,
	/*0xF7*/	CameraModePacket,
	/*0xF8*/	ShowXMasSealPacket,
	/*0xF9*/	EtcStatusUpdatePacket,
	/*0xFA*/	ShortBuffStatusUpdatePacket,
	/*0xFB*/	SSQStatusPacket,
	/*0xFC*/	PetitionVotePacket,
	/*0xFD*/	AgitDecoInfoPacket,
	/*0xFE*/	ExDummyPacket1(0x00),
	/*0xFE*/	ExRegenMaxPacket(0x01),
	/*0xFE*/	ExEventMatchUserInfoPacket(0x02),
	/*0xFE*/	ExColosseumFenceInfoPacket(0x03),
	/*0xFE*/	ExEventMatchSpelledInfoPacket(0x04),
	/*0xFE*/	ExEventMatchFirecrackerPacket(0x05),
	/*0xFE*/	ExEventMatchTeamUnlockedPacket(0x06),
	/*0xFE*/	ExEventMatchGMTestPacket(0x07),
	/*0xFE*/	ExPartyRoomMemberPacket(0x08),
	/*0xFE*/	ExClosePartyRoomPacket(0x09),
	/*0xFE*/	ExManagePartyRoomMemberPacket(0xA),
	/*0xFE*/	ExEventMatchLockResult(0xB),
	/*0xFE*/	ExAutoSoulShot(0xC),
	/*0xFE*/	ExEventMatchListPacket(0xD),
	/*0xFE*/	ExEventMatchObserverPacket(0xE),
	/*0xFE*/	ExEventMatchMessagePacket(0xF),
	/*0xFE*/	ExEventMatchScorePacket(0x10),
	/*0xFE*/	ExServerPrimitivePacket(0x11),
	/*0xFE*/	ExOpenMPCCPacket(0x12),
	/*0xFE*/	ExCloseMPCCPacket(0x13),
	/*0xFE*/	ExShowCastleInfo(0x14),
	/*0xFE*/	ExShowFortressInfo(0x15),
	/*0xFE*/	ExShowAgitInfo(0x16),
	/*0xFE*/	ExShowFortressSiegeInfo(0x17),
	/*0xFE*/	ExPartyPetWindowAdd(0x18),
	/*0xFE*/	ExPartyPetWindowUpdate(0x19),
	/*0xFE*/	ExAskJoinMPCCPacket(0x1A),
	/*0xFE*/	ExPledgeEmblem(0x1B),
	/*0xFE*/	ExEventMatchTeamInfoPacket(0x1C),
	/*0xFE*/	ExEventMatchCreatePacket(0x1D),
	/*0xFE*/	ExFishingStartPacket(0x1E),
	/*0xFE*/	ExFishingEndPacket(0x1F),
	/*0xFE*/	ExShowQuestInfoPacket(0x20),
	/*0xFE*/	ExShowQuestMarkPacket(0x21),
	/*0xFE*/	ExSendManorListPacket(0x22),
	/*0xFE*/	ExShowSeedInfoPacket(0x23),
	/*0xFE*/	ExShowCropInfoPacket(0x24),
	/*0xFE*/	ExShowManorDefaultInfoPacket(0x25),
	/*0xFE*/	ExShowSeedSettingPacket(0x26),
	/*0xFE*/	ExFishingStartCombatPacket(0x27),
	/*0xFE*/	ExFishingHpRegenPacket(0x28),
	/*0xFE*/	ExEnchantSkillListPacket(0x29),
	/*0xFE*/	ExEnchantSkillInfoPacket(0x2A),
	/*0xFE*/	ExShowCropSettingPacket(0x2B),
	/*0xFE*/	ExShowSellCropListPacket(0x2C),
	/*0xFE*/	ExOlympiadMatchEndPacket(0x2D),
	/*0xFE*/	ExMailArrivedPacket(0x2E),
	/*0xFE*/	ExStorageMaxCountPacket(0x2F),
	/*0xFE*/	ExEventMatchManagePacket(0x30),
	/*0xFE*/	ExMultiPartyCommandChannelInfoPacket(0x31),
	/*0xFE*/	ExPCCafePointInfoPacket(0x32),
	/*0xFE*/	ExSetCompassZoneCode(0x33),
	/*0xFE*/	ExGetBossRecord(0x34),
	/*0xFE*/	ExAskJoinPartyRoom(0x35),
	/*0xFE*/	ExListPartyMatchingWaitingRoom(0x36),
	/*0xFE*/	ExSetMpccRouting(0x37),
	/*0xFE*/	ExShowAdventurerGuideBook(0x38),
	/*0xFE*/	ExShowScreenMessage(0x39),
	/*0xFE*/	PledgeSkillListPacket(0x3A),
	/*0xFE*/	PledgeSkillListAddPacket(0x3B),
	/*0xFE*/	PledgeSkillListRemovePacket(0x3C),
	/*0xFE*/	PledgePowerGradeList(0x3D),
	/*0xFE*/	PledgeReceivePowerInfo(0x3E),
	/*0xFE*/	PledgeReceiveMemberInfo(0x3F),
	/*0xFE*/	PledgeReceiveWarList(0x40),
	/*0xFE*/	PledgeReceiveSubPledgeCreated(0x41),
	/*0xFE*/	ExRedSkyPacket(0x42),
	/*0xFE*/	PledgeReceiveUpdatePower(0x43),
	/*0xFE*/	FlySelfDestinationPacket(0x44),
	/*0xFE*/	ShowPCCafeCouponShowUI(0x45),
	/*0xFE*/	ExSearchOrc(0x46),
	/*0xFE*/	ExCursedWeaponList(0x47),
	/*0xFE*/	ExCursedWeaponLocation(0x48),
	/*0xFE*/	ExRestartClient(0x49),
	/*0xFE*/	ExRequestHackShield(0x4A),
	/*0xFE*/	ExUseSharedGroupItem(0x4B),
	/*0xFE*/	ExMPCCShowPartyMemberInfo(0x4C),
	/*0xFE*/	ExDuelAskStart(0x4D),
	/*0xFE*/	ExDuelReady(0x4E),
	/*0xFE*/	ExDuelStart(0x4F),
	/*0xFE*/	ExDuelEnd(0x50),
	/*0xFE*/	ExDuelUpdateUserInfo(0x51),
	/*0xFE*/	ExShowVariationMakeWindow(0x52),
	/*0xFE*/	ExShowVariationCancelWindow(0x53),
	/*0xFE*/	ExPutItemResultForVariationMake(0x54),
	/*0xFE*/	ExPutIntensiveResultForVariationMake(0x55),
	/*0xFE*/	ExPutCommissionResultForVariationMake(0x56),
	/*0xFE*/	ExVariationResult(0x57),
	/*0xFE*/	ExPutItemResultForVariationCancel(0x58),
	/*0xFE*/	ExVariationCancelResult(0x59),
	/*0xFE*/	ExDuelEnemyRelation(0x5A),
	/*0xFE*/	ExPlayAnimation(0x5B),
	/*0xFE*/	ExMPCCPartyInfoUpdate(0x5C),
	/*0xFE*/	ExPlayScene(0x5D),
	/*0xFE*/	ExSpawnEmitterPacket(0x5E),
	/*0xFE*/	ExEnchantSkillInfoDetailPacket(0x5F),
	/*0xFE*/	ExBasicActionList(0x60),
	/*0xFE*/	ExAirShipInfo(0x61),
	/*0xFE*/	ExAttributeEnchantResultPacket(0x62),
	/*0xFE*/	ExChooseInventoryAttributeItemPacket(0x63),
	/*0xFE*/	ExGetOnAirShipPacket(0x64),
	/*0xFE*/	ExGetOffAirShipPacket(0x65),
	/*0xFE*/	ExMoveToLocationAirShipPacket(0x66),
	/*0xFE*/	ExStopMoveAirShipPacket(0x67),
	/*0xFE*/	ExShowTracePacket(0x68),
	/*0xFE*/	ExItemAuctionInfoPacket(0x69),
	/*0xFE*/	ExNeedToChangeName(0x6A),
	/*0xFE*/	ExPartyPetWindowDelete(0x6B),
	/*0xFE*/	ExTutorialList(0x6C),
	/*0xFE*/	ExRpItemLink(0x6D),
	/*0xFE*/	ExMoveToLocationInAirShipPacket(0x6E),
	/*0xFE*/	ExStopMoveInAirShipPacket(0x6F),
	/*0xFE*/	ExValidateLocationInAirShipPacket(0x70),
	/*0xFE*/	ExUISettingPacket(0x71),
	/*0xFE*/	ExMoveToTargetInAirShipPacket(0x72),
	/*0xFE*/	ExAttackInAirShipPacket(0x73),
	/*0xFE*/	ExMagicSkillUseInAirShipPacket(0x74),
	/*0xFE*/	ExShowBaseAttributeCancelWindow(0x75),
	/*0xFE*/	ExBaseAttributeCancelResult(0x76),
	/*0xFE*/	ExSubPledgetSkillAdd(0x77),
	/*0xFE*/	ExResponseFreeServer(0x78),
	/*0xFE*/	ExShowProcureCropDetailPacket(0x79),
	/*0xFE*/	ExHeroListPacket(0x7A),
	/*0xFE*/	ExOlympiadUserInfoPacket(0x7B),
	/*0xFE*/	ExOlympiadSpelledInfoPacket(0x7C),
	/*0xFE*/	ExOlympiadModePacket(0x7D),
	/*0xFE*/	ExShowFortressMapInfo(0x7E),
	/*0xFE*/	ExPVPMatchRecord(0x7F),
	/*0xFE*/	ExPVPMatchUserDie(0x80),
	/*0xFE*/	ExPrivateStoreWholeMsg(0x81),
	/*0xFE*/	ExPutEnchantTargetItemResult(0x82),
	/*0xFE*/	ExPutEnchantSupportItemResult(0x83),
	/*0xFE*/	ExChangeNicknameNColor(0x84),
	/*0xFE*/	ExGetBookMarkInfoPacket(0x85),
	/*0xFE*/	ExNotifyPremiumItem(0x86),
	/*0xFE*/	ExGetPremiumItemListPacket(0x87),
	/*0xFE*/	ExPeriodicItemList(0x88),
	/*0xFE*/	ExJumpToLocation(0x89),
	/*0xFE*/	ExPVPMatchCCRecord(0x8A),
	/*0xFE*/	ExPVPMatchCCMyRecord(0x8B),
	/*0xFE*/	ExPVPMatchCCRetire(0x8C),
	/*0xFE*/	ExShowTerritory(0x8D),
	/*0xFE*/	ExNpcQuestHtmlMessage(0x8E),
	/*0xFE*/	ExSendUIEventPacket(0x8F),
	/*0xFE*/	ExNotifyBirthDay(0x90),
	/*0xFE*/	ExShowDominionRegistry(0x91),
	/*0xFE*/	ExReplyRegisterDominion(0x92),
	/*0xFE*/	ExReplyDominionInfo(0x93),
	/*0xFE*/	ExShowOwnthingPos(0x94),
	/*0xFE*/	ExCleftList(0x95),
	/*0xFE*/	ExCleftState(0x96),
	/*0xFE*/	ExDominionChannelSet(0x97),
	/*0xFE*/	ExBlockUpSetList(0x98),
	/*0xFE*/	ExBlockUpSetState(0x99),
	/*0xFE*/	ExStartScenePlayer(0x9A),
	/*0xFE*/	ExAirShipTeleportList(0x9B),
	/*0xFE*/	ExMpccRoomInfo(0x9C),
	/*0xFE*/	ExListMpccWaiting(0x9D),
	/*0xFE*/	ExDissmissMpccRoom(0x9E),
	/*0xFE*/	ExManageMpccRoomMember(0x9F),
	/*0xFE*/	ExMpccRoomMember(0xA0),
	/*0xFE*/	ExVitalityPointInfo(0xA1),
	/*0xFE*/	ExShowSeedMapInfo(0xA2),
	/*0xFE*/	ExMpccPartymasterList(0xA3),
	/*0xFE*/	ExDominionWarStart(0xA4),
	/*0xFE*/	ExDominionWarEnd(0xA5),
	/*0xFE*/	ExShowLines(0xA6),
	/*0xFE*/	ExPartyMemberRenamed(0xA7),
	/*0xFE*/	ExEnchantSkillResult(0xA8),
	/*0xFE*/	ExRefundList(0xA9),
	/*0xFE*/	ExNoticePostArrived(0xAA),
	/*0xFE*/	ExShowReceivedPostList(0xAB),
	/*0xFE*/	ExReplyReceivedPost(0xAC),
	/*0xFE*/	ExShowSentPostList(0xAD),
	/*0xFE*/	ExReplySentPost(0xAE),
	/*0xFE*/	ExResponseShowStepOne(0xAF),
	/*0xFE*/	ExResponseShowStepTwo(0xB0),
	/*0xFE*/	ExResponseShowContents(0xB1),
	/*0xFE*/	ExShowPetitionHtml(0xB2),
	/*0xFE*/	ExReplyPostItemList(0xB3),
	/*0xFE*/	ExChangePostState(0xB4),
	/*0xFE*/	ExReplyWritePost(0xB5),
	/*0xFE*/	ExInitializeSeed(0xB6),
	/*0xFE*/	ExRaidReserveResult(0xB7),
	/*0xFE*/	ExBuySellListPacket(0xB8),
	/*0xFE*/	ExCloseRaidSocket(0xB9),
	/*0xFE*/	ExPrivateMarketListPacket(0xBA),
	/*0xFE*/	ExRaidCharacterSelected(0xBB),
	/*0xFE*/	ExAskCoupleAction(0xBC),
	/*0xFE*/	ExBrBroadcastEventState(0xBD),
	/*0xFE*/	ExBR_LoadEventTopRankersPacket(0xBE),
	/*0xFE*/	ExChangeNPCState(0xBF),
	/*0xFE*/	ExAskModifyPartyLooting(0xC0),
	/*0xFE*/	ExSetPartyLooting(0xC1),
	/*0xFE*/	ExRotation(0xC2),
	/*0xFE*/	ExChangeClientEffectInfo(0xC3),
	/*0xFE*/	ExMembershipInfo(0xC4),
	/*0xFE*/	ExReplyHandOverPartyMaster(0xC5),
	/*0xFE*/	ExQuestNpcLogList(0xC6),
	/*0xFE*/	ExQuestItemListPacket(0xC7),
	/*0xFE*/	ExGMViewQuestItemListPacket(0xC8),
	/*0xFE*/	ExRestartResponse(0xC9),
	/*0xFE*/	ExVoteSystemInfoPacket(0xCA),
	/*0xFE*/	ExShuttleInfoPacket(0xCB),
	/*0xFE*/	ExSuttleGetOnPacket(0xCC),
	/*0xFE*/	ExSuttleGetOffPacket(0xCD),
	/*0xFE*/	ExSuttleMovePacket(0xCE),
	/*0xFE*/	ExMTLInSuttlePacket(0xCF),
	/*0xFE*/	ExStopMoveInShuttlePacket(0xD0),
	/*0xFE*/	ExValidateLocationInShuttlePacket(0xD1),
	/*0xFE*/	ExAgitAuctionCmdPacket(0xD2),
	/*0xFE*/	ExConfirmAddingPostFriend(0xD3),
	/*0xFE*/	ExReceiveShowPostFriend(0xD4),
	/*0xFE*/	ExReceiveOlympiadPacket(0xD5),
	/*0xFE*/	ExBR_GamePointPacket(0xD6),
	/*0xFE*/	ExBR_ProductListPacket(0xD7),
	/*0xFE*/	ExBR_ProductInfoPacket(0xD8),
	/*0xFE*/	ExBR_BuyProductPacket(0xD9),
	/*0xFE*/	ExBR_PremiumStatePacket(0xDA),
	/*0xFE*/	ExBrExtraUserInfo(0xDB),
	/*0xFE*/	ExBrBuffEventState(0xDC),
	/*0xFE*/	ExBR_RecentProductListPacket(0xDD),
	/*0xFE*/	ExBR_MinigameLoadScoresPacket(0xDE),
	/*0xFE*/	ExBR_AgathionEnergyInfoPacket(0xDF),
	/*0xFE*/	ExShowChannelingEffectPacket(0xE0),
	/*0xFE*/	ExGetCrystalizingEstimation(0xE1),
	/*0xFE*/	ExGetCrystalizingFail(0xE2),
	/*0xFE*/	ExNavitAdventPointInfoPacket(0xE3),
	/*0xFE*/	ExNavitAdventEffectPacket(0xE4),
	/*0xFE*/	ExNavitAdventTimeChangePacket(0xE5),
	/*0xFE*/	ExAbnormalStatusUpdateFromTargetPacket(0xE6),
	/*0xFE*/	ExStopScenePlayerPacket(0xE7),
	/*0xFE*/	ExFlyMove(0xE8),
	/*0xFE*/	ExDynamicQuestPacket(0xE9),
	/*0xFE*/	ExSubjobInfo(0xEA),
	/*0xFE*/	ExChangeMPCost(0xEB),
	/*0xFE*/	ExFriendDetailInfo(0xEC),
	/*0xFE*/	ExBlockAddResult(0xED),
	/*0xFE*/	ExBlockRemoveResult(0xEE),
	/*0xFE*/	ExBlockDefailInfo(0xEF),
	/*0xFE*/	ExLoadInzonePartyHistory(0xF0),
	/*0xFE*/	ExFriendNotifyNameChange(0xF1),
	/*0xFE*/	ExShowCommission(0xF2),
	/*0xFE*/	ExResponseCommissionItemList(0xF3),
	/*0xFE*/	ExResponseCommissionInfo(0xF4),
	/*0xFE*/	ExResponseCommissionRegister(0xF5),
	/*0xFE*/	ExResponseCommissionDelete(0xF6),
	/*0xFE*/	ExResponseCommissionList(0xF7),
	/*0xFE*/	ExResponseCommissionBuyInfo(0xF8),
	/*0xFE*/	ExResponseCommissionBuyItem(0xF9),
	/*0xFE*/	ExAcquirableSkillListByClass(0xFA),
	/*0xFE*/	ExMagicAttackInfo(0xFB),
	/*0xFE*/	ExAcquireSkillInfo(0xFC),
	/*0xFE*/	ExNewSkillToLearnByLevelUp(0xFD),
	/*0xFE*/	ExCallToChangeClass(0xFE),
	/*0xFE*/	ExChangeToAwakenedClass(0xFF),
	/*0xFE*/	ExTacticalSign(0x100),
	/*0xFE*/	ExLoadStatWorldRank(0x101),
	/*0xFE*/	ExLoadStatUser(0x102),
	/*0xFE*/	ExLoadStatHotLink(0x103),
	/*0xFE*/	ExGetWebSessionID(0x104),
	/*0xFE*/	Ex2NDPasswordCheckPacket(0x105),
	/*0xFE*/	Ex2NDPasswordVerifyPacket(0x106),
	/*0xFE*/	Ex2NDPasswordAckPacket(0x107),
	/*0xFE*/	ExFlyMoveBroadcast(0x108),
	/*0xFE*/	ExShowUsmPacket(0x109),
	/*0xFE*/	ExShowStatPage(0x10A),
	/*0xFE*/	ExIsCharNameCreatable(0x10B),
	/*0xFE*/	ExGoodsInventoryChangedNotiPacket(0x10C),
	/*0xFE*/	ExGoodsInventoryInfoPacket(0x10D),
	/*0xFE*/	ExGoodsInventoryResultPacket(0x10E),
	/*0xFE*/	ExAlterSkillRequest(0x10F),
	/*0xFE*/	ExNotifyFlyMoveStart(0x110),
	/*0xFE*/	ExDummyPacket2(0x111),
	/*0xFE*/	ExCloseCommission(0x112),
	/*0xFE*/	ExChangeAttributeItemList(0x113),
	/*0xFE*/	ExChangeAttributeInfo(0x114),
	/*0xFE*/	ExChangeAttributeOk(0x115),
	/*0xFE*/	ExChangeAttributeFail(0x116),
	/*0xFE*/	ExLightingCandleEvent(0x117),
	/*0xFE*/	ExVitalityEffectInfo(0x118),
	/*0xFE*/	ExLoginVitalityEffectInfo(0x119),
	/*0xFE*/	ExBR_PresentBuyProductPacket(0x11A),
	/*0xFE*/	ExMentorList(0x11B),
	/*0xFE*/	ExMentorAdd(0x11C),
	/*0xFE*/	ListMenteeWaitingPacket(0x11D),
	/*0xFE*/	ExInzoneWaitingInfo(0x11E),
	/*0xFE*/	ExCuriousHouseState(0x11F),
	/*0xFE*/	ExCuriousHouseEnter(0x120),
	/*0xFE*/	ExCuriousHouseLeave(0x121),
	/*0xFE*/	ExCuriousHouseMemberList(0x122),
	/*0xFE*/	ExCuriousHouseMemberUpdate(0x123),
	/*0xFE*/	ExCuriousHouseRemainTime(0x124),
	/*0xFE*/	ExCuriousHouseResult(0x125),
	/*0xFE*/	ExCuriousHouseObserveList(0x126),
	/*0xFE*/	ExCuriousHouseObserveMode(0x127),
	/*0xFE*/	ExSysstring(0x128),
	/*0xFE*/	ExChoose_Shape_Shifting_Item(0x129),
	/*0xFE*/	ExPut_Shape_Shifting_Target_Item_Result(0x12A),
	/*0xFE*/	ExPut_Shape_Shifting_Extraction_Item_Result(0x12B),
	/*0xFE*/	ExShape_Shifting_Result(0x12C),
	/*0xFE*/	ExCastleState(0x12D),
	/*0xFE*/	ExNCGuardReceiveDataFromServer(0x12E),
	/*0xFE*/	ExKalieEvent(0x12F),
	/*0xFE*/	ExKalieEventJackpotUser(0x130),
	/*0xFE*/	ExAbnormalVisualEffectInfo(0x131),
	/*0xFE*/	ExNpcInfoSpeed(0x132),
	/*0xFE*/	ExSetPledgeEmblemAck(0x133),
	/*0xFE*/	ExShowBeautyMenuPacket(0x134),
	/*0xFE*/	ExResponseBeautyListPacket(0x135),
	/*0xFE*/	ExResponseBeautyRegistResetPacket(0x136),
	/*0xFE*/	ExResponseResetListPacket(0x137),
	/*0xFE*/	ExShuffleSeedAndPublicKey(0x138),
	/*0xFE*/	ExCheck_SpeedHack(0x139),
	/*0xFE*/	ExBR_NewIConCashBtnWnd(0x13A),
	/*0xFE*/	ExEvent_Campaign_Info(0x13B),
	/*0xFE*/	ExUnReadMailCount(0x13C),
	/*0xFE*/	ExPledgeCount(0x13D),
	/*0xFE*/	ExAdenaInvenCount(0x13E),
	/*0xFE*/	ExPledgeRecruitInfo(0x13F),
	/*0xFE*/	ExPledgeRecruitApplyInfo(0x140),
	/*0xFE*/	ExPledgeRecruitBoardSearch(0x141),
	/*0xFE*/	ExPledgeRecruitBoardDetail(0x142),
	/*0xFE*/	ExPledgeWaitingListApplied(0x143),
	/*0xFE*/	ExPledgeWaitingList(0x144),
	/*0xFE*/	ExPledgeWaitingUser(0x145),
	/*0xFE*/	ExPledgeDraftListSearch(0x146),
	/*0xFE*/	ExPledgeWaitingListAlarm(0x147),
	/*0xFE*/	ExValidateActiveCharacter(0x148),
	/*0xFE*/	ExCloseCommissionRegister(0x149),
	/*0xFE*/	ExTeleportToLocationActivate(0x14A),
	/*0xFE*/	ExNotifyWebPetitionReplyAlarm(0x14B),
	/*0xFE*/	ExEventShowXMasWishCard(0x14C),
	/*0xFE*/	ExInvitation_Event_UI_Setting(0x14D),
	/*0xFE*/	ExInvitation_Event_Ink_Energy(0x14E),
	/*0xFE*/	Ex_Check_Abusing(0x14F),
	/*0xFE*/	ExGMVitalityEffectInfo(0x150),
	/*0xFE*/	ExPathToAwakeningAlarm(0x151),
	/*0xFE*/	ExPutEnchantScrollItemResult(0x152),
	/*0xFE*/	ExRemoveEnchantSupportItemResult(0x153),
	/*0xFE*/	ExShowCardRewardList(0x154),
	/*0xFE*/	ExGmViewCharacterInfo(0x155),
	/*0xFE*/	ExUserInfoEquipSlot(0x156),
	/*0xFE*/	ExUserInfoCubic(0x157),
	/*0xFE*/	ExUserInfoAbnormalVisualEffect(0x158),
	/*0xFE*/	ExUserInfoFishing(0x159),
	/*0xFE*/	ExPartySpelledInfoUpdate(0x15A),
	/*0xFE*/	ExDivideAdenaStart(0x15B),
	/*0xFE*/	ExDivideAdenaCancel(0x15C),
	/*0xFE*/	ExDivideAdenaDone(0x15D),
	/*0xFE*/	PetInfoPacket(0x15E),
	/*0xFE*/	ExAcquireAPSkillList(0x15F),
	/*0xFE*/	ExStartLuckyGame(0x160),
	/*0xFE*/	ExBettingLuckyGameResult(0x161),
	/*0xFE*/	ExTrainingZone_Admission(0x162),
	/*0xFE*/	ExTrainingZone_Leaving(0x163),
	/*0xFE*/	ExPeriodicHenna(0x164),
	/*0xFE*/	ExShowAPListWnd(0x165),
	/*0xFE*/	ExUserInfoInvenWeight(0x166),
	/*0xFE*/	ExCloseAPListWnd(0x167),
	/*0xFE*/	ExEnchantOneOK(0x168),
	/*0xFE*/	ExEnchantOneFail(0x169),
	/*0xFE*/	ExEnchantOneRemoveOK(0x16A),
	/*0xFE*/	ExEnchantOneRemoveFail(0x16B),
	/*0xFE*/	ExEnchantTwoOK(0x16C),
	/*0xFE*/	ExEnchantTwoFail(0x16D),
	/*0xFE*/	ExEnchantTwoRemoveOK(0x16E),
	/*0xFE*/	ExEnchantTwoRemoveFail(0x16F),
	/*0xFE*/	ExEnchantSucess(0x170),
	/*0xFE*/	ExEnchantFail(0x171),
	/*0xFE*/	ExEnchantRetryToPutItemOk(0x172),
	/*0xFE*/	ExEnchantRetryToPutItemFail(0x173),
	/*0xFE*/	ExAccountAttendanceInfo(0x174),
	/*0xFE*/	ExWorldChatCnt(0x175),
	/*0xFE*/	ExAlchemySkillList(0x176),
	/*0xFE*/	ExTryMixCube(0x177),
	/*0xFE*/	ExAlchemyConversion(0x178),
	/*0xFE*/	ExBeautyItemList(0x179),
	/*0xFE*/	ExReceiveClientINI(0x17A),
	/*0xFE*/	ExAutoFishAvailable(0x17B),
	/*0xFE*/	ExChannlChatEnterWorld(0x17C),
	/*0xFE*/	ExChannlChatPlegeInfo(0x17D),
	/*0xFE*/	ExVipAttendanceItemList(0x17E),
	/*0xFE*/	ExConfirmVipAttendanceCheck(0x17F),
	/*0xFE*/	ExShowEnsoulWindow(0x180),
	/*0xFE*/	ExEnsoulResult(0x181),
	/*0xFE*/	ExMultiSellResult(0x182),
	/*0xFE*/	ExCastleWarSeasonResult(0x183),
	/*0xFE*/	ExCastleWarSeasonReward(0x184),
	/*0xFE*/	ReciveVipProductList(0x185),
	/*0xFE*/	ReciveVipLuckyGameInfo(0x186),
	/*0xFE*/	ReciveVipLuckyGameItemList(0x187),
	/*0xFE*/	ReciveVipLuckyGameResult(0x188),
	/*0xFE*/	ReciveVipInfo(0x189),
	/*0xFE*/	ReciveVipInfoRemainTime(0x18A),
	/*0xFE*/	ReceiveVipBotCaptchaImage(0x18B),
	/*0xFE*/	ReceiveVipBotCaptchaAnswerResult(0x18C),
	/*0xFE*/	ExPledgeSigninForOpenJoiningMethod(0x18D),
	/*0xFE*/	ExRequestMatchArena(0x18E),
	/*0xFE*/	ExCompleteMatchArena(0x18F),
	/*0xFE*/	ExConfirmMatchArena(0x190),
	/*0xFE*/	ExCancelMatchArena(0x191),
	/*0xFE*/	ExStartChooseClassArena(0x192),
	/*0xFE*/	ExChangeClassArena(0x193),
	/*0xFE*/	ExConfirmClassArena(0x194),
	/*0xFE*/	ExStartBattleReadyArena(0x195),
	/*0xFE*/	ExBattleReadyArena(0x196),
	/*0xFE*/	ExDecoNPCInfo(0x197),
	/*0xFE*/	ExDecoNPCSet(0x198),
	/*0xFE*/	ExFactionInfo(0x199),
	/*0xFE*/	ExBattleResultArena(0x19A),
	/*0xFE*/	ExClosingArena(0x19B),
	/*0xFE*/	ExClosedArena(0x19C),
	/*0xFE*/	ExDieInArena(0x19D),
	/*0xFE*/	DummyPacket(0x19E),
	/*0xFE*/	ExArenaDashboard(0x19F),
	/*0xFE*/	ExArenaUpdateEquipSlot(0x1A0),
	/*0xFE*/	ExArenaKillInfo(0x1A1),
	/*0xFE*/	ExExitArena(0x1A2),
	/*0xFE*/	ExBalthusEvent(0x1A3),
	/*0xFE*/	ExBalthusEventJackpotUser(0x1A4),
	/*0xFE*/	ExPartyMatchingRoomHistory(0x1A5),
	/*0xFE*/	ExAIContentUIEvent(0x1A6),
	/*0xFE*/	ExArenaCustomNotification(0x1A7),
	/*0xFE*/	ExOneDayReceiveRewardList(0x1A8),
	/*0xFE*/	ExConnectedTimeAndGettableReward(0x1A9),
	/*0xFE*/	ExTodoListRecommand(0x1AA),
	/*0xFE*/	ExTodoListInzone(0x1AB),
	/*0xFE*/	ExTodoListHTML(0x1AC),
	/*0xFE*/	ExQueueTicket(0x1AD),
	/*0xFE*/	ExPledgeBonusOpen(0x1AE),
	/*0xFE*/	ExPledgeBonusList(0x1AF),
	/*0xFE*/	ExPledgeBonusMarkReset(0x1B0),
	/*0xFE*/	ExPledgeBonusUpdate(0x1B1),
	/*0xFE*/	ExSSOAuthnToken(0x1B2),
	/*0xFE*/	ExQueueTicketLogin(0x1B3),
	/*0xFE*/	ExEnSoulExtractionShow(0x1B4),
	/*0xFE*/	ExEnSoulExtractionResult(0x1B5),
	/*0xFE*/	ExFieldEventStep(0x1B6),
	/*0xFE*/	ExFieldEventPoint(0x1B7),
	/*0xFE*/	ExFieldEventEffect(0x1B8),
	/*0xFE*/	ExRaidBossSpawnInfo(0x1B9),
	/*0xFE*/	ExRaidServerInfo(0x1BA),
	/*0xFE*/	ExShowAgitSiegeInfo(0x1BB),
	/*0xFE*/	ExItemAuctionStatus(0x1BC),
	/*0xFE*/	ExMonsterBook(0x1BD),
	/*0xFE*/	ExMonsterBookRewardIcon(0x1BE),
	/*0xFE*/	ExMonsterBookOnFactionUI(0x1BF),
	/*0xFE*/	ExMonsterBookOpenResult(0x1C0),
	/*0xFE*/	ExMonsterBookCloseForce(0x1C1),
	/*0xFE*/	ExFactionLevelUpNotify(0x1C2),
	/*0xFE*/	ExItemAuctionNextInfoPacket(0x1C3),
	/*0xFE*/	ExItemAuctionUpdatedBiddingInfoPacket(0x1C4),
	/*0xFE*/	ExPrivateStoreBuyingResult(0x1C5),
	/*0xFE*/	ExPrivateStoreSellingResult(0x1C6),
	/*0xFE*/	ExEnterWorldPacket(0x1C7),
	/*0xFE*/	ExMatchGroup(0x1C8),
	/*0xFE*/	ExMatchGroupAsk(0x1C9),
	/*0xFE*/	ExMatchGroupWithdraw(0x1CA),
	/*0xFE*/	ExMatchGroupOust(0x1CB),
	/*0xFE*/	ExArenaShowEnemyPartyLocation(0x1CC),
	/*0xFE*/	ExDressRoomUIOpen(0x1CD),
	/*0xFE*/	ExDressHangerList(0x1CE),
	/*0xFE*/	ExShowUpgradeSystem(0x1CF),
	/*0xFE*/	ExUpgradeSystemResult(0x1D0),
	/*0xFE*/	ExUserBanInfo(0x1D1);

	public static final ServerPacketOpcodes[] VALUES = values();

	private final int _exOrdinal;

	private ServerPacketOpcodes(int exOrdinal)
	{
		_exOrdinal = exOrdinal;
	}

	private ServerPacketOpcodes()
	{
		this(-1);
	}

	public int getId()
	{
		int ordinal = super.ordinal();
		if(ordinal >= 0xFE)
			return 0xFE;
		return ordinal;
	}

	public int getExId()
	{
		return _exOrdinal;
	}
}