package br.com.levimendesestudos.avenuecode.activities;

import android.os.Bundle;
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
import br.com.levimendesestudos.avenuecode.mvp.contracts.MainActivityMVP;
import br.com.levimendesestudos.avenuecode.mvp.presenter.MainActivityPresenter;
import br.com.levimendesestudos.avenuecode.utils.KeyBoardUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;

public class MainActivity extends BaseActivity implements MainActivityMVP.View {

    @BindView(R.id.etSearch)
    EditText etSearch;
    @BindView(R.id.ibSearch)
    ImageButton ibSearch;
    @BindView(R.id.rvAddresses)
    RecyclerView rvAddresses;
    @BindView(R.id.pbLoading)
    ProgressBar pbLoading;
    @BindView(R.id.tvNoResults)
    TextView tvNoResults;

    MainActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        configureList();

        mPresenter = new MainActivityPresenter(this);
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
        return etSearch.getText().toString();
    }

    @OnEditorAction(R.id.etSearch)
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