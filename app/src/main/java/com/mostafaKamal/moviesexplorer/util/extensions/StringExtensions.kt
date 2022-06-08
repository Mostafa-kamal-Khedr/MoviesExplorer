package com.mostafaKamal.moviesexplorer.util.extensions


fun String.capitalizeWords(): String = split(" ").joinToString(" ") { it.toLowerCase().capitalize() }
