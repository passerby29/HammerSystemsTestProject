package dev.passerby.data.mappers

import dev.passerby.data.models.db.PizzaDbModel
import dev.passerby.data.models.dto.PizzaDtoItem
import dev.passerby.domain.models.PizzaModel

class PizzaMapper {

    fun mapDtoToDbModel(dto: PizzaDtoItem) = PizzaDbModel(
        category = dto.category,
        id = dto.id,
        imageUrl = dto.imageUrl,
        ingredients = dto.ingredients,
        price = dto.price,
        rating = dto.rating,
        sizes = dto.sizes,
        title = dto.title,
    )

    fun mapDbModelToEntity(dbModel: PizzaDbModel) = PizzaModel(
        category = dbModel.category,
        id = dbModel.id,
        imageUrl = dbModel.imageUrl,
        ingredients = dbModel.ingredients,
        price = dbModel.price,
        rating = dbModel.rating,
        sizes = dbModel.sizes,
        title = dbModel.title
    )
}