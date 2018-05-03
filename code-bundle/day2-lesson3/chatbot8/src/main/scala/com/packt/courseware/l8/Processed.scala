package com.packt.courseware.l8

import com.packt.courseware.l8.modes.ChatbotMode


case class Processed(answer:String, nextMode: ChatbotMode, endOfDialog:Boolean = false, relevance: Double = 1.0)
