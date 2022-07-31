(ns zengen.core-test
  (:require zen.core
            zen.validation
            zengen.core
            clojure.test
            clojure.pprint))

(defmacro with-context
  [ztx schema]
  `(let [data# (zengen.core/example ~ztx '~schema)]
     (->> (zen.validation/validate-schema ztx '~schema data#)
          (:errors)
          (empty?)
          (clojure.test/is))
     data#))

(clojure.test/deftest example-test
  (def ztx (zen.core/new-context {:paths ["zrc"]}))
  (zen.core/read-ns ztx 'zengen.test.core)
  (clojure.test/is (empty? (zen.core/errors ztx)))
  (doseq [[schema-symbol schema] (:symbols @ztx)]
    (when (clojure.string/starts-with? (namespace schema-symbol) "zengen.test")
      (try
        (let [data   (zengen.core/example ztx schema)
              report (zen.core/validate-schema ztx schema data)]
          (clojure.test/is (empty? (:errors report))))
        (catch Exception e
          (throw (Exception. (str (ex-message e) "\n" schema-symbol))))))))


(comment
  (zengen.core/initialize ztx)
  (zen.core/read-ns ztx 'zengen.test.core)
  (zengen.core/example ztx {:confirms #{'zengen.test.core/HumanName}})
  )
