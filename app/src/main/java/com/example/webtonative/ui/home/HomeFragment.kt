package com.example.webtonative.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.example.webtonative.R
import com.example.webtonative.data.db.AppDatabase
import com.example.webtonative.data.db.UrlDao
import com.example.webtonative.data.db.UrlEntity
import com.example.webtonative.utils.UrlUtils
import com.google.android.material.appbar.MaterialToolbar
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import kotlinx.coroutines.launch

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var dao: UrlDao

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dao = AppDatabase.getDatabase(requireContext()).urlDao()

        // Carousel Setup
        val viewPager = view.findViewById<ViewPager2>(R.id.viewPager)
        val dotsIndicator = view.findViewById<DotsIndicator>(R.id.dotsIndicator)
        
        // Using built-in android drawables as placeholders since we don't have custom assets
        val images = listOf(
            android.R.drawable.ic_menu_gallery,
            android.R.drawable.ic_menu_camera,
            android.R.drawable.ic_menu_mapmode
        )
        
        val adapter = CarouselAdapter(images)
        viewPager.adapter = adapter
        dotsIndicator.attachTo(viewPager)

        // URL Handling
        val etUrl = view.findViewById<EditText>(R.id.etUrl)
        
        // Check for returned URL from Navigation (Back Stack)
        val navController = findNavController()
        val currentBackStackEntry = navController.currentBackStackEntry
        val savedStateHandle = currentBackStackEntry?.savedStateHandle
        
        savedStateHandle?.getLiveData<String>("last_url")?.observe(viewLifecycleOwner) { url ->
             if (url.isNotEmpty()) {
                 etUrl.setText(url)
             }
        }
        
        savedStateHandle?.getLiveData<Boolean>("clear_url")?.observe(viewLifecycleOwner) { clear ->
            if (clear) {
                etUrl.setText("")
            }
        }

        view.findViewById<Button>(R.id.btnOpen).setOnClickListener {
            val input = etUrl.text.toString()
            val url = UrlUtils.validateUrl(input)

            if (url == null) {
                Toast.makeText(requireContext(),
                    "Please enter a valid URL", Toast.LENGTH_SHORT).show()
            } else {
                saveUrl(url)
                val action =
                    HomeFragmentDirections.actionHomeToWeb(url)
                findNavController().navigate(action)
            }
        }

        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbar)
        toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.action_history) {
                findNavController()
                    .navigate(R.id.action_home_to_history)
            }
            true
        }
    }

    private fun saveUrl(url: String) {
        lifecycleScope.launch {
            dao.insert(
                UrlEntity(
                    url = url,
                    timestamp = System.currentTimeMillis()
                )
            )
        }
    }
}
