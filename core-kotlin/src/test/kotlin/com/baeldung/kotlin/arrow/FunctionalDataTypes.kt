package com.baeldung.kotlin.arrow

import arrow.core.*
import org.junit.Assert
import org.junit.Test

class FunctionalDataTypes {

    @Test
    fun whenIdCreated_thanValueIsPresent(){
        val const = Id("foo")
        val just = Id.just("foo");

        Assert.assertEquals("foo", const.extract())
        Assert.assertEquals(just, const)
    }

    fun length(s : String) : Int = s.length

    fun isBigEnough(i : Int) : Boolean = i > 10

    @Test
    fun whenIdCreated_thanMapIsAssociative(){
        val foo = Id("foo")

        val map1 = foo.map(::length)
                .map(::isBigEnough)
        val map2 = foo.map { s -> isBigEnough(length(s)) }

        Assert.assertEquals(map1, map2)
    }

    fun lengthId(s : String) : Id<Int> = Id.just(length(s))

    fun isBigEnoughId(i : Int) : Id<Boolean> = Id.just(isBigEnough(i))

    @Test
    fun whenIdCreated_thanFlatMapIsAssociative(){
        val bar = Id("bar")

        val flatMap = bar.flatMap(::lengthId)
            .flatMap(::isBigEnoughId)
        val flatMap1 = bar.flatMap { s -> lengthId(s).flatMap(::isBigEnoughId) }

        Assert.assertEquals(flatMap, flatMap1)
    }

    @Test
    fun whenOptionCreated_thanValueIsPresent(){
        val factory = Option.just(42)
        val constructor = Option(42)
        val emptyOptional = Option.empty<Integer>()
        val fromNullable = Option.fromNullable(null)

        Assert.assertEquals(42, factory.getOrElse { -1 })
        Assert.assertEquals(factory, constructor)
        Assert.assertEquals(emptyOptional, fromNullable)
    }

    @Test
    fun whenOptionCreated_thanConstructorDifferFromStaticFactory(){
        val constructor = Option(null)
        val fromNullable = Option.fromNullable(null)

        Assert.assertNotEquals(constructor, fromNullable)
    }

    fun wrapper(x : Integer?) : Option<Int> = if (x == null) Option.just(-1) else Option.just(x.toInt())

    @Test
    fun whenOptionFromNullableCreated_thanItBreaksLeftIdentity(){
        val optionFromNull = Option.fromNullable(null)

        Assert.assertNotEquals(optionFromNull.flatMap(::wrapper), wrapper(null))
    }

    @Test
    fun whenEitherCreated_thanOneValueIsPresent(){
        val either1 : Either<String,Int> = Either.right(42)
        val either2 : Either<String,Int> = Either.left("foo")

        Assert.assertTrue(either1.isRight())
        Assert.assertTrue(either2.isLeft())
        Assert.assertEquals(42, either1.getOrElse { -1 })
        Assert.assertEquals(-1, either2.getOrElse { -1 })

        Assert.assertEquals(0, either1.map { it % 2 }.getOrElse { -1 })
        Assert.assertEquals(-1, either2.map { it % 2 }.getOrElse { -1 })
        Assert.assertTrue(either1.flatMap { Either.Right(it % 2) }.isRight())
        Assert.assertTrue(either2.flatMap { Either.Right(it % 2) }.isLeft())
    }

}