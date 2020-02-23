(ns mxnovel.core
  (:require [net.cgrand.enlive-html :as html]
            [org.httpkit.client :as http]
            [clojure.java.io :as IO]))

(defn get-dom
  []
  (html/html-snippet
    (:body @(http/get "http://novelmania.com.br/chinesa/ri-indice/ri-capitulo-60/" {:insecure? true}))))

(defn extract-text
  [dom]
  (map
    (comp first :content) (html/select dom [:div.entry-content :span])))

(defn write-file [text]
  (with-open [w (IO/writer  "D:/Programacao/Texts/caps.txt" :append true)]
    (.write w (pr-str text))))

(defn -main
  []
  (let [titles (extract-text (get-dom))]
    (write-file titles)
    (println "Writing Success!")))

(-main)