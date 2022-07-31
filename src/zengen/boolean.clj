(ns zengen.boolean
  (:require zengen.generator
            zengen.random))

(defn generate
  [random]
  (.nextBoolean random))

(defmethod zengen.generator/generate 'zen/boolean
  [context schema]
  (if-some [data (zengen.generator/generate context (dissoc schema :type))]
    data
    (zengen.random/boolean (:random context))))
