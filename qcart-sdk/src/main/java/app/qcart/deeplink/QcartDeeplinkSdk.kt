package app.qcart.deeplink

import android.content.Intent
import android.net.Uri

object QcartDeeplinkSdk {

    /**
     * Handle a deep link intent and invoke the callback with SKUs.
     * Accepts SKUs with or without quantities (defaults to 1 if missing).
     */
    fun handleIntent(intent: Intent?, onSkuListReceived: (List<Pair<String, Int>>) -> Unit) {
        val uri: Uri? = intent?.data
        val skusParam = uri?.getQueryParameter("skus")

        val skuQuantities = skusParam?.split(",")?.mapNotNull { pairStr ->
            val parts = pairStr.split(":")
            when (parts.size) {
                2 -> {
                    val sku = parts[0].trim()
                    val quantity = parts[1].trim().toIntOrNull()
                    if (sku.isNotEmpty() && quantity != null) sku to quantity else null
                }
                1 -> {
                    val sku = parts[0].trim()
                    if (sku.isNotEmpty()) sku to 1 else null
                }
                else -> null
            }
        } ?: emptyList() // empty list if no skusParam

        // Always call callback
        onSkuListReceived(skuQuantities)
    }
}