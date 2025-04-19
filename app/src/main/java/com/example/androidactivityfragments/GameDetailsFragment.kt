package com.example.androidactivityfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GameDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameDetailsFragment : Fragment() {
    private var title: String? = null
    private var year: Int? = null
    private var rating: Int? = null
    private var poster: String? = null
    private var description: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString("TITLE")
            year = it.getInt("YEAR")
            rating = it.getInt("RATING")
            poster = it.getString("POSTER")
            description = it.getString("DESCR")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val titleView: TextView = view.findViewById(R.id.id_game_title_frag)
        val imageView: ImageView = view.findViewById(R.id.id_game_image_frag)
        val yearView: TextView = view.findViewById(R.id.id_game_year_frag)
        val ratingView: TextView = view.findViewById(R.id.id_game_rating_frag)
        val descrView: TextView = view.findViewById(R.id.id_game_descr_frag)


        titleView.text = title ?: "NO_DATA"
        yearView.text = this.getString(R.string.gameReleaseYear, year.toString())
        ratingView.text = this.getString(R.string.gameRating, rating.toString())
        descrView.text = description ?: "NO_DATA"
        val imgId = resources.getIdentifier(poster, "drawable", requireActivity().packageName)
        if (imgId != 0) {
            imageView.setImageResource(imgId)
        }
        else {
            imageView.setImageResource(R.drawable.ic_launcher_background)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment GameDetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GameDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}