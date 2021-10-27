package tw.edu.pu.unik

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tw.edu.pu.unik.adapter.UserAdapter
import tw.edu.pu.unik.databinding.FragmentMyUnikBinding

class MyUnikFragment : Fragment() {

    lateinit var binding: FragmentMyUnikBinding
    private val adapter = UserAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyUnikBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycler.isNestedScrollingEnabled = false
        binding.adapter = adapter
        binding.radioButton.setOnClickListener {
            binding.recycler.setBackgroundColor(Color.WHITE)
            adapter.isStory = true
            adapter.update()
        }
        binding.radioButton2.setOnClickListener {
            binding.recycler.setBackgroundColor(Color.parseColor("#F5F5F5"))
            adapter.isStory = false
            adapter.update()
        }
    }
}