package school.cesar.mobileav1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Toast
import android.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), FruitAdapter.OnImageClickListener {

    var fruitList: MutableList<FruitItem> = mutableListOf()

    companion object {
        const val MAIN_ACTIVITY_REGISTRY_RESULT_CODE = 1
        const val MAIN_ACTIVITY_DELETE_RESULT_CODE = 2
        const val MAIN_ACTIVITY_EXTRA_DATA_ID = "MAIN_ACTIVITY_EXTRA_DATA"
        const val MAIN_ACTIVITY_EXTRA_POSITION_ID = "MAIN_ACTIVITY_EXTRA_DATA_POSITION"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.my_toolbar)
        toolbar.title = "Lista de Frutas"
        setSupportActionBar(toolbar)

        fruitList = initFruitList() as MutableList<FruitItem>

        val button = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        button.setOnClickListener {
            val registerFruitActivity = Intent(this, RegisterFruitActivity::class.java)
            startActivityForResult(registerFruitActivity, MAIN_ACTIVITY_REGISTRY_RESULT_CODE)
        }

        recycler_view.adapter = FruitAdapter(fruitList, this)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
    }

    override fun onImageClick(position: Int) {
        val clickedItem: FruitItem = fruitList[position]
        val deleteActivity = Intent(this, DeleteActivity::class.java)
        deleteActivity.putExtra(MAIN_ACTIVITY_EXTRA_DATA_ID, clickedItem)
        deleteActivity.putExtra(MAIN_ACTIVITY_EXTRA_POSITION_ID, position)
        startActivityForResult(deleteActivity, MAIN_ACTIVITY_DELETE_RESULT_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (MAIN_ACTIVITY_REGISTRY_RESULT_CODE == requestCode) {
                val result = data?.getParcelableExtra<FruitItem>(MAIN_ACTIVITY_EXTRA_DATA_ID)
                val fruitName = result?.fruitName
                val fruitDescription = result?.fruitDescription
                val fruitImageUri = result?.imageUri
                fruitList.add(
                    FruitItem(
                        R.drawable.ic_baseline_filter_vintage_24,
                        fruitName,
                        fruitDescription,
                        fruitImageUri
                    )
                )
                val fruitSize = fruitList.size
                recycler_view.adapter?.notifyItemInserted(fruitSize)
                val toast = Toast.makeText(
                    this,
                    "fruta $fruitName adicionada com sucesso",
                    Toast.LENGTH_LONG
                )
                toast.show()
            }
            if (MAIN_ACTIVITY_DELETE_RESULT_CODE == requestCode) {
                val deletedPosition = data?.getIntExtra(MAIN_ACTIVITY_EXTRA_DATA_ID, 0)
                if (deletedPosition != null) {
                    fruitList.removeAt(deletedPosition)
                    recycler_view.adapter?.notifyItemRemoved(deletedPosition)
                }
            }
        }
    }

    private fun initFruitList(): List<FruitItem> {
        val list = ArrayList<FruitItem>()
        val morango = FruitItem(
            R.drawable.morango,
            fruitName = "Morango",
            fruitDescription = "fruta rica em antioxidantes, como antocianinas e o ácido elágico, que conferem outros benefícios para a saúde."
        )

        val pinha = FruitItem(
            R.drawable.ic_baseline_filter_vintage_24,
            fruitName = "Pinha",
            fruitDescription = "fruta rica em nutrientes como fibras, vitaminas B e C e minerais, com propriedades antioxidantes e anti-inflamatórias que ajudam a combater a dor e inflamação, aumentar as defesas do organismo e controlar os níveis de açúcar no sangue."
        )

        list += morango
        list += pinha
        list += morango
        list += pinha
        list += morango
        list += pinha
        list += morango
        list += pinha
        list += morango
        list += pinha

        return list
    }
}