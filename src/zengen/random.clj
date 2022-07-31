(ns zengen.random)

(defn select
  [random collection]
  (when (seq collection)
    (let [index (integer random 0 (count collection))]
      (nth collection index))))
