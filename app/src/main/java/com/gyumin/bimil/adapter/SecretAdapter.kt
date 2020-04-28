package com.gyumin.bimil.adapter

import androidx.core.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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
        private val addressTv = itemView.findViewById<TextView>(R.id.item_tv_address)
        private val typeNumBtn = itemView.findViewById<Button>(R.id.btn_type_num)
        private val typeCapBtn = itemView.findViewById<Button>(R.id.btn_type_cap)
        private val typeSpeBtn = itemView.findViewById<Button>(R.id.btn_type_spe)
        private val noteTv = itemView.findViewById<TextView>(R.id.item_tv_note)
        private val faviconIv = itemView.findViewById<ImageView>(R.id.item_iv_icon)

        fun bind(secret: Secret) {

            Glide.with(itemView).load("http://${secret.address}/favicon.ico").override(80,80).into(faviconIv)

            nameTv.text = secret.name

            if(secret.typeNumber) {
                typeNumBtn.setBackgroundResource(R.drawable.button_type_bg)
                typeNumBtn.setTextColor(ContextCompat.getColor(itemView.context, R.color.typeText))
            } else {
                typeNumBtn.setBackgroundResource(R.drawable.button_type_gray_bg)
                typeNumBtn.setTextColor(ContextCompat.getColor(itemView.context, R.color.typeTextGray))
            }

            if(secret.typeCapital) {
                typeCapBtn.setBackgroundResource(R.drawable.button_type_bg)
                typeCapBtn.setTextColor(ContextCompat.getColor(itemView.context, R.color.typeText))

            } else {
                typeCapBtn.setBackgroundResource(R.drawable.button_type_gray_bg)
                typeCapBtn.setTextColor(ContextCompat.getColor(itemView.context, R.color.typeTextGray))

            }

            if(secret.typeSpecialCharacter) {
                typeSpeBtn.setBackgroundResource(R.drawable.button_type_bg)
                typeSpeBtn.setTextColor(ContextCompat.getColor(itemView.context, R.color.typeText))

            } else {
                typeSpeBtn.setBackgroundResource(R.drawable.button_type_gray_bg)
                typeSpeBtn.setTextColor(ContextCompat.getColor(itemView.context, R.color.typeTextGray))
            }

            addressTv.text = secret.address

            noteTv.text = secret.note

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