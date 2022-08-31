package com.example.ga_pos

import android.content.Intent
import com.google.gson.Gson
import io.flutter.Log
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant

class MainActivity : FlutterActivity() {

    companion object {
        private const val CHANNEL = "checkout_channel"
        private const val TRANSACTION_REQUEST = 49
        private const val TRANSACTION_INTENT = "com.globalaccelerex.transaction"
    }

    private lateinit var channelResult: MethodChannel.Result

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
                else -> result.notImplemented()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == TRANSACTION_REQUEST && resultCode == RESULT_OK) {
            val status = data!!.getStringExtra("status")
            Log.d("gggg", "Status" + status!!)

            when (status) {
                "00" -> {
                    val successData = data.getStringExtra("data")
//                    var jsonMap: Map<String, Any> = mapOf()

//                    jsonMap = gson.fromJson(successData, jsonMap.javaClass)
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
        } else {
            channelResult.error(
                "com.globalaccelerex.mpos:payment",
                "An error occurred getting results",
                ""
            )
        }
    }

    private fun startTransaction(paymentRequest: Map<String, Any>) {
        val jsonString = gson.toJson(paymentRequest)

        val intent = Intent(TRANSACTION_INTENT)
        intent.putExtra("requestData", jsonString)
        startActivityForResult(intent, TRANSACTION_REQUEST)
    }
}
