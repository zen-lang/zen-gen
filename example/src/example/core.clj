(ns example.core
  (:require zengen.core
            zen.core))

(defonce ztx (zen.core/new-context {:paths ["zrc"]}))
(zen.core/read-ns ztx 'example.core)
(zengen.core/initialize ztx)
(zengen.core/example ztx {:confirms #{'example.core/HumanName}}) 

