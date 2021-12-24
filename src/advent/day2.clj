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

(defn reduce-coords-with-aim
  [{:keys [x y aim]} [command n]]
  (case command
    "forward" {:x (+ x n) :y (+ y (* aim n)) :aim aim}
    "down" {:x x :y y :aim (+ aim n)}
    "up" {:x x :y y :aim (- aim n)}))

(defn part2
  [input]
  (let [{:keys [x y]} (reduce reduce-coords-with-aim {:x 0 :y 0 :aim 0} input)]
    (* x y)))

(comment

  (part1 input);; => 1804520

  (part2 input);; => 1971095320

  ,)
