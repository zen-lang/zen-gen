(ns zengen.number
  (:require zengen.generator
            zengen.random))

(defmethod zengen.generator/generate 'zen/number
  [context schema]
  (if-some [data (zengen.generator/generate context (dissoc schema :type))]
    data
    (zengen.random/long (:random context)
                        (or (:min schema) (- Float/MAX_VALUE))
                        (or (:max schema) Float/MAX_VALUE))))
