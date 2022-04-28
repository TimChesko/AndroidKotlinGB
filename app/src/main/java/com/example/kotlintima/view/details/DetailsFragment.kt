package com.example.kotlintima.view.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import coil.request.ImageRequest
import com.example.kotlintima.databinding.FragmentDetailsBinding
import com.example.kotlintima.repository.Weather
import com.example.kotlintima.utlis.KEY_BUNDLE_WEATHER
import com.example.kotlintima.viewmodel.DetailsState
import com.example.kotlintima.viewmodel.DetailsViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding
        get() = _binding!!

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    private lateinit var currentCityName: String

    private val viewModel: DetailsViewModel by lazy {
        ViewModelProvider(this).get(DetailsViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(
            viewLifecycleOwner
        ) { t -> renderData(t) }
        arguments?.getParcelable<Weather>(KEY_BUNDLE_WEATHER)?.let {
            currentCityName = it.city.name
            viewModel.getWeather(it.city)
        }
    }

    @SuppressLint("SetTextI18n")
    fun renderData(detailsState: DetailsState) = when (detailsState) {
        is DetailsState.Success -> {
            val weather = detailsState.weather
            with(binding) {
                loadingLayout.visibility = View.GONE
                cityName.text = weather.city.name
                temperatureValue.text = weather.temperature.toString()
                feelsLikeValue.text = weather.feelsLike.toString()
                cityCoordinates.text = "${weather.city.lat} ${weather.city.lon}"
                showSnackBar("Work!")
//                    Glide.with(requireContext())
//                        .load("https://freepngimg.com/thumb/city/36275-3-city-hd.png")
//                        .into(headerCityIcon)

//                    Picasso.get()?.load("https://freepngimg.com/thumb/city/36275-3-city-hd.png")
//                         ?.into(headerCityIcon)
                headerCityIcon.load("https://freepngimg.com/thumb/city/36275-3-city-hd.png")
                icon.loadSvg("https://yastatic.net/weather/i/icons/blueye/color/svg/${weather.icon}.svg")
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
                showSnackBar(detailsState.error.toString())
            }
        }
    }

    private fun ImageView.loadSvg(url: String) {
        val imageLoader = ImageLoader.Builder(this.context)
            .componentRegistry { add(SvgDecoder(this@loadSvg.context)) }
            .build()
        val request = ImageRequest.Builder(this.context)
            .crossfade(true)
            .crossfade(500)
            .data(url)
            .target(this)
            .build()
        imageLoader.enqueue(request)
    }

    fun showSnackBar(text: String) {
        Snackbar.make(mainView, text, Snackbar.LENGTH_SHORT).show()
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

