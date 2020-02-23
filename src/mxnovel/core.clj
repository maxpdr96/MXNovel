(ns mxnovel.core
  (:require [net.cgrand.enlive-html :as html]))

(def urlNovel "http://novelmania.com.br/chinesa/ri-indice/ri-capitulo-60/")

(defn fetch-url [url]
  (html/html-resource (java.net.URL. url)))

(def getHTML (fetch-url urlNovel))

(def getBodyTitle (html/select getHTML [:div.inside-article :h1]))
(def getTitle (map (comp :content) getBodyTitle))

(def getBodyText (html/select getHTML [:div.entry-content :span]))
(def getText (map (comp :content) getBodyText))

(println getTitle)
(println getText)