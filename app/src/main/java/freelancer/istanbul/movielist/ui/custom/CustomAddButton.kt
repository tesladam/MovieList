package freelancer.istanbul.movielist.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import freelancer.istanbul.movielist.R
import freelancer.istanbul.movielist.databinding.CustomAddButtonBinding

class CustomAddButton(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    init {
        val binding = CustomAddButtonBinding.inflate(LayoutInflater.from(context), this, true)

        attrs.let {
            val attributes = context.obtainStyledAttributes(it, R.styleable.CustomAddButton, 0, 0)
            val text = attributes.getString(R.styleable.CustomAddButton_text)
            binding.customAddButton.text = text
            attributes.recycle()
        }
    }
}