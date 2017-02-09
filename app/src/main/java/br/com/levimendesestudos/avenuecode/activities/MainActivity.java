package br.com.levimendesestudos.avenuecode.activities;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.List;
import br.com.levimendesestudos.avenuecode.R;
import br.com.levimendesestudos.avenuecode.adapters.AddressesListAdapter;
import br.com.levimendesestudos.avenuecode.models.Address;
import br.com.levimendesestudos.avenuecode.mvp.contracts.MainMVP;
import br.com.levimendesestudos.avenuecode.mvp.presenter.MainPresenter;
import br.com.levimendesestudos.avenuecode.utils.KeyBoardUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;

public class MainActivity extends BaseActivity implements MainMVP.View {

    @BindView(R.id.tietSearch)
    TextInputEditText tietSearch;
    @BindView(R.id.tilSearch)
    TextInputLayout tilSearch;
    @BindView(R.id.ibSearch)
    ImageButton ibSearch;
    @BindView(R.id.rvAddresses)
    RecyclerView rvAddresses;
    @BindView(R.id.pbLoading)
    ProgressBar pbLoading;
    @BindView(R.id.tvNoResults)
    TextView tvNoResults;

    MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //etSearch.setText("SpringField");

        configureList();

        mPresenter = new MainPresenter(this);
    }

    @Override
    public void setErrorAddress(boolean value, String msg) {
        tilSearch.setErrorEnabled(value);
        tilSearch.setError(msg);
    }

    @Override
    public void cleanList() {
        rvAddresses.setAdapter(null);
    }

    @Override
    public void showPbLoading() {
        pbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidePbLoading() {
        pbLoading.setVisibility(View.GONE);
    }

    @Override
    public void showNoResults() {
        tvNoResults.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNoResults() {
        tvNoResults.setVisibility(View.GONE);
    }

    @Override
    public String address() {
        return tietSearch.getText().toString();
    }

    @OnEditorAction(R.id.tietSearch)
    public boolean etSearchAction() {
        mPresenter.search();

        return true;
    }

    @Override
    public void hideKeyboard() {
        KeyBoardUtil.hide(ibSearch);
    }

    @OnClick(R.id.ibSearch)
    public void ibSearchClick() {
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