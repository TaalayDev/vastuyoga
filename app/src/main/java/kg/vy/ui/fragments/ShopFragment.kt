package kg.vy.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import kg.vy.R
import kg.vy.controllers.MainController
import kotlinx.android.synthetic.main.fragment_shop.*

class ShopFragment : Fragment() {

    val mainController = MainController.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shop, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainController!!.venergyLiveData.observe(viewLifecycleOwner) {
            activity?.runOnUiThread { venergyCount.text = it.toString() }
        }
    }

}