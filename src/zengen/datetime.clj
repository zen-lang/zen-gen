(ns zengen.datetime
  (:require zengen.generator
            zengen.string
            zen.v2-validation))

(defn generate
  [random]
  (zengen.string/generate random zen.v2-validation/fhir-datetime-regex))

(defmethod zengen.generator/generate 'zen/datetime
  [context schema]
  (if-some [data (zengen.generator/generate context (dissoc schema :type))]
    data
    (generate (:random context))))
