(ns excel.core
  (:require [ring.adapter.jetty :as jetty])
  (:require [clojure.data.json :as json])
  (:gen-class))


(def counter 0)


(defn redirect-counter []
  (def counter (inc counter)))

(defn request-handler
  "Handles the initial request"
  [request]
  (redirect-counter)
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body (json/write-str {:a "The redirect limit is" :b counter})})


(defn -main
  "Setup a simple server that redirects n times then returns data"
  [& args]
  (jetty/run-jetty request-handler {:port 4895}))
