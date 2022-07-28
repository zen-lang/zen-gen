<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/Panthevm/zengen">
    <img src="https://user-images.githubusercontent.com/43318093/181618649-23a7ad49-a4c8-4d7c-98b6-f528bc819961.png" alt="Logo" width="80" height="80">
  </a>

  <h3 align="center">ZENGEN</h3>

  <p align="center">
    Random Data Generator based on zen schemes
    <br />
    <a href="https://github.com/Panthevm/zengen"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/Panthevm/zengen">View Demo</a>
    ·
    <a href="https://github.com/Panthevm/zengen/issues">Report Bug</a>
    ·
    <a href="https://github.com/Panthevm/zengen/issues">Request Feature</a>
  </p>
</div>

## Getting Started


## Usage


## Examples
Create a schema describing the human's name
``` edn
 ;; zrc/example/core.edn
{ns example.core

 HumanName
 {:zen/tags #{zen/schema}
  :type     zen/map
  :keys     {:text   {:type zen/string}
             :family {:type zen/string}
             :given  {:type zen/vector :every {:type zen/string}}
             :suffix {:type zen/vector :every {:type zen/string}}
             :prefix {:type zen/vector :every {:type zen/string}}}}
```
Generate data based on the above scheme
``` clj
(ns example.core)

;; Initializing zen context
(def ztx (zen.core/new-context {:paths ["zrc"]}))
(zen.core/read-ns ztx 'example.core)

;; Data generation
(zengen.core/example ztx {:confirms #{example.core/HumanName})
;; =>
;; {:text   "E3Y]\\3nf_Eb",
;;  :given  ["$QGn?DBRxM_JEB2" "6pMkz"],
;;  :suffix ["]|u9T" "\"n+KnI#XQ"],
;;  :prefix ["aObgbU"],
;;  :family "sJr)y"}
```
Sometimes it is necessary to generate human-readable data

Let's declare the configuration with the tag `zenden.core/definition` and use the prepared data set from `zenden.dataset`

``` edn
;; zrc/example/definitions.edn
{ns example.definitions
 import #{example.core
          zengen.core
          zengen.dataset.human.firstname
          zengen.dataset.human.lastname
          zengen.dataset.human.fullname
          zengen.dataset.human.suffix
          zengen.dataset.human.prefix}

 GeneratorHumanName
 {:zen/tags #{zengen.core/definition}
  :options  
  {[example.core/HumanName :text]      {:confirms #{zengen.dataset.human.fullname/english}
   [example.core/HumanName :family]    {:confirms #{zengen.dataset.human.lastname/english}}
   [example.core/HumanName :given  :#] {:confirms #{zengen.dataset.human.firstname/english}}
   [example.core/HumanName :suffix :#] {:confirms #{zengen.dataset.human.suffix/english}}
   [example.core/HumanName :prefix :#] {:confirms #{zengen.dataset.human.prefix/english}}}}}}

```
``` clj
(zengen.core/example ztx {:confirms #{example.core/HumanName})
;; =>
;; {:text   "Donnie Williams",
;;  :given  ["Gussie"],
;;  :suffix ["Jr."],
;;  :prefix ["De"],
;;  :family "Wright"}
```
For an example of a `zengen.dataset.human.prefix` schema:
``` edn
{ns zengen.dataset.human.prefix

 english
 {:zen/tags #{zen/schema}
  :enum     [{:value "La"}
             {:value "De"}]}}
```

## Acknowledgments
* [zen](https://github.com/zen-lang/zen)
