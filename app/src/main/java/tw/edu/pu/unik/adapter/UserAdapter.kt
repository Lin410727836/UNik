package tw.edu.pu.unik.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tw.edu.pu.unik.databinding.UserPostItemBinding
import tw.edu.pu.unik.databinding.UserStoryItemBinding

class UserAdapter : RecyclerView.Adapter<UserHolder>() {
    var isStory = true
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        return when(viewType) {
            0 -> {
                val binding = UserStoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                UserHolder(binding)
            }
            else -> {
                val binding = UserPostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                UserHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.setAction()
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun getItemViewType(position: Int): Int {
        return if (isStory) 0 else 1
    }

    @SuppressLint("NotifyDataSetChanged")
    fun update() {
        notifyDataSetChanged()
    }
}

class UserHolder: RecyclerView.ViewHolder {
    private var binding: Any

    constructor(binding: UserStoryItemBinding) : super(binding.root) {
        this.binding = binding
    }

    constructor(binding: UserPostItemBinding) : super(binding.root) {
        this.binding = binding
    }

    fun setAction() {
        if (binding is UserStoryItemBinding) {
            val bind = binding as UserStoryItemBinding
        }else {
            val bind = binding as UserPostItemBinding
            bind.imageButton.setOnClickListener {
                val num = bind.textView16.text.toString().toInt() + 1
                bind.textView16.text = num.toString()
            }
        }
    }
}