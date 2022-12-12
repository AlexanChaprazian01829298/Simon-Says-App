import android.graphics.Color
import androidx.lifecycle.ViewModel

class SimonSaysViewModel : ViewModel() {
    val sequence = mutableListOf<Int>()
    var currentPosition = 0
    var isStarted = false
    var isUserDone = false
    var isGameOver = false
    var isSequenceDisplayed = false
    var buttonColor = Color.argb(255, 160, 32, 240)
    val buttonOff = Color.argb(255,255,255,255)
    var roundNum = 1
    var score = 0

    private fun nextButton() : Int {
        return (1..4).random()
    }

    fun onPressedButton(buttonNum: Int) {
        if(buttonNum == sequence[currentPosition]) {
            currentPosition++
            score++

            if(currentPosition == sequence.size) {
                roundNum++
                isUserDone = true
                currentPosition = 0
                isSequenceDisplayed = false
                sequence.add(nextButton())
            }
            else {
                isUserDone = false
            }
        }
        else {
            sequence.clear()
            currentPosition = 0
            isGameOver = true
        }
    }

    fun start() {
        isStarted = true
        sequence.add(nextButton())
    }
}