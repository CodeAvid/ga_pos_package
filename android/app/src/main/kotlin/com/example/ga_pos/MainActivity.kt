package com.example.ga_pos

import android.content.Intent
import com.globalaccelerex.mpos.payment.CardTransactionResponse
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant

class MainActivity : FlutterActivity() {

    companion object {
        private const val CHANNEL = "checkout_channel"
        private const val REQUEST_CODE = 49
    }

    private lateinit var channelResult: MethodChannel.Result

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
                    val arguments = call.argument<Map<String, Any>>("paymentRequest")

                    startCheckoutActivity(arguments!!)
                }
                else -> result.notImplemented()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            val bundle = data!!.extras!!
            val responseData = bundle.getParcelable<CardTransactionResponse>(Constants.RESULT_DATA)
            val resultData = mapOf(
                "transaction_status" to bundle.getString(Constants.RESULT_STATUS),
                "message" to bundle.getString(Constants.RESULT_MESSAGE),
                if (responseData != null)
                    "data" to mapOf<String, Any?>(
                        "aid" to responseData.aid,
                        "amount" to responseData.amount,
                        "appLabel" to responseData.appLabel,
                        "authCode" to responseData.authcode,
                        "bankLogo" to responseData.bankLogo,
                        "bankName" to responseData.bankName,
                        "cardHolderName" to responseData.cardHolderName,
                        "cashBackAmount" to responseData.cashBackAmount,
                        "currency" to responseData.currency,
                        "customReference" to responseData.customReference,
                        "datetime" to responseData.datetime,
                        "deviceSerialNumber" to responseData.deviceSerialNumber,
                        "expireDate" to responseData.expireDate,
                        "footerMessage" to responseData.footerMessage,
                        "maskedPan" to responseData.maskedPan,
                        "merchantAddress" to responseData.merchantAddress,
                        "merchantCategoryCode" to responseData.merchantCategoryCode,
                        "merchantId" to responseData.merchantId,
                        "merchantName" to responseData.merchantName,
                        "message" to responseData.message,
                        "nuban" to responseData.nuban,
                        "pinType" to responseData.pinType,
                        "ptsp" to responseData.ptsp,
                        "ptspContact" to responseData.ptspContact,
                        "rrn" to responseData.rrn,
                        "statusCode" to responseData.statusCode,
                        "stan" to responseData.stan,
                        "transactionType" to responseData.transactionType,
                        "terminalId" to responseData.terminalId,
                        "approved" to responseData.approved(),
                    )
                else
                    "data" to null
            )
            channelResult.success(resultData)
        } else {
            channelResult.error(
                "com.globalaccelerex.mpos:payment",
                "An error occurred getting results",
                ""
            )
        }
    }

    private fun startCheckoutActivity(paymentRequest: Map<String, Any>) {
        val intent = Intent(this, CheckoutActivity::class.java)
        intent.putExtra(Constants.REQUEST_TYPE, paymentRequest["requestType"] as String)
        intent.putExtra(Constants.AMOUNT, paymentRequest["amount"] as Double)
        intent.putExtra(Constants.PRINT_RECEIPT, paymentRequest["printReceipt"] as Boolean)

        startActivityForResult(intent, REQUEST_CODE)
    }
}
