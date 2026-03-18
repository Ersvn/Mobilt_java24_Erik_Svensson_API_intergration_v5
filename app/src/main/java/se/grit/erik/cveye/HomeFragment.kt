package se.grit.erik.cveye

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import se.grit.erik.cveye.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentHomeBinding.bind(view)

        binding.btnLoadVulnerabilities.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_resultsFragment)
        }

        binding.btnAbout.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_aboutFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}