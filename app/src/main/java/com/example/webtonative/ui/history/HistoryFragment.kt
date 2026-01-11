package com.example.webtonative.ui.history

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.webtonative.R
import com.example.webtonative.data.api.RetrofitClient
import com.example.webtonative.data.db.AppDatabase
import com.example.webtonative.data.db.UrlDao
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.coroutines.launch

class HistoryFragment : Fragment(R.layout.fragment_history) {

    private lateinit var dao: UrlDao

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dao = AppDatabase.getDatabase(requireContext()).urlDao()
        val recycler = view.findViewById<RecyclerView>(R.id.rvHistory)
        recycler.layoutManager = LinearLayoutManager(requireContext())

        loadData(recycler)

        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbarHistory)
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_delete -> clearAll(recycler)
                R.id.action_upload -> uploadData()
            }
            true
        }
    }

    private fun loadData(recycler: RecyclerView) {
        lifecycleScope.launch {
            recycler.adapter = HistoryAdapter(dao.getAll())
        }
    }

    private fun clearAll(recycler: RecyclerView) {
        lifecycleScope.launch {
            dao.deleteAll()
            loadData(recycler)
        }
    }

    private fun uploadData() {
        lifecycleScope.launch {
            try {
                val data = dao.getAll()
                if (data.isEmpty()) {
                    Toast.makeText(requireContext(), "No data to upload", Toast.LENGTH_SHORT).show()
                    return@launch
                }
                RetrofitClient.api.uploadUrls(data)
                Toast.makeText(requireContext(),
                    "Uploaded Successfully", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(requireContext(),
                    "Upload Failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
