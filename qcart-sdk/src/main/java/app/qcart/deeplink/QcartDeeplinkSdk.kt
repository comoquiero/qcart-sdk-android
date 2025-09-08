package app.qcart.deeplink

import android.content.Intent
import android.net.Uri

// --- SDK Parser ---
object QcartDeeplinkSdk {

    /**
     * Parses the Intent deep link and returns all info as a QcartDeeplinkResult
     */
    fun parseIntent(intent: Intent?): QcartDeeplinkResult {
        val uri: Uri? = intent?.data

        // Parse query and fragment parameters
        val queryParams = getAllQueryParams(uri)
        val fragmentParams = getFragmentParams(uri)

        // Check if qcart=true exists in either query or fragment
        val isQcart = (queryParams["qcart"]?.equals("true", ignoreCase = true) == true) ||
                      (fragmentParams["qcart"]?.equals("true", ignoreCase = true) == true)

        // Parse SKUs from both query and fragment
        val querySkus = parseSkus(queryParams["skus"])
        val fragmentSkus = parseSkus(fragmentParams["skus"])

        // Merge SKUs: sum quantities if same SKU appears in both
        val mergedSkus = mutableMapOf<String, Int>()
        for ((sku, qty) in fragmentSkus + querySkus) { // fragment first, then query
            mergedSkus[sku] = (mergedSkus[sku] ?: 0) + qty
        }

        // Build result
        return QcartDeeplinkResult(
            url = uri?.toString(),
            pathSegments = uri?.pathSegments ?: emptyList(),
            queryParameters = queryParams,
            fragmentParameters = fragmentParams,
            isQcart = isQcart,
            skus = mergedSkus.map { it.key to it.value }
        )
    }

    private fun parseSkus(skusParam: String?): List<Pair<String, Int>> {
        return skusParam?.split(",")?.mapNotNull { pairStr ->
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
        } ?: emptyList()
    }

    private fun getAllQueryParams(uri: Uri?): Map<String, String?> {
        if (uri == null) return emptyMap()
        return uri.queryParameterNames.associateWith { uri.getQueryParameter(it) }
    }

    private fun getFragmentParams(uri: Uri?): Map<String, String> {
        if (uri == null) return emptyMap()
        val fragment = uri.fragment ?: return emptyMap()
        return fragment.split("&").mapNotNull {
            val parts = it.split("=")
            if (parts.size == 2) parts[0] to parts[1] else null
        }.toMap()
    }
}

// --- Data class ---
data class QcartDeeplinkResult(
    val url: String? = null,
    val pathSegments: List<String> = emptyList(),
    val queryParameters: Map<String, String?> = emptyMap(),
    val fragmentParameters: Map<String, String> = emptyMap(),
    val isQcart: Boolean = false,
    val skus: List<Pair<String, Int>> = emptyList(),
) {
    // Convert to JSON-friendly map for Flutter
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "url" to url,
            "pathSegments" to pathSegments,
            "queryParameters" to queryParameters,
            "fragmentParameters" to fragmentParameters,
            "isQcart" to isQcart,
            "skus" to skus.map { mapOf("sku" to it.first, "quantity" to it.second) }
        )
    }
}