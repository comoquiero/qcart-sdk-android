package com.example.qcarttestapp

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

// OTHER SDK COMPATIBILITY EXAMPLE
import com.appsamurai.storyly.StorylyInit
import com.appsamurai.storyly.StorylyView

// QCART SDK
import app.qcart.deeplink.QcartDeeplinkSdk

class MainActivity : AppCompatActivity() {

    private lateinit var storylyView: StorylyView
    private lateinit var skuTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        storylyView = findViewById(R.id.storylyView)
        skuTextView = findViewById(R.id.skuTextView)

        storylyView.storylyInit = StorylyInit(
            "YOUR_STORYLY_INSTANCE_TOKEN"
        )

        // Handle deep link directly with callback
        handleDeepLink(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        // Handle new intent in same way
        handleDeepLink(intent)
    }

    // Deeplink handler
    private fun handleDeepLink(intent: Intent?) {
        QcartDeeplinkSdk.handleIntent(intent) { skus: List<Pair<String, Int>> ->
            if (skus.isNotEmpty()) {
                skuTextView.text = skus.joinToString(", ") { "${it.first}:${it.second}" }
            } else {
                // This now executes if there are no SKUs
                skuTextView.text = "Not skus link"
            }
        }
    }

    // IMPORT ASYNC LIBS TO USE THIS WITHOUT CALLBACKS
    // private fun handleDeepLinkAsync(intent: Intent?) {
    //     lifecycleScope.launch {
    //         val skus = QcartDeeplinkSdk.handleIntent(intent)
            
    //         if (skus.isNotEmpty()) {
    //             skuTextView.text = "Received SKUs: ${skus.joinToString(", ")}"
    //             return@launch // SKUs handled, skip other links
    //         }

    //         // Handle other links if no SKUs
    //         skuTextView.text = "Other link"
    //     }
    // }
}