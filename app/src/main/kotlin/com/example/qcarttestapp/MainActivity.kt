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
        val result = QcartDeeplinkSdk.parseIntent(intent)

        // IF YOU WANT CHECK IF IT IS A QCART LINK FIRST
        // val isQcart = result.isQcart
        // if (isQcart) {

        // PRINT EXAMPLE
        skuTextView.text = buildString {
            appendLine("url: ${result.url}")
            appendLine()  // blank line
            appendLine("pathSegments: ${result.pathSegments}")
            appendLine()  // blank line
            appendLine("queryParameters: ${result.queryParameters}")
            appendLine()  // blank line
            appendLine("fragmentParameters: ${result.fragmentParameters}")
            appendLine()  // blank line
            appendLine("isQcart: ${result.isQcart}")
            appendLine()  // blank line
            appendLine("skus: [${result.skus.joinToString { """{"sku":"${it.first}","quantity":${it.second}}""" }}]")
            
        }

        // KEEP LINK HANDLING LOGIC HERE
        // ...
    }
}