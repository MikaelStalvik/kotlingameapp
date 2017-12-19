package com.imploded.kotlingameapp.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.imploded.kotlingameapp.R
import com.imploded.kotlingameapp.utils.load
import com.imploded.kotlingameapp.viewmodels.DetailViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailActivity : AppCompatActivity() {

    private val viewModel = DetailViewModel()

    companion object {
        val ID = "DetailActivity:id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val id = intent.getStringExtra(DetailActivity.ID)
        doAsync {
            viewModel.getGameFromServer(id)
            uiThread {
                with(viewModel.game) {
                    title = name
                    textViewDetailTitle.text = name
                    imageViewLarge.load(picture)
                    textViewPublisher.text = getString(R.string.publisher) + ": " + publisher
                    textViewReleaseYear.text = getString(R.string.releaseYear) + ": " + releaseYear
                    textViewPlatform.text = getString(R.string.platforms) + ": " + platforms.joinToString()
                    textViewDescription.text = description
                }
            }
        }
    }
}
