package tw.edu.pu.unik

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class LiveStreamAdapter:RecyclerView.Adapter<LiveStreamAdapter.ViewHolder> {
    private var context:Context
    constructor(context: Context):super() {
        this.context = context
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
    class ViewHolder : RecyclerView.ViewHolder {
        constructor(itemView: View) : super(itemView)
    }
}