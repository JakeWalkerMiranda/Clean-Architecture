package br.com.jwm.clean.data.mappers

import br.com.jwm.clean.data.models.ContentModel
import br.com.jwm.clean.data.models.TrailModel
import br.com.jwm.clean.domain.entities.Content
import br.com.jwm.clean.domain.entities.Trail

fun TrailModel.toEntity() = Trail(
    id = id,
    title = title,
    description = description,
    trailCode = trailCode,
    iconUrl = iconUrl
)

fun ContentModel.toEntity() = Content(
    id = id,
    title = title,
    description = description,
    contentType = contentType,
    contentUrl = contentUrl,
    imageUrl = imageUrl
)