(ns advent.day9
  (:require [clojure.string :as str]))

(def input
  (->> "resources/day9.txt"
       slurp
       str/split-lines
       (mapv #(mapv (fn [s] (Integer/parseInt s)) (str/split % #"")))))

(defn get-surrounding-heights
  [[x y] heights]
  (->> [[x (- y 1)]
        [x (+ y 1)]
        [(- x 1) y]
        [(+ x 1) y]]
       (map #(get-in heights %))
       (filter identity)))

(defn find-low-points
  [[& rows :as map]]
  (for [x (range 0 (count (first rows)))
        y (range 0 (count rows))
        :let [this-height (get-in map [x y])
              surrounding-heights (get-surrounding-heights [x y] map)
              lower-heights (filter #(< this-height %) surrounding-heights)]
        :when (= (count surrounding-heights) (count lower-heights))]
    this-height))

(defn get-risk-of-low-points
  [low-points]
  (map inc low-points))


(defn part1
  [input]
  (->> input
       find-low-points
       get-risk-of-low-points
       (apply +)))

(comment

  (part1 input);; => 425

  ,)
