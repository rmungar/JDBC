package org.example.OUTPUT

class Console: IOutput {

    override fun showMessage(message: String, lineBreak: Boolean) {
        if (lineBreak) println(message)
        else print(message)
    }

}