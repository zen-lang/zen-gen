(ns zengen.random
  (:refer-clojure :exclude [boolean long])
  (:import java.util.Random
           com.github.curiousoddman.rgxgen.RgxGen))

(defn new-random
  ([]     (java.util.Random.))
  ([seed] (java.util.Random. seed)))

(defn boolean
  ([]
   (boolean (new-random)))
  ([random]
   (.nextBoolean random)))

(defn integer
  ([min-value max-value]
   (integer (new-random) min-value max-value))
  ([random min-value max-value]
   (if (= max-value Integer/MAX_VALUE)
     (.nextInt random min-value max-value)
     (.nextInt random min-value (inc max-value)))))

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
  ([regex]
   (string (new-random) regex))
  ([random regex]
   (.generate (com.github.curiousoddman.rgxgen.RgxGen. regex) random)))

(defn select
  ([collection]
   (select (new-random) collection))
  ([random collection]
   (when (seq collection)
     (let [index (integer random 0 (dec (count collection)))]
       (nth collection index)))))
