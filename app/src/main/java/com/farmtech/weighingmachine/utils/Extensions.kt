package com.farmtech.weighingmachine.utils

import com.apollographql.apollo.api.Optional

fun <T> T.optional(): Optional<T?> = Optional.Present(this)