// package com.example.qcarttestapp

// import android.os.Bundle
// import androidx.appcompat.app.AppCompatActivity
// import com.appsamurai.storyly.StorylyInit
// import com.appsamurai.storyly.StorylyView
// import app.qcart.deeplink.QcartDeeplink

// class MainActivity : AppCompatActivity() {

//     private lateinit var storylyView: StorylyView

//     override fun onCreate(savedInstanceState: Bundle?) {
//         super.onCreate(savedInstanceState)
//         setContentView(R.layout.activity_main)

//         storylyView = findViewById(R.id.storylyView)

//         // Assign StorylyInit instance to storylyInit property
//         storylyView.storylyInit = StorylyInit(
//             "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NfaWQiOjE0NTkwLCJhcHBfaWQiOjIyMjM4LCJpbnNfaWQiOjI1MTM2fQ.2L7FQ1vcqULXvkQn_CMzz4GQllmqxzFyZeQdRlyuPl0"
//             // You can also specify other params here if needed, e.g. segment = "default"
//         )

//         storylyView.setStorylyListener(object : StorylyListener {
//             override fun storylyStoryClicked(storyGroup: StoryGroup, story: Story) {
//                 val deeplink = story.deeplink
//                 if (!deeplink.isNullOrEmpty()) {
//                     // Handle your deeplink here
//                     openDeepLink(deeplink)
//                 }
//             }

//             // other overrides...
//         })

//         // QCART SDK
//         QcartDeeplink.handleIntent(intent) { skus ->
//             // Use the SKUs however you need
//             println("Received SKUs: $skus")
//         }
//     }

//     // QCART SDK
//     override fun onNewIntent(intent: Intent?) {
//         super.onNewIntent(intent)
//         setIntent(intent)
//         QcartDeeplink.handleIntent(intent) { skus ->
//             println("New Intent SKUs: $skus")
//         }
//     }
// }

////////////////////////////////////////////////////////////////////////////////
// super simple stripped-down version

package com.example.qcarttestapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.appsamurai.storyly.StorylyInit
import com.appsamurai.storyly.StorylyView
import app.qcart.deeplink.DeeplinkListener
import app.qcart.deeplink.DeeplinkManager

class MainActivity : AppCompatActivity(), DeeplinkListener {

    private lateinit var storylyView: StorylyView
    private lateinit var skuTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        storylyView = findViewById(R.id.storylyView)
        skuTextView = findViewById(R.id.skuTextView)

        storylyView.storylyInit = StorylyInit(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2NfaWQiOjE0NTkwLCJhcHBfaWQiOjIyMjM4LCJpbnNfaWQiOjI1MTM2fQ.2L7FQ1vcqULXvkQn_CMzz4GQllmqxzFyZeQdRlyuPl0"
        )

        DeeplinkManager.init(this)
        DeeplinkManager.handleIntent(this, intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        DeeplinkManager.handleIntent(this, intent)
    }

    override fun onSkuListReceived(skuList: List<String>) {
        val skuText = "Received SKUs: ${skuList.joinToString(", ")}"
        Log.d("QcartDeepLink", skuText)
        skuTextView.text = skuText
    }

    override fun onDestroy() {
        super.onDestroy()
        DeeplinkManager.clear()
    }
}