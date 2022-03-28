package com.example.kotlintima.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlintima.databinding.FragmentMainBinding
import com.example.kotlintima.viewmodel.AppState
import com.example.kotlintima.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar


class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    lateinit var binding: FragmentMainBinding//утечка памяти

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    //viewModelProvider его задача - верни или создай мне view, если уже есть, возвращает
    //главный плюс provider, при повороте соханяет экземпляр

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val observer = Observer<AppState> { data -> renderData(data) }
        viewModel.getData().observe(viewLifecycleOwner, observer)
        viewModel.getWeather()
    }

    private fun renderData(data: AppState) {
        when (data) {
            is AppState.Success -> {
                binding.loadingLayout.visibility = View.GONE
                binding.message.text = "Получилось"
            }
            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                //binding.message.text="Не получилось ${data.error}"
                Snackbar.make(binding.frameLayout, "Не получилось", Snackbar.LENGTH_LONG)
                    .setAction("Reload") { viewModel.getWeather() }.show()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment().apply {}
    }

}
