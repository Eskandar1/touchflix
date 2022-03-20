package br.com.touchtec.touchflix.touchflix.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class IndexController {
    @GetMapping
    String index(){
        return "Bem-vindo ao Touchflix, a API da Touch de comentários de filmes e séries"
    }
}
