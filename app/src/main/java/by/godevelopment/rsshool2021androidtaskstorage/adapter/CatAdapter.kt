package by.godevelopment.rsshool2021androidtaskstorage.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import by.godevelopment.rsshool2021androidtaskstorage.database.CatProducerWithSql
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
        val cat = CatProvider.catsListSQL[position]
        holder.bindTo(cat)
    }

    override fun getItemCount(): Int = CatProvider.catsListSQL.size

    class CatHolder(
        @NonNull private val binding: ItemCatBinding,       // Можем передавать что угодно, как само view так и его binding
        private val onItemClick: ((position: Int) -> Unit)?
    ) : RecyclerView.ViewHolder(binding.root) {     // Но вернуть в конструктор должны именно view

        init {
            itemView.setOnClickListener {
                // TODO "Проверить подачу позиции"
                onItemClick?.invoke(adapterPosition)
            }
        }

        fun bindTo(cat: Cat) {
            with (binding) {
                Name.text = ("Name cat: ${cat.name}").toString()
                Age.text = ("Age cat: ${cat.age}").toString()
                Breed.text = ("Breed cat: ${cat.breed}").toString()

                // Log.i("bindTo", "(*${cat.name}*, ${cat.age}, *${cat.breed}*)")
            }
        }
    }

}