// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'key_exchange_response.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

_$_KeyExchangeResponse _$$_KeyExchangeResponseFromJson(
        Map<String, dynamic> json) =>
    _$_KeyExchangeResponse(
      billerId: json['BillerID'] as String,
      merchantId: json['MerchantID'] as String,
      terminalId: json['TerminalID'] as String,
      serialNumber: json['serialNumber'] as String,
      ptsp: json['PTSP'] as String,
      footerMessage: json['FooterMessage'] as String,
      state: json['State'] as String,
      city: json['City'] as String,
      merchantName: json['MerchantName'] as String,
      merchantAddress: json['MerchantAddress'] as String,
      bankName: json['BankName'] as String,
      bankLogo: json['BankLogo'] as String,
      merchantCategoryCode: json['MerchantCategoryCode'] as String,
      baseAppVersion: json['baseAppVersion'] as String,
    );

Map<String, dynamic> _$$_KeyExchangeResponseToJson(
        _$_KeyExchangeResponse instance) =>
    <String, dynamic>{
      'BillerID': instance.billerId,
      'MerchantID': instance.merchantId,
      'TerminalID': instance.terminalId,
      'serialNumber': instance.serialNumber,
      'PTSP': instance.ptsp,
      'FooterMessage': instance.footerMessage,
      'State': instance.state,
      'City': instance.city,
      'MerchantName': instance.merchantName,
      'MerchantAddress': instance.merchantAddress,
      'BankName': instance.bankName,
      'BankLogo': instance.bankLogo,
      'MerchantCategoryCode': instance.merchantCategoryCode,
      'baseAppVersion': instance.baseAppVersion,
    };
