package com.example.squeezymo.exampletask2.repo

import com.example.squeezymo.exampletask2.data.Product
import com.example.squeezymo.exampletask2.data.ProductDetails
import com.example.squeezymo.exampletask2.data.ProductID
import io.reactivex.Observable
import io.reactivex.Single
import java.util.*
import java.util.concurrent.TimeUnit

class ProductsRepo : IProductsRepo {

    private val batchSize = 3
    private val gen = Random(1540545148830)

    override fun getProducts(): Observable<List<Product>> =
        Observable
            .interval(3, TimeUnit.SECONDS)
            .map { iteration ->
                // iteration=0 -> ids=[0, 1, 2],
                // iteration=1 -> ids=[3, 4, 5], etc
                (0 until batchSize)
                    .map { inc ->
                        (iteration * batchSize) + inc
                    }
                    .map { productId ->
                        Product(
                            id = productId
                        )
                    }
            }

    override fun getDetailsByProductId(productId: ProductID): Single<ProductDetails> =
        Single
            .just(
                ProductDetails(
                    id = productId,
                    name = "Product $productId"
                )
            )
            .delay(gen.nextInt(4).toLong(), TimeUnit.SECONDS)

}