package yichunyen.ithome2020.challenge

import android.view.LayoutInflater.from
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view_profile.view.*
import yichunyen.ithome2020.challenge.data.Profile
import java.util.*

/**
 * Display the profile information adapter.
 */
class ProfileListAdapter(
    private val list: List<Profile>,
    private val listener: OnClickItemListener
) :
    RecyclerView.Adapter<ProfileListAdapter.ProfileViewHolder>() {
    private val backgroundColorList =
        listOf(
            R.color.profileBackgroundWhite,
            R.color.profileBackgroundBlue,
            R.color.profileBackgroundGreen,
            R.color.profileBackgroundYellow
        )

    interface OnClickItemListener {
        fun onClick(index: Int, profile: Profile)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val view = from(parent.context).inflate(R.layout.view_profile, parent, false)
        return ProfileViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.setupData(list[position], backgroundColorList[position % 4])
    }

    inner class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cardView = itemView.cardView
        private val tvName = itemView.tvName
        private val tvHeight = itemView.tvHeight
        private val tvWeight = itemView.tvWeight
        private val tvGender = itemView.tvGender
        private val tvBirthYear = itemView.tvBirthYear

        init {

        }

        fun setupData(profile: Profile, backgroundRes: Int) {
            cardView.setCardBackgroundColor(getResourceColorId(backgroundRes))
            tvName.text = profile.name
            tvHeight.text = String.format(
                getResourceStringId(R.string.profile_height),
                profile.height
            )
            tvWeight.text = String.format(
                getResourceStringId(R.string.profile_weight),
                profile.weight
            )
            tvGender.text = String.format(
                getResourceStringId(R.string.profile_gender),
                profile.genderType.name.toLowerCase(Locale.getDefault())
            )
            tvBirthYear.text = String.format(
                getResourceStringId(R.string.profile_birth_year),
                profile.birthYear
            )

            itemView.setOnClickListener {
                val index = adapterPosition
                if (index != RecyclerView.NO_POSITION) {
                    listener.onClick(index, profile)
                }
            }
        }

        private fun getResourceStringId(resId: Int): String {
            return try {
                itemView.context.getString(resId)
            } catch (exception: Exception) {
                ""
            }
        }

        private fun getResourceColorId(colorId: Int): Int {
            return try {
                ContextCompat.getColor(itemView.context, colorId)
            } catch (exception: Exception) {
                0
            }
        }
    }

}