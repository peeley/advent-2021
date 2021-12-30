(ns advent.day7
  (:require [clojure.string :as str]))

(def input
  (as-> "resources/day7.txt" $
       (slurp $)
       (str/trim $)
       (str/split $ #",")
       (map #(Integer/parseInt %) $)))

(defn linear-fuel-cost
  [current-x destination-x]
  (Math/abs (- current-x destination-x)))

(defn total-fuel-to-move-to-x
  [fuel-cost-fn fleet-xs x]
  (->> fleet-xs
       (map #(fuel-cost-fn % x))
       (apply +)))

(defn least-fuel-needed-to-align
  [xs fuel-cost-fn]
  (let [max-x (apply max xs)
        min-x (apply min xs)
        possible-xs (range min-x (inc max-x))]
    (->> possible-xs
         (map #(total-fuel-to-move-to-x fuel-cost-fn input %))
         (apply min))))

(defn part1
  [input]
  (least-fuel-needed-to-align input linear-fuel-cost))

(defn exponential-fuel-cost
  [current-x destination-x]
  (let [x-diff (Math/abs (- current-x destination-x))]
    (/ (* x-diff (+ x-diff 1))
       2)))

(defn part2
  [input]
  (least-fuel-needed-to-align input exponential-fuel-cost))

(comment

  (part1 input);; => 336701

  (part2 input);; => 95167302

  ,)
