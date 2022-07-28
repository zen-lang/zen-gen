(ns zengen.integer
  (:require zengen.generator))

(defn generate
  [random min-value max-value]
  (if (= max-value Integer/MAX_VALUE)
    (.nextInt random min-value max-value)
    (.nextInt random min-value (inc max-value))))

(defmethod zengen.generator/generate 'zen/integer
  [context schema]
  (if-some [data (zengen.generator/generate context (dissoc schema :type))]
    data
    (generate (:random context)
              (or (:min schema) Integer/MIN_VALUE)
              (or (:max schema) Integer/MAX_VALUE))))
