package com.paul.groceryapp.prescriptionReader

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import com.paul.groceryapp.R
import java.io.InputStream

class PrescriptionReaderActivity :  AppCompatActivity() {
    private lateinit var imageView: ImageView
    private lateinit var resultTextView: TextView
    private val IMAGE_PICK_CODE = 1001
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_prescription_reader)


        imageView = findViewById(R.id.imageView)
        resultTextView = findViewById(R.id.resultTextView)
        val pickImageBtn: Button = findViewById(R.id.pickImageBtn)

        pickImageBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, IMAGE_PICK_CODE)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_PICK_CODE && resultCode == Activity.RESULT_OK && data != null) {
            val imageUri: Uri? = data.data
            imageUri?.let {
                imageView.setImageURI(it)
                runTextRecognition(it)
            }
        }
    }

    private fun runTextRecognition(imageUri: Uri) {
        val inputStream: InputStream? = contentResolver.openInputStream(imageUri)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        val image = InputImage.fromBitmap(bitmap, 0)

        val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

        recognizer.process(image)
            .addOnSuccessListener { visionText ->
                resultTextView.text = visionText.text
            }
            .addOnFailureListener { e ->
                resultTextView.text = "Error: ${e.message}"
            }
    }
}