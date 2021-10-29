package tw.edu.pu.unik.ui.home

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_home.*
import tw.edu.pu.unik.Data
import tw.edu.pu.unik.R
import tw.edu.pu.unik.adapter.LiveStreamAdapter


class HomeFragment : Fragment() {

    private  val TAG = "HomeFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: ")
        recyclerview.layoutManager = GridLayoutManager(this.context,2)
        val fakedata = mutableListOf(
            R.drawable.livestreamone, R.drawable.livestreamtwo
            , R.drawable.livestreamthree, R.drawable.livestreamfoure
            , R.drawable.livestreamfive, R.drawable.livestreamsix
        )
        val liveStreamAdapter = LiveStreamAdapter(fakedata)
        recyclerview.adapter = liveStreamAdapter
        liveStreamAdapter.isLiveStream = true

        liveStreamButton.setOnClickListener {
            Log.d(TAG, "onViewCreated: liveStreamButton.setOnClickListener")
            recyclerview.layoutManager = GridLayoutManager(this.context,2)
            val fakedata = mutableListOf(
                R.drawable.livestreamone, R.drawable.livestreamtwo
                , R.drawable.livestreamthree, R.drawable.livestreamfoure
                , R.drawable.livestreamfive, R.drawable.livestreamsix
            )
            val liveStreamAdapter = LiveStreamAdapter(fakedata)

            recyclerview.adapter = liveStreamAdapter
            liveStreamAdapter.update()
            liveStreamAdapter.isLiveStream = true
        }
        posts.setOnClickListener {
            Log.d(TAG, "onViewCreated: posts.setOnClickListener")
            recyclerview.layoutManager = LinearLayoutManager(this.context)
            val fakedata: MutableList<Data> = mutableListOf(
                Data(
                    R.drawable.userone,"Mavlin chen","Gold Member from Taiwan",
                "Must buy for boba tea lovers!","If you are a boba milk tea lover, you must try this one! The taste is sooo good! Order right here --> @Bobachic.TW!",
                R.drawable.b1, R.drawable.b2, R.drawable.b3,"24","2"),
            Data(
                R.drawable.usertwo,"Cindy Wang","Silver Member from Taiwan",
                "If you don’t have this one, u r out!","@BingZhen ‘s new natural #Lipstick, I use the 01 color and it’s soooo incredibly gorgeous! I confirm that u r not gonna be regret to buy it. Let’s check it out!",
                R.drawable.item1, R.drawable.item2, R.drawable.item3,"18","5")
            )
           val liveStreamAdapter = LiveStreamAdapter(fakedata)

            recyclerview.adapter = liveStreamAdapter
            liveStreamAdapter.update()
            liveStreamAdapter.isLiveStream = false
        }
        ActivityCompat.requestPermissions(requireActivity(), arrayOf(
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.CAMERA) , 200)
    }
}