import 'package:freezed_annotation/freezed_annotation.dart';

part 'transaction_response_data.freezed.dart';

@freezed
class TransactionResponseData with _$TransactionResponseData {
  const factory TransactionResponseData(
    String? aid,
    String? amount,
    String? cashBackAmount,
    String? appLabel,
    String? authcode,
    String? cardExpireDate,
    String? cardHolderName,
    String? datetime,
    String? maskedPan,
    String? message,
    String? nuban,
    String? pinType,
    String? rrn,
    String? stan,
    String? statuscode,
    String? terminalID,
    String? transactionType,
    String? merchantName,
    String? merchantId,
    String? merchantAddress,
    String? merchantCategoryCode,
    String? bankName,
    String? bankLogo,
    String? ptsp,
    String? ptspContact,
    String? footerMessage,
    String? deviceSerialNumber,
    String? baseAppVersion,
    String? currency,
  ) = _TransactionResponseData;

  factory TransactionResponseData.fromJson(Map<String?, dynamic> json) {
    final aid = json['aid'];
    final amount = json['amount'];
    final cashBackAmount = json['cashBackAmount'];
    final appLabel = json['appLabel'];
    final authcode = json['authcode'];
    final cardExpireDate = json['cardExpireDate'];
    final cardHolderName = json['cardHolderName'];
    final datetime = json['datetime'];
    final maskedPan = json['maskedPan'];
    final message = json['message'];
    final nuban = json['nuban'];
    final pinType = json['pinType'];
    final rrn = json['rrn'];
    final stan = json['stan'];
    final statuscode = json['statuscode'];
    final terminalID = json['terminalID'];
    final transactionType = json['transactionType'];
    final merchantName = json['merchantName'];
    final merchantId = json['merchantId'];
    final merchantAddress = json['merchantAddress'];
    final merchantCategoryCode = json['merchantCategoryCode'];
    final bankName = json['bankName'];
    final bankLogo = json['bankLogo'];
    final ptsp = json['ptsp'];
    final ptspContact = json['ptspContact'];
    final footerMessage = json['footerMessage'];
    final deviceSerialNumber = json['deviceSerialNumber'];
    final baseAppVersion = json['baseAppVersion'];
    final currency = json['currency'];

    return TransactionResponseData(
        aid,
        amount,
        cashBackAmount,
        appLabel,
        authcode,
        cardExpireDate,
        cardHolderName,
        datetime,
        maskedPan,
        message,
        nuban,
        pinType,
        rrn,
        stan,
        statuscode,
        terminalID,
        transactionType,
        merchantName,
        merchantId,
        merchantAddress,
        merchantCategoryCode,
        bankName,
        bankLogo,
        ptsp,
        ptspContact,
        footerMessage,
        deviceSerialNumber,
        baseAppVersion,
        currency);
  }
}
