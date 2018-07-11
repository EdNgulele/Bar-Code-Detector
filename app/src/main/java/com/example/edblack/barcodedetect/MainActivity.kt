package com.example.edblack.barcodedetect

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetectorOptions
import android.util.SparseIntArray
import android.view.Surface


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


    //TODO: determine camera rotation
    //TODO: get and instance of FirebaseVisionBarcodeDetector
    //TODO: pass the image to the detectInImage method



}
