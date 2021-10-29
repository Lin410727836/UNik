package tw.edu.pu.unik.ui.home

import android.Manifest
import android.animation.Animator
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import tw.edu.pu.unik.R
import tw.edu.pu.unik.databinding.FragmentLiveStreamBinding
import androidx.core.app.ActivityCompat

import android.content.pm.PackageManager
import android.util.Log

import androidx.core.content.ContextCompat
import io.agora.rtc.Constants
import io.agora.rtc.Constants.CHANNEL_PROFILE_COMMUNICATION
import io.agora.rtc.IRtcEngineEventHandler
import io.agora.rtc.RtcEngine
import io.agora.rtc.video.VideoCanvas
import io.agora.rtc.video.VideoEncoderConfiguration
import tw.edu.pu.unik.BuildConfig
import android.util.DisplayMetrics
import android.view.*
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import kotlinx.android.synthetic.main.fragment_live_stream.*
import tw.edu.pu.unik.ContainerActivity


class LiveStream : Fragment() , FragmentTouch{
    private  val TAG = "LiveStream"
    companion object{
        @JvmStatic
        var uid = 0
    }
    private lateinit var binding: FragmentLiveStreamBinding
    private lateinit var mRtcEngine: RtcEngine
    private lateinit var video: SurfaceView
    private lateinit var mRtcEventHandler: IRtcEngineEventHandler
    private var isRead = false
    private var isShare = false
    private var height = 0
    private var y = 0f
    private val permission = arrayOf(
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.CAMERA
    )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLiveStreamBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRtcEventHandler = object : IRtcEngineEventHandler() {
            override fun onUserJoined(uid: Int, elapsed: Int) {
            }

            override fun onUserOffline(uid: Int, reason: Int) {
            }

            override fun onJoinChannelSuccess(channel: String?, uid: Int, elapsed: Int) {
                super.onJoinChannelSuccess(channel, uid, elapsed)
                Log.d("UUID2", "onUserJoined: $uid")
                val v = VideoCanvas(video, VideoCanvas.RENDER_MODE_FIT, 1)
                if (uid != 1)
                    mRtcEngine.setupRemoteVideo(v)
                else
                    mRtcEngine.setupLocalVideo(v)


            }

        }
        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
        height = displayMetrics.heightPixels
        if (checkSelfPermission(permission[0], 22)){
            if (checkSelfPermission(permission[1], 22))
                initEngineAndJoinChannel()
        }

        binding.textView24.setOnClickListener {
            if (!isRead){
                showGood()
            }else {
                hideGood()
            }
            isRead = !isRead
        }
        binding.button.setOnClickListener {
            Log.d(TAG, "onViewCreated: ")
            findNavController(view).navigate(R.id.action_liveStream_to_OrderInformation)
        }

    }


    override fun onStart() {
        super.onStart()
        hideGood()
        hideShare()
    }

    override fun onDestroy() {
        super.onDestroy()
        leaveChannel()
        RtcEngine.destroy()
    }

    override fun fragmentTouch(event: MotionEvent): Boolean {
        Log.d("Touch", "fragmentTouch: ${event.actionMasked}")
        when (event.actionMasked) {
            0 -> {
                y = event.y
            }
            2 -> {
                if (event.y - y < -100) {
                    if (!isShare) {
                        isShare = true
                        showShare()
                    }
                } else if (event.y - y > 100) {
                    if (isShare) {
                        isShare = false
                        hideShare()
                    }
                }
            }
        }
        val act = requireActivity() as ContainerActivity
        return act.touch(event)
    }
    private fun leaveChannel() {
        mRtcEngine.leaveChannel()
    }

    private fun checkSelfPermission(permission: String, requestCode: Int): Boolean {
        if (ContextCompat.checkSelfPermission(requireContext(), permission) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(permission), requestCode)
            return false
        }
        return true
    }

    private fun initEngineAndJoinChannel() {
        initializeAgoraEngine()
        val config = VideoEncoderConfiguration(
            VideoEncoderConfiguration.VD_1280x720,
            VideoEncoderConfiguration.FRAME_RATE.FRAME_RATE_FPS_30,
            VideoEncoderConfiguration.STANDARD_BITRATE,
            VideoEncoderConfiguration.ORIENTATION_MODE.ORIENTATION_MODE_FIXED_PORTRAIT
        )
        mRtcEngine.setVideoEncoderConfiguration(config)
        mRtcEngine.enableVideo()
        mRtcEngine.setChannelProfile(Constants.CHANNEL_PROFILE_LIVE_BROADCASTING)
        mRtcEngine.setParameters("{\"che.audio.live_for_comm\": true}")
        if (uid == 1) {
            mRtcEngine.setClientRole(IRtcEngineEventHandler.ClientRole.CLIENT_ROLE_BROADCASTER)
        }
        else {
            mRtcEngine.setClientRole(IRtcEngineEventHandler.ClientRole.CLIENT_ROLE_AUDIENCE)
        }
        video = RtcEngine.CreateRendererView(requireContext())
        video.setZOrderMediaOverlay(true)
        binding.video.addView(video)
        joinChannel()
    }


    private fun initializeAgoraEngine() {
        try {
            mRtcEngine = RtcEngine.create(requireContext(), BuildConfig.app_id, mRtcEventHandler)
        } catch (e: Exception) {
            throw RuntimeException("NEED TO check rtc sdk init fatal error\n$e")
        }
    }

    private fun joinChannel() {
        mRtcEngine.joinChannel(BuildConfig.token, BuildConfig.channel_name, "", uid)

    }

    private fun showShare() {
        binding.shareCons.animate()
            .setDuration(500)
            .alpha(1f)
            .start()
    }

    private fun hideShare() {
        binding.shareCons.animate()
            .setDuration(500)
            .alpha(0f)
            .start()
    }

    private fun showGood() {
        binding.goodCons.animate()
            .setDuration(500)
            .alpha(1f)
            .setListener(object : Animator.AnimatorListener{
                override fun onAnimationStart(animation: Animator?) {
                    binding.goodCons.visibility = View.VISIBLE
                    binding.goodBtn.visibility = View.GONE
                }

                override fun onAnimationEnd(animation: Animator?) {

                }

                override fun onAnimationCancel(animation: Animator?) {
                }

                override fun onAnimationRepeat(animation: Animator?) {
                }
            })
            .start()
    }



    private fun hideGood() {
        binding.goodCons.animate()
            .setDuration(500)
            .alpha(0f)
            .setListener(object : Animator.AnimatorListener{
                override fun onAnimationStart(animation: Animator?) {
                    binding.goodCons.visibility = View.GONE
                    binding.goodBtn.visibility = View.VISIBLE
                }

                override fun onAnimationEnd(animation: Animator?) {


                }

                override fun onAnimationCancel(animation: Animator?) {
                }

                override fun onAnimationRepeat(animation: Animator?) {
                }
            })
            .start()
    }



}
interface FragmentTouch {
    fun fragmentTouch(event: MotionEvent): Boolean
}