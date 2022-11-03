package com.example.ga_pos

import android.content.Intent
import cards.pay.paycardsrecognizer.sdk.Card
import cards.pay.paycardsrecognizer.sdk.ScanCardIntent
import com.google.gson.Gson
import com.stripe.android.stripecardscan.cardscan.CardScanSheet
import io.flutter.Log
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant

class MainActivity : FlutterActivity() {

    companion object {
        private const val CHANNEL = "checkout_channel"
        private const val TRANSACTION_REQUEST = 49
        private const val KEY_EXCHANGE_REQUEST = 94
        private const val TRANSACTION_INTENT = "com.globalaccelerex.transaction"
        private const val KEY_EXCHANGE_INTENT = "com.globalaccelerex.utility"
        private const val REQUEST_CODE_SCAN_CARD = 1
    }

    private lateinit var channelResult: MethodChannel.Result
    private lateinit var cardScanSheet: CardScanSheet

    private val gson = Gson()

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        GeneratedPluginRegistrant.registerWith(flutterEngine)
        super.configureFlutterEngine(flutterEngine)

        MethodChannel(
            flutterEngine.dartExecutor.binaryMessenger,
            CHANNEL
        ).setMethodCallHandler { call, result ->
            channelResult = result
            when (call.method) {
                "checkout" -> {
                    val arguments = call.argument<Map<String, Any>>("requestData")

                    startTransaction(arguments!!)
                }
                "key_exchange" -> {
                    doKeyExchange()
                }
                "scan_card" -> {
                    scanCardWithStripe()
                }
                else -> result.notImplemented()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == TRANSACTION_REQUEST && resultCode == RESULT_OK) {
            val status = data!!.getStringExtra("status")
            Log.d(javaClass.simpleName, "Status" + status!!)

            when (status) {
                "00" -> {
                    val successData = data.getStringExtra("data")
                    channelResult.success(successData)
                }
                "02" -> {
                    channelResult.error(
                        "com.globalaccelerex.mpos:payment",
                        "FAILED",
                        "Message sent to host but transaction has been declined."
                    )
                }
                "03" -> {
                    channelResult.error(
                        "com.globalaccelerex.mpos:payment",
                        "CANCEL",
                        "User canceled transaction, Signal lost from host, or an invalid transaction was aborted by the payment application."
                    )
                }
                "04" -> {
                    channelResult.error(
                        "com.globalaccelerex.mpos:payment",
                        "INVALID FORMAT",
                        "Invalid data format of message sent to payment application."
                    )
                }
                "05" -> {
                    channelResult.error(
                        "com.globalaccelerex.mpos:payment",
                        "WRONG PARAMETER",
                        "Invalid parameter(S) passed as part of the data sent to payment application."
                    )
                }
                "06" -> {
                    channelResult.error(
                        "com.globalaccelerex.mpos:payment",
                        "TIMEOUT",
                        "PIN entry timeout or card read timeout."
                    )
                }
                else -> {
                    channelResult.error(
                        "com.globalaccelerex.mpos:payment",
                        "Unknown error",
                        ""
                    )
                }
            }
        } else if (requestCode == KEY_EXCHANGE_REQUEST && resultCode == RESULT_OK) {
            val status = data!!.getStringExtra("status")
            Log.d(javaClass.simpleName, "Status" + status!!)

            when (status) {
                "00" -> {
                    val successData = data.getStringExtra("data")

                    channelResult.success(successData)
                }
                "02" -> {
                    channelResult.error(
                        "com.globalaccelerex.mpos:payment",
                        "FAILED",
                        "Failed key exchange."
                    )
                }
                else -> {
                    channelResult.error(
                        "com.globalaccelerex.mpos:payment",
                        "Unknown error",
                        ""
                    )
                }
            }
        } else if (requestCode == REQUEST_CODE_SCAN_CARD) {
            when (resultCode) {
                RESULT_OK -> {
                    val card: Card =
                        data!!.getParcelableExtra(ScanCardIntent.RESULT_PAYCARDS_CARD)!!
                    val cardData =
                        "{" +
                                "\"cardNumber\": \"${card.cardNumber}\"," +
                                "\"cardNumberRedacted\": \"${card.cardNumberRedacted}\"," +
                                "\"cardHolder\": \"${card.cardHolderName}\"," +
                                "\"cardExpirationDate\": \"${card.expirationDate}\"" +
                                "}"
                    Log.d(javaClass.simpleName, "Card info: $cardData")
                    channelResult.success(card)
                }
                RESULT_CANCELED -> {
                    channelResult.error(
                        "cards.pay.paycardsrecognizer.sdk",
                        "Scan canceled",
                        ""
                    )
                }
                else -> {
                    channelResult.error(
                        "cards.pay.paycardsrecognizer.sdk",
                        "Scan failed",
                        ""
                    )
                }
            }
        } else {
            channelResult.error(
                "com.globalaccelerex.mpos:payment",
                "An error occurred getting results",
                ""
            )
        }
    }

    private fun doKeyExchange() {
        val intent = Intent(KEY_EXCHANGE_INTENT)
        startActivityForResult(intent, KEY_EXCHANGE_REQUEST)
    }

    private fun startTransaction(paymentRequest: Map<String, Any>) {
        val jsonString = gson.toJson(paymentRequest)

        val intent = Intent(TRANSACTION_INTENT)
        intent.putExtra("requestData", jsonString)
        startActivityForResult(intent, TRANSACTION_REQUEST)
    }

    private fun scanCard() {
        val intent: Intent = ScanCardIntent.Builder(this).build()
        startActivityForResult(intent, REQUEST_CODE_SCAN_CARD)
    }

    private fun scanCardWithStripe() {
        val intent = Intent(this, ScanCardActivity::class.java)
        startActivity(intent)
    }
}
