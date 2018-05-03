package com.packt.courseware.l7

import com.packt.courseware.l7.modes.ChatbotMode

case class Processed(answer: String, nextMode: ChatbotMode, endOfDialog: Boolean = false, relevance: Double = 1.0)
