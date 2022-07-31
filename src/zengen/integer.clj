(ns zengen.integer
  (:require zengen.generator
            zengen.random))

(defmethod zengen.generator/generate 'zen/integer
  [context schema]
  (if-some [data (zengen.generator/generate context (dissoc schema :type))]
    data
    (zengen.random/integer (:random context)
                           (or (:min schema) Integer/MIN_VALUE)
                           (or (:max schema) Integer/MAX_VALUE))))
