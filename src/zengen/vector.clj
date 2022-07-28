(ns zengen.vector
  (:require zengen.generator))

(defmethod zengen.generator/generate 'zen/vector
  [context schema]
  (if-some [data (zengen.generator/generate context (dissoc schema :type))]
    data
    (->> (repeatedly
          #(if-let [defschema (get (:definitions context) (conj (:path context) :#))]
             (zengen.generator/generate context defschema)
             (zengen.generator/generate context (:every schema))))
         (take
          (zengen.integer/generate
           (:random context)
           (get schema :minItems 0)
           (get schema :maxItems (get schema :minItems 2))))
         (vec))))

