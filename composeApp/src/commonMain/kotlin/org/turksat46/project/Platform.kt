package org.turksat46.project

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform