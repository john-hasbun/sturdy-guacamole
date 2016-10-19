(ns excel.core
  (:require [ring.adapter.jetty :as jetty])
  (:gen-class))


(def counter 0)

(defn redirect-counter []
  (def counter (inc counter)))

;; Test refresh
(defn request-handler [request]
  (redirect-counter)
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (str "<html><table><tr><th>" counter "</th></tr></table></html>")})

;; Test redirect
;; (defn request-handler [request]
;;   (redirect-counter)
;;   {:status 302
;;    :headers {"Location" (str "http://localhost:4895?redirect=" counter)}
;;    :body (str "<html><table><tr><th>" counter "</th></tr></table></html>")})


(defn -main [& args]
  (jetty/run-jetty request-handler {:port 4895}))
