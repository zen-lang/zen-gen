(ns example.core
  (:require zengen.core
            zen.core))

(def ztx (zen.core/new-context {:paths ["zrc"]}))

(zen.core/read-ns ztx 'example.core)

(zengen.core/example ztx {:confirms #{'example.core/HumanName}}) 

(zengen.core/initialize ztx)

(zengen.core/example ztx {:confirms #{'example.core/HumanName}}) 

