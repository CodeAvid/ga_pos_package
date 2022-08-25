class ResultData {
  String? transactionData;
  String? message;
  ResponseData? data;

  ResultData.fromJson(Map<String, dynamic> json) {
    transactionData = json['transaction_data'];
    message = json['message'];
    data = json['data'] != null ? ResponseData.fromJson(json['data']) : null;
  }
}

class ResponseData {
  String? aid;
  String? amount;
  String? appLabel;
  String? authCode;
  String? bankLogo;
  String? bankName;
  String? cardHolderName;
  String? cashBackAmount;
  String? currency;
  String? customReference;
  String? datetime;
  String? deviceSerialNumber;
  String? expireDate;
  String? footerMessage;
  String? maskedPan;
  String? merchantAddress;
  String? merchantCategoryCode;
  String? merchantId;
  String? merchantName;
  String? message;
  String? nuban;
  String? pinType;
  String? ptsp;
  String? ptspContact;
  String? rrn;
  String? statusCode;
  String? stan;
  String? transactionType;
  String? terminalId;
  String? approved;

  ResponseData.fromJson(Map<String, dynamic> json) {
    aid = json['aid'];
    amount = json['amount'];
    appLabel = json['authCode'];
    bankLogo = json['bankLogo'];
    bankName = json['bankName'];
    cardHolderName = json['cardHolderName'];
    cashBackAmount = json['cashBackAmount'];
    currency = json['currency'];
    customReference = json['customReference'];
    datetime = json['datetime'];
    deviceSerialNumber = json['deviceSerialNumber'];
    expireDate = json['expireDate'];
    footerMessage = json['footerMessage'];
    maskedPan = json['maskedPan'];
    merchantAddress = json['merchantAddress'];
    merchantCategoryCode = json['merchantCategoryCode'];
    merchantId = json['merchantId'];
    merchantName = json['merchantName'];
    message = json['message'];
    nuban = json['nuban'];
    pinType = json['pinType'];
    ptsp = json['ptsp'];
    ptspContact = json['ptspContact'];
    rrn = json['rrn'];
    statusCode = json['statusCode'];
    stan = json['stan'];
    transactionType = json['transactionType'];
    terminalId = json['terminalId'];
    approved = json['approved'];
  }
}
