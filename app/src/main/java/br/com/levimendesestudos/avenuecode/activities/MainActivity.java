package br.com.levimendesestudos.avenuecode.activities;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.List;

import br.com.levimendesestudos.avenuecode.R;
import br.com.levimendesestudos.avenuecode.adapters.AddressesListAdapter;
import br.com.levimendesestudos.avenuecode.models.Address;
import br.com.levimendesestudos.avenuecode.mvp.contracts.MainActivityMVP;
import br.com.levimendesestudos.avenuecode.mvp.presenter.MainActivityPresenter;
import br.com.levimendesestudos.avenuecode.utils.KeyBoardUtil;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnEditorAction;

public class MainActivity extends BaseActivity implements MainActivityMVP.View {

    @BindView(R.id.etSearch)
    EditText etSearch;
    @BindView(R.id.ibSearch)
    ImageButton ibSearch;
    @BindView(R.id.rvAddresses)
    RecyclerView rvAddresses;

    MainActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureList();

        mPresenter = new MainActivityPresenter(this);
    }

    @Override
    public String address() {
        return etSearch.getText().toString();
    }

    @OnEditorAction(R.id.etSearch)
    public boolean etSearchAction() {
        KeyBoardUtil.hide(etSearch);

        return true;
    }

    @OnClick(R.id.ibSearch)
    public void ibSearchClick() {
        KeyBoardUtil.hide(ibSearch);

        mPresenter.search();
    }

    private void configureList() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        rvAddresses.setLayoutManager(layoutManager);
        rvAddresses.setItemAnimator(new DefaultItemAnimator());
        rvAddresses.setHasFixedSize(true);
    }

    @Override
    public void loadList(List<Address> list) {
        AddressesListAdapter adapter = new AddressesListAdapter(this, list);
        rvAddresses.setAdapter(adapter);
    }
}
