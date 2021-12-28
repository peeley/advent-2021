(ns advent.day5
  (:require [clojure.string :as str]))


(defn parse-coord-string
  [coord-string]
  (as-> coord-string $
    (str/split $ #",")
    (map #(Integer/parseInt %) $)
    (vec $)))

(defn parse-coord-pairs
  [[first-pair second-pair]]
  [(parse-coord-string first-pair)
   (parse-coord-string second-pair)])

(def input
  (->> "resources/day5.txt"
       slurp
       str/split-lines
       (map #(str/split % #" -> "))
       (map parse-coord-pairs)))

(defn line-is-straight?
  [[[x1 y1] [x2 y2]]]
  (or (= x1 x2)
      (= y1 y2)))

(defn get-points-on-line
  [[[x1 y1] [x2 y2]]]
  (let [[lowx highx] (sort [x1 x2])
        [lowy highy] (sort [y1 y2])]
    (set (for [x (range lowx (inc highx))
               y (range lowy (inc highy))]
           [x y]))))

(defn point-on-line?
  [{:keys [xs ys]} [x y]]
  (or (contains? xs x)
      (contains? ys y)))

(defn lines-on-point
  [lines-points point]
  (count (filter #(point-on-line? % point) lines-points)))

(defn part1
  [coord-pairs]
  (let [straight-lines (filter line-is-straight? coord-pairs)
        straight-line-points (mapcat get-points-on-line straight-lines)]
    (->> straight-line-points
         (reduce #(update %1 %2 (fnil inc 0)) {})
         (filter (fn [[k v]] (> v 1)))
         count)))

(comment

  (part1 input);; => 5576

  ,)
