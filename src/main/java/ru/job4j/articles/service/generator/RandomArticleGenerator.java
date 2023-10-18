package ru.job4j.articles.service.generator;

import ru.job4j.articles.model.Article;
import ru.job4j.articles.model.Word;

import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RandomArticleGenerator implements ArticleGenerator {
    @Override
    public Article generate(List<Word> words) {
        var wordsCopy = new WeakReference<>(words);
        Collections.shuffle(wordsCopy.get());
        var content = new WeakReference<>(wordsCopy.get().stream()
                .map(w -> w.getValue())
                .collect(Collectors.joining(" ")));
        words.clear();
        wordsCopy.clear();
        return new Article(content.get());
    }
}
