package kg.vy.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import kg.vy.R
import kg.vy.controllers.MainController
import kg.vy.controllers.PurchaseController
import kg.vy.models.VideoPropsModel
import kg.vy.ui.screens.SelectTimeActivity
import kg.vy.utils.LocaleHelper
import kg.vy.utils.Prefs
import kg.vy.views.loopview.LoopView
import kg.vy.views.loopview.OnItemScrollListener
import kg.vy.views.loopview.OnItemSelectedListener
import kotlinx.android.synthetic.main.fragment_menu.*
import kotlinx.android.synthetic.main.fragment_menu.noAdsText
import kotlinx.android.synthetic.main.fragment_menu.selectButton

class MenuFragment : Fragment() {

    val mainController = MainController.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        PurchaseController.liveData.observe(viewLifecycleOwner) {
            if ( it == 0 ) {
                Prefs.putBoolean(requireContext(), Prefs.ADS_ON, false)
                requireActivity().runOnUiThread {
                    noAdsText.visibility = View.GONE
                }
            }
        }
        PurchaseController.buildBillingClient(requireActivity())

        val list = mutableListOf<String>()
        val videoPropsModelList = VideoPropsModel.menu[LocaleHelper.getLanguage(context)]
        videoPropsModelList!!.forEach {
            list.add(it.title)
        }
        menuListView.setNotLoop()
        menuListView.setItems(list)
        menuListView.setCurrentPosition(1)
        menuListView.setOnItemScrollListener(object: OnItemScrollListener {
            override fun onItemScrollStateChanged(
                loopView: LoopView?,
                currentPassItem: Int,
                oldScrollState: Int,
                scrollState: Int,
                totalScrollY: Int
            ) {
                mainController?.playAudioRaw(requireContext(), R.raw.scroll)
            }

            override fun onItemScrolling(
                loopView: LoopView?,
                currentPassItem: Int,
                scrollState: Int,
                totalScrollY: Int
            ) {}
        })

        selectButton.setOnClickListener {
            mainController?.playAudioRaw(requireContext(), R.raw.button)
            mainController?.setVideoPropsModel(videoPropsModelList[menuListView.selectedItem])
            startActivity(Intent(context, SelectTimeActivity::class.java))
            activity?.finish()
        }

        if ( !Prefs.getBooleanDef(requireContext(), Prefs.ADS_ON, true) )
            noAdsText.visibility = View.GONE

        noAdsText.setOnClickListener {
            PurchaseController.launchBilling("noads30day")
        }

    }

}