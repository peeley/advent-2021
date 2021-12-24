(ns advent.day1
  (:require [clojure.string :as str]))

(def input
  (->> "resources/day1.txt"
       slurp
       str/split-lines
       (map #(Integer/parseInt %))))

(defn day1
  [input]
  (->> input
      (partition 2 1)
      (filter #(< (first %) (second %)))
      count))

(comment

  (day1 input)

  ,)
