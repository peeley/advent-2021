(ns advent.day8
  (:require [clojure.string :as str]))


(def input
  (-> "resources/day8.txt"
      slurp
      str/split-lines))

(def output-values
  (->> input
       (map #(str/split % #" \| "))
       (map second)
       (map #(str/split % #" "))))

(def unique-digit-segments
  "If a number uses only two segments, it's displaying a one; if it's
  using only three segments, it's displaying a seven, etc."
  {2 1
   3 7
   4 4
   7 8})

(defn part1
  [output-values]
  (->> output-values
       (map #(map count %))
       (map #(filter (fn [segs] (contains? unique-digit-segments segs)) %))
       (map count)
       (apply +)))

(comment

  (part1 output-values)

  ,)
