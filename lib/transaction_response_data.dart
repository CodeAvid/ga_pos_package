class TransactionResponseData {
  String? aid;
  String? amount;
  String? cashBackAmount;
  String? appLabel;
  String? authcode;
  String? cardExpireDate;
  String? cardHolderName;
  String? datetime;
  String? maskedPan;
  String? message;
  String? nuban;
  String? pinType;
  String? rrn;
  String? stan;
  String? statuscode;
  String? terminalID;
  String? transactionType;
  String? merchantName;
  String? merchantId;
  String? merchantAddress;
  String? merchantCategoryCode;
  String? bankName;
  String? bankLogo;
  String? ptsp;
  String? ptspContact;
  String? footerMessage;
  String? deviceSerialNumber;
  String? baseAppVersion;
  String? currency;

  TransactionResponseData({
    this.aid,
    this.amount,
    this.cashBackAmount,
    this.appLabel,
    this.authcode,
    this.cardExpireDate,
    this.cardHolderName,
    this.datetime,
    this.maskedPan,
    this.message,
    this.nuban,
    this.pinType,
    this.rrn,
    this.stan,
    this.statuscode,
    this.terminalID,
    this.transactionType,
    this.merchantName,
    this.merchantId,
    this.merchantAddress,
    this.merchantCategoryCode,
    this.bankName,
    this.bankLogo,
    this.ptsp,
    this.ptspContact,
    this.footerMessage,
    this.deviceSerialNumber,
    this.baseAppVersion,
    this.currency,
  });

  TransactionResponseData.fromJson(dynamic json) {
    aid = json['aid'];
    amount = json['amount'];
    cashBackAmount = json['cashBackAmount'];
    appLabel = json['appLabel'];
    authcode = json['authcode'];
    cardExpireDate = json['cardExpireDate'];
    cardHolderName = json['cardHolderName'];
    datetime = json['datetime'];
    maskedPan = json['maskedPan'];
    message = json['message'];
    nuban = json['nuban'];
    pinType = json['pinType'];
    rrn = json['rrn'];
    stan = json['stan'];
    statuscode = json['statuscode'];
    terminalID = json['terminalID'];
    transactionType = json['transactionType'];
    merchantName = json['merchantName'];
    merchantId = json['merchantId'];
    merchantAddress = json['merchantAddress'];
    merchantCategoryCode = json['merchantCategoryCode'];
    bankName = json['bankName'];
    bankLogo = json['bankLogo'];
    ptsp = json['ptsp'];
    ptspContact = json['ptspContact'];
    footerMessage = json['footerMessage'];
    deviceSerialNumber = json['deviceSerialNumber'];
    baseAppVersion = json['baseAppVersion'];
    currency = json['currency'];
  }

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['aid'] = aid;
    map['amount'] = amount;
    map['cashBackAmount'] = cashBackAmount;
    map['appLabel'] = appLabel;
    map['authcode'] = authcode;
    map['cardExpireDate'] = cardExpireDate;
    map['cardHolderName'] = cardHolderName;
    map['datetime'] = datetime;
    map['maskedPan'] = maskedPan;
    map['message'] = message;
    map['nuban'] = nuban;
    map['pinType'] = pinType;
    map['rrn'] = rrn;
    map['stan'] = stan;
    map['statuscode'] = statuscode;
    map['terminalID'] = terminalID;
    map['transactionType'] = transactionType;
    map['merchantName'] = merchantName;
    map['merchantId'] = merchantId;
    map['merchantAddress'] = merchantAddress;
    map['merchantCategoryCode'] = merchantCategoryCode;
    map['bankName'] = bankName;
    map['bankLogo'] = bankLogo;
    map['ptsp'] = ptsp;
    map['ptspContact'] = ptspContact;
    map['footerMessage'] = footerMessage;
    map['deviceSerialNumber'] = deviceSerialNumber;
    map['baseAppVersion'] = baseAppVersion;
    map['currency'] = currency;
    return map;
  }
}
