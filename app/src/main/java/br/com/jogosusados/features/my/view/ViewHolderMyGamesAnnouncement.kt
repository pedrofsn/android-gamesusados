package br.com.jogosusados.features.my.view

import br.com.jogosusados.databinding.ViewholderMyGameAnnouncementBinding
import br.com.jogosusados.features.add.data.GameAnnouncement
import br.com.redcode.base.mvvm.databinding.view.BaseViewHolderMVVM
import br.com.redcode.easyglide.library.load
import br.com.redcode.easyglide.library.loadCompleteUrlImage
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.GrayscaleTransformation

class ViewHolderMyGamesAnnouncement(binding: ViewholderMyGameAnnouncementBinding) :
    BaseViewHolderMVVM<GameAnnouncement, ViewholderMyGameAnnouncementBinding>(binding) {

    override fun bind(data: GameAnnouncement) {
        binding.data = data
        loadImage(data)
    }

    private fun loadImage(data: GameAnnouncement) {
        if (data.enabled) {
            binding.imageView.load(data.game.image)
        } else {
            val requestOptions = RequestOptions.bitmapTransform(GrayscaleTransformation())
            binding.imageView.loadCompleteUrlImage(data.game.image, requestOptions)
        }
    }
}
