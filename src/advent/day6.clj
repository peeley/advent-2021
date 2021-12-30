(ns advent.day6
  (:require [clojure.string :as str]))

(def input
  (as-> "resources/day6.txt" $
      (slurp $)
      (str/trim $)
      (str/split $ #",")
      (map #(Integer/parseInt %) $)))

(defn update-fish-lifetimes
  [lifetimes]
  (let [decreased-lifetimes (map dec lifetimes)
        num-to-spawn (->> decreased-lifetimes
                          (filter #(= -1 %))
                          count)
        spawned-lifetimes (concat decreased-lifetimes (repeat num-to-spawn 8))
        remove-spawned-lifetimes (remove #(= -1 %) spawned-lifetimes)
        respanwed-lifetimes (concat remove-spawned-lifetimes (repeat num-to-spawn 6))]
       respanwed-lifetimes))

(defn part1
  [input]
  (as-> input $
      (iterate update-fish-lifetimes $)
      (nth $ 80)
      (count $)))

(comment

  (part1 input);; => 358214

  ,)
