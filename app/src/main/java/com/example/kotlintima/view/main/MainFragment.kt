package com.example.kotlintima.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlintima.R
import com.example.kotlintima.databinding.FragmentMainBinding
import com.example.kotlintima.viewmodel.AppState
import com.example.kotlintima.viewmodel.MainViewModel

class MainFragment : Fragment() {

    lateinit var binding:FragmentMainBinding//утечка памяти

    override fun onDestroy() {
        super.onDestroy()
        //binding=null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentMainBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_main, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //не спасает от null pointer ex
//        binding.buttonOne.setOnClickListener{}
//        view.findViewById<Button>(R.id.buttonOne).setOnClickListener{}

        //viewModelProvider его задача - верни или создай мне view, если уже есть, возвращает
        //главный плюс provider, при повороте соханяет экземпляр
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        //val observer = Observer<Any>{renderData(it)}
        //callback observer \\
        val observer = object:Observer<AppState>{
            override fun onChanged(data: AppState) {
                renderData(data)
            }
        }
        //livadata подпиши меня как слушателя ориентируясь на мой жизненный цикл (следи за моим состоянием)
        //нужно для того чтобы оно не стучалось просто так (избавились от утечек памяти)
        viewModel.getData().observe(viewLifecycleOwner,observer)

        viewModel.getWeather()
    }

    private fun renderData(data:AppState){
        when(data){
            is AppState.Success -> {
                binding.loadingLayout.visibility=View.GONE
                binding.message.text="Получилось"
            }
            is AppState.Loading -> {
                binding.loadingLayout.visibility=View.VISIBLE
            }
            is AppState.Error -> {
                binding.loadingLayout.visibility=View.GONE
                binding.message.text="Не получилось ${data.error}"
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment().apply {}
    }
}