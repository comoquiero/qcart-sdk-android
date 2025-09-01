/**
 * Listener interface for SKU callbacks.
 * Interface separated for clean architecture and flexibility.
 */

package app.qcart.deeplink

interface DeeplinkListener {
    fun onSkuListReceived(skus: List<Pair<String, Int>>)
}