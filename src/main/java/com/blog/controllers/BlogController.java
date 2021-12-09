package com.blog.controllers;

import com.blog.dao.ArticleRepository;
import com.blog.models.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class BlogController {

    private ArticleRepository articleRepository;

    @Autowired
    public BlogController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/blogs")
    public String blogs(Model model){
        Iterable<Article> articles = articleRepository.findAll();
        model.addAttribute("articles", articles);
        return "blogs";
    }

    @GetMapping("/blogs/new")
    public String newArticle(Model model){
        return "new";
    }

    @PostMapping("/blogs/new")
    public String addNewArticle(@RequestParam String name, @RequestParam String anons,
                                @RequestParam String fullText, Model model){
        Article article = new Article(name, anons, fullText);
        articleRepository.save(article);
        return "redirect:/blogs";
    }

    @GetMapping("/blogs/{id}")
    public String chekFullText(@PathVariable("id") long id, Model model){
        if (!articleRepository.existsById(id))
            return "redirect:/blogs";
        Optional<Article> article = articleRepository.findById(id);
        ArrayList<Article> list = new ArrayList<>();
        article.ifPresent(list::add);
        model.addAttribute("article", list);
        return "read";
    }

    @GetMapping("/blogs/{id}/edit")
    public String editArticle(@PathVariable("id") long id, Model model){
        if (!articleRepository.existsById(id))
            return "redirect:/blogs";
        Optional<Article> article = articleRepository.findById(id);
        ArrayList<Article> list = new ArrayList<>();
        article.ifPresent(list::add);
        model.addAttribute("article", list);
        return "edit";
    }

    @PostMapping("/blogs/{id}/edit")
    public String doEditArticle(@PathVariable("id") long id, @RequestParam String name, @RequestParam String anons,
                                @RequestParam String fullText, Model model){
        Article article = articleRepository.findById(id).orElseThrow();
        article.setName(name);
        article.setAnons(anons);
        article.setFullText(fullText);
        articleRepository.save(article);
        return "redirect:/blogs";
    }

    @PostMapping("/blogs/{id}/delete")
    public String deleteArticle(@PathVariable("id") long id, Model model){
        Article article = articleRepository.findById(id).orElseThrow();
        articleRepository.delete(article);
        return "redirect:/blogs";
    }

}
