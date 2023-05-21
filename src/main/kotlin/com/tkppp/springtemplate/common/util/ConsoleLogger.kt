package com.tkppp.springtemplate.common.util

import org.slf4j.LoggerFactory

inline fun <reified T> T.getLogger() = LoggerFactory.getLogger(T::class.java)