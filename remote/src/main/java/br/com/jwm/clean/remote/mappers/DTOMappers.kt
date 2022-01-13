package br.com.jwm.clean.remote.mappers

import br.com.jwm.clean.data.models.ContentModel
import br.com.jwm.clean.data.models.TrailModel
import br.com.jwm.clean.remote.response.ContentDTO
import br.com.jwm.clean.remote.response.TrailDTO

fun TrailDTO.toModel() = TrailModel(
    id = id,
    title = title,
    description = description,
    trailCode = trailCode,
    iconUrl = iconUrl
)

fun ContentDTO.toModel() = ContentModel(
    id = id,
    title = title,
    description = description,
    contentType = contentType,
    contentUrl = contentUrl,
    imageUrl = imageUrl
)