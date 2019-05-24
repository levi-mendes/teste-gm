package br.com.levimendesestudos.avenuecode.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import java.util.ArrayList
import br.com.levimendesestudos.avenuecode.R
import br.com.levimendesestudos.avenuecode.activities.MapsActivity
import br.com.levimendesestudos.avenuecode.models.Address
import butterknife.BindView
import butterknife.ButterKnife

/**
 * Created by 809778 on 02/05/2016.
 */
class AddressesListAdapter(private val mContext: Context, private val mList: List<Address>) : RecyclerView.Adapter<AddressesListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.address_row, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val address = mList[position]

        holder.itemView.setOnClickListener { view -> callMaps(mList, position) }
        holder.tvFormattedAddress!!.text = address.formattedAddress
    }

    private fun callMaps(list: List<Address>, position: Int) {
        val bundle = Bundle()

        if (position == 0 && list.size > 1) {
            bundle.putBoolean("all", true)
        }

        val intent = Intent(mContext, MapsActivity::class.java)
        bundle.putInt("position", position)
        bundle.putParcelableArrayList("addresses", list as ArrayList<*>)
        intent.putExtras(bundle)
        mContext.startActivity(intent)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    internal inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        @BindView(R.id.tvFormattedAddress)
        var tvFormattedAddress: TextView? = null

        init {

            ButterKnife.bind(this, v)
        }
    }
}