(ns zengen.random
  (:refer-clojure :exclude [boolean long])
  (:import java.util.Random
           com.github.curiousoddman.rgxgen.config.RgxGenProperties
           com.github.curiousoddman.rgxgen.config.RgxGenOption
           com.github.curiousoddman.rgxgen.RgxGen))

(defn new-random
  ([]     (java.util.Random.))
  ([seed] (java.util.Random. seed)))

(defn boolean
  [random]
  (.nextBoolean random))

(defn integer
  [random min-value max-value]
  (.nextInt random min-value max-value))

(defn long
  ([min-value max-value]
   (long (new-random) min-value max-value))
  ([random min-value max-value]
   (.nextLong random min-value max-value)))

(defn decimal
  ([min-value max-value]
   (decimal (new-random) min-value max-value))
  ([random min-value max-value]
   (.nextDouble random min-value max-value)))

(defn string
  [random regex]
  (.generate (com.github.curiousoddman.rgxgen.RgxGen. regex) random))

(defn select
  [random collection]
  (when (seq collection)
    (let [index (integer random 0 (count collection))]
      (nth collection index))))
