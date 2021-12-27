(ns advent.day4
  (:require [clojure.string :as str]
            [clojure.set :as set]))

(def input
  (-> "resources/day4.txt"
       slurp
       str/split-lines))

(def board-numbers (as-> input $
                        (first $)
                        (str/split $ #",")
                        (map #(Integer/parseInt %) $)))

(defn parse-board
  [row-strings]
  ;; a lot of annoying whitespace that needs to get trimmed
  (let [trimmed-row-strings (map #(str/split (str/trim %) #"\s+") row-strings)]
    (mapv (fn [row] (mapv #(Integer/parseInt %) row)) trimmed-row-strings)))

(def boards (as-> input $
                 (rest $)
                 (partition-by #(= % "") $)
                 (remove #(= (first %) "") $)
                 (map parse-board $)))

(defn get-row-sets
  [board]
  (map #(into #{} %) board))

(defn get-col-sets
  [board]
  (let [flat-board (flatten board)]
    (for [col-num (range 0 5)]
      (->> flat-board
           (drop col-num)
           (take-nth 5)
           (into #{})))))

(defn get-down-diag-set
  [board]
  (into #{} (for [idx (range 0 5)
                  :let [row (nth board idx)]]
              (nth row idx))))

(defn get-up-diag-set
  [board]
  (into #{} (for [idx (range 0 5)
                  :let [row (nth board idx)]]
              (nth row (- 4 idx)))))

(defn get-diag-sets
  [board]
  [(get-down-diag-set board)
   (get-up-diag-set board)])

(defn get-possible-bingos
  [board]
  (->> [(get-col-sets board)
        (get-row-sets board)
        (get-diag-sets board)]
       flatten
       vec))

(defn mark-number-on-board
  [bingo-sets number]
  (map #(disj % number) bingo-sets))

(defn board-has-bingo?
  [bingo-sets]
  (some empty? bingo-sets))

(defn sets-with-bingo
  [bingo-sets]
  (filter board-has-bingo? bingo-sets))

(defn sum-unmarked-numbers
  [bingo-set]
  (->> bingo-set
       (apply set/union)
       (apply +)))

(defn part1
  [boards numbers]
  (loop [numbers numbers
         bingo-sets (map get-possible-bingos boards)]
    (let [number-to-mark (first numbers)
          marked-cards (map #(mark-number-on-board % number-to-mark) bingo-sets)
          bingos (sets-with-bingo marked-cards)]
      (if (< 0 (count bingos))
        (* number-to-mark (sum-unmarked-numbers (first bingos)))
        (recur (rest numbers) marked-cards)))))

(defn part2
  [boards numbers]
  (loop [numbers numbers
         bingo-sets (map get-possible-bingos boards)
         last-winner nil
         last-winning-number nil]
    (let [number-to-mark (first numbers)
          marked-cards (map #(mark-number-on-board % number-to-mark) bingo-sets)
          new-bingo (first (sets-with-bingo marked-cards))]
      (if (empty? (rest numbers))
        (* last-winning-number (sum-unmarked-numbers last-winner))
        (recur (rest numbers)
               (remove board-has-bingo? marked-cards)
               (if new-bingo new-bingo last-winner)
               (if new-bingo number-to-mark last-winning-number))))))

(comment

  (part1 boards board-numbers);; => 44088

  (part2 boards board-numbers);; => 23670

  ,)
