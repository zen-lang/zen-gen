(ns zengen.core
  (:require zengen.generator
            zengen.boolean
            zengen.integer
            zengen.number
            zengen.string
            zengen.keyword
            zengen.symbol
            zengen.date
            zengen.datetime
            zengen.vector
            zengen.map
            zengen.any
            zengen.schema
            zen.core))


(defn initialize
  [ztx]
  (swap! ztx assoc ::definitions
         (->> (zen.core/get-tag ztx 'zengen.core/definition)
              (map #(zen.core/get-symbol ztx %))
              (map :options)
              (into {}))))

(defn example
  ([ztx schema]
   (example ztx schema (zengen.random/long Float/MIN_VALUE Float/MAX_VALUE)))
  ([ztx schema seed]
   (zengen.generator/generate
    {:ztx              ztx
     :path             []
     :confirms-stack  #{}
     :definitions      (::definitions @ztx)
     :random           (zengen.generator/new-random seed)}
    schema)))
