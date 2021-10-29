package tw.edu.pu.unik

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_orderinformation.*

class OrderInformationFragment: Fragment(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_orderinformation,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addbutton.setOnClickListener {
            productnumber.text = (productnumber.text.toString().toInt()+1).toString()

        }
        subtractbutton.setOnClickListener {
            productnumber.text = (productnumber.text.toString().toInt()-1).toString()

        }
        delete.setOnClickListener {
            activity?.onBackPressed()
        }

    }


}