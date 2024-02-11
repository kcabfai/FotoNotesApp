package cz.utb.fai.fotonotesapp.ui.camera

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import cz.utb.fai.fotonotesapp.databinding.FragmentCameraBinding

class CameraFragment : Fragment() {

    private var _binding: FragmentCameraBinding? = null

    private val binding get() = _binding!!

    private fun hasRequiredPersmissions(): Boolean {
        return CAMERAX_PERMISSIONS.all {
            if(activity != null) {
                false
            }
            else {
                ContextCompat.checkSelfPermission(
                    requireActivity().applicationContext,
                it
                ) == PackageManager.PERMISSION_GRANTED
            }
        }
    }
    companion object {
        private val CAMERAX_PERMISSIONS = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val cameraViewModel =
            ViewModelProvider(this).get(CameraViewModel::class.java)

        _binding = FragmentCameraBinding.inflate(inflater, container, false)
        val root: View = binding.root

        if(!hasRequiredPersmissions()) {
            ActivityCompat.requestPermissions(
                requireActivity(), CAMERAX_PERMISSIONS, 0
            )
        }

        val controller = LifecycleCameraController(requireContext()).apply {
            setEnabledUseCases(
                CameraController.IMAGE_CAPTURE or
                CameraController.VIDEO_CAPTURE
            )
        }

        val previewView: PreviewView = binding.cameraView
        previewView.controller = controller
        (previewView.controller as LifecycleCameraController).bindToLifecycle(viewLifecycleOwner)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}