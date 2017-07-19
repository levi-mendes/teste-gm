package br.com.levimendesestudos.avenuecode.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import br.com.levimendesestudos.avenuecode.R;
import br.com.levimendesestudos.avenuecode.activities.MapsActivity;
import br.com.levimendesestudos.avenuecode.models.Address;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 809778 on 02/05/2016.
 */
public class AddressesListAdapter extends RecyclerView.Adapter<AddressesListAdapter.ViewHolder> {

    private Context mContext;
    private List<Address> mList;

    public AddressesListAdapter(Context context, List<Address> list) {
        mContext = context;
        mList    = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.address_row, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Address address = mList.get(position);

        holder.itemView.setOnClickListener(view -> callMaps(mList, position));
        holder.tvFormattedAddress.setText(address.formattedAddress);
    }

    private void callMaps(List<Address> list, int position) {
        Bundle bundle = new Bundle();

        if (position == 0 && list.size() > 1) {
            bundle.putBoolean("all", true);
        }

        Intent intent = new Intent(mContext, MapsActivity.class);
        bundle.putInt("position", position);
        bundle.putParcelableArrayList("addresses", (ArrayList)list);
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvFormattedAddress)
        TextView tvFormattedAddress;

        public ViewHolder(View v) {
            super(v);

            ButterKnife.bind(this, v);
        }
    }
}