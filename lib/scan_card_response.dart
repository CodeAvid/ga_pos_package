import 'package:freezed_annotation/freezed_annotation.dart';

part 'scan_card_response.freezed.dart';
part 'scan_card_response.g.dart';

@freezed
class ScanCardResponse with _$ScanCardResponse {
  const factory ScanCardResponse({
    required String cardNumber,
    required String cardNumberRedacted,
    required String cardHolder,
    required String cardExpirationDate,
  }) = _ScanCardResponse;

  factory ScanCardResponse.fromJson(Map<String, dynamic> json) =>
      _$ScanCardResponseFromJson(json);
}
