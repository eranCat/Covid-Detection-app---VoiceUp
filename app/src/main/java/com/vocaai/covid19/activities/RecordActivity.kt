package com.vocaai.covid19.activities

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.kaopiz.kprogresshud.KProgressHUD
import com.vocaai.covid19.R
import com.vocaai.covid19.models.RecordInstructions
import com.vocaai.covid19.utils.extensions.toast
import kotlinx.android.synthetic.main.activity_record.*
import java.io.IOException

private const val LOG_TAG = "AudioRecordTest"
private const val RC_RECORD_AUDIO = 200

class RecordActivity : AppCompatActivity() {

    var recordNumber: Int = 1
    lateinit var instructions: RecordInstructions

    private var fileName: String = ""

    private var isRecording: Boolean = false

    private var recorder: MediaRecorder? = null

    private var player: MediaPlayer? = null

    // Requesting permission to RECORD_AUDIO
    private var permissionToRecordAccepted = false
    private var permissions = arrayOf(Manifest.permission.RECORD_AUDIO)

    companion object {
        fun start(context: Context, instructions: RecordInstructions, recordNumber: Int) {

            val intent = Intent(context, RecordActivity::class.java)
                .putExtra("instructions", instructions)
                .putExtra("recordNum", recordNumber)

            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)

        instructions = intent.getParcelableExtra("instructions")
            ?: RecordInstructions()

        setup(instructions)

        recordNumber = intent.getIntExtra("recordNum", -1)
        if (recordNumber == -1) return

        record_btn.setOnClickListener { recordTapped() }


    // Record to the external cache directory for visibility
        fileName = "${externalCacheDir?.absolutePath}/audiorecordtest.3gp"

        ActivityCompat.requestPermissions(this, permissions, RC_RECORD_AUDIO)

    }

    private fun recordTapped() {
        if (!isRecording) startRecording()
        else finishRecording()

        isRecording = !isRecording
    }

    private fun setup(instructions: RecordInstructions) =
        instructions.instruction?.let {
            instruction_tv.text = it.title
            desc_tv.text = it.description
        }

    override fun onStop() {
        super.onStop()
        recorder?.release()
        recorder = null
        player?.release()
        player = null
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionToRecordAccepted =
            if (requestCode == RC_RECORD_AUDIO) {
                grantResults[0] == PERMISSION_GRANTED
            } else false

        if (!permissionToRecordAccepted)
            toast("Can't check your health without permission")
    }

    private fun onRecord(start: Boolean) {
        if (start) {
            startRecording()
        } else {
            stopRecording()
        }
    }

    private fun onPlay(start: Boolean) {
        if (start) {
            startPlaying()
        } else {
            stopPlaying()
        }
    }

    private fun startPlaying() {
        player = MediaPlayer().apply {
            try {
                setDataSource(fileName)
                prepare()
                start()
            } catch (e: IOException) {
                Log.e(LOG_TAG, "prepare() failed")
                finishRecording()
            }
        }
    }

    private fun finishRecording() {
        stopPlaying()

        KProgressHUD.create(this).show()
        info_tv.text = getString(R.string.re_record)
    }

    private fun stopPlaying() {
        player?.release()
        player = null
    }

    private fun startRecording() {
        recorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setOutputFile(fileName)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)

            try {
                prepare()

                record_btn.setImageResource(R.drawable.stop)

            } catch (e: IOException) {
                Log.e(LOG_TAG, "prepare() failed")
            }

            start()
        }
    }

    private fun stopRecording() {
        recorder?.apply {
            stop()
            release()
        }
        recorder = null
    }

}
