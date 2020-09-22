package yichunyen.ithome2020.challenge

import android.view.LayoutInflater.from
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view_profile.view.*
import yichunyen.ithome2020.challenge.data.Profile
import java.util.*

/**
 * Display the profile information adapter.
 */
class ProfileListAdapter(private val list: List<Profile>) :
    RecyclerView.Adapter<ProfileListAdapter.ProfileViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val view = from(parent.context).inflate(R.layout.view_profile, parent, false)
        return ProfileViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.setupData(list[position])
    }

    inner class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName = itemView.tvName
        private val tvHeight = itemView.tvHeight
        private val tvWeight = itemView.tvWeight
        private val tvGender = itemView.tvGender
        private val tvBirthYear = itemView.tvBirthYear

        fun setupData(profile: Profile) {
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
        }

        private fun getResourceStringId(resId: Int): String {
            return try {
                itemView.context.getString(resId)
            } catch (exception: Exception) {
                ""
            }
        }
    }

}