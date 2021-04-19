package school.cesar.mobileav1

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView

class RegisterFruitActivity : AppCompatActivity() {
    lateinit var imageView: ImageView

    private val pickImage = 100
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_fruit)

        val cancelBtn = findViewById<Button>(R.id.cancelBtn)
        cancelBtn.setOnClickListener {
            finish()
        }

        val addImageBtn = findViewById<Button>(R.id.add_img)
        addImageBtn.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, pickImage)
        }

        imageView = findViewById(R.id.imageView)

        val confirmBtn = findViewById<Button>(R.id.confirmBtn)
        confirmBtn.setOnClickListener {
            val returnIntent = Intent()
            val fruitNameInput = findViewById<EditText>(R.id.fruit_name_input)
            val fruitName = fruitNameInput.text.toString();
            val fruiDescriptionInput = findViewById<EditText>(R.id.fruit_description_input)
            val fruitDescription = fruiDescriptionInput.text.toString();
            val fruitItem = FruitItem(R.drawable.morango, fruitName, fruitDescription, imageUri)
            returnIntent.putExtra(MainActivity.MAIN_ACTIVITY_EXTRA_DATA_ID, fruitItem)
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == pickImage) {
            imageUri = data?.data
            imageView.setImageURI(imageUri)
        }
    }
}