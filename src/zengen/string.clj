(ns zengen.string
  (:require zengen.generator
            zengen.random)
  (:import com.github.curiousoddman.rgxgen.RgxGen))

(defn generate
  [random regex]
  (zengen.random/string random regex))

(defmethod zengen.generator/generate 'zen/string
  [context schema]
  (if-some [data (zengen.generator/generate context (dissoc schema :type))]
    data
    (if (:regex schema)
      (generate (:random context) (:regex schema))
      (generate
       (:random context)
       (format ".{%s,%s}"
               (or (:minLength schema) 0)
               (or (:maxLength schema) 20))))))
