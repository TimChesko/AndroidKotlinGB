package com.example.kotlintima.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kotlintima.R
import com.example.kotlintima.databinding.FragmentDetailsBinding
import com.example.kotlintima.repository.Weather
import com.example.kotlintima.utlis.KEY_BUNDLE_WEATHER
import com.google.android.material.snackbar.Snackbar

class DetailsFragment : Fragment() {


    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding
        get() {
            return _binding!!
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<Weather>(KEY_BUNDLE_WEATHER)?.let {
            renderData(it)
        } //при рендере точно не будет null
    }

    private fun renderData(weather: Weather) {
        /**
         * Функции apply и also возвращают объект контекста -> цепочка функций
         * let, run и with возвращают результат лямбды -> присваивание переменной
         *
         * Поэтому я использую with, а не apply
         * Подробнее: https://kotlinlang.ru/docs/reference/scope-functions.html
         *
         * Примечание: не ипользовать with внутри with, чтобы не ломать читаемость кода и просто не было проблем !!!
         **/
        with(binding) {
            loadingLayout.visibility = View.GONE
            cityName.text = weather.city.name
            temperatureValue.text = weather.temperature.toString()
            feelsLikeValue.text = weather.feelsLike.toString()
            cityCoordinates.text = "${weather.city.lat} ${weather.city.lon}"
            imageCity.setImageResource(weather.city.imageCity)
            mainView.showSnackBar(mainView, "Work")
        }
    }

    fun View.showSnackBar(view: View, text: String) {
        Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show()
        //short чтобы не бесило
    }

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}

