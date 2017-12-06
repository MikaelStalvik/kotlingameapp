package com.imploded.kotlingameapp.ui

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.imploded.kotlingameapp.R
import kotlinx.android.synthetic.main.activity_sorting.*

class SortingActivity : AppCompatActivity() {

    companion object {
        val SortingNameId = "SortingNameId"
        val SortingPublisherId = "SortingPublisherId"
        val SortingReleaseYearId = "SortingReleaseYearId"
        val SortingId = "SortingId"
    }

    fun closeView() {
        val mainIntent = Intent(this, MainActivity.javaClass)
        when {
            sortingNameRadioButton.isChecked -> mainIntent.putExtra(SortingId, SortingNameId)
            sortingPublisherRadioButton2.isChecked -> mainIntent.putExtra(SortingId, SortingPublisherId)
            sortingPublisherRadioButton2.isChecked -> mainIntent.putExtra(SortingId, SortingReleaseYearId)
        }
        setResult(Activity.RESULT_OK, mainIntent)
        finish()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sorting)

        applySortingButton.setOnClickListener({
            closeView()
        })
    }
}
