(ns zengen.schema
  (:require zengen.generator
            deep.merge
            zen.core))

(defmethod zengen.generator/generate 'zen/schema
  [context schema]
  (cond

    (contains? schema :confirms)
    (let [confirms-data
          (->> (:confirms schema)
               (remove (:confirms-stack context))
               (filter identity)
               (mapv
                (fn [schema-name]
                  (->> (zen.core/get-symbol (:ztx context) schema-name)
                       (zengen.generator/generate
                        (update context :path conj schema-name))))))
          data
          (->> (dissoc schema :confirms)
               (zengen.generator/generate context)
               (conj confirms-data)
               (remove nil?)
               (apply deep.merge/distinct-merge))]
      data)

    (contains? schema :const)
    (get-in schema [:const :value])

    (contains? schema :enum)
    (->> (:enum schema)
         (zengen.random/select (:random context))
         (:value))))
