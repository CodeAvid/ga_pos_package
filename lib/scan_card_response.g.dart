// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'scan_card_response.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

_$_ScanCardResponse _$$_ScanCardResponseFromJson(Map<String, dynamic> json) =>
    _$_ScanCardResponse(
      cardNumber: json['cardNumber'] as String,
      cardNumberRedacted: json['cardNumberRedacted'] as String,
      cardHolder: json['cardHolder'] as String,
      cardExpirationDate: json['cardExpirationDate'] as String,
    );

Map<String, dynamic> _$$_ScanCardResponseToJson(_$_ScanCardResponse instance) =>
    <String, dynamic>{
      'cardNumber': instance.cardNumber,
      'cardNumberRedacted': instance.cardNumberRedacted,
      'cardHolder': instance.cardHolder,
      'cardExpirationDate': instance.cardExpirationDate,
    };
