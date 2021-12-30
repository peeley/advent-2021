(ns advent.day7
  (:require [clojure.string :as str]))

(def input
  (as-> "resources/day7.txt" $
       (slurp $)
       (str/trim $)
       (str/split $ #",")
       (map #(Integer/parseInt %) $)))

(defn total-fuel-to-move-to-x
  [fleet-xs x]
  (->> fleet-xs
       (map #(Math/abs (- x %)))
       (apply +)))

(defn check-if-x-is-less-fuel
  [current-min-fuel x fleet-xs]
  (let [needed-fuel (total-fuel-to-move-to-x fleet-xs x)]
    (if (< needed-fuel current-min-fuel)
      needed-fuel
      current-min-fuel)))

(defn part1
  [input]
  (let [max-x (apply max input)
        min-x (apply min input)
        possible-xs (range min-x (inc max-x))]
    (->> possible-xs
         (map #(total-fuel-to-move-to-x input %))
         (apply min))))

(comment

  (part1 input);; => 336701

  ,)
