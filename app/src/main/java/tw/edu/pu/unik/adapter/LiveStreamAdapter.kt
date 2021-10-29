package tw.edu.pu.unik.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView
import tw.edu.pu.unik.Data
import tw.edu.pu.unik.R
import tw.edu.pu.unik.databinding.UserPostItemBinding
import tw.edu.pu.unik.databinding.UserStoryItemBinding
import tw.edu.pu.unik.ui.home.LiveStream

class LiveStreamAdapter(var fakedata: List<Any>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val TAG = "LiveStreamAdapter"
     var isLiveStream = true

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType) {
            1 -> {
                val liveStreamViewHolder = holder as LiveStreamViewHolder
                liveStreamViewHolder.bind(fakedata[position])
                Log.d(TAG, "onBindViewHolder: "+fakedata[position])
                holder.itemView.setOnClickListener {
                    LiveStream.uid = position + 1
                    Navigation.findNavController(holder.itemView).navigate(R.id.action_homeFragment_to_liveStream)
                }
            }
            else -> {
                val postsViewHolder = holder as PostsViewHolder
                postsViewHolder.bind(fakedata[position] as Data)
                Log.d(TAG, "onBindViewHolder: "+fakedata[position])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d(TAG, "onCreateViewHolder: ")
        return when(viewType) {
            1 -> {
                val inflater = LayoutInflater.from(parent.context)
                val example = inflater.inflate(R.layout.livestream_item, parent, false)
                return LiveStreamViewHolder(example)
            }
            else -> {
                val inflater = LayoutInflater.from(parent.context)
                val example = inflater.inflate(R.layout.post_item, parent, false)
                return PostsViewHolder(example)
            }
        }
    }

    fun update() {
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        Log.d(TAG, "getItemCount: ")
        return  if(isLiveStream) 6 else 2
    }

    override fun getItemViewType(position: Int): Int {
        Log.d(TAG, "getItemViewType: ")
        return if(isLiveStream) 1 else 0
    }
    class LiveStreamViewHolder : RecyclerView.ViewHolder {
        private  val TAG = "LiveStreamAdapter"
        constructor(itemView: View) : super(itemView)
        val liveStreamImageView = itemView.findViewById<ImageView>(R.id.liveStreamImageView)
        fun bind(fakedata: Any) {
            Log.d(TAG, "bind: ")
            liveStreamImageView.setBackgroundResource(fakedata as Int)

        }
    }
    class PostsViewHolder : RecyclerView.ViewHolder {
        constructor(itemView: View) : super(itemView)
        val postsUserIcon = itemView.findViewById<CircleImageView>(R.id.circleImageView2)
        val postsUserName = itemView.findViewById<TextView>(R.id.textView10)
        val postsUserLevel = itemView.findViewById<TextView>(R.id.textView11)
        val topic = itemView.findViewById<TextView>(R.id.textView12)
        val content = itemView.findViewById<TextView>(R.id.textView13)
        val picture1 = itemView.findViewById<ImageView>(R.id.imageView2)
        val picture2 = itemView.findViewById<ImageView>(R.id.imageView3)
        val picture3 = itemView.findViewById<ImageView>(R.id.imageView4)
        val love = itemView.findViewById<TextView>(R.id.textView16)
        val message = itemView.findViewById<TextView>(R.id.textView17)
        fun bind(fakedata: Data) {
            postsUserIcon.setImageResource(fakedata.userIcon)
            postsUserName.text = fakedata.userName
            postsUserLevel.text = fakedata.userLevel
            topic.text = fakedata.topic
            content.text = fakedata.content
            picture1.setImageResource(fakedata.picture1)
            picture2.setImageResource(fakedata.picture2)
            picture3.setImageResource(fakedata.picture3)
            love.text = fakedata.love
            message.text = fakedata.message

        }
    }
}