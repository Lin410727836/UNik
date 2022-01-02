package tw.edu.pu.unik.ui.chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_notification.*
import tw.edu.pu.unik.OrderFragment
import tw.edu.pu.unik.R
import tw.edu.pu.unik.adapter.LiveStreamAdapter
import tw.edu.pu.unik.adapter.NotificationAdapter

class ChatFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notification, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        orderib.setOnClickListener {

            Navigation.findNavController(view).navigate(R.id.action_chatFragment_to_Order)

        }
        chatbutton.setOnClickListener {

            Navigation.findNavController(view).navigate(R.id.action_chatFragment_to_Social)

        }
       /* latestnotificationrecyclerview.layoutManager = LinearLayoutManager(this.context)
        val notificationAdapter = NotificationAdapter()

        recyclerview.adapter = notificationAdapter*/
    }

}