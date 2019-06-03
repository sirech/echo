package com.hceris.echo

import com.hceris.echo.fishes.Fish
import com.hceris.echo.fishes.FishList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

private const val PATH = "/fishes"

@RestController
@RequestMapping(PATH)
class FishController {
    @Autowired
    lateinit var fishList: FishList

    @GetMapping("{fish}")
    fun fishes(@PathVariable("fish") fish: String): ResponseEntity<Fish> {
        return fishList.findFish(fish)?.run {
            ResponseEntity.ok(this)
        } ?: ResponseEntity.notFound().build()
    }
}

fun FishList.findFish(fish: String) = firstOrNull { fish.toLowerCase() in it.combined() }
fun Fish.combined() = values.joinToString(separator = "|")


