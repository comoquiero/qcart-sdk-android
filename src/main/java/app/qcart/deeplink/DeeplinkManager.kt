/**
 * Manages deep link intents and notifies the listener with SKU lists.
 */

package app.qcart.deeplink

import android.app.Activity
import android.content.Intent

object DeeplinkManager {

    private var listener: DeeplinkListener? = null

    fun init(listener: DeeplinkListener) {
        this.listener = listener
    }

    fun handleIntent(activity: Activity, intent: Intent?): Boolean {
        var handled = false
        QcartDeeplink.handleIntent(intent) { skuList ->
            handled = true
            activity.runOnUiThread {
                listener?.onSkuListReceived(skuList)
            }
        }
        return handled
    }

    // Call this in case activity is recreated (optional cleanup)
    fun clear() {
        listener = null
    }
}