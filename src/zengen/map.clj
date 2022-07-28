(ns zengen.map
  (:require zengen.generator))

(defmethod zengen.generator/generate 'zen/map
  [context schema]
  (if-some [data (zengen.generator/generate context (dissoc schema :type))]
    data
    (reduce-kv
     (fn [accumulator keyname keyschema]
       (let [path (conj (:path context) keyname)]
         (assoc accumulator keyname
                (if-let [defschema (get (:definitions context) path)]
                  (zengen.generator/generate context defschema)
                  (zengen.generator/generate (assoc context :path path) keyschema)))))
     {} (:keys schema))))
