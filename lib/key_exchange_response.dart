// ignore_for_file: invalid_annotation_target

import 'package:freezed_annotation/freezed_annotation.dart';

part 'key_exchange_response.freezed.dart';
part 'key_exchange_response.g.dart';

@freezed
class KeyExchangeResponse with _$KeyExchangeResponse {
  const factory KeyExchangeResponse({
    @JsonKey(name: 'BillerID') required String billerId,
    @JsonKey(name: 'MerchantID') required String merchantId,
    @JsonKey(name: 'TerminalID') required String terminalId,
    @JsonKey(name: 'serialNumber') required String serialNumber,
    @JsonKey(name: 'PTSP') required String ptsp,
    @JsonKey(name: 'FooterMessage') required String footerMessage,
    @JsonKey(name: 'State') required String state,
    @JsonKey(name: 'City') required String city,
    @JsonKey(name: 'MerchantName') required String merchantName,
    @JsonKey(name: 'MerchantAddress') required String merchantAddress,
    @JsonKey(name: 'BankName') required String bankName,
    @JsonKey(name: 'BankLogo') required String bankLogo,
    @JsonKey(name: 'MerchantCategoryCode') required String merchantCategoryCode,
    @JsonKey(name: 'baseAppVersion') required String baseAppVersion,
  }) = _KeyExchangeResponse;

  factory KeyExchangeResponse.fromJson(Map<String, dynamic> json) =>
      _$KeyExchangeResponseFromJson(json);
}
