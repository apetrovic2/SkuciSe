package com.example.myapplication.ui.notifications

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.DataModel
import com.example.myapplication.PhotoAdapter
import com.example.myapplication.R
import com.example.myapplication.Registration
import com.example.myapplication.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private var _binding: FragmentNotificationsBinding? = null


//    private lateinit var photoAdapter: PhotoAdapter
//    private var dataList= mutableListOf<DataModel>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

      //  val textView: TextView = binding.textNotifications
      //  notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
      //      textView.text = it
      //  })
//        val binding: ViewDataBinding? = DataBindingUtil.inflate(inflater, R.layout.fragment_notifications,container,false)
//        photoAdapter = PhotoAdapter(container!!.context)
//        binding.recyclerViewProfile.adapter = photoAdapter
//
//        dataList.add(DataModel("Title","Desc",R.drawable.photo1))
//        dataList.add(DataModel("Title","Desc",R.drawable.photo2))
//        dataList.add(DataModel("Title","Desc",R.drawable.photo3))
//        dataList.add(DataModel("Title","Desc",R.drawable.photo4))

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}