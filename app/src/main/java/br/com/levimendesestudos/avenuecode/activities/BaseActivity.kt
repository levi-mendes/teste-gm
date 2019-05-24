package br.com.levimendesestudos.avenuecode.activities

import android.support.v7.app.AppCompatActivity
import android.view.MenuItem

import br.com.levimendesestudos.avenuecode.mvp.contracts.BasicView
import br.com.levimendesestudos.avenuecode.utils.ToastUtil

/**
 * Created by 809778 on 09/08/2016.
 */
open class BaseActivity : AppCompatActivity(), BasicView {

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            // Respond to the action bar's Up/Home button
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }//

    override fun showToast(res: Int) {
        ToastUtil.showShort(this, getString(res))
    }
}
