package com.example.ga_pos

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.globalaccelerex.mpos.payment.MposPayment
import com.globalaccelerex.mpos.payment.PaymentRequest
import com.globalaccelerex.mpos.payment.RequestType
import com.globalaccelerex.mpos.payment.TransactionStatus

class CheckoutActivity : AppCompatActivity() {
    private val cardPayment =
        registerForActivityResult(MposPayment.CardTransactionContract()) { result ->

            when (result.status) {
                TransactionStatus.APPROVED -> {
                    Intent().apply {
                        putExtras(
                            Bundle().apply {
                                putString(Constants.RESULT_STATUS, result.status.name)
                                putString(Constants.RESULT_MESSAGE, result.responseMessage)
                                putParcelable(Constants.RESULT_DATA, result.responseData)
                            }
                        )
                    }.also {
                        setResult(RESULT_OK, it)
                        finish()
                    }
                }
                TransactionStatus.DECLINED -> {
                    Intent().apply {
                        putExtras(
                            Bundle().apply {
                                putString(Constants.RESULT_STATUS, result.status.name)
                                putString(Constants.RESULT_MESSAGE, result.responseMessage)
                            }
                        )
                    }.also {
                        setResult(RESULT_OK, it)
                        finish()
                    }
                }

                TransactionStatus.FAILED -> {
                    Intent().apply {
                        putExtras(
                            Bundle().apply {
                                putString(Constants.RESULT_STATUS, result.status.name)
                                putString(Constants.RESULT_MESSAGE, result.responseMessage)
                            }
                        )
                    }.also {
                        setResult(RESULT_OK, it)
                        finish()
                    }
                }
                TransactionStatus.CANCELLED -> {
                    Intent().apply {
                        putExtras(
                            Bundle().apply {
                                putString(Constants.RESULT_STATUS, result.status.name)
                                putString(Constants.RESULT_MESSAGE, result.responseMessage)
                            }
                        )
                    }.also {
                        setResult(RESULT_OK, it)
                        finish()
                    }
                }
                TransactionStatus.TIMEOUT -> {
                    Intent().apply {
                        putExtras(
                            Bundle().apply {
                                putString(Constants.RESULT_STATUS, result.status.name)
                                putString(Constants.RESULT_MESSAGE, result.responseMessage)
                            }
                        )
                    }.also {
                        setResult(RESULT_OK, it)
                        finish()
                    }
                }
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startCheckout()
    }

    private fun startCheckout() {
        // Create a checkout request
        val request = PaymentRequest {
            requestType = when (intent.getStringExtra(Constants.REQUEST_TYPE)) {
                RequestType.PURCHASE.name -> RequestType.PURCHASE
                RequestType.REFUND.name -> RequestType.REFUND
                RequestType.BALANCE.name -> RequestType.BALANCE
                else -> RequestType.PURCHASE
            }
            amount = intent.getDoubleExtra(Constants.AMOUNT, 0.00)
            printReceipt = intent.getBooleanExtra(Constants.PRINT_RECEIPT, false)
        }

        // Start the payment process
        cardPayment.launch(request)
    }
}
