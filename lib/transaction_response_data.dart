import 'package:freezed_annotation/freezed_annotation.dart';

part 'transaction_response_data.freezed.dart';
part 'transaction_response_data.g.dart';

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

  factory TransactionResponseData.fromJson(Map<String, dynamic> json) =>
      _$TransactionResponseDataFromJson(json);
}
