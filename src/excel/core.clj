(ns excel.core
  (:require [ring.adapter.jetty :as jetty])
  (:require [clojure.data.json :as json])
  (:gen-class))


(def counter 0)

(defn redirect-counter []
  (def counter (inc counter)))

(defn request-handler [request]
  (redirect-counter)
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body (str "Field,Value;The redirect limit is," counter ";")})


(defn -main [& args]
  (jetty/run-jetty request-handler {:port 4895}))
