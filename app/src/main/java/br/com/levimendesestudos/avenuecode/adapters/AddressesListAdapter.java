package br.com.levimendesestudos.avenuecode.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import br.com.levimendesestudos.avenuecode.R;
import br.com.levimendesestudos.avenuecode.models.Address;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 809778 on 02/05/2016.
 */
public class AddressesListAdapter extends RecyclerView.Adapter<AddressesListAdapter.ViewHolder> {

    //public List<Solicitacao> mDataset;
    //private ListaOnCheckListener mListaOnCheckListener;
    private Context mContext;
    private List<Address> mList;

    public AddressesListAdapter(Context context, List<Address> list) {
        mContext = context;
        mList    = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.address_row, parent, false);
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        /*Solicitacao solicitacao = mDataset.get(position);

        holder.cb.setOnCheckedChangeListener((buttonView, isChecked) -> {
            solicitacao.marked = isChecked;
            mListaOnCheckListener.onCheckSolicitacao(position, isChecked);
        });
        holder.cb.setChecked(solicitacao.marked);
        holder.tvIdSolicitacao.setText(String.valueOf(solicitacao.codigoSolicitacao));
        holder.tvProtocolo.setText(String.valueOf(solicitacao.protocolo));
        holder.tvDataProjeto.setText(solicitacao.dataProtocolo);
        holder.tvNumProjeto.setText(String.valueOf(solicitacao.projeto));
        holder.tvEndereco.setText(solicitacao.endereco);
        holder.tvBairro.setText(solicitacao.bairro);
        holder.tvMunicipio.setText(solicitacao.municipio);

        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.linha_par));

        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.linha_impar));
        }*/
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        //@BindView(R.id.cb)
        //CheckBox cb;

        public ViewHolder(View v) {
            super(v);

            ButterKnife.bind(this, v);
        }
    }
}