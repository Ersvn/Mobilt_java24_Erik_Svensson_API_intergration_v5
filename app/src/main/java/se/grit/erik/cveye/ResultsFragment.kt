package se.grit.erik.cveye

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import se.grit.erik.cveye.databinding.FragmentResultsBinding
import se.grit.erik.cveye.model.Vulnerability
import se.grit.erik.cveye.network.RetrofitInstance
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

class ResultsFragment : Fragment(R.layout.fragment_results) {

    private var _binding: FragmentResultsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentResultsBinding.bind(view)
        binding.recyclerViewCves.layoutManager = LinearLayoutManager(requireContext())

        loadVulnerabilities()
    }

    private fun loadVulnerabilities() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val utcTimeZone = TimeZone.getTimeZone("UTC")
                val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
                formatter.timeZone = utcTimeZone

                val endCalendar = Calendar.getInstance(utcTimeZone)
                val startCalendar = Calendar.getInstance(utcTimeZone)
                startCalendar.add(Calendar.DAY_OF_YEAR, -30)

                val pubStartDate = formatter.format(startCalendar.time)
                val pubEndDate = formatter.format(endCalendar.time)

                val response = RetrofitInstance.api.getLatestVulnerabilities(
                    resultsPerPage = 10,
                    pubStartDate = pubStartDate,
                    pubEndDate = pubEndDate
                )

                if (response.isSuccessful) {
                    val body = response.body()
                    val cveItems = body?.vulnerabilities?.map { vulnerability ->
                        vulnerability.toCveItem()
                    } ?: emptyList()

                    val adapter = CveAdapter(cveItems) { selectedItem ->
                        val bundle = Bundle().apply {
                            putString("cve_id", selectedItem.id)
                            putString("cve_published", selectedItem.published)
                            putString("cve_last_modified", selectedItem.lastModified)
                            putString("cve_description", selectedItem.description)
                        }

                        findNavController().navigate(
                            R.id.action_resultsFragment_to_detailFragment,
                            bundle
                        )
                    }

                    binding.recyclerViewCves.adapter = adapter
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Could not load vulnerabilities",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: Exception) {
                Toast.makeText(
                    requireContext(),
                    "Error: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun Vulnerability.toCveItem(): CveItem {
        val englishDescription = cve.descriptions.firstOrNull { it.lang == "en" }?.value
            ?: "No description available."

        return CveItem(
            id = cve.id,
            published = cve.published,
            lastModified = cve.lastModified,
            description = englishDescription
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}