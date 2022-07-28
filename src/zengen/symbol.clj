(ns zengen.symbol
  (:require zengen.generator
            zengen.string))

(defn generate
  [random]
  (symbol (zengen.string/generate random "\\w\\w+")
          (zengen.string/generate random "\\w\\w+")))

(defmethod zengen.generator/generate 'zen/symbol
  [context schema]
  (if-some [data (zengen.generator/generate context (dissoc schema :type))]
    data
    (generate (:random context))))
