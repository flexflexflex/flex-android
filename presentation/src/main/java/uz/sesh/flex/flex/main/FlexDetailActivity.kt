package uz.sesh.flex.flex.main

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_flex_detail.*
import kotlinx.android.synthetic.main.content_flex_detail.*
import uz.sesh.flex.domain.model.Flex
import uz.sesh.flex.flex.R
import uz.sesh.flex.flex.utils.load

class FlexDetailActivity : AppCompatActivity() {
    private lateinit var flex: Flex
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flex_detail)
        setSupportActionBar(toolbar)
        flex = intent.extras.get("data") as Flex
        initUi(flex)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun initUi(flex: Flex) {

        loadHeadImage(flex.image)
        toolbar_layout.title = flex.title
        //textViewDescription.text = flex.description
    }

    fun loadHeadImage(url: String?) {
        supportPostponeEnterTransition()
        url?.let {
            flexImage.load(it) {
                supportStartPostponedEnterTransition()
            }
        }
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
