package school.cesar.mobileav1

import android.app.Activity
import android.content.Intent
import android.content.Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION
import android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
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

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.register_toolbar)
        toolbar.title = "Adicionar Fruta"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val cancelBtn = findViewById<Button>(R.id.cancelBtn)
        cancelBtn.setOnClickListener {
            finish()
        }

        val addImageBtn = findViewById<Button>(R.id.add_img)
        addImageBtn.setOnClickListener {
            val gallery = Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
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

            val contentResolver = applicationContext.contentResolver
            val takeFlags: Int = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
            imageUri?.let { contentResolver.takePersistableUriPermission(it, takeFlags) }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }
}