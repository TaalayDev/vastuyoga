package kg.vy.controllers

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import com.android.billingclient.api.*


class PurchaseController {

    companion object {

        val liveData = MutableLiveData<Int>()

        private val mSkuDetailsMap = HashMap<String, SkuDetails>()
        private lateinit var billingClient: BillingClient
        private lateinit var activity: Activity

        private val purchasesUpdateListener =
            PurchasesUpdatedListener { billingResult, purchases ->
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK && purchases != null) {
                    liveData.value = 0
                } else if (billingResult.responseCode == BillingClient.BillingResponseCode.USER_CANCELED) {
                    liveData.value = 1
                } else {
                    liveData.value = -1
                }
            }

        fun buildBillingClient(activity: Activity) {
            this.activity = activity

            billingClient = BillingClient.newBuilder(activity)
                .setListener(this.purchasesUpdateListener)
                .enablePendingPurchases()
                .build()

            billingClient.startConnection(object : BillingClientStateListener {
                override fun onBillingSetupFinished(billingResult: BillingResult) {
                    if (billingResult.responseCode ==  BillingClient.BillingResponseCode.OK) {
                        querySkuDetails()
                    }
                }
                override fun onBillingServiceDisconnected() {
                    // Try to restart the connection on the next request to
                    // Google Play by calling the startConnection() method.
                }
            })
        }

        fun querySkuDetails() {
            val skuList = ArrayList<String>()
            skuList.add("noads30day")
            val params = SkuDetailsParams.newBuilder()
            params.setSkusList(skuList).setType(BillingClient.SkuType.SUBS)
            billingClient.querySkuDetailsAsync(params.build()) { billingResult, skuDetailsList ->
                if (billingResult.responseCode == 0) {
                    for (skuDetails in skuDetailsList!!) {
                        mSkuDetailsMap[skuDetails.sku] = skuDetails
                    }
                }
            }
        }

        fun launchBilling(skuId: String) {
            try {
                val flowParams = BillingFlowParams.newBuilder()
                    .setSkuDetails(this.mSkuDetailsMap[skuId]!!)
                    .build()
                val responseCode =
                    billingClient.launchBillingFlow(this.activity, flowParams).responseCode
            } catch (e: Exception) {

            }
        }

    }

}