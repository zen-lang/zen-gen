(ns zengen.number
  (:require zengen.generator))

(defn generate
  [random min-value max-value]
  (.nextLong random min-value max-value))

(defmethod zengen.generator/generate 'zen/number
  [context schema]
  (if-some [data (zengen.generator/generate context (dissoc schema :type))]
    data
    (generate (:random context)
              (or (:min schema) (- Float/MAX_VALUE))
              (or (:max schema) Float/MAX_VALUE))))
