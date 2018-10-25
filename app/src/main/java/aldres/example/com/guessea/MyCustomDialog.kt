package aldres.example.com.guessea

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.android.synthetic.main.popup_dialog.view.*
import java.util.*
import kotlin.collections.ArrayList

class MyCustomDialog : DialogFragment() {


    lateinit var callback: IAnswerPicked
    var rightAnswer : String = ""
    var answers = arrayListOf<String>()
    private lateinit var firstButton: Button
    private lateinit var secondButton: Button
    private lateinit var thirdButton: Button
    private lateinit var fourthButton: Button

    override fun setArguments(args: Bundle?) {
        super.setArguments(args)
        this.answers = args!!.getStringArrayList("A")
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.popup_dialog, container, false)
        callback = activity as IAnswerPicked
        this.answers.add(0, "A")
        this.answers.add(1, "A")
        this.answers.add(2, "A")
        this.answers.add(3, "A")

        firstButton = v.findViewById(R.id.first_answer)
        firstButton.text = answers[0]
        secondButton = v.findViewById(R.id.second_answer)
        secondButton.text = answers[1]
        thirdButton = v.findViewById(R.id.third_answer)
        thirdButton.text = answers[2]
        fourthButton = v.findViewById(R.id.fourth_answer)
        fourthButton.text = answers[3]
        return v
    }

}