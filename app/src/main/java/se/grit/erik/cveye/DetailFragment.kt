package se.grit.erik.cveye

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import se.grit.erik.cveye.databinding.FragmentDetailBinding

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentDetailBinding.bind(view)

        val cveId = arguments?.getString("cve_id") ?: "Unknown CVE"
        val published = arguments?.getString("cve_published") ?: "Unknown date"
        val lastModified = arguments?.getString("cve_last_modified") ?: "Unknown date"
        val description = arguments?.getString("cve_description") ?: "No description available."

        binding.tvDetailTitle.text = cveId
        binding.tvDetailPublished.text = "Published: $published"
        binding.tvDetailLastModified.text = "Last modified: $lastModified"
        binding.tvDetailDescription.text = description
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}