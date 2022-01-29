package br.com.jogosusados.features.add

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import br.com.jogosusados.features.games.list.GameItem
import br.com.jogosusados.features.games.select.GameSelectActivity

class SelectGameContract : ActivityResultContract<Long, GameItem?>() {
    override fun createIntent(context: Context, input: Long): Intent {
        return Intent(context, GameSelectActivity::class.java).apply {
            putExtra(GameSelectActivity.TAG_ID_PLATFORM, input)
        }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): GameItem? {
        val result = intent?.getParcelableExtra<GameItem>(GameSelectActivity.TAG_GAME_ITEM)
        return if (resultCode == Activity.RESULT_OK && result != null) result else null
    }
}
