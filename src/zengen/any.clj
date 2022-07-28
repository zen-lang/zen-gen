(ns zengen.any
  (:require zengen.generator
            zengen.random))

(defmethod zengen.generator/generate 'zen/any
  [context schema]
  (let [data (zengen.generator/generate context (assoc schema :type 'zen/schema))]
    (if (some? data)
      data
      (->> (methods zengen.generator/generate)
           (remove #(= 'zen/schema (key %)))
           (zengen.random/select (:random context))
           (val)
           (#(% context schema))))))
