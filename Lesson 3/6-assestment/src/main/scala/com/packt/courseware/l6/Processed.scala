package com.packt.courseware.l6

import com.packt.courseware.l6.modes.ChatbotMode


case class Processed(answer:String, nextMode: ChatbotMode, endOfDialog:Boolean = false, relevance: Double = 1.0)
