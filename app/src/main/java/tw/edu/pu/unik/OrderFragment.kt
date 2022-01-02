package tw.edu.pu.unik

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.framgnet_order.*

class OrderFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.framgnet_order,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        promotionbutton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_OrderFragment_to_Promotion)
        }
        unikcoinbutton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_OrderFragment_to_UnikCoin)
        }
    }
}