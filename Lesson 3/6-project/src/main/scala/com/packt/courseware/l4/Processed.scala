package com.packt.courseware.l4

import com.packt.courseware.l4.modes.ChatbotMode


case class Processed(answer:String, nextMode: ChatbotMode, endOfDialog:Boolean)
