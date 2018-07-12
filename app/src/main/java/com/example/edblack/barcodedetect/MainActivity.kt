package com.example.edblack.barcodedetect

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetectorOptions
import android.util.SparseIntArray
import android.view.Surface
import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager
import android.support.v4.view.ViewCompat.getRotation
import android.hardware.camera2.CameraAccessException
import android.app.Activity
import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.Log


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    val options = FirebaseVisionBarcodeDetectorOptions.Builder()
            .setBarcodeFormats(
                    FirebaseVisionBarcode.FORMAT_AZTEC,
                    FirebaseVisionBarcode.FORMAT_QR_CODE)
            .build()

    val detector = FirebaseVision.getInstance()
            .getVisionBarcodeDetector(options)


    private val ORIENTATIONS = SparseIntArray()



    init {
        ORIENTATIONS.append(Surface.ROTATION_0, 90)
        ORIENTATIONS.append(Surface.ROTATION_90, 0)
        ORIENTATIONS.append(Surface.ROTATION_180, 270)
        ORIENTATIONS.append(Surface.ROTATION_270, 180)
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Throws(CameraAccessException::class)
    private fun getRotationCompensation(cameraId: String, activity: Activity, context: Context): Int {


        val deviceRotation = activity.windowManager.defaultDisplay.rotation
        var rotationCompensation = ORIENTATIONS.get(deviceRotation)

        val cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
        val sensorOrientation = cameraManager
                .getCameraCharacteristics(cameraId)
                .get(CameraCharacteristics.SENSOR_ORIENTATION)!!
        rotationCompensation = (rotationCompensation + sensorOrientation + 270) % 360

        val result: Int
        when (rotationCompensation) {
            0 -> result = FirebaseVisionImageMetadata.ROTATION_0
            90 -> result = FirebaseVisionImageMetadata.ROTATION_90
            180 -> result = FirebaseVisionImageMetadata.ROTATION_180
            270 -> result = FirebaseVisionImageMetadata.ROTATION_270
            else ->{
                result = FirebaseVisionImageMetadata.ROTATION_0
            }
        }

        return result
    }



    //TODO: get and instance of FirebaseVisionBarcodeDetector
    //TODO: pass the image to the detectInImage method


}
