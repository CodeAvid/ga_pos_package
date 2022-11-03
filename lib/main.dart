import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:ga_pos/key_exchange_response.dart';
import 'package:ga_pos/scan_card_response.dart';
import 'package:ga_pos/transaction_response_data.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  Future<TransactionResponseData?> _connectToNativePlatform() async {
    const channel = MethodChannel('checkout_channel');
    try {
      final value = await channel.invokeMethod('checkout', {
        "requestData": {
          "transType": "PURCHASE", // this must be in uppercase
          "amount": "60.0",
          "print": "true",
        }
      });

      return TransactionResponseData.fromJson(json.decode(value ?? ''));
    } on PlatformException catch (e) {
      debugPrint('Error: $e');

      return null;
    }
  }

  Future<KeyExchangeResponse?> _connectToNativePlatformForKeyExchange() async {
    const channel = MethodChannel('checkout_channel');
    try {
      final value = await channel.invokeMethod('key_exchange');

      return KeyExchangeResponse.fromJson(json.decode(value ?? ''));
    } on PlatformException catch (e) {
      debugPrint('Error: $e');

      return null;
    }
  }

  Future<ScanCardResponse?> _connectToNativePlatformForScanCard() async {
    const channel = MethodChannel('checkout_channel');
    try {
      final value = await channel.invokeMethod('scan_card');

      return ScanCardResponse.fromJson(json.decode(value ?? ''));
    } on PlatformException catch (e) {
      debugPrint('Error: $e');

      return null;
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: ListView(
          children: <Widget>[
            TextButton(
              onPressed: () {
                _connectToNativePlatform();
              },
              child: const Text('Checkout'),
            ),
            TextButton(
              onPressed: () {
                _connectToNativePlatformForKeyExchange();
              },
              child: const Text('Key Exchange'),
            ),
            TextButton(
              onPressed: () {
                _connectToNativePlatformForScanCard();
              },
              child: const Text('Scan Card'),
            ),
          ],
        ),
      ),
    );
  }
}
