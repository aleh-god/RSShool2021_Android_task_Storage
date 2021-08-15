package by.godevelopment.rsshool2021androidtaskstorage.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import by.godevelopment.rsshool2021androidtaskstorage.database.CatProvider
import by.godevelopment.rsshool2021androidtaskstorage.entity.Cat
import by.godevelopment.rsshool2021androidtaskstorage.databinding.ItemCatBinding

class CatAdapter(
    private val onItemClick: ((position: Int) -> Unit)? = null
) : RecyclerView.Adapter<CatAdapter.CatHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatHolder {
        return CatHolder(
            ItemCatBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onItemClick
        )
    }

    override fun onBindViewHolder(holder: CatHolder, position: Int) {
        val cat = CatProvider.catsList[position]
        holder.bindTo(cat)
    }

    override fun getItemCount(): Int = CatProvider.catsList.size

    class CatHolder(
        @NonNull binding: ItemCatBinding,       // Можем передавать что угодно, как само view так и его binding
        onItemClick: ((position: Int) -> Unit)?
    ) : RecyclerView.ViewHolder(binding.root) {     // Но вернуть в конструктор должны именно view
        private val textName: TextView = binding.Name
        private val textAge: TextView = binding.Age
        private val textBreed: TextView = binding.Breed

        init {
            itemView.setOnClickListener {
                // TODO "Проверить подачу позиции"
                onItemClick?.invoke(adapterPosition)
            }
        }

        fun bindTo(cat: Cat) {
            textName.text = ("Name cat: ${cat.name}").toString()
            textAge.text = ("Age cat: ${cat.age}").toString()
            textBreed.text = ("Breed cat: ${cat.breed}").toString()
        }
    }

}