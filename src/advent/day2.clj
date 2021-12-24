(ns advent.day2
  (:require [clojure.string :as str]))


(def input
  (->> "resources/day2.txt"
       slurp
       str/split-lines
       (map #(str/split % #" "))
       (map (fn [[cmd n]] [cmd (Integer/parseInt n)]))
       ))

(defn reduce-coords
  [{x :x y :y} [command n]]
  (case command
    "forward" {:x (+ x n) :y y}
    "up" {:x x :y (- y n)}
    "down" {:x x :y (+ y n)}))

(defn part1
  [input]
  (let [{:keys [x y]} (reduce reduce-coords {:x 0 :y 0} input)]
    (* x y)))

(comment

  (part1 input);; => 1804520

  ,)
