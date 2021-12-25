(ns advent.day3
  (:require [clojure.string :as str]))

(def input
  (->> "resources/day3.txt"
       slurp
       str/split-lines
       (map #(str/split % #""))))

(defn increment-ones-counts
  [counts bit-string]
  (let [bit-vec (map #(Integer/parseInt %) bit-string)]
    (map + counts bit-vec)))

(defn bools-to-int
  [bool-vec]
  (let [binary (map-indexed
                (fn [idx bool] (if bool (Math/pow 2 idx) 0))
                (reverse bool-vec))]
    (reduce + binary)))

(defn part1
  [input]
  (let [bit-length (count (first input))
        ones-counts (vec (repeat bit-length 0))
        total-ones-counts (reduce increment-ones-counts ones-counts input)
        ;; since there's only two types of bits, we one is the most common if
        ;; it's in more than half the numbers
        gamma-bits (map #(> (/ (count input) 2) %) total-ones-counts)
        gamma-int (bools-to-int gamma-bits)
        epsilon-bits (map not gamma-bits)
        epsilon-int (bools-to-int epsilon-bits)]
    (* gamma-int epsilon-int)))

(comment

  (part1 input);; => 2743844.0

  ,)
