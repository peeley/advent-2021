(ns advent.day1
  (:require [clojure.string :as str]))

(def input
  (->> "resources/day1.txt"
       slurp
       str/split-lines
       (map #(Integer/parseInt %))))

(defn part1
  [input]
  (->> input
      (partition 2 1)
      (filter #(< (first %) (second %)))
      count))

(defn part2
  [input]
  (->> input
       (partition 3 1)
       (map #(apply + %))
       part1))

(comment

  (part1 input);; => 1390

  (part2 input)

  ,)
