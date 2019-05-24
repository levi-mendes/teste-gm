package br.com.levimendesestudos.avenuecode.activities

import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.KeyEvent
import android.view.View
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import br.com.levimendesestudos.avenuecode.R
import br.com.levimendesestudos.avenuecode.adapters.AddressesListAdapter
import br.com.levimendesestudos.avenuecode.models.Address
import br.com.levimendesestudos.avenuecode.mvp.contracts.MainMVP
import br.com.levimendesestudos.avenuecode.mvp.presenter.MainPresenter
import br.com.levimendesestudos.avenuecode.utils.KeyBoardUtil
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.OnEditorAction

class MainActivity : BaseActivity(), MainMVP.View {

    @BindView(R.id.tietSearch)
    internal var tietSearch: TextInputEditText? = null
    @BindView(R.id.tilSearch)
    internal var tilSearch: TextInputLayout? = null
    @BindView(R.id.ibSearch)
    internal var ibSearch: ImageButton? = null
    @BindView(R.id.rvAddresses)
    internal var rvAddresses: RecyclerView? = null
    @BindView(R.id.pbLoading)
    internal var pbLoading: ProgressBar? = null
    @BindView(R.id.tvNoResults)
    internal var tvNoResults: TextView? = null

    lateinit var mPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        tietSearch!!.setText("SpringField")

        configureList()

        mPresenter = MainPresenter(this)
    }

    override fun setErrorAddress(value: Boolean, msg: String) {
        tilSearch!!.isErrorEnabled = value
        tilSearch!!.error = msg
    }

    override fun cleanList() {
        rvAddresses!!.adapter = null
    }

    override fun showPbLoading() {
        pbLoading!!.visibility = View.VISIBLE
    }

    override fun hidePbLoading() {
        pbLoading!!.visibility = View.GONE
    }

    override fun showNoResults() {
        tvNoResults!!.visibility = View.VISIBLE
    }

    override fun hideNoResults() {
        tvNoResults!!.visibility = View.GONE
    }

    override fun address(): String {
        return tietSearch!!.text.toString()
    }

    @OnEditorAction(R.id.tietSearch)
    fun etSearchAction(textView: TextView, i: Int, keyEvent: KeyEvent): Boolean {
        mPresenter.search()

        return true
    }

    override fun hideKeyboard() {
        KeyBoardUtil.hide(ibSearch!!)
    }

    @OnClick(R.id.ibSearch)
    fun ibSearchClick() {
        mPresenter.search()
    }

    private fun configureList() {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        rvAddresses!!.layoutManager = layoutManager
        rvAddresses!!.itemAnimator = DefaultItemAnimator()
        rvAddresses!!.setHasFixedSize(true)
    }

    override fun loadList(list: List<Address>) {
        val adapter = AddressesListAdapter(this, list)
        rvAddresses!!.adapter = adapter
    }
}