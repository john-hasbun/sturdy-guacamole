(ns excel.core
  (:require [ring.adapter.jetty :as jetty])
  (:gen-class))


(def counter 0)

(defn redirect-counter []
  (def counter (inc counter)))

(defn request-handler [request]
  (redirect-counter)
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (str "<html><table><tr><th>" counter "</th></tr></table></html>")})


(defn -main [& args]
  (jetty/run-jetty request-handler {:port 4895}))
