package com.packt.courseware.l3

import com.packt.courseware.l3.modes.{Or, Otherwise}


trait ChatbotMode {

    def process(message: String, effects: EffectsProvider): LineStepResult

    def or(other: ChatbotMode): ChatbotMode = Or(this,other)

    def otherwise(other: ChatbotMode): ChatbotMode = Otherwise(this,other)
}


