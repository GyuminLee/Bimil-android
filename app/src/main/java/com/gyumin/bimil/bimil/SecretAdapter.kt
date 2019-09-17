package com.gyumin.bimil.bimil

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.gyumin.bimil.R
import com.gyumin.bimil.data.Secret

class SecretAdapter(val secretItemClick: (Secret) -> Unit, val secretItemLongClick: (Secret) -> Unit)
    : RecyclerView.Adapter<SecretAdapter.ViewHolder>() {
    private var secrets: List<Secret> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_secret, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return secrets.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(secrets[position])
    }


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val nameTv = itemView.findViewById<TextView>(R.id.item_tv_name)
//        private val typeTv = itemView.findViewById<TextView>(R.id.item_tv_type)
        private val addressTv = itemView.findViewById<TextView>(R.id.item_tv_address)
//        private val noteTv = itemView.findViewById<TextView>(R.id.item_tv_note)
        private val faviconIv = itemView.findViewById<ImageView>(R.id.item_iv_icon)

        fun bind(secret: Secret) {
            nameTv.text = secret.name
//            typeTv.text = secret.type
            addressTv.text = secret.address
//            noteTv.text = secret.note
//            faviconIv.setImageResource()

            itemView.setOnClickListener {
                secretItemClick(secret)
            }

            itemView.setOnLongClickListener{
                secretItemLongClick(secret)
                true
            }
        }
    }

    fun setSecrets(secrets: List<Secret>) {
       this.secrets = secrets
        notifyDataSetChanged()
    }
}