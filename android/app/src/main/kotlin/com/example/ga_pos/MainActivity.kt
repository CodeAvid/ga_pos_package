package com.example.ga_pos

import android.content.Intent
import com.google.gson.Gson
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
                    val arguments = call.argument<Map<String, Any>>("paymentRequest")

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
            val statusMessage = data.getStringExtra("statusMessage")

            if (status == "00") {
                val successData = data.getStringExtra("data")
                var jsonMap: Map<String, Any> = HashMap()

                jsonMap = gson.fromJson(successData, jsonMap.javaClass)
                channelResult.success(jsonMap)
            } else {
                channelResult.error(
                    "com.globalaccelerex.mpos:payment",
                    statusMessage,
                    ""
                )
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
