/**
 * Parses deep link intents to extract SKU query parameters and pass them to a callback.
 */

package app.qcart.deeplink

import android.content.Intent
import android.net.Uri

object QcartDeeplink {
    fun handleIntent(intent: Intent?,  onSkus: (List<Pair<String, Int>>) -> Unit) {
        // val uri: Uri = intent?.data ?: return
        // val skus = uri.getQueryParameters("skus")
        // if (skus.isNotEmpty()) {
        //     onSkus(skus)
        // }

        // val uri: Uri = intent?.data ?: return
        // val skusParam = uri.getQueryParameter("skus") ?: return
        // val skus = skusParam.split(",").map { it.trim() }.filter { it.isNotEmpty() }
        // if (skus.isNotEmpty()) {
        //     onSkus(skus)
        // }

        val uri: Uri = intent?.data ?: return
        val skusParam = uri.getQueryParameter("skus") ?: return

        val skuQuantities = skusParam.split(",")
            .mapNotNull { pairStr ->
                val parts = pairStr.split(":")
                if (parts.size == 2) {
                    val sku = parts[0].trim()
                    val quantity = parts[1].trim().toIntOrNull()
                    if (sku.isNotEmpty() && quantity != null) {
                        sku to quantity
                    } else null
                } else null
            }

        if (skuQuantities.isNotEmpty()) {
            onSkus(skuQuantities)
        }
    }
}