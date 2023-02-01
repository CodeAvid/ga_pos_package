package com.example.ga_pos

import android.content.Intent
import cards.pay.paycardsrecognizer.sdk.Card
import cards.pay.paycardsrecognizer.sdk.ScanCardIntent
import com.google.gson.Gson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
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
        private const val PRINT_REQUEST = 33
        private const val TRANSACTION_INTENT = "com.globalaccelerex.transaction"
        private const val KEY_EXCHANGE_INTENT = "com.globalaccelerex.utility"
        private const val PRINTER_INTENT = "com.globalaccelerex.printer"
        private const val REQUEST_CODE_SCAN_CARD = 1
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
                "key_exchange" -> {
                    doKeyExchange()
                }
                "scan_card" -> {
                    scanCardWithStripe()
                }
                "print" -> {
                    val arguments = call.argument<Map<String, Any>>("printData")

                    printReceipt(arguments!!)
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

            handleTransactionResult(status, data)
        } else if (requestCode == KEY_EXCHANGE_REQUEST && resultCode == RESULT_OK) {
            val status = data!!.getStringExtra("status")
            Log.d(javaClass.simpleName, "Status" + status!!)

            handleExchangeResult(status, data)
        } else if (requestCode == REQUEST_CODE_SCAN_CARD) {
            handleScanCodeResult(resultCode, data)
        } else if (requestCode == PRINT_REQUEST && resultCode == RESULT_OK) {
            val status = data!!.getStringExtra("status")
            Log.d(javaClass.simpleName, "Status" + status!!)

            handlePrintResult(status, data)
        } else {
            if (this::channelResult.isInitialized) {
                channelResult.error(
                    "com.globalaccelerex.mpos:payment",
                    "An error occurred getting results",
                    ""
                )
            }
        }
    }

    private fun handlePrintResult(status: String, data: Intent) {
        when (status) {
            "00" -> {
                val successData = data.getStringExtra("data")

                channelResult.success(successData)
            }
            "02" -> {
                channelResult.error(
                    "com.globalaccelerex.mpos:payment",
                    "FAILED",
                    "Failed to print"
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
    }

    private fun handleScanCodeResult(resultCode: Int, data: Intent?) {
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
    }

    private fun handleExchangeResult(status: String, data: Intent) {
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
    }

    private fun handleTransactionResult(status: String, data: Intent) {
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

    private fun printReceipt(receipt: Map<String, Any>) {
        val moshi: Moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<PrintObject> = moshi.adapter(PrintObject::class.java)

        val printFields = mutableListOf<PrintField>()

        printFields.add(
            addPrintField(
                listOf(
                    addStringField(
                        addHeaderTextField("****CUSTOMER COPY****", isBold = true),
                        addBodyTextField("")
                    ),
                    addStringField(addHeaderTextField(""), addBodyTextField(""), true)
                ),
                8
            )
        )

        printFields.add(
            addPrintField(
                listOf(
                    addStringField(
                        addHeaderTextField("MerchantName: ", "left"),
                        addBodyTextField(
                            (receipt["merchantName"] ?: "").toString().uppercase(),
                            "left"
                        ),
                        false
                    ),
                    addStringField(
                        addHeaderTextField("Merchant Location: ", "left"),
                        addBodyTextField(
                            (receipt["merchantLocation"] ?: "").toString().uppercase(),
                            "left"
                        ),
                        false
                    ),
                    addStringField(
                        addHeaderTextField("Terminal ID: ", "left"),
                        addBodyTextField(
                            (receipt["terminalId"] ?: "").toString().uppercase(),
                            "left"
                        ),
                        false
                    ),
                )
            )
        )

        printFields.add(
            addPrintField(
                listOf(
                    addStringField(
                        addHeaderTextField("------------------------"),
                        addBodyTextField("")
                    ),
                ),
            )
        )

        printFields.add(
            addPrintField(
                listOf(
                    addStringField(
                        addHeaderTextField("STAN: ", "left"),
                        addBodyTextField((receipt["originalTransStan"] ?: "").toString(), "left")
                    ),
                    addStringField(
                        addHeaderTextField("DATE/TIME: ", "left"),
                        addBodyTextField((receipt["transmissionDate"] ?: "").toString(), "left")
                    ),
                ),
            )
        )

        printFields.add(
            addPrintField(
                listOf(
                    addStringField(
                        addHeaderTextField("------------------------"),
                        addBodyTextField("")
                    ),
                ),
            )
        )

        printFields.add(
            addPrintField(
                listOf(
                    addStringField(
                        addHeaderTextField("AMOUNT:  ", isBold = true),
                        addBodyTextField(
                            (receipt["originalMinorAmount"] ?: "").toString(),
                            size = "large",
                            isBold = true
                        ),
                        false
                    ),
                ),
            )
        )

        printFields.add(
            addPrintField(
                listOf(
                    addStringField(
                        addHeaderTextField("------------------------"),
                        addBodyTextField("")
                    ),
                ),
            )
        )

        printFields.add(
            addPrintField(
                listOf(
                    addStringField(
                        addHeaderTextField("", isBold = true),
                        addBodyTextField(
                          "Transaction " + (receipt["transactionComment"] ?: "").toString(),
                            size = "large",
                            isBold = true
                        ),
                        false
                    ),
                    addStringField(
                        addHeaderTextField("------------------------"),
                        addBodyTextField("")
                    ),
                    addStringField(
                        addHeaderTextField("", isBold = true),
                        addBodyTextField(
                          (receipt["footer"] ?: "").toString(),
                            size = "small",
                        ),
                        false
                    ),
                ),
            )
        )

        val printObject = PrintObject(printFields)
        val intent = Intent(PRINTER_INTENT)
        intent.putExtra("jsonData", jsonAdapter.toJson(printObject))
        startActivityForResult(intent, PRINT_REQUEST)
    }

    private fun addPrintField(stringFields: List<StringField>, letterSpacing: Int = 5) = PrintField(
        filename = "",
        letterSpacing = letterSpacing,
        stringFields = stringFields
    )

    private fun addStringField(header: TextField, body: TextField, isMultiline: Boolean = true) =
        StringField(
            isMultiline = isMultiline,
            header = header,
            body = body,
        )

    private fun addHeaderTextField(
        header: String, align: String = "center",
        size: String = "large",
        isBold: Boolean = false,
    ) = TextField(
        text = header,
        align = align,
        size = size,
        isBold = isBold
    )

    private fun addBodyTextField(
        body: String,
        align: String = "center",
        size: String = "normal",
        isBold: Boolean = false,
    ) =
        TextField(
            text = body,
            align = align,
            size = size,
            isBold = isBold
        )
}
