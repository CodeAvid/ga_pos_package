// coverage:ignore-file
// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: type=lint
// ignore_for_file: unused_element, deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies, annotate_overrides, invalid_annotation_target

part of 'scan_card_response.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

final _privateConstructorUsedError = UnsupportedError(
    'It seems like you constructed your class using `MyClass._()`. This constructor is only meant to be used by freezed and you are not supposed to need it nor use it.\nPlease check the documentation here for more information: https://github.com/rrousselGit/freezed#custom-getters-and-methods');

ScanCardResponse _$ScanCardResponseFromJson(Map<String, dynamic> json) {
  return _ScanCardResponse.fromJson(json);
}

/// @nodoc
mixin _$ScanCardResponse {
  String get cardNumber => throw _privateConstructorUsedError;
  String get cardNumberRedacted => throw _privateConstructorUsedError;
  String get cardHolder => throw _privateConstructorUsedError;
  String get cardExpirationDate => throw _privateConstructorUsedError;

  Map<String, dynamic> toJson() => throw _privateConstructorUsedError;
  @JsonKey(ignore: true)
  $ScanCardResponseCopyWith<ScanCardResponse> get copyWith =>
      throw _privateConstructorUsedError;
}

/// @nodoc
abstract class $ScanCardResponseCopyWith<$Res> {
  factory $ScanCardResponseCopyWith(
          ScanCardResponse value, $Res Function(ScanCardResponse) then) =
      _$ScanCardResponseCopyWithImpl<$Res, ScanCardResponse>;
  @useResult
  $Res call(
      {String cardNumber,
      String cardNumberRedacted,
      String cardHolder,
      String cardExpirationDate});
}

/// @nodoc
class _$ScanCardResponseCopyWithImpl<$Res, $Val extends ScanCardResponse>
    implements $ScanCardResponseCopyWith<$Res> {
  _$ScanCardResponseCopyWithImpl(this._value, this._then);

  // ignore: unused_field
  final $Val _value;
  // ignore: unused_field
  final $Res Function($Val) _then;

  @pragma('vm:prefer-inline')
  @override
  $Res call({
    Object? cardNumber = null,
    Object? cardNumberRedacted = null,
    Object? cardHolder = null,
    Object? cardExpirationDate = null,
  }) {
    return _then(_value.copyWith(
      cardNumber: null == cardNumber
          ? _value.cardNumber
          : cardNumber // ignore: cast_nullable_to_non_nullable
              as String,
      cardNumberRedacted: null == cardNumberRedacted
          ? _value.cardNumberRedacted
          : cardNumberRedacted // ignore: cast_nullable_to_non_nullable
              as String,
      cardHolder: null == cardHolder
          ? _value.cardHolder
          : cardHolder // ignore: cast_nullable_to_non_nullable
              as String,
      cardExpirationDate: null == cardExpirationDate
          ? _value.cardExpirationDate
          : cardExpirationDate // ignore: cast_nullable_to_non_nullable
              as String,
    ) as $Val);
  }
}

/// @nodoc
abstract class _$$_ScanCardResponseCopyWith<$Res>
    implements $ScanCardResponseCopyWith<$Res> {
  factory _$$_ScanCardResponseCopyWith(
          _$_ScanCardResponse value, $Res Function(_$_ScanCardResponse) then) =
      __$$_ScanCardResponseCopyWithImpl<$Res>;
  @override
  @useResult
  $Res call(
      {String cardNumber,
      String cardNumberRedacted,
      String cardHolder,
      String cardExpirationDate});
}

/// @nodoc
class __$$_ScanCardResponseCopyWithImpl<$Res>
    extends _$ScanCardResponseCopyWithImpl<$Res, _$_ScanCardResponse>
    implements _$$_ScanCardResponseCopyWith<$Res> {
  __$$_ScanCardResponseCopyWithImpl(
      _$_ScanCardResponse _value, $Res Function(_$_ScanCardResponse) _then)
      : super(_value, _then);

  @pragma('vm:prefer-inline')
  @override
  $Res call({
    Object? cardNumber = null,
    Object? cardNumberRedacted = null,
    Object? cardHolder = null,
    Object? cardExpirationDate = null,
  }) {
    return _then(_$_ScanCardResponse(
      cardNumber: null == cardNumber
          ? _value.cardNumber
          : cardNumber // ignore: cast_nullable_to_non_nullable
              as String,
      cardNumberRedacted: null == cardNumberRedacted
          ? _value.cardNumberRedacted
          : cardNumberRedacted // ignore: cast_nullable_to_non_nullable
              as String,
      cardHolder: null == cardHolder
          ? _value.cardHolder
          : cardHolder // ignore: cast_nullable_to_non_nullable
              as String,
      cardExpirationDate: null == cardExpirationDate
          ? _value.cardExpirationDate
          : cardExpirationDate // ignore: cast_nullable_to_non_nullable
              as String,
    ));
  }
}

/// @nodoc
@JsonSerializable()
class _$_ScanCardResponse implements _ScanCardResponse {
  const _$_ScanCardResponse(
      {required this.cardNumber,
      required this.cardNumberRedacted,
      required this.cardHolder,
      required this.cardExpirationDate});

  factory _$_ScanCardResponse.fromJson(Map<String, dynamic> json) =>
      _$$_ScanCardResponseFromJson(json);

  @override
  final String cardNumber;
  @override
  final String cardNumberRedacted;
  @override
  final String cardHolder;
  @override
  final String cardExpirationDate;

  @override
  String toString() {
    return 'ScanCardResponse(cardNumber: $cardNumber, cardNumberRedacted: $cardNumberRedacted, cardHolder: $cardHolder, cardExpirationDate: $cardExpirationDate)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other.runtimeType == runtimeType &&
            other is _$_ScanCardResponse &&
            (identical(other.cardNumber, cardNumber) ||
                other.cardNumber == cardNumber) &&
            (identical(other.cardNumberRedacted, cardNumberRedacted) ||
                other.cardNumberRedacted == cardNumberRedacted) &&
            (identical(other.cardHolder, cardHolder) ||
                other.cardHolder == cardHolder) &&
            (identical(other.cardExpirationDate, cardExpirationDate) ||
                other.cardExpirationDate == cardExpirationDate));
  }

  @JsonKey(ignore: true)
  @override
  int get hashCode => Object.hash(runtimeType, cardNumber, cardNumberRedacted,
      cardHolder, cardExpirationDate);

  @JsonKey(ignore: true)
  @override
  @pragma('vm:prefer-inline')
  _$$_ScanCardResponseCopyWith<_$_ScanCardResponse> get copyWith =>
      __$$_ScanCardResponseCopyWithImpl<_$_ScanCardResponse>(this, _$identity);

  @override
  Map<String, dynamic> toJson() {
    return _$$_ScanCardResponseToJson(
      this,
    );
  }
}

abstract class _ScanCardResponse implements ScanCardResponse {
  const factory _ScanCardResponse(
      {required final String cardNumber,
      required final String cardNumberRedacted,
      required final String cardHolder,
      required final String cardExpirationDate}) = _$_ScanCardResponse;

  factory _ScanCardResponse.fromJson(Map<String, dynamic> json) =
      _$_ScanCardResponse.fromJson;

  @override
  String get cardNumber;
  @override
  String get cardNumberRedacted;
  @override
  String get cardHolder;
  @override
  String get cardExpirationDate;
  @override
  @JsonKey(ignore: true)
  _$$_ScanCardResponseCopyWith<_$_ScanCardResponse> get copyWith =>
      throw _privateConstructorUsedError;
}
