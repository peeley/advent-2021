(ns advent.day6
  (:require [clojure.string :as str]))

(def input
  (as-> "resources/day6.txt" $
      (slurp $)
      (str/trim $)
      (str/split $ #",")
      (map #(Integer/parseInt %) $)
      (frequencies $)))

(defn update-fish-lifetimes
  [initial-lifetimes]
  (let [decreased-lifetimes (->> initial-lifetimes
                                 (map (fn [[k v]] [(dec' k) v]))
                                 (into {}))
        num-to-spawn (get decreased-lifetimes -1 0)]
    (-> decreased-lifetimes
        (assoc 8 num-to-spawn)
        (update 6 (fnil #(+ num-to-spawn %) 0))
        (dissoc -1))))

(defn calc-population-after-n-days
  [initial-fish n-days]
  (as-> initial-fish $
      (iterate update-fish-lifetimes $)
      (nth $ n-days)
      (reduce (fn [accum [_ v]] (+' accum v)) 0 $)))

(defn part1
  [input]
  (calc-population-after-n-days input 80))

(defn part2
  [input]
  (calc-population-after-n-days input 256))

(comment

  (part1 input);; => 358214

  (part2 input);; => 1622533344325

  ,)
