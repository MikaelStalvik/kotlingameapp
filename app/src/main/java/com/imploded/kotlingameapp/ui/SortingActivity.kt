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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sorting)

        val activeSorting = intent.getStringExtra(SortingId)
        when(activeSorting) {
            SortingNameId -> sortingNameRadioButton.isChecked = true
            SortingPublisherId -> sortingPublisherRadioButton.isChecked = true
            SortingReleaseYearId -> sortingReleaseYearRadioButton.isChecked = true
        }
        applySortingButton.setOnClickListener({
            closeView()
        })
    }

    private fun closeView() {
        val result = Intent(this, MainActivity::class.java)
        when {
            sortingNameRadioButton.isChecked -> result.putExtra(SortingId, SortingNameId)
            sortingPublisherRadioButton.isChecked -> result.putExtra(SortingId, SortingPublisherId)
            sortingReleaseYearRadioButton.isChecked -> result.putExtra(SortingId, SortingReleaseYearId)
        }
        setResult(Activity.RESULT_OK, result)
        finish()
    }

}
