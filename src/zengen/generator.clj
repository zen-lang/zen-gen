(ns zengen.generator
  (:import java.util.Random))

(defn new-random
  ([]     (java.util.Random.))
  ([seed] (java.util.Random. seed)))

(defmulti generate
  (fn [context schema]
    (:type schema))
  :default 'zen/schema)
