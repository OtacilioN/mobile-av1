package school.cesar.mobileav1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FruitAdapter(private val fruitList: List<FruitItem>) :
    RecyclerView.Adapter<FruitAdapter.FruitViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.fruit_item, parent, false)
        return FruitViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FruitViewHolder, position: Int) {
        val currentItem = fruitList[position]
        if(currentItem.imageUri != null){
            holder.imageView.setImageURI(currentItem.imageUri)
        }
        else{
            holder.imageView.setImageResource(currentItem.imageResource)
        }
        holder.textViewTitle.text = currentItem.fruitName
        holder.textViewDescription.text = currentItem.fruitDescription
    }

    override fun getItemCount() = fruitList.size

    class FruitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.fruit_image)
        val textViewTitle: TextView = itemView.findViewById(R.id.fruit_title)
        val textViewDescription: TextView = itemView.findViewById(R.id.fruit_description)
    }
}