package com.example.ga_pos

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.stripe.android.stripecardscan.cardscan.CardScanSheet
import com.stripe.android.stripecardscan.cardscan.CardScanSheetResult
import com.stripe.android.stripecardscan.scanui.CancellationReason
import io.flutter.Log

class ScanCardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_card)

        val cardScanSheet = CardScanSheet.create(
            from = this,
            stripePublishableKey = "pk_live_9QACeWSE0dzkD7K4GaQAsusX00efKrK01G",
            { cardScanSheetResult: CardScanSheetResult ->
                when (cardScanSheetResult) {
                    is CardScanSheetResult.Completed -> {
                        /*
                         * The user scanned a card. The result of the scan can be found
                         * by querying the stripe card image verification endpoint with
                         * the CVI_ID, CVI_SECRET, and Stripe secret key.
                         *
                         * Details about the card itself are returned in the `scannedCard`
                         * field of the result.
                         */
                        Log.d(javaClass.simpleName, cardScanSheetResult.scannedCard.pan)
                    }
                    is CardScanSheetResult.Canceled -> {
                        /*
                         * The scan was canceled. This could be because any of the
                         * following reasons (returned as the
                         * [CancellationReason] in the result):
                         *
                         * - Closed - the user pressed the X
                         * - Back - the user pressed the back button
                         * - UserCannotScan - the user is unable to scan this card
                         * - CameraPermissionDenied - the user did not grant permissions
                         */
                        when (cardScanSheetResult.reason) {
                            is CancellationReason.Back -> Log.d(
                                javaClass.simpleName,
                                "User pressed back"
                            )
                            is CancellationReason.Closed -> Log.d(
                                javaClass.simpleName,
                                "User closed screen"
                            )
                            is CancellationReason.UserCannotScan -> Log.d(
                                javaClass.simpleName,
                                "User was unable to scan"
                            )
                            is CancellationReason.CameraPermissionDenied -> Log.d(
                                javaClass.simpleName,
                                "User did not grant camera permission"
                            )
                        }
                    }
                    is CardScanSheetResult.Failed -> {
                        /*
                         * The scan failed. The error that caused the failure is
                         * included in the [Throwable] `error` field of the verification
                         * result.
                         */
                        Log.d(javaClass.simpleName, cardScanSheetResult.error.message!!)
                    }
                    else -> {
                        Log.d(javaClass.simpleName, "Nothing happened")
                    }
                }
            }
        )

        findViewById<Button>(R.id.button).setOnClickListener {
            cardScanSheet.present()
        }
    }
}
