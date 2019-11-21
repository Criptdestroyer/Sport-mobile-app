package com.aemiralfath.league

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class DetailLeagueActivity : AppCompatActivity() {
    private lateinit var dataItem: Item
    private lateinit var leagueNameTextView: TextView
    private lateinit var leagueDescriptionTextView: TextView
    private lateinit var leagueImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataItem = intent.getParcelableExtra("EXTRA_ITEM")


        scrollView {
            lparams(width = matchParent, height = wrapContent)

            verticalLayout {
                lparams(width = matchParent, height = wrapContent)
                leagueImageView = imageView {
                    id = R.id.image_detail_league
                }.lparams {
                    height = dip(250)
                    width = matchParent
                    bottomMargin = dip(24)
                }


                leagueNameTextView = textView {
                    id = R.id.name_detail_league
                    textSize = 25f
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                }.lparams {
                    height = wrapContent
                    width = matchParent
                    padding = dip(16)
                    bottom = R.id.image_detail_league
                }

                leagueDescriptionTextView = textView {
                    id = R.id.description_league
                    textSize = 15f
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                }.lparams {
                    height = wrapContent
                    width = matchParent
                    padding = dip(16)
                    bottom = R.id.name_detail_league
                }
            }

        }

        leagueNameTextView.text = dataItem.name
        leagueDescriptionTextView.text = dataItem.description
        dataItem.image?.let { Picasso.get().load(it).fit().into(leagueImageView) }

    }
}
