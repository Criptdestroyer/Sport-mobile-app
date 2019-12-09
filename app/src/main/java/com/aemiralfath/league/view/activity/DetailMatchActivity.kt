package com.aemiralfath.league.view.activity

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteConstraintException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.aemiralfath.league.R
import com.aemiralfath.league.invisible
import com.aemiralfath.league.model.response.MatchResponse
import com.aemiralfath.league.model.api.ApiRepository
import com.aemiralfath.league.model.db.Favorite
import com.aemiralfath.league.model.db.database
import com.aemiralfath.league.model.response.TeamResponse
import com.aemiralfath.league.presenter.MatchPresenter
import com.aemiralfath.league.view.view.DetailMatchView
import com.aemiralfath.league.visible
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import java.text.SimpleDateFormat
import java.util.*

class DetailMatchActivity : AppCompatActivity(),
    DetailMatchView {
    private lateinit var presenter: MatchPresenter
    private lateinit var imageViewHome: ImageView
    private lateinit var imageViewAway: ImageView
    private lateinit var textViewScore: TextView
    private lateinit var textViewAway: TextView
    private lateinit var textViewHome: TextView
    private lateinit var textViewDate: TextView
    private lateinit var textViewTime: TextView
    private lateinit var textViewAwayTeam: TextView
    private lateinit var textViewHomeTeam: TextView
    private lateinit var textViewAwayScore: TextView
    private lateinit var textViewHomeScore: TextView
    private lateinit var textViewAwayFormation: TextView
    private lateinit var textViewHomeFormation: TextView
    private lateinit var textViewAwayGoal: TextView
    private lateinit var textViewHomeGoal: TextView
    private lateinit var textViewAwayYellow: TextView
    private lateinit var textViewHomeYellow: TextView
    private lateinit var textViewAwayRed: TextView
    private lateinit var textViewHomeRed: TextView
    private lateinit var textViewAwayShots: TextView
    private lateinit var textViewHomeShots: TextView
    private lateinit var textViewStadium: TextView
    private lateinit var progressBar: ProgressBar

    private lateinit var item: List<Any?>
    private var idMatch: String? = null
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)

        idMatch = intent.getStringExtra("EXTRA_ID")

        supportActionBar?.title = intent.getStringExtra("EXTRA_NAME")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        imageViewHome = findViewById(R.id.imageViewHome)
        imageViewAway = findViewById(R.id.imageViewAway)
        textViewScore = findViewById(R.id.textViewScore)
        textViewHome = findViewById(R.id.textViewHome)
        textViewAway = findViewById(R.id.textViewAway)
        textViewDate = findViewById(R.id.tr_date)
        textViewTime = findViewById(R.id.tr_time)
        textViewHomeTeam = findViewById(R.id.tr_homeTeam)
        textViewAwayTeam = findViewById(R.id.tr_awayTeam)
        textViewHomeScore = findViewById(R.id.tr_homeScore)
        textViewAwayScore = findViewById(R.id.tr_awayScore)
        textViewHomeFormation = findViewById(R.id.tr_homeFormation)
        textViewAwayFormation = findViewById(R.id.tr_awayFormation)
        textViewHomeGoal = findViewById(R.id.tr_homeGoal)
        textViewAwayGoal = findViewById(R.id.tr_awayGoal)
        textViewHomeYellow = findViewById(R.id.tr_homeYellow)
        textViewAwayYellow = findViewById(R.id.tr_awayYellow)
        textViewHomeRed = findViewById(R.id.tr_homeRed)
        textViewAwayRed = findViewById(R.id.tr_awayRed)
        textViewHomeShots = findViewById(R.id.tr_homeShot)
        textViewAwayShots = findViewById(R.id.tr_awayShot)
        textViewStadium = findViewById(R.id.tr_stadium)
        progressBar = findViewById(R.id.progressBarMatch)

        favoriteState()
        val request = ApiRepository()
        val gson = Gson()
        presenter = MatchPresenter(this, request, gson)
        presenter.getMatchDetail(idMatch)
    }

    @SuppressLint("SimpleDateFormat")
    fun toGMTFormat(date: String?, time: String?): String? {
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        formatter.timeZone = TimeZone.getTimeZone("WIB")

        val dateTime: String = when {
            date == null -> "0000-00-00 $time"
            time == null -> "$date 00:00:00"
            else -> "$date $time"
        }

        return "${formatter.parse(dateTime)}"
    }

    @SuppressLint("SetTextI18n")
    override fun showDetailMatch(
        dataMatch: MatchResponse,
        dataTeamHome: TeamResponse,
        dataTeamAway: TeamResponse
    ) {
        textViewScore.text =
            "${dataMatch.events[0].scoreHome ?: "-"} : ${dataMatch.events[0].scoreAway ?: "-"}"

        textViewHome.text = dataTeamHome.teams[0].teamName
        textViewAway.text = dataTeamAway.teams[0].teamName
        textViewHomeTeam.text = dataTeamHome.teams[0].teamName
        textViewAwayTeam.text = dataTeamAway.teams[0].teamName
        textViewHomeScore.text = dataMatch.events[0].scoreHome?.toString() ?: "-"
        textViewAwayScore.text = dataMatch.events[0].scoreAway?.toString() ?: "-"
        textViewHomeFormation.text = dataMatch.events[0].homeFormation ?: "-"
        textViewAwayFormation.text = dataMatch.events[0].awayFormation ?: "-"
        textViewHomeGoal.text = dataMatch.events[0].homeGoal ?: "-"
        textViewAwayGoal.text = dataMatch.events[0].awayGoal ?: "-"
        textViewHomeYellow.text = dataMatch.events[0].homeYellow ?: "-"
        textViewAwayYellow.text = dataMatch.events[0].awayYellow ?: "-"
        textViewHomeRed.text = dataMatch.events[0].homeRed ?: "-"
        textViewAwayRed.text = dataMatch.events[0].awayRed ?: "-"
        textViewHomeShots.text = dataMatch.events[0].homeShots ?: "-"
        textViewAwayShots.text = dataMatch.events[0].awayShots ?: "-"

        textViewDate.text = dataMatch.events[0].eventDate
        textViewTime.text =
            toGMTFormat(dataMatch.events[0].eventDate, dataMatch.events[0].eventTime)
        textViewStadium.text = dataTeamHome.teams[0].strTeamStadium

        dataTeamHome.teams[0].strTeamBadge?.let {
            Picasso.get().load(it).fit().into(imageViewHome)
        }
        dataTeamAway.teams[0].strTeamBadge?.let {
            Picasso.get().load(it).fit().into(imageViewAway)
        }

        item = listOf(
            dataMatch.events[0].eventId,
            dataMatch.events[0].eventName,
            toGMTFormat(dataMatch.events[0].eventDate, dataMatch.events[0].eventTime)
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }

    private fun favoriteState() {
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                .whereArgs(
                    "(EVENT_ID = {id})",
                    "id" to idMatch!!
                )
            val favorite = result.parseList(classParser<Favorite>())
            if (favorite.isNotEmpty()) isFavorite = true
        }
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(
                    Favorite.TABLE_FAVORITE,
                    Favorite.EVENT_ID to item[0],
                    Favorite.EVENT_NAME to item[1],
                    Favorite.EVENT_DATE to item[2]
                )
            }
            progressBar.snackbar("Added to Favorite")
        } catch (e: SQLiteConstraintException) {
            progressBar.snackbar(e.localizedMessage!!).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE, "(EVENT_ID = {id})", "id" to idMatch!!)
            }
            progressBar.snackbar("Remove from Favorite")
        } catch (e: SQLiteConstraintException) {
            progressBar.snackbar(e.localizedMessage!!)
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }
}

