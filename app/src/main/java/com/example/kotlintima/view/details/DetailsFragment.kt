package com.example.kotlintima.view.details

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.kotlintima.BuildConfig
import com.example.kotlintima.databinding.FragmentDetailsBinding
import com.example.kotlintima.repository.OnServerResponse
import com.example.kotlintima.repository.Weather
import com.example.kotlintima.repository.dto.WeatherDTO
import com.example.kotlintima.utlis.*
import com.example.kotlintima.viewmodel.DetailsState
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

class DetailsFragment : Fragment(), OnServerResponse {

    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding
        get() {
            return _binding!!
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(receiver)
    }

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            intent?.let { intent ->
                intent.getParcelableExtra<WeatherDTO>(KEY_BUNDLE_SERVICE_BROADCAST_WEATHER)?.let {
                    onResponse(it)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    private lateinit var currentCityName: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(
            receiver,
            IntentFilter(KEY_WAVE_SERVICE_BROADCAST)
        )
        arguments?.getParcelable<Weather>(KEY_BUNDLE_WEATHER)?.let {
            currentCityName = it.city.name
//            requireActivity().startService(
//                Intent(
//                    requireContext(),
//                    DetailsService::class.java
//                ).apply {
//                    putExtra(KEY_BUNDLE_LAT, it.city.lat)
//                    putExtra(KEY_BUNDLE_LON, it.city.lon)
//                })
            getWeather(it.city.lat, it.city.lon)
        }
    }

    private fun getWeather(lat: Double, lon: Double) {
        binding.loadingLayout.visibility = View.VISIBLE // renderData(Loading)

        val client = OkHttpClient()
        val builder = Request.Builder()
        builder.addHeader(YANDEX_API_KEY, BuildConfig.WEATHER_API_KEY)
        builder.url("$YANDEX_DOMAIN${YANDEX_ENDPOINT}lat=$lat&lon=$lon")
        val request = builder.build()
        val callback: Callback = object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                renderData(DetailsState.Error(error = e))
            }
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val weatherDTO: WeatherDTO =
                        Gson().fromJson(response.body()!!.string(), WeatherDTO::class.java)
                    requireActivity().runOnUiThread {
                        renderData(DetailsState.Success(weatherDTO))
                    }
                } else {
                    renderData(DetailsState.Loading)
                }
            }
        }
        val call = client.newCall(request)
        call.enqueue(callback)
    }

    @SuppressLint("SetTextI18n")
    private fun renderData(detailsState: DetailsState){
        when(detailsState){
            is DetailsState.Success -> {
                val weather = detailsState.weather
                with(binding) {
                    loadingLayout.visibility = View.GONE
                    cityName.text = currentCityName
                    temperatureValue.text = weather.fact.temp.toString()
                    feelsLikeValue.text = weather.fact.feelsLike.toString()
                    cityCoordinates.text = "${weather.info.lat} ${weather.info.lon}"
                }
            }
            is DetailsState.Loading -> {
                with(binding) {
                    loadingLayout.visibility = View.VISIBLE
                }
            }
            is DetailsState.Error -> {
                with(binding) {
                    loadingLayout.visibility = View.GONE
                    showSnackBar(mainView, "Error ${detailsState.error}")
                }
            }
        }
    }

    fun showSnackBar(view: View, text: String) {
        Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show()
    }

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onResponse(weatherDTO: WeatherDTO) {
        renderData(DetailsState.Success(weatherDTO))
    }
}

